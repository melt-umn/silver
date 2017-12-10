
package silver.definition.concrete_syntax.ast;

// top::SyntaxDcl ::= t::Type subdcls::Syntax 
public final class PsyntaxNonterminal extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_t = 0;
	public static final int i_subdcls = 1;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class,silver.definition.concrete_syntax.ast.NSyntax.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxNonterminal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_subdcls] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];

	}

	public PsyntaxNonterminal(final Object c_t, final Object c_subdcls) {
		super();
		this.child_t = c_t;
		this.child_subdcls = c_subdcls;

	}

	private Object child_t;
	public final silver.definition.type.NType getChild_t() {
		return (silver.definition.type.NType) (child_t = common.Util.demand(child_t));
	}

	private Object child_subdcls;
	public final silver.definition.concrete_syntax.ast.NSyntax getChild_subdcls() {
		return (silver.definition.concrete_syntax.ast.NSyntax) (child_subdcls = common.Util.demand(child_subdcls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_subdcls: return getChild_subdcls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_subdcls: return child_subdcls;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:syntaxNonterminal erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:syntaxNonterminal";
	}

	static void initProductionAttributeDefinitions() {
		// top.sortKey = "EEE" ++ t.typeName
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("EEE")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type))); } };
		// top.cstDcls = [ pair(t.typeName, top) ] ++ subdcls.cstDcls
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type), context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_subdcls, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.cstErrors := if length(searchEnvTree(t.typeName, top.cstEnv)) == 1 then [] else [ "Name conflict with nonterminal " ++ t.typeName ]
		if(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } })).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Name conflict with nonterminal ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.cstErrors <- subdcls.cstErrors
		if(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_subdcls).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax)); } });
		// top.cstProds = subdcls.cstProds
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_subdcls).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_Syntax)); } };
		// top.cstNormalize = let myProds :: [SyntaxDcl] = searchEnvTree(t.typeName, top.cstNTProds) in if null(myProds) then [] else [ syntaxNonterminal(t, foldr(consSyntax, nilSyntax(), myProds)) ] end
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_1923_myProds = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } };
return (((Boolean)core.Pnull.invoke(__SV_LOCAL_1923_myProds)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxNonterminal(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, __SV_LOCAL_1923_myProds)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }).eval()); } };
		// top.xmlCopper = "\n  <Nonterminal id=\"" ++ makeCopperName(t.typeName) ++ "\">\n" ++ "    <PP>" ++ t.typeName ++ "</PP>\n" ++ "    <Type><![CDATA[" ++ makeNTClassName(t.typeName) ++ "]]></Type>\n" ++ "  </Nonterminal>\n" ++ subdcls.xmlCopper
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n  <Nonterminal id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</PP>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Type><![CDATA[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeNTClassName.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]]></Type>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  </Nonterminal>\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_subdcls).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax)))))))))))); } };
		// t.boundVariables = t.freeVariables
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.childInheritedAttributes[silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } };
		// top.unparses = [ "nt(" ++ unparseTyVars(t.freeVariables, t.boundVariables) ++ ", " ++ t.unparse ++ ")" ] ++ subdcls.unparses
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("nt(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTyVars.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t, silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t).inherited(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_t).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(")")))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxNonterminal.i_subdcls, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax))); } };

	}

	public static final common.NodeFactory<PsyntaxNonterminal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxNonterminal> {

		@Override
		public PsyntaxNonterminal invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxNonterminal(children[0], children[1]);
		}
	};

}
