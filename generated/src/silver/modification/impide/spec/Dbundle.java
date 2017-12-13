package silver.modification.impide.spec;

import java.util.*;

public class Dbundle extends common.Decorator {

public static final Dbundle singleton = new Dbundle();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:impide:spec:bundle");
	}
}
