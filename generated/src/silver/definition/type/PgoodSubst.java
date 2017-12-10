
package silver.definition.type;

// top::Substitution ::= sublst::[Pair<TyVar Type>] 
public final class PgoodSubst extends silver.definition.type.NSubstitution {

	public static final int i_sublst = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_goodSubst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NSubstitution.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgoodSubst(final Object c_sublst) {
		super();
		this.child_sublst = c_sublst;

	}

	private Object child_sublst;
	public final common.ConsCell getChild_sublst() {
		return (common.ConsCell) (child_sublst = common.Util.demand(child_sublst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sublst: return getChild_sublst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sublst: return child_sublst;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:goodSubst erroneously claimed to forward");
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
		return "silver:definition:type:goodSubst";
	}

	static void initProductionAttributeDefinitions() {
		// top.substList = sublst
		silver.definition.type.PgoodSubst.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_substList__ON__silver_definition_type_Substitution] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.type.PgoodSubst.i_sublst)); } };
		// top.substErrors = []
		silver.definition.type.PgoodSubst.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_substErrors__ON__silver_definition_type_Substitution] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.failure = false
		silver.definition.type.PgoodSubst.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_failure__ON__silver_definition_type_Substitution] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PgoodSubst> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgoodSubst> {

		@Override
		public PgoodSubst invoke(final Object[] children, final Object[] annotations) {
			return new PgoodSubst(children[0]);
		}
	};

}
