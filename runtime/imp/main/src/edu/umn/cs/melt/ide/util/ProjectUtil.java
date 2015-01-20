package edu.umn.cs.melt.ide.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import common.ConsCell;
import common.StringCatter;

/**
 * The collection of services supporting manipulation on IDE project and its 
 * resources. It's interfaced by Silver from grammar <code>ide</code>.
 * <p>
 * To call a service, invoke {@link #disptachService(int, Object, Object)}. The
 * first argument is the service type, which is exposed by this class as public
 * constants, including:
 * <pre>
 *   {@link #PROJECT_GET_NAME}
 *   {@link #PROJECT_REFRESH}
 *   {@link #PROJECT_GET_ABS_PATH}
 *   {@link #PROJECT_GET_GEN_PATH}
 *   {@link #PROJECT_GET_MEMBERS}
 *   
 *   {@link #RESOURCE_DELETE}
 *   {@link #RESOURCE_COPY_TO}
 *   {@link #RESOURCE_MOVE_TO}
 *   {@link #RESOURCE_CREATE_FILE}
 *   {@link #RESOURCE_CREATE_FOLDER}
 *   {@link #RESOURCE_GET_REL_PATH}
 *   {@link #RESOURCE_GET_MEMBERS}
 *   {@link #RESOURCE_IS_FOLDER}
 *   {@link #RESOURCE_IS_FILE}
 *   {@link #RESOURCE_IS_LINKED}
 *   {@link #RESOURCE_IS_HIDDEN}
 *   {@link #RESOURCE_IS_DERIVED}
 *   {@link #RESOURCE_EXISTS}</pre>
 * <p>
 * To use this class properly, the IDE plug-in must call (for once only)
 * {@link #setSilverTypeWrapper(ISilverTypeWrapper)} at an early timing, such
 * as in some startup hook.
 */
public final class ProjectUtil {

	private static abstract class AbstractResourceService {
		final static int RESOURCE = 1;
		final static int PROJECT = 3;
		
		int targetType;
		
		int getTargetType(){
			return targetType;
		}
		
		final static int IO = 1;
		final static int IOV = 2;
		final static int IOVMAYBE = 3;
		
		int returnType;
		
		int getReturnType(){
			return returnType;
		}
		
		protected AbstractResourceService(int targetT, int returnT){
			targetType = targetT;
			returnType = returnT;
		}
	}
	
	private static interface ResourceExecutable {
		abstract Object execute(IResource res, Object data);
	}
	
	private static interface ProjectExecutable {
		abstract Object execute(IProject proj, Object data);
	}
	
	private static abstract class ResourceService 
		extends AbstractResourceService implements ResourceExecutable {
		protected ResourceService(int returnT) {
			super(RESOURCE, returnT);
		}		
	}
	
	private static abstract class ProjectService 
		extends AbstractResourceService implements ProjectExecutable {
		protected ProjectService(int returnT) {
			super(PROJECT, returnT);
		}		
	}
	
	private static final AbstractResourceService PROJECT_GET_NAME_SERV = 
		new ProjectService(AbstractResourceService.IOV){
			@Override
			public Object execute(IProject project, Object data) {
				return new StringCatter(project.getName());
			}
		};	
		
