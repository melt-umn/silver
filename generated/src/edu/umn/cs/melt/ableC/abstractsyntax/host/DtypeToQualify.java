package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DtypeToQualify extends common.Decorator {

public static final DtypeToQualify singleton = new DtypeToQualify();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:typeToQualify");
	}
}
