package com.freqds.dbo.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.freqds.mapping.base.BaseCountry;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.dbo.BaseDAO;
import cosco.vsagent.mapping.base.Bascountry;
import cosco.vsagent.mapping.base.Basvslmsg;

public class BaseCountryDAO extends DAO {
	public List<BaseCountry> getBaseCountry(QueryInfo qi) {
		QueryRunner qRunner = new QueryRunner();
		try {
			Connection con = ConnectManager.getConnection();
			List country = (List) qRunner.query(con,
					" select * from Base_Country", new BeanListHandler(
							BaseCountry.class));
			ConnectManager.closeConn(con);
			return country;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * Connection con = null; Statement sm = null; ResultSet rs = null; try
		 * { con = ConnectManager.getConnection(); sm = con.createStatement();
		 * // 得到总记录数 rs = sm.executeQuery("select count(*) from Bascountry");
		 * rs.next(); qi.rsCount = rs.getInt(1); if (qi.rsCount == 0)//
		 * 等于0表示没有记录 return Collections.emptyList(); // 算出总页数 if (qi.rsCount %
		 * qi.pageSize == 0) qi.pageCount = qi.rsCount / qi.pageSize; else
		 * qi.pageCount = (qi.rsCount / qi.pageSize) + 1; // 算出起始位置＝
		 * (当前页号-1)*每页记录数 int start = (qi.currentPage - 1) * qi.pageSize; rs =
		 * sm.executeQuery("select * from Bascountry limit " + start + "," +
		 * qi.pageSize); List<Bascountry> list = new
		 * ArrayList<Bascountry>(qi.pageSize);
		 * 
		 * while (rs.next()) { list.add(Bascountry(rs)); } return list; } catch
		 * (SQLException e) { e.printStackTrace(); } finally { close(rs);
		 * close(sm); close(con); }
		 */
		return Collections.emptyList();
	}

	// 取客户端数据
	public List<Bascountry> getBascountry(String sql) {
		return getBascountry(sql, false);
	}

	/**
	 * 
	 * @param sql
	 * @param flag
	 *            true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Bascountry> getBascountry(String sql, boolean flag) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		/*
		try {
			if (flag == true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();

			if (con == null)
				return Collections.emptyList();

			sm = con.createStatement();
			rs = sm.executeQuery(sql);

			List<Bascountry> list = new ArrayList<Bascountry>();
			while (rs.next()) {
				list.add(Bascountry(rs, flag));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(sm);
			close(con);
		}
		*/
		return Collections.emptyList();
	}

	public Bascountry findByNationcode(String nationcode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		/*
		try {

			con = ConnectManager.getConnection();

			if (con == null)
				return null;

			sm = con.createStatement();
			rs = sm.executeQuery("select * from Bascountry where Nationcode ='"
					+ nationcode + "'");
			if (rs.next()) {
				return Bascountry(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(sm);
			close(con);
		}
		*/
		return null;
	}

	public Bascountry Bascountry(ResultSet rs) {
		return Bascountry(rs, false);
	}

	public Bascountry Bascountry(ResultSet rs, boolean flag) {
		Bascountry bascountry = new Bascountry();
		try {
			bascountry.setMainkey(rs.getInt("mainkey"));
			bascountry.setNationcode(rs.getString("nationcode"));
			bascountry.setCname(rs.getString("cname"));
			bascountry.setEname(rs.getString("ename"));
			bascountry.setMakercode(rs.getString("makercode"));
			bascountry.setMaker(rs.getString("maker"));
			bascountry.setMadetime(rs.getTimestamp("madetime"));
			bascountry.setModicode(rs.getString("modicode"));
			bascountry.setModifier(rs.getString("modifier"));
			bascountry.setModitime(rs.getTimestamp("moditime"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bascountry;
	}

	public boolean inserBascountry(Bascountry o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		/*
		try {
			con = ConnectManager.getConnection();
			if (con == null)
				return false;

			// 重复
			// if(findByPkgcode(o.getPkgcode())!=null) return false;

			String sql = "insert into Bascountry(cname,ename,makercode,maker,madetime,"
					+ "modicode,modifier,moditime,nationcode,mainkey)"
					+ "values(?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);

			setBascountry(sm, o);

			sm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result = false;

		} finally {
			close(sm);
			close(rs);
			close(con);
		}
		*/
		return result;
	}

	private void setBascountry(PreparedStatement sm, Bascountry o)
			throws SQLException {
		// TODO Auto-generated method stub
		sm.setString(1, o.getCname() == null ? "" : o.getCname());
		sm.setString(2, o.getEname() == null ? "" : o.getEname());
		sm.setString(3, o.getMakercode() == null ? "" : o.getMakercode());
		sm.setString(4, o.getMaker() == null ? "" : o.getMaker());
		sm.setTimestamp(5, o.getMadetime());
		sm.setString(6, o.getModicode() == null ? "" : o.getModicode());
		sm.setString(7, o.getModifier() == null ? "" : o.getModifier());
		sm.setTimestamp(8, o.getModitime());
		sm.setString(9, o.getNationcode());
		sm.setInt(10, o.getMainkey());

	}

	public boolean removeBascountry(Bascountry bascountry) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		/*
		try {
			con = ConnectManager.getConnection();
			if (con == null)
				return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Bascountry where Nationcode='"
					+ bascountry.getNationcode() + "'");

			sm.executeBatch();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			result = false;

			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			close(rs);
			close(sm);
			close(con);
		}
		*/
		return result;
	}

	public List<Bascountry> getAllBascountry() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
/*
		try {
			con = ConnectManager.getConnection();
			sm = con.prepareStatement("select * from Bascountry");
			rs = sm.executeQuery();

			List<Bascountry> list = new ArrayList<Bascountry>();
			while (rs.next()) {
				Bascountry bascountry = new Bascountry();
				bascountry.setNationcode(rs.getString("nationcode"));
				bascountry.setCname(rs.getString("cname"));

				list.add(bascountry);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(sm);
			close(con);
		}
		*/
		return Collections.emptyList();
	}

	public boolean modifyBaseCountry(BaseCountry baseCountry) {
		/*
		 * Connection con = null; PreparedStatement sm = null; ResultSet rs =
		 * null; try{ con = ConnectManager.getConnection();
		 * con.setAutoCommit(false); String sql = "update Basvslmsg " +
		 * "set enname=?,cnname=? WHERE  shipcode=? And vslcode=? "; sm =
		 * con.prepareStatement(sql); sm.setString(1, basvslmsg.getEnname());
		 * sm.setString(2, basvslmsg.getCnname()); // sm.setString(3,
		 * basvslmsg.getSequence()); sm.setString(3, basvslmsg.getShipcode());
		 * sm.setString(4, basvslmsg.getVslcode()); sm.executeUpdate();
		 * con.setAutoCommit(true); }catch(SQLException e){ e.printStackTrace();
		 * try{con.rollback();}catch(Exception e2){e2.printStackTrace();} return
		 * false; }finally{ close(con);close(sm);close(rs); }
		 */
		return true;
	}
	
	
}
