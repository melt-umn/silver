package silver.definition.env;

import java.util.*;

public class DproductionFlowGraphs extends common.Decorator {

public static final DproductionFlowGraphs singleton = new DproductionFlowGraphs();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:env:productionFlowGraphs");
	}
}
