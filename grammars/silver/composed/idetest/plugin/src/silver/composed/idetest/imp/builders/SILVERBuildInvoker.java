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

import silver.modification.impide.NIdeMessage;
import silver.modification.impide.NIdeProperty;
import silver.modification.impide.PmakeIdeProperty;
import silver.composed.idetest.SILVERProperties;

import edu.umn.cs.melt.ide.silver.property.ProjectProperties;
import edu.umn.cs.melt.ide.silver.property.Property;
import edu.umn.cs.melt.ide.silver.env.SilverEnv;
import edu.umn.cs.melt.ide.silver.misc.ConsoleLoggingStream;

/**
 * A helper class used to invoke the method build(NIdeProperty[]) in the language 
 * implementation jar.
 * <p>
 * Used for all-in-one plugin.
 */
public class SILVERBuildInvoker {
	
	private static SILVERBuildInvoker invoker;
	
	public static SILVERBuildInvoker getInstance(){
		if(invoker==null){
			invoker = new SILVERBuildInvoker();
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
	public boolean build(
		String projectPath, 
		ConsoleLoggingStream clstream, 
		IProgressMonitor monitor,
		MessageHandler handler) {

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
			
			List<NIdeMessage> list = silver.composed.idetest.Build.build(args);
			return handler.handle(list);
		} catch (Exception t) {
			t.printStackTrace();
			clstream.error("BUILD FAILED: failed to invoke builder of SILVER.)");
			return false;
		}
		
	}

}

