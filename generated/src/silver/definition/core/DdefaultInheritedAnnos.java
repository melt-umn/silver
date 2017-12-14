package silver.definition.core;

import java.util.*;

public class DdefaultInheritedAnnos extends common.Decorator {

public static final DdefaultInheritedAnnos singleton = new DdefaultInheritedAnnos();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:defaultInheritedAnnos");
	}
}
