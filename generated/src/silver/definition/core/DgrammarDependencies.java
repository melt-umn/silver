package silver.definition.core;

import java.util.*;

public class DgrammarDependencies extends common.Decorator {

public static final DgrammarDependencies singleton = new DgrammarDependencies();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:grammarDependencies");
	}
}
