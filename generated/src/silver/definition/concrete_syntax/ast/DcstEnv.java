package silver.definition.concrete_syntax.ast;

import java.util.*;

public class DcstEnv extends common.Decorator {

public static final DcstEnv singleton = new DcstEnv();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:ast:cstEnv");
	}
}
