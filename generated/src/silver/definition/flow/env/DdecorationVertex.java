package silver.definition.flow.env;

import java.util.*;

public class DdecorationVertex extends common.Decorator {

public static final DdecorationVertex singleton = new DdecorationVertex();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:flow:env:decorationVertex");
	}
}
