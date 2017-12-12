package silver.extension.bidirtransform;

import java.util.*;

public class DabsGroup extends common.Decorator {

public static final DabsGroup singleton = new DabsGroup();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:bidirtransform:absGroup");
	}
}
