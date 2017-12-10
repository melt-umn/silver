package edu.umn.cs.melt.ableC.abstractsyntax.env;

import java.util.*;

public class Denv extends common.Decorator {

public static final Denv singleton = new Denv();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:env:env");
	}
}
