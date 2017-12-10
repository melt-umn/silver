package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DcallExpr extends common.Decorator {

public static final DcallExpr singleton = new DcallExpr();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:callExpr");
	}
}
