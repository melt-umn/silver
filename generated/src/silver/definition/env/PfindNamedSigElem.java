
package silver.definition.env;

public final class PfindNamedSigElem extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_l = 1;
	public static final int i_z = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_findNamedSigElem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindNamedSigElem(final Object c_s, final Object c_l, final Object c_z) {
		this.child_s = c_s;
		this.child_l = c_l;
		this.child_z = c_z;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_z;
	public final Integer getChild_z() {
		return (Integer) (child_z = common.Util.demand(child_z));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_l: return getChild_l();
			case i_z: return getChild_z();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_l: return child_l;
			case i_z: return child_z;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:definition:env:findNamedSigElem";
	}

	public static Integer invoke(final Object c_s, final Object c_l, final Object c_z) {
		try {
		final common.DecoratedNode context = new PfindNamedSigElem(c_s, c_l, c_z).decorate();
		//if null(l) then -1 else if s == head(l).elementName then z else findNamedSigElem(s, tail(l), z + 1)
		return (Integer)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.env.PfindNamedSigElem.i_l))) ? Integer.valueOf((int)-1) : (((common.StringCatter)context.childAsIs(silver.definition.env.PfindNamedSigElem.i_s)).equals(((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PfindNamedSigElem.i_l))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement))) ? ((Integer)context.childAsIs(silver.definition.env.PfindNamedSigElem.i_z)) : ((Integer)silver.definition.env.PfindNamedSigElem.invoke(context.childAsIsLazy(silver.definition.env.PfindNamedSigElem.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PfindNamedSigElem.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.definition.env.PfindNamedSigElem.i_z)) + Integer.valueOf((int)1)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:findNamedSigElem", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindNamedSigElem.invoke(children[0], children[1], children[2]);
		}
	};
}