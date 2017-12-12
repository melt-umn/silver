
package silver.definition.type;

public final class PunifyDirectional extends common.FunctionNode {

	public static final int i_fromte = 0;
	public static final int i_tote = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_unifyDirectional;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_fromte] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_tote] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PunifyDirectional(final Object c_fromte, final Object c_tote) {
		this.child_fromte = c_fromte;
		this.child_tote = c_tote;

	}

	private Object child_fromte;
	public final silver.definition.type.NType getChild_fromte() {
		return (silver.definition.type.NType) (child_fromte = common.Util.demand(child_fromte));
	}

	private Object child_tote;
	public final silver.definition.type.NType getChild_tote() {
		return (silver.definition.type.NType) (child_tote = common.Util.demand(child_tote));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fromte: return getChild_fromte();
			case i_tote: return getChild_tote();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fromte: return child_fromte;
			case i_tote: return child_tote;

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
		return "silver:definition:type:unifyDirectional";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_fromte, final Object c_tote) {
		try {
		final common.DecoratedNode context = new PunifyDirectional(c_fromte, c_tote).decorate();
		//unify(fromte, tote)
		return (silver.definition.type.NSubstitution)(((silver.definition.type.NSubstitution)silver.definition.type.Punify.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PunifyDirectional.i_fromte)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PunifyDirectional.i_tote)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:unifyDirectional", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunifyDirectional.invoke(children[0], children[1]);
		}
	};
}