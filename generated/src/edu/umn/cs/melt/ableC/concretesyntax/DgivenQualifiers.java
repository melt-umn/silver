package edu.umn.cs.melt.ableC.concretesyntax;

import java.util.*;

public class DgivenQualifiers extends common.Decorator {

public static final DgivenQualifiers singleton = new DgivenQualifiers();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:concretesyntax:givenQualifiers");
	}
}
