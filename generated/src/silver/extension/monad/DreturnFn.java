package silver.extension.monad;

import java.util.*;

public class DreturnFn extends common.Decorator {

public static final DreturnFn singleton = new DreturnFn();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:monad:returnFn");
	}
}
