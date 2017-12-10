package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DtypeModifiersIn extends common.Decorator {

public static final DtypeModifiersIn singleton = new DtypeModifiersIn();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:typeModifiersIn");
	}
}
