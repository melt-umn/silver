
package silver.definition.type;

public final class PunifyFunctions extends common.FunctionNode {

	public static final int i_te1 = 0;
	public static final int i_te2 = 1;
	public static final int i_n1 = 2;
	public static final int i_n2 = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_unifyFunctions;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunifyFunctions(final Object c_te1, final Object c_te2, final Object c_n1, final Object c_n2) {
		this.child_te1 = c_te1;
		this.child_te2 = c_te2;
		this.child_n1 = c_n1;
		this.child_n2 = c_n2;

	}

	private Object child_te1;
	public final common.ConsCell getChild_te1() {
		return (common.ConsCell) (child_te1 = common.Util.demand(child_te1));
	}

	private Object child_te2;
	public final common.ConsCell getChild_te2() {
		return (common.ConsCell) (child_te2 = common.Util.demand(child_te2));
	}

	private Object child_n1;
	public final common.ConsCell getChild_n1() {
		return (common.ConsCell) (child_n1 = common.Util.demand(child_n1));
	}

	private Object child_n2;
	public final common.ConsCell getChild_n2() {
		return (common.ConsCell) (child_n2 = common.Util.demand(child_n2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te1: return getChild_te1();
			case i_te2: return getChild_te2();
			case i_n1: return getChild_n1();
			case i_n2: return getChild_n2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te1: return child_te1;
			case i_te2: return child_te2;
			case i_n1: return child_n1;
			case i_n2: return child_n2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:definition:type:unifyFunctions";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_te1, final Object c_te2, final Object c_n1, final Object c_n2) {
		try {
		final common.DecoratedNode context = new PunifyFunctions(c_te1, c_te2, c_n1, c_n2).decorate();
		//composeSubst(first, second)
		return (silver.definition.type.NSubstitution)(((silver.definition.type.NSubstitution)silver.definition.type.PcomposeSubst.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.type.Init.first__ON__silver_definition_type_unifyFunctions)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.type.Init.second__ON__silver_definition_type_unifyFunctions)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:unifyFunctions", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunifyFunctions.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}