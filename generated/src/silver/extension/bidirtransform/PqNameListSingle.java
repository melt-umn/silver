
package silver.extension.bidirtransform;

// top::QNameList ::= id::QName 
public final class PqNameListSingle extends silver.extension.bidirtransform.NQNameList {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_qNameListSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PqNameListSingle(final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;

	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:qNameListSingle erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:qNameListSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.pp
		silver.extension.bidirtransform.PqNameListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_QNameList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PqNameListSingle.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.qList = [ id ]
		silver.extension.bidirtransform.PqNameListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PqNameListSingle.i_id)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PqNameListSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameListSingle> {

		@Override
		public PqNameListSingle invoke(final Object[] children, final Object[] annotations) {
			return new PqNameListSingle(children[0], annotations[0]);
		}
	};

}
