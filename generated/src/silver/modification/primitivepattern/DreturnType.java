package silver.modification.primitivepattern;

import java.util.*;

public class DreturnType extends common.Decorator {

public static final DreturnType singleton = new DreturnType();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:primitivepattern:returnType");
	}
}
