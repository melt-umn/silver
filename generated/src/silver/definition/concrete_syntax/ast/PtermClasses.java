
package silver.definition.concrete_syntax.ast;

// top::SyntaxTerminalModifier ::= cls::[String] 
public final class PtermClasses extends silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier {

	public static final int i_cls = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_termClasses;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtermClasses(final Object c_cls) {
		super();
		this.child_cls = c_cls;

	}

	private Object child_cls;
	public final common.ConsCell getChild_cls() {
		return (common.ConsCell) (child_cls = common.Util.demand(child_cls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_cls: return getChild_cls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_cls: return child_cls;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:termClasses erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:termClasses";
	}

	static void initProductionAttributeDefinitions() {
		// clsRefsL = lookupStrings(cls, top.cstEnv)
		silver.definition.concrete_syntax.ast.PtermClasses.localAttributes[silver.definition.concrete_syntax.ast.Init.clsRefsL__ON__silver_definition_concrete_syntax_ast_termClasses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PlookupStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PtermClasses.i_cls), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier))); } };
		// clsRefs = map(head, clsRefsL)
		silver.definition.concrete_syntax.ast.PtermClasses.localAttributes[silver.definition.concrete_syntax.ast.Init.clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(core.Phead.factory, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.clsRefsL__ON__silver_definition_concrete_syntax_ast_termClasses))); } };
		// top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]>  -> if ! null(a.snd) then [] else [ "Lexer Class " ++ a.fst ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from lexer class on terminal " ++ top.terminalName ++ ")" ], zipWith(pair, cls, clsRefsL))
		if(silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] == null)
			silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1853_a = args[0];

    return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_1853_a)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Lexer Class ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_1853_a)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from lexer class on terminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_terminalName__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)), (common.StringCatter)(new common.StringCatter(")"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.childAsIsLazy(silver.definition.concrete_syntax.ast.PtermClasses.i_cls), context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.clsRefsL__ON__silver_definition_concrete_syntax_ast_termClasses))); } })); } });
		// top.dominatesXML = implode("", map((.classDomContribs), clsRefs))
		silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classDomContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses))); } })); } };
		// top.submitsXML = implode("", map((.classSubContribs), clsRefs))
		silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classSubContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses))); } })); } };
		// top.lexerclassesXML = implode("", map(xmlCopperRef, clsRefs))
		silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses))); } })); } };
		// top.unparses = [ "classes(" ++ unparseStrings(cls) ++ ")" ]
		silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("classes(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PtermClasses.i_cls))), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PtermClasses> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermClasses> {

		@Override
		public PtermClasses invoke(final Object[] children, final Object[] annotations) {
			return new PtermClasses(children[0]);
		}
	};

}
