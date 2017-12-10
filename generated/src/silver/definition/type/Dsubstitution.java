package silver.definition.type;

import java.util.*;

public class Dsubstitution extends common.Decorator {

public static final Dsubstitution singleton = new Dsubstitution();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:type:substitution");
	}
}
