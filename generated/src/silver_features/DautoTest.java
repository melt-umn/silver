package silver_features;

import java.util.*;

public class DautoTest extends common.Decorator {

public static final DautoTest singleton = new DautoTest();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver_features:autoTest");
	}
}
