
package silver.definition.type;

public final class PperformSubstitution extends common.FunctionNode {

	public static final int i_te = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_performSubstitution;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PperformSubstitution(final Object c_te, final Object c_s) {
		this.child_te = c_te;
		this.child_s = c_s;

	}

	private Object child_te;
	public final silver.definition.type.NType getChild_te() {
		return (silver.definition.type.NType) (child_te = common.Util.demand(child_te));
	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;
			case i_s: return child_s;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:definition:type:performSubstitution";
	}

	public static silver.definition.type.NType invoke(final Object c_te, final Object c_s) {
		try {
		final common.DecoratedNode context = new PperformSubstitution(c_te, c_s).decorate();
		//te.substituted
		return (silver.definition.type.NType)(((silver.definition.type.NType)context.childDecorated(silver.definition.type.PperformSubstitution.i_te).synthesized(silver.definition.type.Init.silver_definition_type_substituted__ON__silver_definition_type_Type)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:performSubstitution", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NType> {
		@Override
		public silver.definition.type.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PperformSubstitution.invoke(children[0], children[1]);
		}
	};
}