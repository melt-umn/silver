/*
 * Variables used:
 *   PKG_NAME
 *   LANG_NAME
 */
package silver.composed.idetest.imp.builders;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;

import silver.modification.impide.NIdeProperty;
import silver.modification.impide.PmakeIdeProperty;
import silver.composed.idetest.SILVERProperties;

import edu.umn.cs.melt.ide.silver.property.ProjectProperties;
import edu.umn.cs.melt.ide.silver.property.Property;
import edu.umn.cs.melt.ide.silver.env.SilverEnv;
import edu.umn.cs.melt.ide.silver.misc.ConsoleLoggingStream;

/**
 * A helper class used to invoke the method Analyze(NIdeProperty[]) in the language 
 * implementation jar.
 * <p>
 * Used for all-in-one plugin.
 */
public class SILVERAnalysisInvoker {
	
	private static SILVERAnalysisInvoker invoker;
	
	public static SILVERAnalysisInvoker getInstance(){
		if(invoker==null){
			invoker = new SILVERAnalysisInvoker();
		}
		
		common.Util.environment.put("SILVER_HOME", SilverEnv.getSilverHome().getAbsolutePath());
		
		return invoker;
	}
	
	/**
	 * 
	 * @param projectPath	The project path relative to workspace
	 * @param clstream		
	 * @param monitor
	 * @param handler
	 * @return
	 */
	public boolean analyze(
		String projectPath, 
		ConsoleLoggingStream clstream, 
		IProgressMonitor monitor,
		AnalysisHandler handler) {

		//Get properties
		ProjectProperties properties = SILVERProperties.getInstance().getByProject(projectPath);
		
		try {
			//Extract properties			
			Set<Entry<String, Property>> set = properties.getAll();
			NIdeProperty[] args = new NIdeProperty[set.size()];
			int i=0;
			for(Entry<String, Property> entry: set){
				args[i] = new PmakeIdeProperty(
					new common.StringCatter(entry.getKey()), 
					new common.StringCatter(entry.getValue().getSValue())
				);
				i++;
			}
			
			List<String> list = silver.composed.idetest.Analyze2.analyze(args);
			return handler.handle(list);
		} catch (Exception t) {
			t.printStackTrace();
			clstream.error("BUILD FAILED: failed to invoke analyzer of SILVER.)");
			return false;
		}
		
	}
	
	public static interface AnalysisHandler {
		/**
		 * Handle the message list returned by analyzer.
		 * 
		 * @param list the message list. 
		 * @return true if the build is considered successful; false otherwise
		 */
		boolean handle(List<String> list);
	}

}

