package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DgivenAttributes extends common.Decorator {

public static final DgivenAttributes singleton = new DgivenAttributes();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:givenAttributes");
	}
}
