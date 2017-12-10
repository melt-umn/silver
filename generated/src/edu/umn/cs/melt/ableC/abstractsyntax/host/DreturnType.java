package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DreturnType extends common.Decorator {

public static final DreturnType singleton = new DreturnType();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:returnType");
	}
}
