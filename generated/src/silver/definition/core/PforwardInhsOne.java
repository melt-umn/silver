
package silver.definition.core;

// top::ForwardInhs ::= lhs::ForwardInh 
public final class PforwardInhsOne extends silver.definition.core.NForwardInhs {

	public static final int i_lhs = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NForwardInh.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_forwardInhsOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NForwardInhs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NForwardInhs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NForwardInh.num_inh_attrs];

	}

	public PforwardInhsOne(final Object c_lhs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;

	}

	private Object child_lhs;
	public final silver.definition.core.NForwardInh getChild_lhs() {
		return (silver.definition.core.NForwardInh) (child_lhs = common.Util.demand(child_lhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:forwardInhsOne erroneously claimed to forward");
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
		return "silver:definition:core:forwardInhsOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = lhs.pp
		silver.definition.core.PforwardInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PforwardInhsOne.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInh)); } };
		// top.errors := lhs.errors
		if(silver.definition.core.PforwardInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs] == null)
			silver.definition.core.PforwardInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs);
		((common.CollectionAttribute)silver.definition.core.PforwardInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PforwardInhsOne.i_lhs).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInh)); } });

	}

	public static final common.NodeFactory<PforwardInhsOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardInhsOne> {

		@Override
		public PforwardInhsOne invoke(final Object[] children, final Object[] annotations) {
			return new PforwardInhsOne(children[0], annotations[0]);
		}
	};

}
