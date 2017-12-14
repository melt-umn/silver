
package silver.extension.bidirtransform;

// top::NonterminalList ::= fnt::FullNonterminal 
public final class PsingleNt extends silver.extension.bidirtransform.NNonterminalList {

	public static final int i_fnt = 0;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NFullNonterminal.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_singleNt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fnt] = new common.Lazy[silver.extension.bidirtransform.NFullNonterminal.num_inh_attrs];

	}

	public PsingleNt(final Object c_fnt, final Object a_core_location) {
		super(a_core_location);
		this.child_fnt = c_fnt;

	}

	private Object child_fnt;
	public final silver.extension.bidirtransform.NFullNonterminal getChild_fnt() {
		return (silver.extension.bidirtransform.NFullNonterminal) (child_fnt = common.Util.demand(child_fnt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnt: return getChild_fnt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnt: return child_fnt;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:singleNt erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:singleNt";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors := fnt.errors
		if(silver.extension.bidirtransform.PsingleNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList] == null)
			silver.extension.bidirtransform.PsingleNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PsingleNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PsingleNt.i_fnt).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal)); } });
		// top.ntList = [ fnt ]
		silver.extension.bidirtransform.PsingleNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.bidirtransform.PsingleNt.i_fnt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PsingleNt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsingleNt> {

		@Override
		public PsingleNt invoke(final Object[] children, final Object[] annotations) {
			return new PsingleNt(children[0], annotations[0]);
		}
	};

}
