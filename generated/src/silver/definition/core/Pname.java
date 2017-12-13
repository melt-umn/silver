
package silver.definition.core;

public final class Pname extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_name;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public Pname(final Object c_n, final Object c_l) {
		this.child_n = c_n;
		this.child_l = c_l;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_l: return child_l;

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
		return "silver:definition:core:name";
	}

	public static silver.definition.core.NName invoke(final Object c_n, final Object c_l) {
		try {
		final common.DecoratedNode context = new Pname(c_n, c_l).decorate();
		//nameIdLower(terminal(IdLower_t, n, l),location=l)
		return (silver.definition.core.NName)(((silver.definition.core.NName)new silver.definition.core.PnameIdLower(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIdLower_t(((common.StringCatter)context.childAsIs(silver.definition.core.Pname.i_n)), (core.NLocation)context.childDecorated(silver.definition.core.Pname.i_l).undecorate()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.Pname.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:name", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NName> {
		@Override
		public silver.definition.core.NName invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pname.invoke(children[0], children[1]);
		}
	};
}