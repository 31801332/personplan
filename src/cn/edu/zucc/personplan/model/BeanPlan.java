package cn.edu.zucc.personplan.model;

import java.sql.Date;

public class BeanPlan {
	public static final String[] tableTitles = {"���", "����", "������", "�������"};
	/**
	 * �����и���javabean������޸ı��������룬col��ʾ�������е�����ţ�0��ʼ
	 */
	public int Pid;//plan_id
	public String Uid;//user_id
	public int Porder;//�ƻ���ţ�Ҫ��ͬһ�û�����Ų��ظ�
	public String Pname;//�ƻ�����
	public java.sql.Date Ctime;//create_time
	public int Scount;//��������
	public int StartCount;//�Ѿ���ʼ�Ĳ�����
	public int FinishedCount;//�Ѿ���ɵĲ�����

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
