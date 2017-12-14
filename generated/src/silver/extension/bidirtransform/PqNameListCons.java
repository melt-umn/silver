
package silver.extension.bidirtransform;

// top::QNameList ::= id1::QName ',' id2::QNameList 
public final class PqNameListCons extends silver.extension.bidirtransform.NQNameList {

	public static final int i_id1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_id2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TComma_t.class,silver.extension.bidirtransform.NQNameList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_qNameListCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id1] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_id2] = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_inh_attrs];

	}

	public PqNameListCons(final Object c_id1, final Object c__G_1, final Object c_id2, final Object a_core_location) {
		super(a_core_location);
		this.child_id1 = c_id1;
		this.child__G_1 = c__G_1;
		this.child_id2 = c_id2;

	}

	private Object child_id1;
	public final silver.definition.core.NQName getChild_id1() {
		return (silver.definition.core.NQName) (child_id1 = common.Util.demand(child_id1));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_id2;
	public final silver.extension.bidirtransform.NQNameList getChild_id2() {
		return (silver.extension.bidirtransform.NQNameList) (child_id2 = common.Util.demand(child_id2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id1: return getChild_id1();
			case i__G_1: return getChild__G_1();
			case i_id2: return getChild_id2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id1: return child_id1;
			case i__G_1: return child__G_1;
			case i_id2: return child_id2;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:qNameListCons erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:qNameListCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id1.pp ++ ", " ++ id2.pp
		silver.extension.bidirtransform.PqNameListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_QNameList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PqNameListCons.i_id1).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PqNameListCons.i_id2).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_QNameList)))); } };
		// top.qList = [ id1 ] ++ id2.qList
		silver.extension.bidirtransform.PqNameListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PqNameListCons.i_id1)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PqNameListCons.i_id2, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList))); } };

	}

	public static final common.NodeFactory<PqNameListCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameListCons> {

		@Override
		public PqNameListCons invoke(final Object[] children, final Object[] annotations) {
			return new PqNameListCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
