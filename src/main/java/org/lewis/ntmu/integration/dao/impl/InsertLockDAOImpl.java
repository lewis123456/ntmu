package org.lewis.ntmu.integration.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.lewis.ntmu.common.InsertLockDTO;
import org.lewis.ntmu.integration.dao.InsertLockDAO;
import org.lewis.ntmu.integration.dao.SimpleDAO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @Author lpf
 * @Date 12/30/17 1:01 AM
 */
@Repository("org.lewis.ntmu.integration.dao.InsertLockDAO")
public class InsertLockDAOImpl extends SimpleDAO implements InsertLockDAO {
    @Override
    public void insertLock(InsertLockDTO request) throws SQLException {
        try (SqlSession sqlSession = getSession()) {
            InsertLockDAO mapper = sqlSession.getMapper(InsertLockDAOImpl.class);
            mapper.insertLock(request);
        }
    }

    @Override
    public void deleteLock(String method) throws SQLException {
        try (SqlSession sqlSession = getSession()) {
            InsertLockDAO mapper = sqlSession.getMapper(InsertLockDAOImpl.class);
            mapper.deleteLock(method);
        }
    }

    @Override
    public InsertLockDTO selectByMethod(String method) throws SQLException {
        try (SqlSession sqlSession = getSession()) {
            InsertLockDAO mapper = sqlSession.getMapper(InsertLockDAOImpl.class);
            return mapper.selectByMethod(method);
        }
    }
}
