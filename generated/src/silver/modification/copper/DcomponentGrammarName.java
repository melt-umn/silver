package silver.modification.copper;

import java.util.*;

public class DcomponentGrammarName extends common.Decorator {

public static final DcomponentGrammarName singleton = new DcomponentGrammarName();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:modification:copper:componentGrammarName");
	}
}
