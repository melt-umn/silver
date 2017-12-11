package silver.extension.bidirtransform;

import java.util.*;

public class DinhProds extends common.Decorator {

public static final DinhProds singleton = new DinhProds();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:bidirtransform:inhProds");
	}
}
