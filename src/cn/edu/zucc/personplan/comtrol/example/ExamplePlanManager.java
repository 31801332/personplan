package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;

public class ExamplePlanManager implements IPlanManager {

	@Override
	public BeanPlan addPlan(String name) throws BaseException {
		// TODO Auto-generated method stub
		BeanUser user = BeanUser.currentLoginUser;
		BeanPlan plan = new BeanPlan();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select MAX(plan_order) from tbl_plan where user_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BaseException("未知用户");
			}
			plan.setPorder(rs.getInt(1) + 1);
			pst.close();
			rs.close();

			sql = "select MAX(plan_id) from tbl_plan";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BaseException("plan_id get error");
			}
			plan.setPid(rs.getInt(1) + 1);
			pst.close();
			rs.close();

			plan.setUid(user.getId());
			plan.setPname(name);
			plan.setCtime(new Date(System.currentTimeMillis()));

			sql = "insert into tbl_plan values (?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPid());
			pst.setString(2, plan.getUid());
			pst.setInt(3, plan.getPorder());
			pst.setString(4, plan.getPname());
			pst.setDate(5, plan.getCtime());
			pst.setInt(6, plan.getScount());
			pst.setInt(7, plan.getStartCount());
			pst.setInt(8, plan.getFinishedCount());
			int line = pst.executeUpdate();
			if (line != 1) {
				throw new BaseException("insert error");
			}
			pst.close();
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
		return plan;
	}

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		List<BeanPlan> result = new ArrayList<BeanPlan>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select plan_id,user_id,plan_order,plan_name,create_time,step_count,start_step_count,finished_step_count from tbl_plan";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanPlan p = new BeanPlan();
				p.setPid(rs.getInt(1));
				p.setUid(rs.getString(2));
				p.setPorder(rs.getInt(3));
				p.setPname(rs.getString(4));
				p.setCtime(rs.getDate(5));
				p.setScount(rs.getInt(6));
				p.setStartCount(rs.getInt(7));
				p.setFinishedCount(rs.getInt(8));
				result.add(p);
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
		return result;
	}

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select plan_id from tbl_plan where plan_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPid());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BaseException("未知计划");
			}
			if (rs.getInt(1) != plan.getPid()) {
				throw new BaseException("select error");
			}
			pst.close();
			rs.close();

			sql = "delete from tbl_plan where plan_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPid());
			int line = pst.executeUpdate();
			if (line != 1) {
				throw new BaseException("tbl_plan delete error");
			}
			pst.close();

			sql = "delete from tbl_step where plan_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPid());
			line = pst.executeUpdate();
			pst.close();
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

	public static void main(String[] args) {
		try {
			BeanUser.currentLoginUser = new ExampleUserManager().login("testid", "testnewpwd");
			List<BeanPlan> plan = new ArrayList<BeanPlan>();
			plan = new ExamplePlanManager().loadAll();
			plan.forEach(beanPlan -> System.out.println(beanPlan.toString()));
			new ExamplePlanManager().deletePlan(plan.get(2));
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
