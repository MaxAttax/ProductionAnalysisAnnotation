package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	private long projectId;
	private String status;
	
	public Project(String status) {
		super();
		this.status = status;
	}
	
	public Project() {
	}

	@Id
	@GeneratedValue
	@Column(name="PrimaryId")
	public long getId() {
		return projectId;
	}
	
	public void setId(long id) {
		this.projectId = id;
	}
	
	@Column(name="Status", nullable=false)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
