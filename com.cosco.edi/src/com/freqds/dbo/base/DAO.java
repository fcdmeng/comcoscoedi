package com.freqds.dbo.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import cosco.vsagent.db.ConnectManager;

public class DAO {

	public Object load(String sql, Object[] params, ResultSetHandler rsh)
			throws SQLException

	{
		Connection conn = ConnectManager.getConnection();
		QueryRunner run = new QueryRunner();
		
		Object result = run.query(conn, sql, params, rsh);

		// 执行sql语句
		DbUtils.close(conn); // 关闭连接

		return result; // 返回结果(Object)

	}

	public List executequery(String sql, Object[] params, ResultSetHandler rsh)
			throws SQLException

	{
		Connection conn = ConnectManager.getConnection();
		QueryRunner run = new QueryRunner();

		List result = (List) run.query(conn, sql, params, rsh);
		DbUtils.close(conn);
		
		return result;

	}

	public void update(String sql, Object[] params) throws SQLException 

	{
		Connection conn = ConnectManager.getConnection();
		QueryRunner run = new QueryRunner();
		run.update(conn, sql, params);

		DbUtils.close(conn);
	}

	
}
