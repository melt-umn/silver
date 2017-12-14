
package silver.definition.type;

public final class PsetUnionTyVars extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_setUnionTyVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsetUnionTyVars(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final common.ConsCell getChild_a() {
		return (common.ConsCell) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final common.ConsCell getChild_b() {
		return (common.ConsCell) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		return "silver:definition:type:setUnionTyVars";
	}

	public static common.ConsCell invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PsetUnionTyVars(c_a, c_b).decorate();
		//a ++ removeTyVars(b, a)
		return (common.ConsCell)(((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.definition.type.PsetUnionTyVars.i_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PremoveTyVars.invoke(context.childAsIsLazy(silver.definition.type.PsetUnionTyVars.i_b), context.childAsIsLazy(silver.definition.type.PsetUnionTyVars.i_a))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:setUnionTyVars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsetUnionTyVars.invoke(children[0], children[1]);
		}
	};
}