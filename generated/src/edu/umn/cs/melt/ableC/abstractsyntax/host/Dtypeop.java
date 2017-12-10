package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class Dtypeop extends common.Decorator {

public static final Dtypeop singleton = new Dtypeop();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:typeop");
	}
}
