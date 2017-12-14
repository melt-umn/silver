package silver.modification.primitivepattern;

import java.util.*;

public class DscrutineeType extends common.Decorator {

public static final DscrutineeType singleton = new DscrutineeType();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:primitivepattern:scrutineeType");
	}
}
