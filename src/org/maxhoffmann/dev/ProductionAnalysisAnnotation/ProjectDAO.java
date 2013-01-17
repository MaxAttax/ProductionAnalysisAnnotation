package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.maxhoffmann.dev.ProductionAnalysisAnnotation.Project;
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
				System.out.println("ID: " + project.getId() + "  Projektstatus: " + project.getStatus());
			}
			
			transaction.commit();
		} catch ( HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public Long addProject(String projectName) {
		Session sessionAdd = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionAdd = null;
		Long newProjectId = null;
		try {
			transactionAdd = sessionAdd.beginTransaction();
			Project addStatus = new Project();
			addStatus.setStatus(projectName);
			newProjectId = (Long) sessionAdd.save(addStatus);
			transactionAdd.commit();
		} catch (HibernateException e) {
			transactionAdd.rollback();
			e.printStackTrace();
		} finally {
			sessionAdd.close();
		}
		return newProjectId;
	}
	
	public void deleteProject(Long deleteProjectId) {
		Session sessionDelete = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionDelete = null;
		try {
			transactionDelete = sessionDelete.beginTransaction();
			Project projectDelete = (Project) sessionDelete.get(Project.class, deleteProjectId);
			sessionDelete.delete(projectDelete);
			transactionDelete.commit();
		} catch ( HibernateException e ) {
			transactionDelete.rollback();
			e.printStackTrace();
		} finally {
			sessionDelete.close();
		}
	}
	
	public void deleteProjectByStatus(String statusToDelete) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "delete from Project where Status = :setProjectStatus";
			Query query = session.createQuery(hql);
			query.setParameter("setProjectStatus", statusToDelete);
			query.executeUpdate();
			System.out.println("SQL Query: " + hql);
			transaction.commit();
		} catch ( HibernateException e ) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
}
