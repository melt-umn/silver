package edu.umn.cs.melt.ableC.abstractsyntax.host;

import java.util.*;

public class DgivenRefId extends common.Decorator {

public static final DgivenRefId singleton = new DgivenRefId();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:ableC:abstractsyntax:host:givenRefId");
	}
}
