package org.lewis.ntmu.utils;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.lewis.ntmu.common.InsertLockDTO;
import org.lewis.ntmu.integration.dao.InsertLockDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;

@Component("org.lewis.ntmu.utils.DbLockUtil")
@Lazy(false)
public class DbLockUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DbLockUtil.class);

    @Autowired
    @Qualifier("org.lewis.ntmu.integration.dao.InsertLockDAO")
    private InsertLockDAO insertLockDAO;

    public boolean getInsertLock(InsertLockDTO request) throws Exception{
        boolean result = true;
        try {
            insertLockDAO.insertLock(request);
        } catch (PersistenceException e) {
            result = false;
        }
        return result;
    }

    public void releaseInsertLock(String method) throws Exception {
        insertLockDAO.deleteLock(method);
    }

    public InsertLockDTO selectInsertLock(String method) throws Exception {
        return insertLockDAO.selectByMethod(method);
    }

    public String getIpThreadId() throws Exception{
        String ip = null;
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            NetworkInterface intf = en.nextElement();
            String name = intf.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                            ip = ipaddress;
                            LOGGER.info("ip {}", ip);
                            break;
                        }
                    }
                }
            }
        }
        long threadId = Thread.currentThread().getId();
        if (StringUtils.isEmpty(ip)) {
            LOGGER.error("get ip failed");
            return null;
        } else {
            String ipThreadId = ip.concat("_").concat(String.valueOf(threadId));
            LOGGER.info("ipThreadId {}", ipThreadId);
            return ipThreadId;
        }
    }
}
