package silver.extension.bidirtransform;

import java.util.*;

public class DcncGroup extends common.Decorator {

public static final DcncGroup singleton = new DcncGroup();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:bidirtransform:cncGroup");
	}
}
