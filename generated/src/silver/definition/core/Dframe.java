package silver.definition.core;

import java.util.*;

public class Dframe extends common.Decorator {

public static final Dframe singleton = new Dframe();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:frame");
	}
}
