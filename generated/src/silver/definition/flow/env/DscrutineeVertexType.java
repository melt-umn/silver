package silver.definition.flow.env;

import java.util.*;

public class DscrutineeVertexType extends common.Decorator {

public static final DscrutineeVertexType singleton = new DscrutineeVertexType();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:definition:flow:env:scrutineeVertexType");
	}
}
