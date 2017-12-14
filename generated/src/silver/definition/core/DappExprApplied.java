package silver.definition.core;

import java.util.*;

public class DappExprApplied extends common.Decorator {

public static final DappExprApplied singleton = new DappExprApplied();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:appExprApplied");
	}
}
