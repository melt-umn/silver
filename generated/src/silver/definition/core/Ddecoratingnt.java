package silver.definition.core;

import java.util.*;

public class Ddecoratingnt extends common.Decorator {

public static final Ddecoratingnt singleton = new Ddecoratingnt();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:core:decoratingnt");
	}
}
