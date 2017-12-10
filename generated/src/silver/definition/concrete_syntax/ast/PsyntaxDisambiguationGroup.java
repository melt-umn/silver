
package silver.definition.concrete_syntax.ast;

// top::SyntaxDcl ::= n::String terms::[String] acode::String 
public final class PsyntaxDisambiguationGroup extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_n = 0;
	public static final int i_terms = 1;
	public static final int i_acode = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PsyntaxDisambiguationGroup(final Object c_n, final Object c_terms, final Object c_acode) {
		super();
		this.child_n = c_n;
		this.child_terms = c_terms;
		this.child_acode = c_acode;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_terms;
	public final common.ConsCell getChild_terms() {
		return (common.ConsCell) (child_terms = common.Util.demand(child_terms));
	}

	private Object child_acode;
	public final common.StringCatter getChild_acode() {
		return (common.StringCatter) (child_acode = common.Util.demand(child_acode));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_terms: return getChild_terms();
			case i_acode: return getChild_acode();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_terms: return child_terms;
			case i_acode: return child_acode;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:syntaxDisambiguationGroup erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:syntaxDisambiguationGroup";
	}

	static void initProductionAttributeDefinitions() {
		// top.sortKey = "DDD" ++ n
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("DDD")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_n))); } };
		// top.cstDcls = []
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// trefs = lookupStrings(terms, top.cstEnv)
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.localAttributes[silver.definition.concrete_syntax.ast.Init.trefs__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PlookupStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_terms), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } };
		// top.cstErrors := flatMap(\ p::Pair<String [Decorated SyntaxDcl]>  -> if ! null(p.snd) then [] else [ "Terminal " ++ p.fst ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from disambiguation group " ++ n ++ ")" ], zipWith(pair, terms, trefs))
		if(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_2036_p = args[0];

    return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_2036_p)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Terminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_2036_p)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from disambiguation group ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_n)), (common.StringCatter)(new common.StringCatter(")"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_terms), context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.trefs__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup))); } })); } });
		// top.cstNormalize = [ top ]
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.undecorate(), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.xmlCopper = "  <DisambiguationFunction id=\"" ++ makeCopperName(n) ++ "\">\n" ++ "    <Members>" ++ implode("", map(xmlCopperRef, map(head, trefs))) ++ "</Members>\n" ++ "    <Code><![CDATA[\n" ++ acode ++ "]]></Code>\n" ++ "  </DisambiguationFunction>\n"
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <DisambiguationFunction id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_n))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Members>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(core.Phead.factory, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.trefs__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup))); } })); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Members>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Code><![CDATA[\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_acode)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]]></Code>\n")), (common.StringCatter)(new common.StringCatter("  </DisambiguationFunction>\n"))))))))))); } };
		// top.unparses = [ "disambig('" ++ n ++ "', " ++ unparseStrings(terms) ++ ", \"" ++ escapeString(acode) ++ "\")" ]
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("disambig('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_terms))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PescapeString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.i_acode))), (common.StringCatter)(new common.StringCatter("\")")))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PsyntaxDisambiguationGroup> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxDisambiguationGroup> {

		@Override
		public PsyntaxDisambiguationGroup invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxDisambiguationGroup(children[0], children[1], children[2]);
		}
	};

}
