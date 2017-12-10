package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

import java.util.*;

public class Dname_i extends common.Decorator {

public static final Dname_i singleton = new Dname_i();

	public void decorate(Class production) {
		decorateAutoCopy(production, "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:name_i");
	}
}
