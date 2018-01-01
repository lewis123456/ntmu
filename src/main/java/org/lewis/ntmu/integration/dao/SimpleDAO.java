package org.lewis.ntmu.integration.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @Author lpf
 * @Date 12/31/17 2:39 PM
 */
public class SimpleDAO {
    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
