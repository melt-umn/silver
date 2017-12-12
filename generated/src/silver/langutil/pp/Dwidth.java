package silver.langutil.pp;

import java.util.*;

public class Dwidth extends common.Decorator {

public static final Dwidth singleton = new Dwidth();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:langutil:pp:width");
	}
}
