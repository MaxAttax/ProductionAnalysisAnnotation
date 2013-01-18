package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "productionorderhistory" )
public class ProductionOrderHistory {
	
	private int ProductionOrderHistoryId;
	private int workplanNo;
	private Date startProduction;
	private double operationTime;
	private Order order;
	private ResourceGroup resourceGroup;
	
	@Id
	@GeneratedValue
	@Column(name = "ProductionOrderHistoryId")
	public int getProductionOrderHistoryId() {
		return this.ProductionOrderHistoryId;
	}
	
	public void setProductionOrderHistoryId(int pohId) {
		this.ProductionOrderHistoryId = pohId;
	}
	
	@Column(name = "WorkplanNo")
	public int getWorkplanNo() {
		return this.workplanNo;
	}
	
	public void setWorkplanNo(int workplanNo) {
		this.workplanNo = workplanNo;
	}
	
	@Column(name = "StartProduction")
	public Date getStartProduction() {
		return this.startProduction;
	}
	
	public void setStartProduction(Date startProduction) {
		this.startProduction = startProduction;
	}
	
	@Column(name = "OperationTime") 
	public double getOperationTime() {
		return this.operationTime;
	}
	
	public void setOperationTime(double operationTime) {
		this.operationTime = operationTime;
	}
	
	@ManyToOne
	@JoinColumn(name = "OrderId", nullable = false)
	public Order getOrder() {
		return this.order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne
	@JoinColumn(name = "ResourceGroupId", nullable = false)
	public ResourceGroup getResourceGroup() {
		return this.resourceGroup;
	}
	
	public void setResourceGroup(ResourceGroup resourceGroup) {
		this.resourceGroup = resourceGroup;
	}
	
}









