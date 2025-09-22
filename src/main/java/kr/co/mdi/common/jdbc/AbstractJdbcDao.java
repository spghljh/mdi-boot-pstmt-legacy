package kr.co.mdi.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJdbcDao {
	@Autowired
	protected DataSource dataSource;

	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
