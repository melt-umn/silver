package silver.definition.concrete_syntax.ast;

import java.util.*;

public class DterminalName extends common.Decorator {

public static final DterminalName singleton = new DterminalName();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:ast:terminalName");
	}
}
