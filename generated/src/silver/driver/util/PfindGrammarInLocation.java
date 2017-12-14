
package silver.driver.util;

public final class PfindGrammarInLocation extends common.FunctionNode {

	public static final int i_gram = 0;
	public static final int i_inPath = 1;
	public static final int i_iIn = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_findGrammarInLocation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindGrammarInLocation(final Object c_gram, final Object c_inPath, final Object c_iIn) {
		this.child_gram = c_gram;
		this.child_inPath = c_inPath;
		this.child_iIn = c_iIn;

	}

	private Object child_gram;
	public final common.StringCatter getChild_gram() {
		return (common.StringCatter) (child_gram = common.Util.demand(child_gram));
	}

	private Object child_inPath;
	public final common.StringCatter getChild_inPath() {
		return (common.StringCatter) (child_inPath = common.Util.demand(child_inPath));
	}

	private Object child_iIn;
	public final Object getChild_iIn() {
		return (Object) (child_iIn = common.Util.demand(child_iIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_gram: return getChild_gram();
			case i_inPath: return getChild_inPath();
			case i_iIn: return getChild_iIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_gram: return child_gram;
			case i_inPath: return child_inPath;
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
		return "silver:driver:util:findGrammarInLocation";
	}

	public static core.NIOVal invoke(final Object c_gram, final Object c_inPath, final Object c_iIn) {
		try {
		final common.DecoratedNode context = new PfindGrammarInLocation(c_gram, c_inPath, c_iIn).decorate();
		//if idx == -1 then ioval(iIn, nothing()) else if exists.iovalue then ioval(exists.io, just(inPath ++ gram)) else findGrammarInLocation(nextGram, inPath, exists.io)
		return (core.NIOVal)((((Integer)context.localAsIs(silver.driver.util.Init.idx__ON__silver_driver_util_findGrammarInLocation)).equals(Integer.valueOf((int)-1)) ? ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.util.PfindGrammarInLocation.i_iIn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })) : (((Boolean)context.localDecorated(silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarInLocation).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarInLocation).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PfindGrammarInLocation.i_inPath)), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PfindGrammarInLocation.i_gram))); } })); } })) : ((core.NIOVal)silver.driver.util.PfindGrammarInLocation.invoke(context.localAsIsLazy(silver.driver.util.Init.nextGram__ON__silver_driver_util_findGrammarInLocation), context.childAsIsLazy(silver.driver.util.PfindGrammarInLocation.i_inPath), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarInLocation).synthesized(core.Init.core_io__ON__core_IOVal)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:findGrammarInLocation", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindGrammarInLocation.invoke(children[0], children[1], children[2]);
		}
	};
}