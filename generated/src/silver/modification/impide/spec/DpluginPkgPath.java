package silver.modification.impide.spec;

import java.util.*;

public class DpluginPkgPath extends common.Decorator {

public static final DpluginPkgPath singleton = new DpluginPkgPath();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:impide:spec:pluginPkgPath");
	}
}
