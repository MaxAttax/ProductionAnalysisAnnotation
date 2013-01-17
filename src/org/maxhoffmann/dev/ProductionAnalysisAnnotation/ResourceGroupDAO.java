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
				/*
				 * In order to list the foreign key (Primary Key of "Project" in the ResourceGroups List
				 * the println command has to call the getId() method of the class project
				 */
				System.out.println("ID: " + resourceGroup.getResourceGroupId() + "  Projekt-ID: " + resourceGroup.getProject().getId()
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
	
	public void addResourceGroup(String description, String label, long projectId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Project project = new Project();			// An empty project has to be initialized in order to to
														// add the foreign key constraint of the project to the ResourceGroup
			
			ResourceGroup newResourceGroup = new ResourceGroup();
			newResourceGroup.setDescription(description);
			newResourceGroup.setLabel(label);
			
			newResourceGroup.setProject(project);		// Add the project property to ResourceGroup -> ProjectId constraint
			project.setId(projectId);					// assign a certain projectId (type long) to the added project
			
			session.save(newResourceGroup);				// save the new data set
			transaction.commit();						// commit the transaction execution to the database
			
		} catch (HibernateException e) {				// common hibernate procedure to catch errors
			transaction.rollback();						
			e.printStackTrace();
			
		} finally {										// close the session in both cases
			session.close();
		}
		
	}
	
}