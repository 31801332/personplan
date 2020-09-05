package cn.edu.zucc.personplan.model;

import java.sql.Date;

public class BeanPlan {
	public static final String[] tableTitles = {"序号", "名称", "步骤数", "已完成数"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public int Pid;//plan_id
	public String Uid;//user_id
	public int Porder;//计划编号，要求同一用户下序号不重复
	public String Pname;//计划名称
	public java.sql.Date Ctime;//create_time
	public int Scount;//步骤总数
	public int StartCount;//已经开始的步骤数
	public int FinishedCount;//已经完成的步骤数

	public BeanPlan() {
		Pid = 0;
		Uid = null;
		Porder = 0;
		Pname = null;
		Ctime = null;
		Scount = 0;
		StartCount = 0;
		FinishedCount = 0;
	}

	public BeanPlan(int pid, String uid, int porder, String pname, Date ctime, int scount, int startCount, int finishedCount) {
		Pid = pid;
		Uid = uid;
		Porder = porder;
		Pname = pname;
		Ctime = ctime;
		Scount = scount;
		StartCount = startCount;
		FinishedCount = finishedCount;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}

	public int getPorder() {
		return Porder;
	}

	public void setPorder(int porder) {
		Porder = porder;
	}

	public String getPname() {
		return Pname;
	}

	public void setPname(String pname) {
		Pname = pname;
	}

	public Date getCtime() {
		return Ctime;
	}

	public void setCtime(Date ctime) {
		Ctime = ctime;
	}

	public int getScount() {
		return Scount;
	}

	public void setScount(int scount) {
		Scount = scount;
	}

	public int getStartCount() {
		return StartCount;
	}

	public void setStartCount(int startCount) {
		StartCount = startCount;
	}

	public int getFinishedCount() {
		return FinishedCount;
	}

	public void setFinishedCount(int finishedCount) {
		FinishedCount = finishedCount;
	}

	@Override
	public String toString() {
		return "BeanPlan{" +
				"Pid=" + Pid +
				", Uid='" + Uid + '\'' +
				", Porder=" + Porder +
				", Pname='" + Pname + '\'' +
				", Ctime=" + Ctime +
				", Scount=" + Scount +
				", StartCount=" + StartCount +
				", FinishedCount=" + FinishedCount +
				'}';
	}

	public String getCell(int col) {
		if (col == 0) return String.valueOf(this.getPid());
		else if (col == 1) return this.getPname();
		else if (col == 2) return String.valueOf(this.getScount());
		else if (col == 3) return String.valueOf(this.getStartCount());
		else return "";
	}

}
