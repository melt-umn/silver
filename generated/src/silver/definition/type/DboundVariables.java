package silver.definition.type;

import java.util.*;

public class DboundVariables extends common.Decorator {

public static final DboundVariables singleton = new DboundVariables();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:type:boundVariables");
	}
}
