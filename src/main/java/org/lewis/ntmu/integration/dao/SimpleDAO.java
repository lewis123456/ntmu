package org.lewis.ntmu.integration.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author lpf
 * @Date 12/31/17 2:39 PM
 */
public class SimpleDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDAO.class);

    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public SqlSession getSession() {
        LOGGER.debug("getSession");
        return sqlSessionFactory.openSession();
    }
}
