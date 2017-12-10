package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class Dop extends common.Decorator {

public static final Dop singleton = new Dop();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:op");
	}
}
