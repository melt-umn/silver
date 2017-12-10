package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

import java.util.*;

public class DlhsToModify extends common.Decorator {

public static final DlhsToModify singleton = new DlhsToModify();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lhsToModify");
	}
}
