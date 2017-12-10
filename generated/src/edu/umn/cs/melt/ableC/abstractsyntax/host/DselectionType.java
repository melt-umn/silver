package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DselectionType extends common.Decorator {

public static final DselectionType singleton = new DselectionType();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:selectionType");
	}
}
