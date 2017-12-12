package silver.modification.impide.spec;

import java.util.*;

public class DvisibleName extends common.Decorator {

public static final DvisibleName singleton = new DvisibleName();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:impide:spec:visibleName");
	}
}
