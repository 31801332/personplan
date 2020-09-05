package cn.edu.zucc.personplan.model;

import java.sql.Date;

public class BeanStep {
	public static final String[] tblStepTitle = {"序号", "名称", "计划开始时间", "计划完成时间", "实际开始时间", "实际完成时间"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	private int Sid;
	private int Pid;
	private int Sorder;
	String Sname;
	private java.sql.Date pbt;
	private java.sql.Date pet;
	private java.sql.Date rbt;
	private java.sql.Date ret;

	public BeanStep() {
		this.Sid = 0;
		this.Pid = 0;
		this.Sorder = 0;
		this.Sname = null;
		this.pbt = null;
		this.pet = null;
		this.rbt = null;
		this.ret = null;
	}

	public BeanStep(int Sid, int Pid, int Sorder, String Sname, java.sql.Date pbt, java.sql.Date pet, java.sql.Date rbt, java.sql.Date ret) {
		this.Sid = Sid;
		this.Pid = Pid;
		this.Sorder = Sorder;
		this.Sname = Sname;
		this.pbt = pbt;
		this.pet = pet;
		this.rbt = rbt;
		this.ret = ret;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public int getSorder() {
		return Sorder;
	}

	public void setSorder(int sorder) {
		Sorder = sorder;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public Date getPbt() {
		return pbt;
	}

	public void setPbt(Date pbt) {
		this.pbt = pbt;
	}

	public Date getPet() {
		return pet;
	}

	public void setPet(Date pet) {
		this.pet = pet;
	}

	public Date getRbt() {
		return rbt;
	}

	public void setRbt(Date rbt) {
		this.rbt = rbt;
	}

	public Date getRet() {
		return ret;
	}

	public void setRet(Date ret) {
		this.ret = ret;
	}

	public String getCell(int col) {
		if (col == 0) return String.valueOf(this.getSid());
		else if (col == 1) return this.getSname();
		else if (col == 2) return this.getPbt().toString();
		else if (col == 3) return this.getPet().toString();
		else if (col == 4) return this.getRbt().toString();
		else if (col == 5) return this.getRet().toString();
		else return "";
	}

	@Override
	public String toString() {
		return "BeanStep{" +
				"Sid=" + Sid +
				", Pid=" + Pid +
				", Sorder=" + Sorder +
				", Sname='" + Sname + '\'' +
				", pbt=" + pbt +
				", pet=" + pet +
				", rbt=" + rbt +
				", ret=" + ret +
				'}';
	}
}
