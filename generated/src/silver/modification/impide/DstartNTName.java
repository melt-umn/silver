package silver.modification.impide;

import java.util.*;

public class DstartNTName extends common.Decorator {

public static final DstartNTName singleton = new DstartNTName();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:impide:startNTName");
	}
}
