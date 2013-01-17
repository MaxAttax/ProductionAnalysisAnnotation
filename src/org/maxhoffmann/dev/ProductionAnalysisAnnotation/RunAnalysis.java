package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

public class RunAnalysis {

	public static void main(String[] args) {
		System.out.println("Let's start the Analysis!");
		
		ProjectDAO projectDAO = new ProjectDAO();
		
		long primaryIdProject001 = projectDAO.addProject("eins");
		long primaryIdProject002 = projectDAO.addProject("zwei");
		long primaryIdProject003 = projectDAO.addProject("drei");
		long primaryIdProject004 = projectDAO.addProject("vier");
		long primaryIdProject005 = projectDAO.addProject("fuenf");
		long primaryIdProject006 = projectDAO.addProject("sechs");
				
		projectDAO.listProjects();
		
		projectDAO.deleteProject(primaryIdProject003);
		
		projectDAO.listProjects();
		
		projectDAO.deleteProjectByStatus("eins");
		
		projectDAO.listProjects();
		
		ResourceGroupDAO resourceGroupDAO = new ResourceGroupDAO();
		
		resourceGroupDAO.addResourceGroup("sawing", "R110");
		
		resourceGroupDAO.listResourceGroups();
		
	}
	
}