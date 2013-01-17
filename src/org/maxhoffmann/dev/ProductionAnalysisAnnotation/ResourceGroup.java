package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="resourcegroup")
public class ResourceGroup {
	private Project project;
	private long resourceGroupId;
	private long projectId;
	private String label;
	private String description;
	
	public ResourceGroup() {		
	}
	
	public ResourceGroup(long projectId, String label, String description) {
		this.projectId = projectId;
		this.label = label;
		this.description = description;	
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ResourceGroupId")
	public long getResourceGroupId() {
		return this.resourceGroupId;
	}
	
	public void setResourceGroupId(long resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}
	
	@ManyToOne
	@JoinColumn(name = "ProjectIdFK", nullable = true)
	public Project getProject() {
		return this.project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	/*
	@ManyToOne(cascade = CascadeType.ALL)
	public long getProjectId() {
		return this.projectId;
	}
	
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	*/
	
	@Column(name = "Label")
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}












