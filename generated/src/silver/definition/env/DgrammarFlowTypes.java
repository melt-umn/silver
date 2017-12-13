package silver.definition.env;

import java.util.*;

public class DgrammarFlowTypes extends common.Decorator {

public static final DgrammarFlowTypes singleton = new DgrammarFlowTypes();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:env:grammarFlowTypes");
	}
}
