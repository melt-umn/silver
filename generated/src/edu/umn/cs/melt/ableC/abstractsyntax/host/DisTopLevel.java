package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DisTopLevel extends common.Decorator {

public static final DisTopLevel singleton = new DisTopLevel();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:isTopLevel");
	}
}
