package org.lewis.ntmu.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lewis.ntmu.common.InsertLockDTO;
import org.lewis.ntmu.server.NtmuServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author lpf
 * @Date 12/31/17 4:33 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NtmuServer.class)
public class DbLockUtilTest {
    private Logger LOGGER = LoggerFactory.getLogger(DbLockUtilTest.class);

    @Autowired
    @Qualifier("org.lewis.ntmu.utils.DbLockUtil")
    private DbLockUtil dbLockUtil;

    @Test
    public void test() throws Exception{
        LOGGER.debug("test");
        InsertLockDTO insertLockDTO = new InsertLockDTO();
        insertLockDTO.setIpThreadId(dbLockUtil.getIpThreadId());
        insertLockDTO.setMethod("test3");
        if (dbLockUtil.getInsertLock(insertLockDTO)) {
            LOGGER.info("get lock success 1");
        }
        Runnable one = new Runnable() {
            @Override
            public void run() {
                try {
                    InsertLockDTO insertLockDTO = new InsertLockDTO();
                    insertLockDTO.setIpThreadId(dbLockUtil.getIpThreadId());
                    insertLockDTO.setMethod("test3");
                    if (dbLockUtil.getInsertLock(insertLockDTO)) {
                        LOGGER.info("get lock success 2");
                    } else {
                        LOGGER.info("get lock fail 2");
                    }
                } catch (Exception e) {
                    LOGGER.error("get lock fail", e);
                }

            }
        };
        one.run();
    }
}