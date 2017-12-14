package silver.extension.bidirtransform;

import java.util.*;

public class DprodOutput extends common.Decorator {

public static final DprodOutput singleton = new DprodOutput();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:bidirtransform:prodOutput");
	}
}
