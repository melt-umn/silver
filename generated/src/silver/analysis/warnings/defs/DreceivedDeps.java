package silver.analysis.warnings.defs;

import java.util.*;

public class DreceivedDeps extends common.Decorator {

public static final DreceivedDeps singleton = new DreceivedDeps();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:analysis:warnings:defs:receivedDeps");
	}
}
