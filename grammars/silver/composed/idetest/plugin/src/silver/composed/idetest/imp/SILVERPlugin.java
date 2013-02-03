/*
 * Variables used:
 *   PKG_NAME
 *   LANG_NAME
 */
package silver.composed.idetest.imp;

import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

import edu.umn.cs.melt.ide.silver.env.SilverEnv;

public class SILVERPlugin extends PluginBase {

    public static final String kPluginID= "SILVER_IDE";
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
    	sPlugin= this;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        
        SilverEnv.initialize(context.getBundle());
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
