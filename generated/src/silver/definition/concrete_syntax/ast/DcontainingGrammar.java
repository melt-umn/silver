package silver.definition.concrete_syntax.ast;

import java.util.*;

public class DcontainingGrammar extends common.Decorator {

public static final DcontainingGrammar singleton = new DcontainingGrammar();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:ast:containingGrammar");
	}
}
