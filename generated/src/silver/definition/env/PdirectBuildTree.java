
package silver.definition.env;

public final class PdirectBuildTree extends common.FunctionNode {

	public static final int i_eis = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_directBuildTree;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PdirectBuildTree(final Object c_eis) {
		this.child_eis = c_eis;

	}

	private Object child_eis;
	public final common.ConsCell getChild_eis() {
		return (common.ConsCell) (child_eis = common.Util.demand(child_eis));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eis: return getChild_eis();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eis: return child_eis;

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
		return "silver:definition:env:directBuildTree";
	}

	public static Object invoke(final Object c_eis) {
		try {
		final common.DecoratedNode context = new PdirectBuildTree(c_eis).decorate();
		//rtm:add(eis, rtm:empty(compareString))
		return (Object)(((Object)silver.util.raw.treemap.Padd.invoke(context.childAsIsLazy(silver.definition.env.PdirectBuildTree.i_eis), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:directBuildTree", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdirectBuildTree.invoke(children[0]);
		}
	};
}