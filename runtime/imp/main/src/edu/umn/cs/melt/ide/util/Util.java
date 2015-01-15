package edu.umn.cs.melt.ide.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import edu.umn.cs.melt.ide.imp.services.Console;
import edu.umn.cs.melt.ide.silver.misc.ConsoleLoggingStream;

/**
 * This utility class is an API collection used mainly by Silver code (through
 * foreign code block). 
 */
public final class Util {

	/* IDE Console */
	
	public static Object info(String msg) {
		Console.getConsoleLoggingStream().info(msg);
		return null;
	}
	
	public static Object error(String msg){
		Console.getConsoleLoggingStream().error(msg);
		return null;
	}
	
	/* Apache Ant tool */
	
	public static Object ant(String buildFile, String arguments, String target){
		
		ANT_RUNNER.ant(buildFile, arguments, target);

		/*
		AntRunner runner = new AntRunner();
		runner.setBuildFileLocation(buildFile);
		runner.setArguments(arguments);
		if(target != null && !"".equals(target.trim())){
			runner.setExecutionTargets(new String[]{target});
		}
		try {
			runner.run();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		*/
		
		return null;
	}
	
	private static IAntRunnable ANT_RUNNER;
	
	public static void setAntRunnable(IAntRunnable iat){
		ANT_RUNNER = iat;
	}
	
	public static interface IAntRunnable {
		void ant(String buildFile, String arguments, String target);
	}
	
	/* Project-related operations */
	
	/**
	 * Refresh project with given name. Return false if found. Newly found project
	 * will be cached.
	 * 
	 * @param projectName
	 * @param depth	the depth down to which to refresh this project. Can only
	 * use predefined constants from {@link org.eclipse.core.resources.IResource IResource},
	 * including DEPTH_ZERO, DEPTH_ONE, DEPTH_INFINITE
	 */
	public static synchronized Object refresh(String projectName, int depth){
		if(findProject(projectName)){
			try {
				activeProject.refreshLocal(depth, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private static IProject activeProject;
	
	/**
	 * Find project with given name. Return false if found. Newly found project
	 * will be cached.
	 * 
	 * @param projectName
	 * @return false if no project is found; true otherwise
	 */
	private static boolean findProject(String projectName){
		if(activeProject!=null && projectName.equals(activeProject.getName())){
			return true;
		}
		
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		if(projects!=null){
			for(IProject pr:projects){
				if(pr.getName().equals(projectName)){
					activeProject = pr;
					return true;
				}
			}
		}
		
		return false;
	}
}
