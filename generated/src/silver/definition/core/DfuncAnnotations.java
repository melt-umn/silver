package silver.definition.core;

import java.util.*;

public class DfuncAnnotations extends common.Decorator {

public static final DfuncAnnotations singleton = new DfuncAnnotations();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:funcAnnotations");
	}
}
