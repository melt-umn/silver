
package silver.modification.primitivepattern;

public final class Prefine extends common.FunctionNode {

	public static final int i_te1 = 0;
	public static final int i_te2 = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_refine;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te1] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_te2] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public Prefine(final Object c_te1, final Object c_te2) {
		this.child_te1 = c_te1;
		this.child_te2 = c_te2;

	}

	private Object child_te1;
	public final silver.definition.type.NType getChild_te1() {
		return (silver.definition.type.NType) (child_te1 = common.Util.demand(child_te1));
	}

	private Object child_te2;
	public final silver.definition.type.NType getChild_te2() {
		return (silver.definition.type.NType) (child_te2 = common.Util.demand(child_te2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te1: return getChild_te1();
			case i_te2: return getChild_te2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te1: return child_te1;
			case i_te2: return child_te2;

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
		return "silver:modification:primitivepattern:refine";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_te1, final Object c_te2) {
		try {
		final common.DecoratedNode context = new Prefine(c_te1, c_te2).decorate();
		//if null(leftward.substErrors) then leftward else rightward
		return (silver.definition.type.NSubstitution)((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.primitivepattern.Init.leftward__ON__silver_modification_primitivepattern_refine).synthesized(silver.definition.type.Init.silver_definition_type_substErrors__ON__silver_definition_type_Substitution)); } })) ? context.localDecorated(silver.modification.primitivepattern.Init.leftward__ON__silver_modification_primitivepattern_refine).undecorate() : context.localDecorated(silver.modification.primitivepattern.Init.rightward__ON__silver_modification_primitivepattern_refine).undecorate()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:primitivepattern:refine", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Prefine.invoke(children[0], children[1]);
		}
	};
}