package com.ems.domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Department {
	private long deptId;
	private String name;
	private String activeFl;
	private Date createDateTime;
	private Date updateDateTime;
	
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActiveFl() {
		return activeFl;
	}
	public void setActiveFl(String activeFl) {
		this.activeFl = activeFl;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public String getCreateDateStr() {
		if(createDateTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(createDateTime);
		}
		return "";
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public String getUpdateDateStr() {
		if(updateDateTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(updateDateTime);
		}
		return "";
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", name=" + name + "]";
	}
	public Object getDepartment() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}