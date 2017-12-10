package edu.umn.cs.melt.ableC.concretesyntax;

import java.util.*;

public class DgivenDesignator extends common.Decorator {

public static final DgivenDesignator singleton = new DgivenDesignator();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:concretesyntax:givenDesignator");
	}
}
