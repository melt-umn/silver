package silver.definition.flow.env;

import java.util.*;

public class DflowEnv extends common.Decorator {

public static final DflowEnv singleton = new DflowEnv();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:flow:env:flowEnv");
	}
}
