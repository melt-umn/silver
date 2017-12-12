package silver.modification.primitivepattern;

import java.util.*;

public class DmatchingAgainst extends common.Decorator {

public static final DmatchingAgainst singleton = new DmatchingAgainst();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:primitivepattern:matchingAgainst");
	}
}
