package com.ninza.hrm.api.pojoclass;

public class ProjectPojo {
	
	String projectName;  // variable name and key name should be same 
	String status;       //what ever key given in swagger document bcz it is a casesensitive
	String createdBy;
	int teamSize;
	
	public ProjectPojo() {
		
	}
	public ProjectPojo(String projectName, String status, String createdBy, int teamSize) {
		super();
		this.projectName = projectName;
		this.status = status;
		this.createdBy = createdBy;
		this.teamSize = teamSize;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	
}
