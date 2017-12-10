
package silver.util.fixedmap;

public final class PinternalBuild extends common.FunctionNode {

	public static final int i_collected = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_internalBuild;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinternalBuild(final Object c_collected) {
		this.child_collected = c_collected;

	}

	private Object child_collected;
	public final common.ConsCell getChild_collected() {
		return (common.ConsCell) (child_collected = common.Util.demand(child_collected));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_collected: return getChild_collected();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_collected: return child_collected;

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
		return "silver:util:fixedmap:internalBuild";
	}

	public static silver.util.fixedmap.NMap invoke(final Object c_collected) {
		try {
		final common.DecoratedNode context = new PinternalBuild(c_collected).decorate();
		//internalBuildHelp(collected, length(collected),)
		return (silver.util.fixedmap.NMap)(((silver.util.fixedmap.NMap)silver.util.fixedmap.PinternalBuildHelp.invoke(context.childAsIsLazy(silver.util.fixedmap.PinternalBuild.i_collected), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.util.fixedmap.PinternalBuild.i_collected))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:internalBuild", t);
		}
	}

	public static final common.NodeFactory<silver.util.fixedmap.NMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.fixedmap.NMap> {
		@Override
		public silver.util.fixedmap.NMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinternalBuild.invoke(children[0]);
		}
	};
}