package silver.definition.core;

import java.util.*;

public class DgrammarName extends common.Decorator {

public static final DgrammarName singleton = new DgrammarName();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:grammarName");
	}
}
