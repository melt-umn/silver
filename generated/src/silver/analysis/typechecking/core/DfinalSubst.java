package silver.analysis.typechecking.core;

import java.util.*;

public class DfinalSubst extends common.Decorator {

public static final DfinalSubst singleton = new DfinalSubst();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:analysis:typechecking:core:finalSubst");
	}
}
