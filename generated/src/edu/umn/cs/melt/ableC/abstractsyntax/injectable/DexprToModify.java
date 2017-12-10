package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

import java.util.*;

public class DexprToModify extends common.Decorator {

public static final DexprToModify singleton = new DexprToModify();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:injectable:exprToModify");
	}
}
