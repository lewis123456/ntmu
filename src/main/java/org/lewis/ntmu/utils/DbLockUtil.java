package org.lewis.ntmu.utils;

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
        /*boolean result = true;
        try {
            insertLockDAO.insertLock(request);
        } catch (Exception e) {
            result = false;
        }
        return result;*/
        insertLockDAO.insertLock(request);
        return true;
    }

    public void releaseInsertLock(String method) throws Exception {
        insertLockDAO.deleteLock(method);
    }

    public InsertLockDTO selectInsertLock(String method) throws Exception {
        return insertLockDAO.selectByMethod(method);
    }

    //todo: get ip failed
    public String getIpThreadId() throws Exception{
        Properties properties = System.getProperties();
        LOGGER.info("{}", properties.getProperty("java.net.preferIPv4Stack"));
        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        String ip = null;
        InetAddress maybeIp = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface)allNetInterfaces.nextElement();
            LOGGER.info("{}", networkInterface);
            Enumeration addresses = networkInterface.getInetAddresses();
            maybeIp = (InetAddress) addresses.nextElement();
            LOGGER.info("maybeIp {}", maybeIp.getHostAddress());
            if (!maybeIp.isSiteLocalAddress() && !maybeIp.isLoopbackAddress() &&
                    -1 == maybeIp.getHostAddress().indexOf(":")) {
                ip = maybeIp.getHostAddress();
                break;
            } else {
                maybeIp = null;
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
