package silver.definition.concrete_syntax.ast;

import java.util.*;

public class DprefixesForTerminals extends common.Decorator {

public static final DprefixesForTerminals singleton = new DprefixesForTerminals();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:ast:prefixesForTerminals");
	}
}
