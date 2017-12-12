
package silver.extension.convenience;

// top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs 'with' attrs::QNames ';' 
public final class PnonterminalWithDcl extends silver.definition.core.NAGDcl {

	public static final int i_cl = 0;
	public static final int i__G_5 = 1;
	public static final int i_id = 2;
	public static final int i_tl = 3;
	public static final int i__G_2 = 4;
	public static final int i_attrs = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.definition.core.NClosedOrNot.class,silver.definition.core.TNonTerminal_kwd.class,silver.definition.core.NName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TWith_kwd.class,silver.extension.convenience.NQNames.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_nonterminalWithDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_cl] = new common.Lazy[silver.definition.core.NClosedOrNot.num_inh_attrs];
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_attrs] = new common.Lazy[silver.extension.convenience.NQNames.num_inh_attrs];

	}

	public PnonterminalWithDcl(final Object c_cl, final Object c__G_5, final Object c_id, final Object c_tl, final Object c__G_2, final Object c_attrs, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_cl = c_cl;
		this.child__G_5 = c__G_5;
		this.child_id = c_id;
		this.child_tl = c_tl;
		this.child__G_2 = c__G_2;
		this.child_attrs = c_attrs;
		this.child__G_0 = c__G_0;

	}

	private Object child_cl;
	public final silver.definition.core.NClosedOrNot getChild_cl() {
		return (silver.definition.core.NClosedOrNot) (child_cl = common.Util.demand(child_cl));
	}

	private Object child__G_5;
	public final silver.definition.core.TNonTerminal_kwd getChild__G_5() {
		return (silver.definition.core.TNonTerminal_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_2;
	public final silver.definition.core.TWith_kwd getChild__G_2() {
		return (silver.definition.core.TWith_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_attrs;
	public final silver.extension.convenience.NQNames getChild_attrs() {
		return (silver.extension.convenience.NQNames) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_cl: return getChild_cl();
			case i__G_5: return getChild__G_5();
			case i_id: return getChild_id();
			case i_tl: return getChild_tl();
			case i__G_2: return getChild__G_2();
			case i_attrs: return getChild_attrs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_cl: return child_cl;
			case i__G_5: return child__G_5;
			case i_id: return child_id;
			case i_tl: return child_tl;
			case i__G_2: return child__G_2;
			case i_attrs: return child_attrs;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PnonterminalDcl(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PnonterminalWithDcl.i_cl)), context.childAsIsLazy(silver.extension.convenience.PnonterminalWithDcl.i__G_5), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PnonterminalWithDcl.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PnonterminalWithDcl.i_tl)), context.childAsIsLazy(silver.extension.convenience.PnonterminalWithDcl.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.extension.convenience.PnonterminalWithDcl.i_attrs, silver.extension.convenience.Init.silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.convenience.NQNameWithTL)new silver.extension.convenience.PqNameWithTL(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)new silver.definition.core.PqNameId(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PnonterminalWithDcl.i_id)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_id).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PnonterminalWithDcl.i_tl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:convenience:nonterminalWithDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "nonterminal " ++ id.pp ++ tl.pp ++ " with " ++ attrs.pp ++ " ;"
		silver.extension.convenience.PnonterminalWithDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("nonterminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_attrs).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNames)), (common.StringCatter)(new common.StringCatter(" ;"))))))); } };

	}

	public static final common.NodeFactory<PnonterminalWithDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnonterminalWithDcl> {

		@Override
		public PnonterminalWithDcl invoke(final Object[] children, final Object[] annotations) {
			return new PnonterminalWithDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
