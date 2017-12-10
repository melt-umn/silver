package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DcallVariadic extends common.Decorator {

public static final DcallVariadic singleton = new DcallVariadic();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:callVariadic");
	}
}
