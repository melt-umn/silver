
package silver.definition.env;

public final class PoccursOnHelp extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_fnat = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_occursOnHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PoccursOnHelp(final Object c_i, final Object c_fnat) {
		this.child_i = c_i;
		this.child_fnat = c_fnat;

	}

	private Object child_i;
	public final common.ConsCell getChild_i() {
		return (common.ConsCell) (child_i = common.Util.demand(child_i));
	}

	private Object child_fnat;
	public final common.StringCatter getChild_fnat() {
		return (common.StringCatter) (child_fnat = common.Util.demand(child_fnat));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_fnat: return getChild_fnat();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
			case i_fnat: return child_fnat;

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
		return "silver:definition:env:occursOnHelp";
	}

	public static common.ConsCell invoke(final Object c_i, final Object c_fnat) {
		try {
		final common.DecoratedNode context = new PoccursOnHelp(c_i, c_fnat).decorate();
		//if null(i) then [] else if head(i).attrOccurring == fnat then (head(i) :: occursOnHelp(tail(i), fnat)) else occursOnHelp(tail(i), fnat)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_i))) ? ((common.ConsCell)core.Pnil.invoke()) : (((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_i))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo)).equals(((common.StringCatter)context.childAsIs(silver.definition.env.PoccursOnHelp.i_fnat))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_i))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PoccursOnHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_i))); } }, context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_fnat))); } })) : ((common.ConsCell)silver.definition.env.PoccursOnHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_i))); } }, context.childAsIsLazy(silver.definition.env.PoccursOnHelp.i_fnat))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:occursOnHelp", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PoccursOnHelp.invoke(children[0], children[1]);
		}
	};
}