	private static final AbstractResourceService PROJECT_REFRESH_SERV = 
		new ProjectService(AbstractResourceService.IO){
			@Override
			public Object execute(IProject project, Object data) {
				try {
					project.refreshLocal((Integer)data, null);
				} catch (CoreException e) {
					e.printStackTrace();
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
	private static final AbstractResourceService PROJECT_GET_ABS_PATH_SERV = 
		new ProjectService(AbstractResourceService.IOV){
			@Override
			public Object execute(IProject project, Object data) {
				return new StringCatter(project.getLocation().toOSString());
			}
		};	
		
	private static final AbstractResourceService PROJECT_GET_GEN_PATH_SERV = 
		new ProjectService(AbstractResourceService.IOV){
			@Override
			public Object execute(IProject project, Object data) {
				return new StringCatter(project.getLocation().toOSString() 
					+ IPath.SEPARATOR + "bin");//FIXME - get this from IDE plug-in
			}
		};		
		
	private static final AbstractResourceService PROJECT_GET_MEMBERS_SERV = 
		new ProjectService(AbstractResourceService.IOVMAYBE){
			@Override
			public Object execute(IProject project, Object data) {
				try {
					ConsCell cons = makeCons(project.members());
					return cons;
				} catch (CoreException e) {
					e.printStackTrace();
				}
				
				return null;
			}
		};
		
	private static final AbstractResourceService RESOURCE_DELETE_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				try {
					resource.delete(true, null);
				} catch (CoreException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
		};
		
	private static final AbstractResourceService RESOURCE_COPY_TO_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				try {
					IPath path = resource.getProject().getFullPath().append(makeString(data));
					resource.copy(path, true, null);
				} catch (CoreException e) {
					e.printStackTrace();
					return false;
				} catch (ClassCastException e) {
					Util.error("[Internal Error] When calling ide.copyTo(...), " +
						"the 2nd argument must be of String type.");
					return false;				
				}
				
				return true;
			}
		};	
	
	private static final AbstractResourceService RESOURCE_MOVE_TO_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				try {
					IPath path = resource.getProject().getFullPath().append(makeString(data));
					resource.move(path, true, null);
				} catch (CoreException e) {
					e.printStackTrace();
					return false;
				} catch (ClassCastException e) {
					Util.error("[Internal Error] When calling ide.moveTo(...), " +
						"the 2nd argument must be of String type.");
					return false;				
				}
				
				return true;
			}
		};
	
	/**
	 * {@link #RESOURCE_CREATE_FILE}
	 */
	private static final AbstractResourceService RESOURCE_CREATE_FILE_SERV = 
		new ProjectService(AbstractResourceService.IOVMAYBE){
			@Override
			public Object execute(IProject project, Object data) {				
				try {
					Object[] args = makeArray(data);
					
					IFile file = project.getFile(makeString(args[0]));
					file.create(new ByteArrayInputStream(makeString(args[1]).getBytes()), true, null);	
					return file;
				} catch (CoreException e) {
					e.printStackTrace();
				} catch (ClassCastException e) {
					Util.error("[Internal Error] When calling ide.createFile(...), " +
						"the 2nd and 3rd argument must be of String type.");				
				}
				
				return null;
			}
		};	

	/**
	 * {@link #RESOURCE_CREATE_FOLDER}
	 */
	private static final AbstractResourceService RESOURCE_CREATE_FOLDER_SERV = 
		new ProjectService(AbstractResourceService.IOVMAYBE){
			@Override
			public Object execute(IProject project, Object data) {
				try {
					IFolder folder = project.getFolder(makeString(data));
					folder.create(true, true, null);
					return folder;
				} catch (CoreException e) {
					e.printStackTrace();
				} catch (ClassCastException e) {
					Util.error("[Internal Error] When calling ide.createFolder(...), " +
						"the 2nd argument must be of String type.");
				}
				
				return null;
			}
		};

	private static final AbstractResourceService RESOURCE_GET_REL_PATH_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return resource.isLinked()?
					new StringCatter(resource.getProjectRelativePath().toOSString()):
					new StringCatter("");
			}
		};

	private static final AbstractResourceService RESOURCE_GET_ABS_PATH_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return new StringCatter(resource.getLocation().toOSString());
			}
		};
			
	private static final AbstractResourceService RESOURCE_GET_MEMBERS_SERV = 
		new ResourceService(AbstractResourceService.IOVMAYBE){
			@Override
			public Object execute(IResource resource, Object data) {
				//Demand members only if it is a folder
				if(resource instanceof IFolder){
					try {
						//1.1) if successful, return a cons
						return makeCons(((IFolder) resource).members());
					} catch (CoreException e) {
						//1.2) else return null
						e.printStackTrace();
						return null;
					}
				} else {
					//2) if not folder, return nil 
					return ConsCell.nil;
				}
			}
		};
			
	private static final AbstractResourceService RESOURCE_IS_FOLDER_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return (resource instanceof IFolder)?true:false;
			}
		};	
	
	private static final AbstractResourceService RESOURCE_IS_FILE_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return (resource instanceof IFile)?true:false;
			}
		};
		
	private static final AbstractResourceService RESOURCE_IS_LINKED_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return (resource.isLinked())?true:false;
			}
		};			

	private static final AbstractResourceService RESOURCE_IS_HIDDEN_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return (resource.isHidden())?true:false;
			}
		};	
			
	private static final AbstractResourceService RESOURCE_IS_DERIVED_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return (resource.isDerived())?true:false;
			}
		};	
		
	private static final AbstractResourceService RESOURCE_EXISTS_SERV = 
		new ResourceService(AbstractResourceService.IOV){
			@Override
			public Object execute(IResource resource, Object data) {
				return (resource.exists())?true:false;
			}
		};	
			
	private static AbstractResourceService[] SERVICES;
	
	/* Increment this number when adding new service */
	private static int SERV_TOTAL = 0;
	
	/** Get the name of the given project. */
	public static final int PROJECT_GET_NAME = SERV_TOTAL++;
	
	/** 
	 * Refresh the given project down to given depth, for which only 
	 * predefined constants (0, 1, indefinite) can be used. 
	 */
	public static final int PROJECT_REFRESH = SERV_TOTAL++;
	
	/** Get the absolute path of given project. */
	public static final int PROJECT_GET_ABS_PATH = SERV_TOTAL++;
	
	/** Get the generated path of given project, relative to root. */
	public static final int PROJECT_GET_GEN_PATH = SERV_TOTAL++;
	
	/** Get members (direct sub-resources) of given project. */
	public static final int PROJECT_GET_MEMBERS = SERV_TOTAL++;
	
	/** Delete a resource, returning true if successful. */
	public static final int RESOURCE_DELETE = SERV_TOTAL++;
	
	/** 
	 * Copy a resource to another location within the project, 
	 * using a path relative to project’s root. Returns true if successful.
	 */
	public static final int RESOURCE_COPY_TO = SERV_TOTAL++;
	
	/** 
	 * Move (shortcut for copy and delete) a resource to another location 
	 * within the project, using a path relative to project’s root. Returns 
	 * true if successful.
	 */
	public static final int RESOURCE_MOVE_TO = SERV_TOTAL++;
	
	/**
	 * Create a new file with given input as contents. The path is relative to 
	 * project root. Returns the resource if created successfully.
	 */
	public static final int RESOURCE_CREATE_FILE = SERV_TOTAL++;
	
	/** Create a new folder. Returns the resource if created successfully. */
	public static final int RESOURCE_CREATE_FOLDER = SERV_TOTAL++;
	
	/** 
	 * Get the path of given resource, relative to project’s root. 
	 * If the resource is linked, an empty string will be returned.
	 */
	public static final int RESOURCE_GET_REL_PATH = SERV_TOTAL++;
	
	/** Get the absolute path of given resource. */
	public static final int RESOURCE_GET_ABS_PATH = SERV_TOTAL++;
	
	/** 
	 * Get members of given resource. Returns an empty list if the resource is 
	 * a file. 
	 */
	public static final int RESOURCE_GET_MEMBERS = SERV_TOTAL++;
	
	/** Check if the resource is a folder. */
	public static final int RESOURCE_IS_FOLDER = SERV_TOTAL++;
	
	/** Check if the resource is a file. */
	public static final int RESOURCE_IS_FILE = SERV_TOTAL++;
	
	/** 
	 * Check whether a resource is linked from outside project’s physical 
	 * location. This is a feature from Eclipse.  
	 */
	public static final int RESOURCE_IS_LINKED = SERV_TOTAL++;
	
	/** 
	 * Check whether a resource is hidden in Eclipse. That is, invisible to IDE 
	 * users. 
	 */
	public static final int RESOURCE_IS_HIDDEN = SERV_TOTAL++;
	
	/** 
	 * Determine if a resource is marked as derived. “Derived”, in Eclipse’s 
	 * terminology, means a resource is the result of automatic generation, 
	 * such as compilation, out of another resource(s). Such resource is thus 
	 * not subject to user’s manual modification.
	 */
	public static final int RESOURCE_IS_DERIVED = SERV_TOTAL++;
	
	/** 
	 * Check whether the resource does exist. After deleting a resource 
	 * successfully, calling this function should return false.
	 */
	public static final int RESOURCE_EXISTS = SERV_TOTAL++;
	
	//Add more service types here ...
	
	/*
	 * This static initialization is better getting generated. But for now 
	 * let's just write it manually. If you want to add a new service,
	 * 1) declare a constant at the end of declaration block above,
	 * 	 public static final int [PROJECT|RESOURCE]_SOMETHING = SERV_TOTAL++;
	 * 2) implement the service
	 *    private static final AbstractResourceService [PROJECT|RESOURCE]_SOMETHING_SERV = 
	 *      new [Project|Resource]Service(AbstractResourceService.[IO|IOV|IOVMAYBE]){
	 *		  ...
	 *      };	
	 * 3) add this at the end of the static block
	 *   SERVICES[[PROJECT|RESOURCE]_SOMETHING] = [PROJECT|RESOURCE]_SOMETHING_SERV;
	 */
	static {
		SERVICES = new AbstractResourceService[SERV_TOTAL];
		
		SERVICES[PROJECT_GET_NAME] = PROJECT_GET_NAME_SERV;
		SERVICES[PROJECT_REFRESH] = PROJECT_REFRESH_SERV;
		SERVICES[PROJECT_GET_ABS_PATH] = PROJECT_GET_ABS_PATH_SERV;
		SERVICES[PROJECT_GET_GEN_PATH] = PROJECT_GET_GEN_PATH_SERV;
		SERVICES[PROJECT_GET_MEMBERS] = PROJECT_GET_MEMBERS_SERV;
		
		SERVICES[RESOURCE_DELETE] = RESOURCE_DELETE_SERV;
		SERVICES[RESOURCE_COPY_TO] = RESOURCE_COPY_TO_SERV;
		SERVICES[RESOURCE_MOVE_TO] = RESOURCE_MOVE_TO_SERV;
		SERVICES[RESOURCE_CREATE_FILE] = RESOURCE_CREATE_FILE_SERV;
		SERVICES[RESOURCE_CREATE_FOLDER] = RESOURCE_CREATE_FOLDER_SERV;
		SERVICES[RESOURCE_GET_REL_PATH] = RESOURCE_GET_REL_PATH_SERV;
		SERVICES[RESOURCE_GET_ABS_PATH] = RESOURCE_GET_ABS_PATH_SERV;
		SERVICES[RESOURCE_GET_MEMBERS] = RESOURCE_GET_MEMBERS_SERV;
		SERVICES[RESOURCE_IS_FOLDER] = RESOURCE_IS_FOLDER_SERV;
		SERVICES[RESOURCE_IS_FILE] = RESOURCE_IS_FILE_SERV;
		SERVICES[RESOURCE_IS_LINKED] = RESOURCE_IS_LINKED_SERV;
		SERVICES[RESOURCE_IS_HIDDEN] = RESOURCE_IS_HIDDEN_SERV;
		SERVICES[RESOURCE_IS_DERIVED] = RESOURCE_IS_DERIVED_SERV;
		SERVICES[RESOURCE_EXISTS] = RESOURCE_EXISTS_SERV;
		
		//Initiate more services here ...
	}
		
	/**
	 * Dispatch a service to appropriate handler.
	 * <p>
	 * While declared as public, this method is not supposed to be used from 
	 * IDE plug-in or any Java code written by users. The only legitimate 
	 * call-sites are those Eclipse-related functions defined in Silver's 
	 * <code>ide</code> grammar.
	 *  
	 * @param type The type of service, 
	 * see {@link edu.umn.cs.melt.ide.util.ProjectUtil class documentation} for more details.
	 * @param io The IO token passed along by Silver language
	 * @param obj The object as the target of operation, should be an instance of 
	 * {@link org.eclipse.core.resources.IResource IResource}. More precisely, could be
	 * an IProject, an IFoler or an IFile.
	 * @param data The extra data to use for calling this service. Its structure is dependent
	 * on the service.
	 * @return The result returned by service handler, wrapped properly in Silver's type.
	 */
	public static Object dispatchService(int type, Object io, Object obj, Object data){
		//1) retrieve the service
		AbstractResourceService service = SERVICES[type];
		
		//2) execute the service
		Object result = null;
		
		switch(service.getTargetType()){
		case AbstractResourceService.RESOURCE:
			try {
				ResourceService resServ = (ResourceService) service;
				
				IResource res = (IResource) obj;
				result = resServ.execute(res, data);
			} catch (ClassCastException e) {
				Util.error("[Internal Error] " + e.getMessage());
				e.printStackTrace();
			}
			break;
		case AbstractResourceService.PROJECT:
			try {
				ProjectService projServ = (ProjectService) service;
						
				IProject proj = (IProject) obj;
				result = projServ.execute(proj, data);
			} catch (Exception e) {
				Util.error("[Internal Error] " + e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		
		//3) wrap the result appropriately, and return
		switch(service.getReturnType()){
		case AbstractResourceService.IO:
			return common.Util.io(io, result);
		case AbstractResourceService.IOV:
			return new core.Pioval(io, result);
		case AbstractResourceService.IOVMAYBE:
			return new core.Pioval(io, (result==null)?new core.Pnothing():new core.Pjust(result));
		}
		
		return null;
	}
	
	/**
	 * Helper method to convert Java array to Silver cons
	 *
	 * @see {@link #makeArray(Object)} for reversed operation
	 * @param args
	 * @return
	 */
	private static common.ConsCell makeCons(Object[] args){
		ConsCell result = ConsCell.nil;
		for(int i = args.length - 1; i >= 0; i --) {
			result = new ConsCell(args[i], result);
		}
		return result;
	}
	
	/**
	 * Helper method to convert StringCatter object to String
	 * @param str an instance of {@link common.StringCatter StringCatter}
	 * @return
	 */
	private static String makeString(Object str){
		return ((StringCatter) str).toString();
	}
	
	/**
	 * Helper method to convert Silver cons to Java array
	 * 
	 * @see {@link #makeCons(Object[])} for reversed operation
	 * @param obj
	 * @return
	 */
	private static Object[] makeArray(Object obj){
		ConsCell cc = (ConsCell) obj;
		
		List<Object> list = new ArrayList<Object>();
		while(!cc.nil()){
			list.add(cc.head());
			cc = cc.tail();
		}
		
		return list.toArray();
	}
}
