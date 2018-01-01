package org.lewis.ntmu.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lewis.ntmu.server.NtmuServer;
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
    @Autowired
    @Qualifier("org.lewis.ntmu.utils.DbLockUtil")
    private DbLockUtil dbLockUtil;

    @Test
    public void test() throws Exception{
        dbLockUtil.getIpThreadId();
    }
}