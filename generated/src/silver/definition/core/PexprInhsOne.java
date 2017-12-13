
package silver.definition.core;

// top::ExprInhs ::= lhs::ExprInh 
public final class PexprInhsOne extends silver.definition.core.NExprInhs {

	public static final int i_lhs = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NExprInh.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_exprInhsOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExprInhs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExprInhs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NExprInh.num_inh_attrs];

	}

	public PexprInhsOne(final Object c_lhs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;

	}

	private Object child_lhs;
	public final silver.definition.core.NExprInh getChild_lhs() {
		return (silver.definition.core.NExprInh) (child_lhs = common.Util.demand(child_lhs));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:exprInhsOne erroneously claimed to forward");
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
		return "silver:definition:core:exprInhsOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = lhs.pp
		silver.definition.core.PexprInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ExprInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PexprInhsOne.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ExprInh)); } };
		// top.errors := lhs.errors
		if(silver.definition.core.PexprInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs] == null)
			silver.definition.core.PexprInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs);
		((common.CollectionAttribute)silver.definition.core.PexprInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PexprInhsOne.i_lhs).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInh)); } });
		// top.suppliedInhs = lhs.suppliedInhs
		silver.definition.core.PexprInhsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_suppliedInhs__ON__silver_definition_core_ExprInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PexprInhsOne.i_lhs).synthesized(silver.definition.core.Init.silver_definition_core_suppliedInhs__ON__silver_definition_core_ExprInh)); } };

	}

	public static final common.NodeFactory<PexprInhsOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PexprInhsOne> {

		@Override
		public PexprInhsOne invoke(final Object[] children, final Object[] annotations) {
			return new PexprInhsOne(children[0], annotations[0]);
		}
	};

}
