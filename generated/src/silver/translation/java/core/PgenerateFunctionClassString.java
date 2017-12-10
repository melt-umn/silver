
package silver.translation.java.core;

public final class PgenerateFunctionClassString extends common.FunctionNode {

	public static final int i_whatGrammar = 0;
	public static final int i_whatName = 1;
	public static final int i_whatSig = 2;
	public static final int i_whatResult = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,silver.definition.env.NNamedSignature.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_generateFunctionClassString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_whatSig] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PgenerateFunctionClassString(final Object c_whatGrammar, final Object c_whatName, final Object c_whatSig, final Object c_whatResult) {
		this.child_whatGrammar = c_whatGrammar;
		this.child_whatName = c_whatName;
		this.child_whatSig = c_whatSig;
		this.child_whatResult = c_whatResult;

	}

	private Object child_whatGrammar;
	public final common.StringCatter getChild_whatGrammar() {
		return (common.StringCatter) (child_whatGrammar = common.Util.demand(child_whatGrammar));
	}

	private Object child_whatName;
	public final common.StringCatter getChild_whatName() {
		return (common.StringCatter) (child_whatName = common.Util.demand(child_whatName));
	}

	private Object child_whatSig;
	public final silver.definition.env.NNamedSignature getChild_whatSig() {
		return (silver.definition.env.NNamedSignature) (child_whatSig = common.Util.demand(child_whatSig));
	}

	private Object child_whatResult;
	public final common.StringCatter getChild_whatResult() {
		return (common.StringCatter) (child_whatResult = common.Util.demand(child_whatResult));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_whatGrammar: return getChild_whatGrammar();
			case i_whatName: return getChild_whatName();
			case i_whatSig: return getChild_whatSig();
			case i_whatResult: return getChild_whatResult();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_whatGrammar: return child_whatGrammar;
			case i_whatName: return child_whatName;
			case i_whatSig: return child_whatSig;
			case i_whatResult: return child_whatResult;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:translation:java:core:generateFunctionClassString";
	}

	public static common.StringCatter invoke(final Object c_whatGrammar, final Object c_whatName, final Object c_whatSig, final Object c_whatResult) {
		try {
		final common.DecoratedNode context = new PgenerateFunctionClassString(c_whatGrammar, c_whatName, c_whatSig, c_whatResult).decorate();
		//"\npackage " ++ makeName(whatGrammar) ++ ";\n\npublic final class " ++ className ++ " extends common.FunctionNode {\n\n" ++ makeIndexDcls(0, whatSig.inputElements) ++ "\n\n\tpublic static final Class<?> childTypes[] = { " ++ implode(",", map(makeChildTypes, whatSig.inputElements)) ++ " };\n\n\tpublic static final int num_local_attrs = Init." ++ localVar ++ ";\n\tpublic static final String[] occurs_local = new String[num_local_attrs];\n\n\tpublic static final common.Lazy[][] childInheritedAttributes = new common.Lazy[" ++ toString(length(whatSig.inputElements)) ++ "][];\n\n\tpublic static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];\n\tpublic static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];\n\n\tstatic{\n" ++ implode("", map((.childStaticElem), whatSig.inputElements)) ++ "\n\t}\n\n\tpublic " ++ className ++ "(" ++ whatSig.javaSignature ++ ") {\n" ++ implode("", map(makeChildAssign, whatSig.inputElements)) ++ "\n\t}\n\n" ++ implode("", map((.childDeclElem), whatSig.inputElements)) ++ "\n\n\t@Override\n\tpublic Object getChild(final int index) {\n\t\tswitch(index) {\n" ++ implode("", map(makeChildAccessCase, whatSig.inputElements)) ++ "\n\t\t\tdefault: return null;\n\t\t}\n\t}\n\n\t@Override\n\tpublic Object getChildLazy(final int index) {\n\t\tswitch(index) {\n" ++ implode("", map(makeChildAccessCaseLazy, whatSig.inputElements)) ++ "\n\t\t\tdefault: return null;\n\t\t}\n\t}\n\n\t@Override\n\tpublic final int getNumberOfChildren() {\n\t\treturn " ++ toString(length(whatSig.inputElements)) ++ ";\n\t}\n\n\t@Override\n\tpublic common.Lazy[] getLocalInheritedAttributes(final int key) {\n\t\treturn localInheritedAttributes[key];\n\t}\n\n\t@Override\n\tpublic common.Lazy[] getChildInheritedAttributes(final int key) {\n\t\treturn childInheritedAttributes[key];\n\t}\n\n\t@Override\n\tpublic common.Lazy getLocal(final int key) {\n\t\treturn localAttributes[key];\n\t}\n\n\t@Override\n\tpublic final int getNumberOfLocalAttrs() {\n\t\treturn num_local_attrs;\n\t}\n\n\t@Override\n\tpublic final String getNameOfLocalAttr(final int index) {\n\t\treturn occurs_local[index];\n\t}\n\n\t@Override\n\tpublic String getName() {\n\t\treturn \"" ++ whatSig.fullName ++ "\";\n\t}\n\n\tpublic static " ++ whatSig.outputElement.typerep.transType ++ " invoke(" ++ whatSig.javaSignature ++ ") {\n\t\ttry {\n\t\t" ++ whatResult ++ "\n\t\t} catch(Throwable t) {\n\t\t\tthrow new common.exceptions.TraceException(\"Error while evaluating function " ++ whatSig.fullName ++ "\", t);\n\t\t}\n\t}\n\n\tpublic static final common.NodeFactory<" ++ whatSig.outputElement.typerep.transType ++ "> factory = new Factory();\n\n\tpublic static final class Factory extends common.NodeFactory<" ++ whatSig.outputElement.typerep.transType ++ "> {\n\t\t@Override\n\t\tpublic " ++ whatSig.outputElement.typerep.transType ++ " invoke(final Object[] children, final Object[] namedNotApplicable) {\n\t\t\treturn " ++ className ++ ".invoke(" ++ implode(", ", unpackChildren(0, whatSig.inputElements)) ++ ");\n\t\t}\n\t};\n}"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\npackage ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeName.invoke(context.childAsIsLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatGrammar))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\npublic final class ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.className__ON__silver_translation_java_core_generateFunctionClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" extends common.FunctionNode {\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeIndexDcls.invoke(Integer.valueOf((int)0), context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n\tpublic static final Class<?> childTypes[] = { ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.translation.java.core.PmakeChildTypes.factory, context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" };\n\n\tpublic static final int num_local_attrs = Init.")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.localVar__ON__silver_translation_java_core_generateFunctionClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\tpublic static final String[] occurs_local = new String[num_local_attrs];\n\n\tpublic static final common.Lazy[][] childInheritedAttributes = new common.Lazy[")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("][];\n\n\tpublic static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];\n\tpublic static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];\n\n\tstatic{\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.translation.java.core.Init.silver_translation_java_core_childStaticElem__ON__silver_definition_env_NamedSignatureElement, context), context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t}\n\n\tpublic ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.className__ON__silver_translation_java_core_generateFunctionClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.translation.java.core.Init.silver_translation_java_core_javaSignature__ON__silver_definition_env_NamedSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.translation.java.core.PmakeChildAssign.factory, context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t}\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.translation.java.core.Init.silver_translation_java_core_childDeclElem__ON__silver_definition_env_NamedSignatureElement, context), context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n\t@Override\n\tpublic Object getChild(final int index) {\n\t\tswitch(index) {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.translation.java.core.PmakeChildAccessCase.factory, context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t\t\tdefault: return null;\n\t\t}\n\t}\n\n\t@Override\n\tpublic Object getChildLazy(final int index) {\n\t\tswitch(index) {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.translation.java.core.PmakeChildAccessCaseLazy.factory, context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t\t\tdefault: return null;\n\t\t}\n\t}\n\n\t@Override\n\tpublic final int getNumberOfChildren() {\n\t\treturn ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\t}\n\n\t@Override\n\tpublic common.Lazy[] getLocalInheritedAttributes(final int key) {\n\t\treturn localInheritedAttributes[key];\n\t}\n\n\t@Override\n\tpublic common.Lazy[] getChildInheritedAttributes(final int key) {\n\t\treturn childInheritedAttributes[key];\n\t}\n\n\t@Override\n\tpublic common.Lazy getLocal(final int key) {\n\t\treturn localAttributes[key];\n\t}\n\n\t@Override\n\tpublic final int getNumberOfLocalAttrs() {\n\t\treturn num_local_attrs;\n\t}\n\n\t@Override\n\tpublic final String getNameOfLocalAttr(final int index) {\n\t\treturn occurs_local[index];\n\t}\n\n\t@Override\n\tpublic String getName() {\n\t\treturn \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\";\n\t}\n\n\tpublic static ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" invoke(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.translation.java.core.Init.silver_translation_java_core_javaSignature__ON__silver_definition_env_NamedSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") {\n\t\ttry {\n\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PgenerateFunctionClassString.i_whatResult)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t\t} catch(Throwable t) {\n\t\t\tthrow new common.exceptions.TraceException(\"Error while evaluating function ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\", t);\n\t\t}\n\t}\n\n\tpublic static final common.NodeFactory<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("> factory = new Factory();\n\n\tpublic static final class Factory extends common.NodeFactory<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("> {\n\t\t@Override\n\t\tpublic ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" invoke(final Object[] children, final Object[] namedNotApplicable) {\n\t\t\treturn ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.className__ON__silver_translation_java_core_generateFunctionClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".invoke(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.translation.java.core.PunpackChildren.invoke(Integer.valueOf((int)0), context.childDecoratedSynthesizedLazy(silver.translation.java.core.PgenerateFunctionClassString.i_whatSig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })), (common.StringCatter)(new common.StringCatter(");\n\t\t}\n\t};\n}")))))))))))))))))))))))))))))))))))))))))))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:generateFunctionClassString", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateFunctionClassString.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}