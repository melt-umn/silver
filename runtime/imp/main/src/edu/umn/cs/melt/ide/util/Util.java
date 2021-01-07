package edu.umn.cs.melt.ide.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

import common.ConsCell;
import common.IOToken;
import common.StringCatter;
import common.javainterop.ConsCellCollection;
import silver.core.NIOVal;
import silver.core.NMaybe;
import silver.core.Pioval;
import silver.core.Pjust;
import silver.core.Pnothing;

import edu.umn.cs.melt.ide.imp.services.Console;
import edu.umn.cs.melt.ide.impl.SVRegistry;

/**
 * This utility class is an API collection used mainly by Silver code (through
 * foreign code block). 
 */
public final class Util {

	/* IDE Console */
	
	public static IOToken info(String msg) {
		Console.getConsoleLoggingStream().info(msg);
		return IOToken.singleton;
	}
	
	public static IOToken error(String msg){
		Console.getConsoleLoggingStream().error(msg);
		return IOToken.singleton;
	}
	
	/* Apache Ant tool */
	
	public static IOToken ant(StringCatter buildFile, StringCatter arguments, StringCatter target){
		
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
		
		return IOToken.singleton;
	}
	
	/* Project-related operations */
	
	/**
	 * Refresh project with given name. Return false if found. Newly found project
	 * will be cached.
	 * 
	 * @param projectName  The name of the project to refresh
	 */
	public static IOToken refresh(StringCatter projectName) {
		
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(projectName.toString()).refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// Dunno what to do
			e.printStackTrace();
		}
				
		return IOToken.singleton;
	}
	
	public static NIOVal getProjectName(Object/*IProject*/ _project, Object ioin) {
		IProject project = (IProject)_project;
		return new Pioval(ioin, new StringCatter(project.getName()));
	}
	
	public static IOToken refreshProject(Object/*IProject*/ _project, Object ioin) {
		IProject project = (IProject)_project;
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// who knows
			e.printStackTrace();
		}
		return IOToken.singleton;
	}
	
	public static NIOVal getAbsoluteProjectPath(Object/*IProject*/ _project, Object ioin) {
		IProject project = (IProject)_project;
		return new Pioval(ioin, new StringCatter(project.getLocation().toOSString()));
	}
	
	public static NIOVal getGeneratedProjectPath(Object/*IProject*/ _project, Object ioin) {
		IProject project = (IProject)_project;
		return new Pioval(ioin, new StringCatter(project.getLocation().toOSString() 
				+ IPath.SEPARATOR + "bin"));//FIXME - get this from IDE plug-in
	}
	
	public static NIOVal getProjectMembers(Object/*IProject*/ _project, Object ioin) {
		IProject project = (IProject)_project;
		NMaybe result;
		try {
			result = new Pjust(ConsCellCollection.fromList(Arrays.asList(project.members())));
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = new Pnothing();
		}
		return new Pioval(ioin, result);
	}
	
	public static NIOVal deleteResource(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
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

	public static NIOVal copyResource(Object/*IResource*/ _resource, StringCatter dest, Object ioin) {
		IResource resource = (IResource)_resource;
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
	
	public static NIOVal moveResource(Object/*IResource*/ _resource, StringCatter dest, Object ioin) {
		IResource resource = (IResource)_resource;
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

	public static NIOVal createResource(Object/*IProject*/ _project, StringCatter file, StringCatter contents, Object ioin) {
		IProject project = (IProject)_project;
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

	public static NIOVal createFolderResource(Object/*IProject*/ _project, StringCatter file, Object ioin) {
		IProject project = (IProject)_project;
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
	
	public static NIOVal getRelativePath(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		return new Pioval(ioin, new StringCatter(resource.getProjectRelativePath().toOSString()));
	}
	public static NIOVal getAbsolutePath(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		return new Pioval(ioin, new StringCatter(resource.getLocation().toOSString()));
	}

	public static NIOVal getResourceMembers(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		ConsCell result;
		try {
			if(resource instanceof IFolder) {
				IFolder f = (IFolder) resource;
				result = ConsCellCollection.fromList(Arrays.asList(f.members()));
			} else {
				result = ConsCell.nil;
			}
		} catch (CoreException e) {
			// TODO dunno?
			e.printStackTrace();
			result = ConsCell.nil;
		}
		return new Pioval(ioin, result);
	}
	
	public static NIOVal resourceIsFolder(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		boolean result = resource instanceof IFolder;
		return new Pioval(ioin, result);
	}
	public static NIOVal resourceIsFile(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		boolean result = resource instanceof IFile;
		return new Pioval(ioin, result);
	}
	public static NIOVal resourceIsLinked(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		boolean result = resource.isLinked();
		return new Pioval(ioin, result);
	}
	public static NIOVal resourceIsHidden(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		boolean result = resource.isHidden();
		return new Pioval(ioin, result);
	}
	public static NIOVal resourceIsDerived(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		boolean result = resource.isDerived();
		return new Pioval(ioin, result);
	}
	public static NIOVal resourceExists(Object/*IResource*/ _resource, Object ioin) {
		IResource resource = (IResource)_resource;
		boolean result = resource.exists();
		return new Pioval(ioin, result);
	}
	
	public static NIOVal getIdeResource(StringCatter id, Object ioin) {
		URL url = Platform.getBundle(SVRegistry.get().pluginId()).getEntry("resource/" + id.toString());
		String fileurl = "";
		try {
			// Evidently, when it's a file url, getFile gets the whole path string
			fileurl = FileLocator.toFileURL(url).getFile().toString();
		} catch (IOException e) {
			// TODO Not sure what to do ~
			e.printStackTrace();
		}
		return new Pioval(ioin, new StringCatter(fileurl));
	}
}
