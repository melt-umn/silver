package silver.extension.convenience;

import java.util.*;

public class Dlhsdcl extends common.Decorator {

public static final Dlhsdcl singleton = new Dlhsdcl();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:convenience:lhsdcl");
	}
}
