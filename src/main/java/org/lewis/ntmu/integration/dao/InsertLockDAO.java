package org.lewis.ntmu.integration.dao;

import org.lewis.ntmu.common.InsertLockDTO;

import java.sql.SQLException;

/**
 * @Author lpf
 * @Date 12/30/17 1:01 AM
 */
public interface InsertLockDAO {
    void insertLock(InsertLockDTO request) throws SQLException;
    void deleteLock(String method) throws SQLException;
    InsertLockDTO selectByMethod(String method) throws SQLException;
}
