package edu.umn.cs.melt.ableC.concretesyntax;

import java.util.*;

public class DgivenType extends common.Decorator {

public static final DgivenType singleton = new DgivenType();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:concretesyntax:givenType");
	}
}
