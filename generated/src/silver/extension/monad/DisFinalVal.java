package silver.extension.monad;

import java.util.*;

public class DisFinalVal extends common.Decorator {

public static final DisFinalVal singleton = new DisFinalVal();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:monad:isFinalVal");
	}
}
