package silver.definition.concrete_syntax.ast;

import java.util.*;

public class DcstNTProds extends common.Decorator {

public static final DcstNTProds singleton = new DcstNTProds();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:ast:cstNTProds");
	}
}
