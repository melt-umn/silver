package edu.umn.cs.melt.ide.util;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import common.StringCatter;
import common.javainterop.ConsCellCollection;
import core.NIOVal;
import core.NMaybe;
import core.Pioval;
import core.Pjust;
import core.Pnothing;

import edu.umn.cs.melt.ide.imp.services.Console;

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
	
	public static Object ant(StringCatter buildFile, StringCatter arguments, StringCatter target){
		
		AntRunner runner = new AntRunner();
		runner.setBuildFileLocation(buildFile.toString());
		runner.setArguments(arguments.toString());
		if(!"".equals(target.toString().trim())) {
			runner.setExecutionTargets(new String[]{ target.toString() });
		}
		try {
			runner.run();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Project-related operations */
	
	/**
	 * Refresh project with given name. Return false if found. Newly found project
	 * will be cached.
	 * 
	 * @param projectName  The name of the project to refresh
	 */
	public static Object refresh(StringCatter projectName) {
		
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(projectName.toString()).refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// Dunno what to do
			e.printStackTrace();
		}
				
		return null;
	}
	
	public static NIOVal getProjectName(IProject project, Object ioin) {
		return new Pioval(ioin, new StringCatter(project.getName()));
	}
	
	public static Object refreshProject(IProject project, Object ioin) {
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// who knows
			e.printStackTrace();
		}
		return null;
	}
	
	public static NIOVal getAbsoluteProjectPath(IProject project, Object ioin) {
		return new Pioval(ioin, new StringCatter(project.getLocation().toOSString()));
	}
	
	public static NIOVal getGeneratedProjectPath(IProject project, Object ioin) {
		return new Pioval(ioin, new StringCatter(project.getLocation().toOSString() 
				+ IPath.SEPARATOR + "bin"));//FIXME - get this from IDE plug-in
	}
	
	public static NIOVal getProjectMembers(IProject project, Object ioin) {
		NMaybe result;
		try {
			result = new Pjust(ConsCellCollection.fromIterator(Arrays.asList(project.members()).iterator()));
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = new Pnothing();
		}
		return new Pioval(ioin, result);
	}
	
	public static NIOVal deleteResource(IResource resource, Object ioin) {
		boolean result = true;
		try {
			resource.delete(true, null);
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = false;
		}
		return new Pioval(ioin, result);
	}

	public static NIOVal copyResource(IResource resource, StringCatter dest, Object ioin) {
		IPath path = resource.getProject().getFullPath().append(dest.toString());
		boolean result = true;
		try {
			resource.copy(path, true, null);
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = false;
		}
		return new Pioval(ioin, result);
	}
	
	public static NIOVal moveResource(IResource resource, StringCatter dest, Object ioin) {
		IPath path = resource.getProject().getFullPath().append(dest.toString());
		boolean result = true;
		try {
			resource.move(path, true, null);
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = false;
		}
		return new Pioval(ioin, result);
	}

	public static NIOVal createResource(IProject project, StringCatter file, StringCatter contents, Object ioin) {
		IFile f = project.getFile(file.toString());
		NMaybe result;
		try {
			f.create(new ByteArrayInputStream(contents.toString().getBytes()), true, null);
			result = new Pjust(f);
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = new Pnothing();
		}
		return new Pioval(ioin, result);
	}

	public static NIOVal createFolderResource(IProject project, StringCatter file, Object ioin) {
		IFolder f = project.getFolder(file.toString());
		NMaybe result;
		try {
			f.create(true, true, null);
			result = new Pjust(f);
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = new Pnothing();
		}
		return new Pioval(ioin, result);
	}
	
	public static NIOVal getRelativePath(IResource resource, Object ioin) {
		return new Pioval(ioin, new StringCatter(resource.getProjectRelativePath().toOSString()));
	}
	public static NIOVal getAbsolutePath(IResource resource, Object ioin) {
		return new Pioval(ioin, new StringCatter(resource.getLocation().toOSString()));
	}

}
