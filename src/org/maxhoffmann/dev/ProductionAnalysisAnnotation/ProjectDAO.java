package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxhoffmann.dev.util.HibernateUtil;


public class ProjectDAO {
	
	@SuppressWarnings("unchecked")
	public void listProjects() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Project> projects = session.createQuery("from Project").list();
			
			for (Project project : projects) {
				System.out.println("Projekt: " + project.getStatus());
			}
			
			transaction.commit();
		} catch ( HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}