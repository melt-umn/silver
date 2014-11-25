package edu.umn.cs.melt.ide.eclipse.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * A tracker of linked folders sitting at <b>first level</b> of project.
 * <p>
 * This class is mainly used to map between the absolute path (key) and the 
 * symbolic path (value), its implementation thus being underlied by a hash map.
 * <p>
 * The absolute path used as key is the physical address of linked folder in
 * the local file system. It must be always ended by a path separater.
 * <p> 
 * The symbolic path is the virtual path of target folder relative to the 
 * project root. It must be always ended by a path separater. Since this 
 * tracker for now only deals with linked folders directly under project,
 * a symbolic path used as the value here contains only the folder name and 
 * a trailing path separater.
 * <p>
 * To enforce the rule above, when methods such as {@link #add(String, String)}, 
 * {@link #delete(String)}, and {@link #get(String)} are called, helper method
 * {@link #getAbsPath(IResource)} and {@link #getRelPath(IResource)} should be
 * used to convert the target resource to appropriate key/value format.
 * <p>
 * Example:<br>
 * <pre> absolute path (key) : <code>/home/test/a/b/c/</code>
 * symbolic path (value) : <code>xyz/</code></pre>
 * If project path is <code>/app/eclipse/workspace/PROJ</code>, then what the above pair of 
 * paths means is that folder <code>/app/eclipse/workspace/PROJ/xyz/</code> is a symbolic 
 * path under project PROJ that actually links to physical address <code>/home/test/a/b/c/</code>.
 */
public class LinkedResourceTracker {

	private HashMap<String, String> map;
	
	public LinkedResourceTracker(IProject project){
		map = new HashMap<String, String>();
		
		try {
			//Populate the map with all linked folders
			IResource[] res = project.members();			
			for(IResource r:res){
				if(r.isLinked() && r instanceof IFolder){					
					add(getAbsPath(r),getRelPath(r));
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteByRelativePath(String relPath){
		Set<Map.Entry<String, String>> set = map.entrySet();
		List<String> keyList = null;
		
		for(Map.Entry<String, String> entry:set){
			if(relPath.equals(entry.getValue())){
				if(keyList==null){
					keyList = new ArrayList<String>();
				}
				keyList.add(entry.getKey());
			}
		}
		
		if(keyList!=null){
			for(String key:keyList){
				delete(key);
			}
		}
	}
	
	/**
	 * Get the absolute path of a resource to be used as the key for instance 
	 * methods of this class.
	 * 
	 * @see {@link #add(String, String)}, {@link #delete(String)}, {@link #get(String)}
	 * @param r an IResource instance
	 * @return
	 */
	public static String getAbsPath(IResource r){
		return r.getLocation().toString() + "/";
	}

	/**
	 * Canonize a given path to the format as required by instance methods of 
	 * this class.
	 * 
	 * @see {@link #add(String, String)}, {@link #delete(String)}, {@link #get(String)}
	 * @param r the raw path
	 * @return	the canonized path
	 */
	public static String getAbsPath(String path){
		if(path.endsWith("/")){
			return path;
		}
		
		return path + "/";
	}
	
	/**
	 * Get the relative path of a resource to be used as the value for instance 
	 * methods of this class.
	 * 
	 * @see {@link #add(String, String)}, {@link #delete(String)}, {@link #get(String)}
	 * @param r an IResource instance
	 * @return
	 */
	public static String getRelPath(IResource r){
		return r.getProjectRelativePath().toString() + "/";
	}
	
	/**
	 * Add a new absolute/symbolic path pair to the tracker.
	 * @param absPath
	 * @param symPath
	 */
	public void add(String absPath, String symPath) {		
		map.put(absPath, symPath);
	}

	/**
	 * Delete an absolute/symbolic path pair from the tracker.
	 * @param absPath
	 */
	public void delete(String absPath) {
		map.remove(absPath);
	}
	
	/**
	 * Check whether an absolute/symbolic path pair is tracked, and return
	 * symbolic path if it is.
	 * 
	 * @param absPath
	 * @return the symbolic path corresponding to the given absolute path,
	 * or null if no such absolute path is tracked.
	 */	
	public String get(String absPath){
		return map.get(absPath);
	}
	
}
