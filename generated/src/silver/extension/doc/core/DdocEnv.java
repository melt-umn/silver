package silver.extension.doc.core;

import java.util.*;

public class DdocEnv extends common.Decorator {

public static final DdocEnv singleton = new DdocEnv();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:doc:core:docEnv");
	}
}
