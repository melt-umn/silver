package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

import java.util.*;

public class DrhsToModify extends common.Decorator {

public static final DrhsToModify singleton = new DrhsToModify();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rhsToModify");
	}
}
