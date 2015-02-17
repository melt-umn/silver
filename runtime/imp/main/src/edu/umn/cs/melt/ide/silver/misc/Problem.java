package edu.umn.cs.melt.ide.silver.misc;

import java.io.File;

import ide.NIdeMessage;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import common.DecoratedNode;
import common.Lazy;
import common.StringCatter;
import common.TopNode;
import core.NLocation;

/**
 * A wrapper of problem message returned by compiler.
 * <p>
 * There are two types of problems, based on its associativity with either file
 * or project. For a file problem, the user should provide project, file, and 
 * location information, in addition to the severity and message. 
 * <p>
 * For a project problem, the user only needs to provide severity and message.
 */
public class Problem {
	
	/**
	 * If an integer value is unknown, it has this value, which shouldn't be 
	 * interpreted literally. 
	 */
	public static final int UNKNOWN = -1;
	public static final int INFO = IMarker.SEVERITY_INFO;
	public static final int WARNING = IMarker.SEVERITY_WARNING;
	public static final int ERROR = IMarker.SEVERITY_ERROR;

	private IPath file;
	private int column;
	private int line;
	private int startInd;
	private int endInd;
	private String message;
	private int level;
	private boolean projMsg = false;
	
	/**
	 * Create a file-related problem from given argument set.
	 * 
	 * @param project	the project under which the resource declName+fileName 
	 * 	is found.
	 * @param filePath	the file's path in format "a/b/c", relative to project root
	 * @param fileName	the file's full name, including extension. Note the 
	 * resource can be located under project root using {filePath + "/" + fileName}
	 * @param line
	 * @param column
	 * @param startInd	starting index relative to start of file. Use {@link #UNKNOWN} 
	 * if not available.
	 * @param endInd	ending index relative to start of file. Use {@link #UNKNOWN} 
	 * if not available.
	 * @param severity	such as {@link #ERROR} and {@link #WARNING}
	 * @param message	a human-oriented message that will be shown in IDE.
	 * @return
	 */
	private static Problem createFileProblem(
		IPath file, 
		int line, int column, int startInd, int endInd, 
		int severity, String message){
		
		return new Problem(file, column, line, message, severity, startInd, endInd, false);
	}
	
	/**
	 * Create a project-level problem.
	 * <p>
	 * A project problem is not associated with any particular file.
	 * 
	 * @param severity	such as {@link #ERROR} and {@link #WARNING}
	 * @param message	a human-oriented message that will be shown in IDE.
	 * @return
	 */
	private static Problem createProjectProblem(int severity, String message){
		return new Problem(null, UNKNOWN, UNKNOWN, message, severity, UNKNOWN, UNKNOWN, true);
	}
	
	/**
	 * The most primitive constructor.
	 */
	private Problem(IPath file, int column, int line, String message, int level, 
		int startInd, int endInd, boolean projMsg) {
		this.file = file;
		this.column = column;
		this.line = line;
		this.message = message;
		this.level = level;
		this.startInd = startInd;
		this.endInd = endInd;
		this.projMsg = projMsg;
	}
	
	public boolean buildBlocker() {
		return level == ERROR;
	}
	
	public static Problem extractProblem(IProject project, NIdeMessage ideMsg) {
		//Extract values
    	DecoratedNode ideMsgDecNode = ideMsg.decorate(TopNode.singleton, (Lazy[])null);
    	
    	StringCatter msg = (StringCatter)ideMsgDecNode.synthesized(ide.Init.ide_msg__ON__ide_IdeMessage);
    	Integer severity = (Integer)ideMsgDecNode.synthesized(ide.Init.ide_severity__ON__ide_IdeMessage);
    	Boolean isProjMsg = (Boolean)ideMsgDecNode.synthesized(ide.Init.ide_systemLevel__ON__ide_IdeMessage);
    	
    	if(isProjMsg)
    		return Problem.createProjectProblem(severity, msg.toString());
    	
    	StringCatter resPath = (StringCatter)ideMsgDecNode.synthesized(ide.Init.ide_resPath__ON__ide_IdeMessage);
    	
    	NLocation loc = (NLocation)ideMsgDecNode.synthesized(ide.Init.ide_loc__ON__ide_IdeMessage);
    	DecoratedNode locDecNode = loc.decorate(TopNode.singleton, (Lazy[])null);
    	
    	StringCatter fileName = (StringCatter)locDecNode.synthesized(core.Init.core_filename__ON__core_Location);
    	
    	Integer lineNo = (Integer)locDecNode.synthesized(core.Init.core_line__ON__core_Location);
    	Integer columnNo = (Integer)locDecNode.synthesized(core.Init.core_column__ON__core_Location);
    	Integer startInd = (Integer)locDecNode.synthesized(core.Init.core_index__ON__core_Location);
    	Integer endInd = (Integer)locDecNode.synthesized(core.Init.core_endIndex__ON__core_Location);
    	
    	boolean isLinked = (Boolean)ideMsgDecNode.synthesized(ide.Init.ide_isLinked__ON__ide_IdeMessage);

    	if(isLinked) {
        	// TODO: remove linked stuff
        	return Problem.createProjectProblem(severity, fileName.toString() + ": " + msg.toString());
    	} 

    	//Assemble a problem
    	//IProject project, String pathRelativeToProjectRoot, String fileName, 
		//int line, int column, int startInd, int endInd, int severity, String message, 
    	return Problem.createFileProblem(
    		new Path(resPath.toString()).addTrailingSeparator().append(fileName.toString()),
    		lineNo, columnNo, startInd, endInd, severity, msg.toString());
    	
    }
	
	public void createMarker(IProject project, String markerType) throws CoreException {
		if(projMsg) {
			IMarker marker = project.createMarker(markerType);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, level);
		} else {
			IFile f;
			// If we get an absolute path to the project, strip it off.
			if(project.getLocation().isPrefixOf(file)) {
				f = project.getFile(file.makeRelativeTo(project.getLocation()));
			} else {
				f = project.getFile(file);				
			}
			
			if(!f.exists()) {
				IMarker marker = project.createMarker(markerType);
				marker.setAttribute(IMarker.MESSAGE, "Unknown file with error message: " + f.getFullPath().toString() + " : " + message);
				marker.setAttribute(IMarker.SEVERITY, level);
				return;
			}

			//Reuse ErrorMarkerID for whatever kind of problem
			IMarker marker = f.createMarker(markerType);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.LINE_NUMBER, line);
			int index = startInd;
			if(index != Problem.UNKNOWN) {
				marker.setAttribute(IMarker.CHAR_START, index);
			}
			index = endInd;
			if(index != Problem.UNKNOWN) {
				marker.setAttribute(IMarker.CHAR_END, index);
			}
			marker.setAttribute(IMarker.SEVERITY, level);

		}
	}

}
