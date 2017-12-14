
package silver.definition.core;

// top::NameList ::= n::QName 
public final class PnameListOne extends silver.definition.core.NNameList {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_nameListOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NNameList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NNameList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PnameListOne(final Object c_n, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;

	}

	private Object child_n;
	public final silver.definition.core.NQName getChild_n() {
		return (silver.definition.core.NQName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:nameListOne erroneously claimed to forward");
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
		return "silver:definition:core:nameListOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = n.pp
		silver.definition.core.PnameListOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_NameList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PnameListOne.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.names = [ n.name ]
		silver.definition.core.PnameListOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_names__ON__silver_definition_core_NameList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PnameListOne.i_n, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PnameListOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnameListOne> {

		@Override
		public PnameListOne invoke(final Object[] children, final Object[] annotations) {
			return new PnameListOne(children[0], annotations[0]);
		}
	};

}
