package silver.modification.impide.spec;

import java.util.*;

public class Dpackage extends common.Decorator {

public static final Dpackage singleton = new Dpackage();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:impide:spec:package");
	}
}
