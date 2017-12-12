
package silver.extension.bidirtransform;

// top::AGDcl ::= 'nonterminals' qn::QName '=' nts::NonterminalList ';' 
public final class PnonterminalGroup extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i_qn = 1;
	public static final int i__G_2 = 2;
	public static final int i_nts = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.TNonterminals_kwd.class,silver.definition.core.NQName.class,silver.definition.core.TEqual_t.class,silver.extension.bidirtransform.NNonterminalList.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_nonterminalGroup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_nts] = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_inh_attrs];

	}

	public PnonterminalGroup(final Object c__G_4, final Object c_qn, final Object c__G_2, final Object c_nts, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_qn = c_qn;
		this.child__G_2 = c__G_2;
		this.child_nts = c_nts;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.extension.bidirtransform.TNonterminals_kwd getChild__G_4() {
		return (silver.extension.bidirtransform.TNonterminals_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_2;
	public final silver.definition.core.TEqual_t getChild__G_2() {
		return (silver.definition.core.TEqual_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_nts;
	public final silver.extension.bidirtransform.NNonterminalList getChild_nts() {
		return (silver.extension.bidirtransform.NNonterminalList) (child_nts = common.Util.demand(child_nts));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_qn: return getChild_qn();
			case i__G_2: return getChild__G_2();
			case i_nts: return getChild_nts();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_qn: return child_qn;
			case i__G_2: return child__G_2;
			case i_nts: return child_nts;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:nonterminalGroup erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:nonterminalGroup";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors := nts.errors
		if(silver.extension.bidirtransform.PnonterminalGroup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PnonterminalGroup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PnonterminalGroup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PnonterminalGroup.i_nts).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList)); } });
		// top.defs = [ ntGroupDef(ntGroupDcl(top.grammarName, top.location, qn.name, nts)) ]
		silver.extension.bidirtransform.PnonterminalGroup.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)new silver.extension.bidirtransform.PntGroupDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.extension.bidirtransform.PntGroupDcl(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PnonterminalGroup.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.childDecoratedLazy(silver.extension.bidirtransform.PnonterminalGroup.i_nts))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// nts.env = newScopeEnv(top.defs, top.env)
		silver.extension.bidirtransform.PnonterminalGroup.childInheritedAttributes[silver.extension.bidirtransform.PnonterminalGroup.i_nts][silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_NonterminalList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };

	}

	public static final common.NodeFactory<PnonterminalGroup> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnonterminalGroup> {

		@Override
		public PnonterminalGroup invoke(final Object[] children, final Object[] annotations) {
			return new PnonterminalGroup(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
