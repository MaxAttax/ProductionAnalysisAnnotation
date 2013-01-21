package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxhoffmann.dev.util.HibernateUtil;

public class ProductionOrderHistoryDAO {
	
	@SuppressWarnings("unchecked")
	public List<ProductionOrderHistory> listProductionOrderHistories() {
		List<ProductionOrderHistory> productionOrderResult = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ProductionOrderHistory");
			// query.setMaxResults(100);
			List<ProductionOrderHistory> productionOrderHistories = query.list();
			productionOrderResult = productionOrderHistories;
			/*
			System.out.println("\n");
			for ( ProductionOrderHistory productionOrderHistory : productionOrderHistories ) {
				int productionOrderHistoryId = productionOrderHistory.getProductionOrderHistoryId();
				int workplanNo = productionOrderHistory.getWorkplanNo();
				Date startProduction = productionOrderHistory.getStartProduction();
				double operationTime = productionOrderHistory.getOperationTime();
				int orderId = productionOrderHistory.getOrder().getOrderId();
				int resourceGroupId = productionOrderHistory.getResourceGroup().getResourceGroupId();
				String resourceGroupLabel = productionOrderHistory.getResourceGroup().getLabel();
				System.out.println("ID: " + productionOrderHistoryId 
						+ "  Workplan No.: " + workplanNo 
						+ "  Start Production: " + startProduction
						+ "  Operation Time: " + operationTime 
						+ "  Order-ID: " + orderId 
						+ "  Order-No: " + productionOrderHistory.getOrder().getOrderNo()   
						+ "  ResourceGroup-ID: " + resourceGroupId
						+ "  ResourceGroup-Label: " + resourceGroupLabel);
			}
			System.out.println("\n");
			*/
		} catch ( HibernateException e ) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return productionOrderResult;
	}	
}