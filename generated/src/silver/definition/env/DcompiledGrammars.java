package silver.definition.env;

import java.util.*;

public class DcompiledGrammars extends common.Decorator {

public static final DcompiledGrammars singleton = new DcompiledGrammars();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:env:compiledGrammars");
	}
}
