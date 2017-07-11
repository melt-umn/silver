grammar silver:translation:java:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local className :: String = "P" ++ id.name;

  top.setupInh := body.setupInh;
  top.initProd := "\t\t" ++ makeName(top.grammarName) ++ "." ++ className ++ ".initProductionAttributeDefinitions();\n";
  top.postInit := "\t\tcommon.Decorator.applyDecorators(" ++ fnnt ++ ".decorators, " ++ className ++ ".class);\n";

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);
  local fnnt :: String = makeNTClassName(namedSig.outputElement.typerep.typeName);

  top.genFiles := [pair(className ++ ".java",
s"""
package ${makeName(top.grammarName)};

// ${ns.pp}
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
		  then "throw new common.exceptions.SilverInternalError(\"Production " ++ fName ++ " erroneously claimed to forward\")"
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

	static void initProductionAttributeDefinitions() {
${body.translation}
	}

	public static final common.NodeFactory<${className}> factory = new Factory();

	public static final class Factory extends common.NodeFactory<${className}> {

		@Override
		public ${className} invoke(final Object[] children, final Object[] annotations) {
			return new ${className}(${implode(", ", unpackChildren(0, namedSig.inputElements) ++ unpackAnnotations(0, namedSig.namedInputElements))});
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


