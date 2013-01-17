package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

public class RunAnalysis {

	public static void main(String[] args) {
		System.out.println("Let's start the Analysis!");
		
		ProjectDAO projectDAO = new ProjectDAO();
		
		long primaryIdProject001 = projectDAO.addProject("eins");
		projectDAO.addProject("zwei");
		long primaryIdProject003 = projectDAO.addProject("drei");
				
		projectDAO.listProjects();
		
		projectDAO.deleteProject(primaryIdProject003);
		
		projectDAO.listProjects();
		
		projectDAO.deleteProjectByStatus("zwei");
		
		projectDAO.listProjects();
		
		ResourceGroupDAO resourceGroupDAO = new ResourceGroupDAO();
		
		resourceGroupDAO.addResourceGroup("sawing", "R110", primaryIdProject001);
		resourceGroupDAO.addResourceGroup("milling", "R160", primaryIdProject001);
		resourceGroupDAO.addResourceGroup("drilling", "R150", primaryIdProject001);
		resourceGroupDAO.addResourceGroup("assembling", "R310", primaryIdProject001);
		resourceGroupDAO.addResourceGroup("quality controlling", "R340", primaryIdProject001);
		resourceGroupDAO.addResourceGroup("grinding", "R200", primaryIdProject001);
		resourceGroupDAO.addResourceGroup("turning", "R140", primaryIdProject001);
		
		resourceGroupDAO.listResourceGroups();
		
	}
	
}