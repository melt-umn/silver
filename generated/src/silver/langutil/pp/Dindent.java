package silver.langutil.pp;

import java.util.*;

public class Dindent extends common.Decorator {

public static final Dindent singleton = new Dindent();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:langutil:pp:indent");
	}
}
