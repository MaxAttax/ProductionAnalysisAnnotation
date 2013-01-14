package org.maxhoffmann.dev.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import org.maxhoffmann.dev.ProductionAnalysisAnnotation.Project;


public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
								 .configure()
								 .addPackage("org.maxhoffmann.dev.ProductionAnalysisAnnotation")
								 .addAnnotatedClass(Project.class)
								 .buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed. Error: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}