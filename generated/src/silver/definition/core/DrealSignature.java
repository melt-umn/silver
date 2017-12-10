package silver.definition.core;

import java.util.*;

public class DrealSignature extends common.Decorator {

public static final DrealSignature singleton = new DrealSignature();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:realSignature");
	}
}
