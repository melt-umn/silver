
package silver.extension.bidirtransform;

public final class PgetProdsFromDefs2 extends common.FunctionNode {

	public static final int i_fnnt = 0;
	public static final int i_dfs = 1;
	public static final int i_skipGrammar = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.definition.env.NDefs.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getProdsFromDefs2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_dfs] = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	}

	public PgetProdsFromDefs2(final Object c_fnnt, final Object c_dfs, final Object c_skipGrammar) {
		this.child_fnnt = c_fnnt;
		this.child_dfs = c_dfs;
		this.child_skipGrammar = c_skipGrammar;

	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_dfs;
	public final silver.definition.env.NDefs getChild_dfs() {
		return (silver.definition.env.NDefs) (child_dfs = common.Util.demand(child_dfs));
	}

	private Object child_skipGrammar;
	public final common.StringCatter getChild_skipGrammar() {
		return (common.StringCatter) (child_skipGrammar = common.Util.demand(child_skipGrammar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnnt: return getChild_fnnt();
			case i_dfs: return getChild_dfs();
			case i_skipGrammar: return getChild_skipGrammar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnnt: return child_fnnt;
			case i_dfs: return child_dfs;
			case i_skipGrammar: return child_skipGrammar;

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
		return "silver:extension:bidirtransform:getProdsFromDefs2";
	}

	public static common.ConsCell invoke(final Object c_fnnt, final Object c_dfs, final Object c_skipGrammar) {
		try {
		final common.DecoratedNode context = new PgetProdsFromDefs2(c_fnnt, c_dfs, c_skipGrammar).decorate();
		//getProdsFromDefs(fnnt, dfs, skipGrammar)
		return (common.ConsCell)(((common.ConsCell)silver.extension.bidirtransform.PgetProdsFromDefs.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromDefs2.i_fnnt), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PgetProdsFromDefs2.i_dfs)), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromDefs2.i_skipGrammar))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getProdsFromDefs2", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdsFromDefs2.invoke(children[0], children[1], children[2]);
		}
	};
}