package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import com.mysql.fabric.xmlrpc.base.Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class ExampleUserManager implements IUserManager {

	@Override
	public BeanUser reg(String userid, String pwd, String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		if (userid == null || pwd == null || pwd2 == null) {
			throw new BaseException("¿Õ×Ö¶Î");
		}
		if (pwd != pwd2) {
			throw new BaseException("ÃÜÂë²»Ò»ÖÂ");
		}

		BeanUser user = new BeanUser(userid, pwd, new java.sql.Date(System.currentTimeMillis()));
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select user_id,user_pwd,register_time from tbl_user where user_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BaseException("ÓÃ»§idÖØ¸´");
			}
			rs.close();
			pst.close();

			sql = "insert into tbl_user values (?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			pst.setDate(3, (Date) user.getR_time());
			int line = pst.executeUpdate();
			if (line == 0) {
				throw new BaseException("²åÈëÊ§°Ü");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}


	@Override
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		if (userid == null || pwd == null) {
			throw new BaseException("¿Õ×Ö¶Î");
		}
		Connection conn = null;
		BeanUser ans = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select user_id,user_pwd,register_time from tbl_user where user_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BaseException("ÓÃ»§²»´æÔÚ");
			}
			ans = new BeanUser(rs.getString(1), rs.getString(2), rs.getDate(3));
			if(ans.getPwd().compareTo(pwd)!=0){
				ans=null;
				throw new BaseException("ÃÜÂë´íÎó");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ans;
	}


	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
						  String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if (user == null || oldPwd == null || newPwd == null || newPwd2 == null) {
			throw new BaseException("¿Õ×Ö¶Î");
		}
		if (newPwd != newPwd2) {
			throw new BaseException("ÐÂÃÜÂë²»Ò»ÖÂ");
		}
		if (user.getPwd() != oldPwd) {
			throw new BaseException("Ô­ÃÜÂë´íÎó");
		}

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select user_id,user_pwd,register_time from tbl_user where user_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BaseException("Î´ÖªÓÃ»§");
			}
			rs.close();
			pst.close();

			sql = "update tbl_user set user_pwd = ? where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, user.getId());
			int line = pst.executeUpdate();
			if (line == 0) {
				throw new BaseException("ÐÞ¸ÄÊ§°Ü");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws BaseException {
		try {
			BeanUser user = new BeanUser("Ptestid", "Ptestpwd", new Date(System.currentTimeMillis()));
			new ExampleUserManager().changePwd(user,user.getPwd(),"Ptestnewpwd","Ptestnewpwd");
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
