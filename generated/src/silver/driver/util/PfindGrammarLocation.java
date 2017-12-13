
package silver.driver.util;

public final class PfindGrammarLocation extends common.FunctionNode {

	public static final int i_path = 0;
	public static final int i_searchPaths = 1;
	public static final int i_iIn = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_findGrammarLocation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindGrammarLocation(final Object c_path, final Object c_searchPaths, final Object c_iIn) {
		this.child_path = c_path;
		this.child_searchPaths = c_searchPaths;
		this.child_iIn = c_iIn;

	}

	private Object child_path;
	public final common.StringCatter getChild_path() {
		return (common.StringCatter) (child_path = common.Util.demand(child_path));
	}

	private Object child_searchPaths;
	public final common.ConsCell getChild_searchPaths() {
		return (common.ConsCell) (child_searchPaths = common.Util.demand(child_searchPaths));
	}

	private Object child_iIn;
	public final Object getChild_iIn() {
		return (Object) (child_iIn = common.Util.demand(child_iIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_path: return getChild_path();
			case i_searchPaths: return getChild_searchPaths();
			case i_iIn: return getChild_iIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_path: return child_path;
			case i_searchPaths: return child_searchPaths;
			case i_iIn: return child_iIn;

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
		return "silver:driver:util:findGrammarLocation";
	}

	public static core.NIOVal invoke(final Object c_path, final Object c_searchPaths, final Object c_iIn) {
		try {
		final common.DecoratedNode context = new PfindGrammarLocation(c_path, c_searchPaths, c_iIn).decorate();
		//if null(searchPaths) then ioval(iIn, nothing()) else if exists.iovalue.isJust then exists else findGrammarLocation(path, tail(searchPaths), exists.io)
		return (core.NIOVal)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_searchPaths))) ? ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_iIn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })) : (((Boolean)((core.NMaybe)context.localDecorated(silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarLocation).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? context.localDecorated(silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarLocation).undecorate() : ((core.NIOVal)silver.driver.util.PfindGrammarLocation.invoke(context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_path), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_searchPaths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarLocation).synthesized(core.Init.core_io__ON__core_IOVal)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:findGrammarLocation", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindGrammarLocation.invoke(children[0], children[1], children[2]);
		}
	};
}