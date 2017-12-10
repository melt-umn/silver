package silver.extension.bidirtransform;

import java.util.*;

public class DgrantedDefs extends common.Decorator {

public static final DgrantedDefs singleton = new DgrantedDefs();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:bidirtransform:grantedDefs");
	}
}
