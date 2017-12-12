package silver.definition.env;

import java.util.*;

public class Dconfig extends common.Decorator {

public static final Dconfig singleton = new Dconfig();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:env:config");
	}
}
