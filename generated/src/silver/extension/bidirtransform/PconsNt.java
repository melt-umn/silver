
package silver.extension.bidirtransform;

// top::NonterminalList ::= fnt::FullNonterminal ',' lst::NonterminalList 
public final class PconsNt extends silver.extension.bidirtransform.NNonterminalList {

	public static final int i_fnt = 0;
	public static final int i__G_1 = 1;
	public static final int i_lst = 2;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NFullNonterminal.class,silver.definition.core.TComma_t.class,silver.extension.bidirtransform.NNonterminalList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_consNt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fnt] = new common.Lazy[silver.extension.bidirtransform.NFullNonterminal.num_inh_attrs];
	childInheritedAttributes[i_lst] = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_inh_attrs];

	}

	public PconsNt(final Object c_fnt, final Object c__G_1, final Object c_lst, final Object a_core_location) {
		super(a_core_location);
		this.child_fnt = c_fnt;
		this.child__G_1 = c__G_1;
		this.child_lst = c_lst;

	}

	private Object child_fnt;
	public final silver.extension.bidirtransform.NFullNonterminal getChild_fnt() {
		return (silver.extension.bidirtransform.NFullNonterminal) (child_fnt = common.Util.demand(child_fnt));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_lst;
	public final silver.extension.bidirtransform.NNonterminalList getChild_lst() {
		return (silver.extension.bidirtransform.NNonterminalList) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnt: return getChild_fnt();
			case i__G_1: return getChild__G_1();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnt: return child_fnt;
			case i__G_1: return child__G_1;
			case i_lst: return child_lst;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:consNt erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:consNt";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors := fnt.errors ++ lst.errors
		if(silver.extension.bidirtransform.PconsNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList] == null)
			silver.extension.bidirtransform.PconsNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PconsNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PconsNt.i_fnt, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PconsNt.i_lst, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList))); } });
		// top.ntList = [ fnt ] ++ lst.ntList
		silver.extension.bidirtransform.PconsNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.bidirtransform.PconsNt.i_fnt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PconsNt.i_lst, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList))); } };
		// fnt.env = top.env
		silver.extension.bidirtransform.PconsNt.childInheritedAttributes[silver.extension.bidirtransform.PconsNt.i_fnt][silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_FullNonterminal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_NonterminalList)); } };
		// lst.env = top.env
		silver.extension.bidirtransform.PconsNt.childInheritedAttributes[silver.extension.bidirtransform.PconsNt.i_lst][silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_NonterminalList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_NonterminalList)); } };

	}

	public static final common.NodeFactory<PconsNt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsNt> {

		@Override
		public PconsNt invoke(final Object[] children, final Object[] annotations) {
			return new PconsNt(children[0], children[1], children[2], annotations[0]);
		}
	};

}
