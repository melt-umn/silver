package silver.definition.core;

import java.util.*;

public class DglobalImports extends common.Decorator {

public static final DglobalImports singleton = new DglobalImports();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:globalImports");
	}
}
