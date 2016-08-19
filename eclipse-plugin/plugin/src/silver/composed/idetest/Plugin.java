
package silver.composed.idetest;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleActivator;

import edu.umn.cs.melt.ide.impl.SVRegistry;

public class Plugin implements BundleActivator {

    public void start(BundleContext context) throws Exception {

        Init.initAllStatics();
        Init.init();
        Init.postInit();

        SVRegistry.register(new SVIdeInterface());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
