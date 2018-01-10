package org.lewis.ntmu.controller;

import org.lewis.ntmu.common.InsertLockDTO;
import org.lewis.ntmu.utils.DbLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lpf
 * @Date 12/31/17 4:55 PM
 */
@RestController
@RequestMapping("test")
public class MyTestController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTestController.class);

    @Autowired
    @Qualifier("org.lewis.ntmu.utils.DbLockUtil")
    private DbLockUtil dbLockUtil;

    @RequestMapping("dblock")
    public String dblock(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) {
        String response = null;
        try {
            InsertLockDTO insertLockDTO = new InsertLockDTO();
            insertLockDTO.setIpThreadId(dbLockUtil.getIpThreadId());
            insertLockDTO.setMethod("test1");
            if (dbLockUtil.getInsertLock(insertLockDTO)) {
                LOGGER.info("get lock success");
            }
            Runnable one = new Runnable() {
                @Override
                public void run() {
                    try {
                        InsertLockDTO insertLockDTO = new InsertLockDTO();
                        insertLockDTO.setIpThreadId(dbLockUtil.getIpThreadId());
                        insertLockDTO.setMethod("test1");
                        if (dbLockUtil.getInsertLock(insertLockDTO)) {
                            LOGGER.info("get lock success");
                        }
                    } catch (Exception e) {
                        LOGGER.error("get lock fail", e);
                    }

                }
            };
            one.run();
            response = dbLockUtil.getIpThreadId();
        } catch (Exception e) {
            LOGGER.error("", e);
        }

        return response;
    }
}
