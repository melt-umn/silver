package silver.extension.monad;

import java.util.*;

public class DbindFn extends common.Decorator {

public static final DbindFn singleton = new DbindFn();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:monad:bindFn");
	}
}
