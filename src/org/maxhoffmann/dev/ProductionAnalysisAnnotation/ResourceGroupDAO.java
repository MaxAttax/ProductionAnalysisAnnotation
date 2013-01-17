package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxhoffmann.dev.util.HibernateUtil;

public class ResourceGroupDAO {
	
	@SuppressWarnings("unchecked")
	public void listResourceGroups() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<ResourceGroup> resourceGroups = session.createQuery("from ResourceGroup").list();
			
			for ( ResourceGroup resourceGroup : resourceGroups ) {
				System.out.println("ID: " + resourceGroup.getResourceGroupId() + "  Projekt-ID: "
						+ "  Label: " + resourceGroup.getLabel() + "  Description: " + resourceGroup.getDescription());
			}
			
			transaction.commit();
		} catch ( HibernateException e ) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void addResourceGroup(String description, String label) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ResourceGroup newResourceGroup = new ResourceGroup();
			newResourceGroup.setDescription(description);
			newResourceGroup.setLabel(label);
			session.save(newResourceGroup);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}