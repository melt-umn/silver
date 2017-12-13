
package core;

public final class PfromMaybe extends common.FunctionNode {

	public static final int i_otherwise = 0;
	public static final int i_ifJust = 1;


	public static final Class<?> childTypes[] = { Object.class,core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__core_fromMaybe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ifJust] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PfromMaybe(final Object c_otherwise, final Object c_ifJust) {
		this.child_otherwise = c_otherwise;
		this.child_ifJust = c_ifJust;

	}

	private Object child_otherwise;
	public final Object getChild_otherwise() {
		return (Object) (child_otherwise = common.Util.demand(child_otherwise));
	}

	private Object child_ifJust;
	public final core.NMaybe getChild_ifJust() {
		return (core.NMaybe) (child_ifJust = common.Util.demand(child_ifJust));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_otherwise: return getChild_otherwise();
			case i_ifJust: return getChild_ifJust();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_otherwise: return child_otherwise;
			case i_ifJust: return child_ifJust;

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
		return "core:fromMaybe";
	}

	public static Object invoke(final Object c_otherwise, final Object c_ifJust) {
		try {
		final common.DecoratedNode context = new PfromMaybe(c_otherwise, c_ifJust).decorate();
		//if ifJust.isJust then ifJust.fromJust else otherwise
		return (Object)((((Boolean)context.childDecorated(core.PfromMaybe.i_ifJust).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((Object)context.childDecorated(core.PfromMaybe.i_ifJust).synthesized(core.Init.core_fromJust__ON__core_Maybe)) : ((Object)context.childAsIs(core.PfromMaybe.i_otherwise))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:fromMaybe", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfromMaybe.invoke(children[0], children[1]);
		}
	};
}