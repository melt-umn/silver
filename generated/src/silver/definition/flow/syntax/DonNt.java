package silver.definition.flow.syntax;

import java.util.*;

public class DonNt extends common.Decorator {

public static final DonNt singleton = new DonNt();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:flow:syntax:onNt");
	}
}
