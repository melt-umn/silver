package silver.definition.concrete_syntax.ast;

import java.util.*;

public class DunivLayout extends common.Decorator {

public static final DunivLayout singleton = new DunivLayout();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:concrete_syntax:ast:univLayout");
	}
}
