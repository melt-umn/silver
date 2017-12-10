
package silver.definition.core;

public final class PqName extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_qName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PqName(final Object c_l, final Object c_s) {
		this.child_l = c_l;
		this.child_s = c_s;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
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
		return "silver:definition:core:qName";
	}

	public static silver.definition.core.NQName invoke(final Object c_l, final Object c_s) {
		try {
		final common.DecoratedNode context = new PqName(c_l, c_s).decorate();
		//qNameId(nameIdLower(terminal(IdLower_t, s, l),location=l),location=l)
		return (silver.definition.core.NQName)(((silver.definition.core.NQName)new silver.definition.core.PqNameId(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)new silver.definition.core.PnameIdLower(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIdLower_t(((common.StringCatter)context.childAsIs(silver.definition.core.PqName.i_s)), (core.NLocation)context.childDecorated(silver.definition.core.PqName.i_l).undecorate()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PqName.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PqName.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:qName", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NQName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NQName> {
		@Override
		public silver.definition.core.NQName invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PqName.invoke(children[0], children[1]);
		}
	};
}