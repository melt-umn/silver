package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DcontainingEnum extends common.Decorator {

public static final DcontainingEnum singleton = new DcontainingEnum();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:containingEnum");
	}
}
