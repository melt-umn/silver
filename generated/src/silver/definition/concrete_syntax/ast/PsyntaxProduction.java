
package silver.definition.concrete_syntax.ast;

// top::SyntaxDcl ::= ns::NamedSignature modifiers::SyntaxProductionModifiers 
public final class PsyntaxProduction extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_ns = 0;
	public static final int i_modifiers = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.NNamedSignature.class,silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxProduction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
	childInheritedAttributes[i_modifiers] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.num_inh_attrs];

	}

	public PsyntaxProduction(final Object c_ns, final Object c_modifiers) {
		super();
		this.child_ns = c_ns;
		this.child_modifiers = c_modifiers;

	}

	private Object child_ns;
	public final silver.definition.env.NNamedSignature getChild_ns() {
		return (silver.definition.env.NNamedSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_modifiers;
	public final silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers getChild_modifiers() {
		return (silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers) (child_modifiers = common.Util.demand(child_modifiers));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();
			case i_modifiers: return getChild_modifiers();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;
			case i_modifiers: return child_modifiers;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:syntaxProduction erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:syntaxProduction";
	}

	static void initProductionAttributeDefinitions() {
		// top.sortKey = "FFF" ++ ns.fullName
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("FFF")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))); } };
		// top.cstDcls = [ pair(ns.fullName, top) ]
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// modifiers.productionName = ns.fullName
		silver.definition.concrete_syntax.ast.PsyntaxProduction.childInheritedAttributes[silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)); } };
		// lhsRef = searchEnvTree(ns.outputElement.typerep.typeName, top.cstEnv)
		silver.definition.concrete_syntax.ast.PsyntaxProduction.localAttributes[silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } };
		// rhsRefs = lookupStrings(map((.typeName), map((.typerep), ns.inputElements)), top.cstEnv)
		silver.definition.concrete_syntax.ast.PsyntaxProduction.localAttributes[silver.definition.concrete_syntax.ast.Init.rhsRefs__ON__silver_definition_concrete_syntax_ast_syntaxProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PlookupStrings.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement, context), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })); } }, context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } };
		// top.cstErrors := modifiers.cstErrors
		if(silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)); } });
		// top.cstErrors <- if length(searchEnvTree(ns.fullName, top.cstEnv)) == 1 then [] else [ "Name conflict with production " ++ ns.fullName ]
		if(silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } })).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Name conflict with production ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.cstErrors <- if length(lhsRef) == 1 then case head(lhsRef) of syntaxNonterminal(_, _) -> [] | _ -> [ "LHS of production " ++ ns.fullName ++ " is not a nonterminal" ] end else [ "Nonterminal " ++ ns.outputElement.typerep.typeName ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from LHS of production " ++ ns.fullName ++ ")" ]
		if(silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction))).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_1964___fail_1965 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("LHS of production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)), (common.StringCatter)(new common.StringCatter(" is not a nonterminal")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxNonterminal) { final common.Thunk<Object> __SV_LOCAL___pv1970___sv_tmp_pv_1971 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv1972___sv_tmp_pv_1973 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_1964___fail_1965.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction)))); } }).eval()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Nonterminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from LHS of production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)), (common.StringCatter)(new common.StringCatter(")"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.cstErrors <- checkRHS(ns.fullName, map((.typerep), ns.inputElements), rhsRefs)
		if(silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PcheckRHS.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement, context), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } }, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.rhsRefs__ON__silver_definition_concrete_syntax_ast_syntaxProduction))); } });
		// top.cstProds = [ pair(ns.outputElement.typerep.typeName, top) ]
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.undecorate())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstNormalize = []
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.xmlCopper = "  <Production id=\"" ++ makeCopperName(ns.fullName) ++ "\">\n" ++ (if modifiers.productionPrecedence.isJust then "    <Class>main</Class>\n" ++ "    <Precedence>" ++ toString(modifiers.productionPrecedence.fromJust) ++ "</Precedence>\n" else "") ++ "    <Code><![CDATA[\n" ++ "RESULT = new " ++ makeClassName(ns.fullName) ++ "(" ++ fetchChildren(0, ns.inputElements) ++ insertLocationAnnotation(ns) ++ ");\n" ++ modifiers.acode ++ "]]></Code>\n" ++ "    <LHS>" ++ xmlCopperRef(head(lhsRef)) ++ "</LHS>\n" ++ "    <RHS>" ++ implode("", map(xmlCopperRef, map(head, rhsRefs))) ++ "</RHS>\n" ++ (if modifiers.customLayout.isJust then "    <Layout>" ++ modifiers.customLayout.fromJust ++ "</Layout>\n" else "") ++ (if modifiers.productionOperator.isJust then "    <Operator>" ++ modifiers.productionOperator.fromJust ++ "</Operator>\n" else "") ++ "  </Production>\n"
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <Production id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Class>main</Class>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Precedence>")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)))), (common.StringCatter)(new common.StringCatter("</Precedence>\n"))))) : (new common.StringCatter(""))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Code><![CDATA[\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("RESULT = new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeClassName.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PfetchChildren.invoke(Integer.valueOf((int)0), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.invoke(context.childDecoratedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(");\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]]></Code>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <LHS>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PxmlCopperRef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</LHS>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <RHS>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(core.Phead.factory, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.rhsRefs__ON__silver_definition_concrete_syntax_ast_syntaxProduction))); } })); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</RHS>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Layout>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)), (common.StringCatter)(new common.StringCatter("</Layout>\n")))) : (new common.StringCatter(""))), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Operator>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)), (common.StringCatter)(new common.StringCatter("</Operator>\n")))) : (new common.StringCatter(""))), (common.StringCatter)(new common.StringCatter("  </Production>\n"))))))))))))))))))))))); } };
		// ns.boundVariables = ns.typerep.freeVariables
		silver.definition.concrete_syntax.ast.PsyntaxProduction.childInheritedAttributes[silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns][silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.type.NType)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } };
		// top.unparses = [ "prod(" ++ unparseTyVars(ns.boundVariables, ns.boundVariables) ++ ", " ++ ns.unparse ++ ", " ++ unparseNonStrings(modifiers.unparses) ++ ")" ]
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prod(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).inherited(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).inherited(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseNonStrings.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxProduction.i_modifiers, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))), (common.StringCatter)(new common.StringCatter(")")))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PsyntaxProduction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxProduction> {

		@Override
		public PsyntaxProduction invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxProduction(children[0], children[1]);
		}
	};

}
