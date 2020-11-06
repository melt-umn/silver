grammar silver:translation:java:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local className :: String = "P" ++ id.name;

  top.setupInh := body.setupInh;
  top.initProd := s"\t\t${makeName(top.grammarName)}.${className}.initProductionAttributeDefinitions();\n";
  top.postInit := s"\t\tcommon.Decorator.applyDecorators(${fnnt}.decorators, ${className}.class);\n";

  top.initWeaving := s"\tpublic static int ${localVar} = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);
  local ntName :: String = namedSig.outputElement.typerep.typeName;
  local fnnt :: String = makeNTClassName(ntName);

  top.genFiles := [pair(className ++ ".java", s"""
package ${makeName(top.grammarName)};

// ${ns.unparse}
public final class ${className} extends ${fnnt} {

${makeIndexDcls(0, namedSig.inputElements)}

	public static final Class<?> childTypes[] = {${implode(",", map(makeChildTypes, namedSig.inputElements))}};

	public static final int num_local_attrs = Init.${localVar};
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[${fnnt}.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[${fnnt}.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[${toString(length(namedSig.inputElements))}][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
${implode("", map((.childStaticElem), namedSig.inputElements))}
	}

	public ${className}(${namedSig.javaSignature}) {
		super(${implode(", ", map((.annoRefElem), namedSig.namedInputElements))});
${implode("", map(makeChildAssign, namedSig.inputElements))}
	}

${implode("", map((.childDeclElem), namedSig.inputElements))}

	@Override
	public Object getChild(final int index) {
		switch(index) {
${implode("", map(makeChildAccessCase, namedSig.inputElements))}
			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
${implode("", map(makeChildAccessCaseLazy, namedSig.inputElements))}
			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return ${toString(length(namedSig.inputElements))};
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public boolean hasForward() {
		return ${(if null(body.uniqueSignificantExpression) then "false" else "true")};
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		${if null(body.uniqueSignificantExpression) 
		  then s"throw new common.exceptions.SilverInternalError(\"Production ${fName} erroneously claimed to forward\")"
		  else "return " ++ head(body.uniqueSignificantExpression).translation};
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "${fName}";
	}
	
	@Override
	public final common.BaseTypeRep getType() {
${makeTyVarDecls(2, namedSig.typerep.freeVariables)}
		
		${implode("\n\t\t", map(makeChildUnify(fName, _), namedSig.inputElements))}
		
		return ${namedSig.outputElement.typerep.transFreshTypeRep};
	}

	static void initProductionAttributeDefinitions() {
${body.translation}
	}

	public static ${className} reify(
		final common.TypeRep resultType,
		final core.reflect.NAST[] childASTs,
		final String[] annotationNames,
		final core.reflect.NAST[] annotationASTs) {
		assert annotationNames.length == annotationASTs.length;
${makeAnnoIndexDcls(0, namedSig.namedInputElements)}
${makeTyVarDecls(2, namedSig.typerep.freeVariables)}
		
		common.TypeRep givenType = ${namedSig.outputElement.typerep.transFreshTypeRep};
		if (!common.TypeRep.unify(resultType, givenType)) {
			throw new common.exceptions.SilverError("reify is constructing " + resultType.toString() + ", but found " + givenType.toString() + " production ${fName} AST.");
		}
		
		if (childASTs.length != ${toString(length(namedSig.inputElements))}) {
			throw new common.exceptions.SilverError("Production ${fName} expected ${toString(length(namedSig.inputElements))} child(ren), but got " + childASTs.length + ".");
		}
		
		String[] expectedAnnotationNames = new String[] {${implode(", ", map((.annoNameElem), annotationsForNonterminal(namedSig.outputElement.typerep, top.env)))}};
		if (!java.util.Arrays.equals(annotationNames, expectedAnnotationNames)) {
			throw new common.exceptions.SilverError("Production ${fName} expected " + common.Util.namesToString(expectedAnnotationNames, "no") + " annotation(s), but got " + common.Util.namesToString(annotationNames, "none") + ".");
		}
		
		${implode("\n\t\t", map(makeChildReify(fName, length(namedSig.inputElements), _), namedSig.inputElements))}
		${implode("\n\t\t", map(makeAnnoReify(fName, _), namedSig.namedInputElements))}
		
		return new ${className}(${namedSig.refInvokeTrans});
	}

	public static final common.NodeFactory<${fnnt}> factory = new Factory();

	public static final class Factory extends common.NodeFactory<${fnnt}> {
		@Override
		public final ${fnnt} invoke(final Object[] children, final Object[] annotations) {
			return new ${className}(${implode(", ", unpackChildren(0, namedSig.inputElements) ++ unpackAnnotations(0, namedSig.namedInputElements))});
		}
		
		@Override
		public final common.FunctionTypeRep getType() {
${makeTyVarDecls(3, namedSig.typerep.freeVariables)}
			return ${namedSig.typerep.transFreshTypeRep};
		}
		
		@Override
		public final String toString() {
			return "${top.grammarName}:${id.name}";
		}
	};

}
""")];

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
    if id.name == "main"
    then [err(top.location, "main should be a function!")]
    else [];
}
