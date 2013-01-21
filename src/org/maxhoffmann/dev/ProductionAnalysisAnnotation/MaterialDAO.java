package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxhoffmann.dev.util.HibernateUtil;

public class MaterialDAO {
	
	@SuppressWarnings("unchecked")
	public void listMaterial() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Material");
			query.setMaxResults(10);
			List<Material> materials = query.list();
			System.out.println("\n");
			for (Material material : materials) {
				int materialId = material.getId();
				int materialNo = material.getMaterialNo();
				String description = material.getDescription();
				System.out.println("ID: " + materialId + "  Material No: " + materialNo + "  Description: " + description);
			}
			transaction.commit();
		} catch ( HibernateException e ) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}