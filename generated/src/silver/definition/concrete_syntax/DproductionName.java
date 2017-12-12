package silver.definition.concrete_syntax;

import java.util.*;

public class DproductionName extends common.Decorator {

public static final DproductionName singleton = new DproductionName();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:productionName");
	}
}
