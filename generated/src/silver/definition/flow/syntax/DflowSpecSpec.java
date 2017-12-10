package silver.definition.flow.syntax;

import java.util.*;

public class DflowSpecSpec extends common.Decorator {

public static final DflowSpecSpec singleton = new DflowSpecSpec();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:flow:syntax:flowSpecSpec");
	}
}
