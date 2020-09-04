package cn.edu.zucc.personplan.model;

import com.mysql.fabric.xmlrpc.base.Data;

import java.util.Date;

public class BeanUser {
	public static BeanUser currentLoginUser = null;
	private String id;
	private String pwd;
	private java.sql.Date r_time;

	public BeanUser() {
		this.id = null;
		this.pwd = null;
		this.r_time = null;
	}

	public BeanUser(String userid, String pwd, Date data) {
		this.id = userid;
		this.pwd = pwd;
		this.r_time = (java.sql.Date) data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getR_time() {
		return r_time;
	}

	public void setR_time(Date r_time) {
		this.r_time = (java.sql.Date) r_time;
	}

	@Override
	public String toString() {
		return "BeanUser{" +
				"id='" + id + '\'' +
				", pwd='" + pwd + '\'' +
				", r_time=" + r_time +
				'}';
	}
}
