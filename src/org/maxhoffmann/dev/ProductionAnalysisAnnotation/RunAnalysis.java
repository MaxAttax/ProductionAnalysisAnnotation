package org.maxhoffmann.dev.ProductionAnalysisAnnotation;

public class RunAnalysis {

	public static void main(String[] args) {
		System.out.println("Let's start the Analysis!");
		
		ProjectDAO projectDAO = new ProjectDAO();
		
		projectDAO.listProjects();
	}
	
}