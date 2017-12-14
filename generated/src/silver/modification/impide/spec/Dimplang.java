package silver.modification.impide.spec;

import java.util.*;

public class Dimplang extends common.Decorator {

public static final Dimplang singleton = new Dimplang();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:impide:spec:implang");
	}
}
