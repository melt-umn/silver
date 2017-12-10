package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DisTypedef extends common.Decorator {

public static final DisTypedef singleton = new DisTypedef();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:isTypedef");
	}
}
