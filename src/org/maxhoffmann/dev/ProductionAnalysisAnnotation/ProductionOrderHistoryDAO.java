package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxhoffmann.dev.util.HibernateUtil;

public class ProductionOrderHistoryDAO {
	
	@SuppressWarnings("unchecked")
	public void listProductionOrderHistories() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ProductionOrderHistory");
			List<ProductionOrderHistory> productionOrderHistories = query.list();
			for ( ProductionOrderHistory productionOrderHistory : productionOrderHistories ) {
				int productionOrderHistoryId = productionOrderHistory.getProductionOrderHistoryId();
				int workplanNo = productionOrderHistory.getWorkplanNo();
				Date startProduction = productionOrderHistory.getStartProduction();
				double operationTime = productionOrderHistory.getOperationTime();
			}
		} catch ( HibernateException e ) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
}