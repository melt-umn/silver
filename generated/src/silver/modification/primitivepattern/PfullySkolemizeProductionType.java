
package silver.modification.primitivepattern;

public final class PfullySkolemizeProductionType extends common.FunctionNode {

	public static final int i_te = 0;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_fullySkolemizeProductionType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PfullySkolemizeProductionType(final Object c_te) {
		this.child_te = c_te;

	}

	private Object child_te;
	public final silver.definition.type.NType getChild_te() {
		return (silver.definition.type.NType) (child_te = common.Util.demand(child_te));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:modification:primitivepattern:fullySkolemizeProductionType";
	}

	public static silver.definition.type.NType invoke(final Object c_te) {
		try {
		final common.DecoratedNode context = new PfullySkolemizeProductionType(c_te).decorate();
		//performSubstitution(te, skolemize)
		return (silver.definition.type.NType)(((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PfullySkolemizeProductionType.i_te)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.skolemize__ON__silver_modification_primitivepattern_fullySkolemizeProductionType)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:primitivepattern:fullySkolemizeProductionType", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NType> {
		@Override
		public silver.definition.type.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfullySkolemizeProductionType.invoke(children[0]);
		}
	};
}