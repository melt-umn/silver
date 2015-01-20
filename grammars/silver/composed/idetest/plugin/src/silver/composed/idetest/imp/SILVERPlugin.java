/*
 * Variables used:
 *   PKG_NAME
 *   LANG_NAME
 */
package silver.composed.idetest.imp;

import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

import edu.umn.cs.melt.ide.silver.env.SilverEnv;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.runtime.CoreException;

import edu.umn.cs.melt.ide.util.ISilverTypeWrapper;
import edu.umn.cs.melt.ide.util.ProjectUtil;
import edu.umn.cs.melt.ide.util.Util;
import edu.umn.cs.melt.ide.util.Util.IAntRunnable;
import edu.umn.cs.melt.ide.util.cst.Locator;
import edu.umn.cs.melt.ide.util.cst.DefinitionFinder;

public class SILVERPlugin extends PluginBase {

    public static final String kPluginID = "SILVER_IDE";
    public static final String kLanguageID = "SILVER";
    
    /**
     * The unique instance of this plugin class
     */
    protected static SILVERPlugin sPlugin;

    public static SILVERPlugin getInstance() {
    	if (sPlugin == null)
    		new SILVERPlugin();
        return sPlugin;
    }

    public SILVERPlugin() {
    	super();
    	sPlugin = this;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);

			silver.composed.idetest.Init.initAllStatics();
			silver.composed.idetest.Init.init();
			silver.composed.idetest.Init.postInit();
        
        SilverEnv.initialize(context.getBundle());
		common.Util.environment.put("SILVER_HOME", SilverEnv.getSilverHome().getAbsolutePath());
		common.Util.environment.put("SILVER_GEN", SilverEnv.getGeneratedFolder().getAbsolutePath());

		edu.umn.cs.melt.ide.impl.SVRegistry.register(new silver.composed.idetest.SVIdeInterface());

        //Set up ant runner
        Util.setAntRunnable(new IAntRunnable(){

			@Override
			public void ant(String buildFile, String arguments, String target) {
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
			}
        	
        });

        ProjectUtil.setSilverTypeWrapper(new ISilverTypeWrapper(){
			@Override
			public Object wrapInIOVal(Object io, Object arg) {
				return new core.Pioval(io, arg);
			}

			@Override
			public Object wrapInMaybe(Object arg) {
				if(arg!=null){
					return new core.Pjust(arg);
				} else {
					return new core.Pnothing(); 
				}
			}
        });
    
        //Set up definition locator
        Locator.setDefinitionFinder(new DefinitionFinder());
    }

    @Override
    public void shutdown() {        
    }

    @Override
    public String getID() {
    	return kPluginID;
    }  

    @Override
    public String getLanguageID() {
        return kLanguageID;
    }
}
