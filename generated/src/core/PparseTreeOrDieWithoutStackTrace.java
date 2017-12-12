
package core;

public final class PparseTreeOrDieWithoutStackTrace extends common.FunctionNode {

	public static final int i_pr = 0;


	public static final Class<?> childTypes[] = { core.NParseResult.class };

	public static final int num_local_attrs = Init.count_local__ON__core_parseTreeOrDieWithoutStackTrace;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_pr] = new common.Lazy[core.NParseResult.num_inh_attrs];

	}

	public PparseTreeOrDieWithoutStackTrace(final Object c_pr) {
		this.child_pr = c_pr;

	}

	private Object child_pr;
	public final core.NParseResult getChild_pr() {
		return (core.NParseResult) (child_pr = common.Util.demand(child_pr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pr: return getChild_pr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pr: return child_pr;

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
		return "core:parseTreeOrDieWithoutStackTrace";
	}

	public static Object invoke(final Object c_pr) {
		try {
		final common.DecoratedNode context = new PparseTreeOrDieWithoutStackTrace(c_pr).decorate();
		//unsafeTrace(pr.parseTree, if pr.parseSuccess then unsafeIO() else exit(-1, print(pr.parseErrors ++ "\n\n", unsafeIO())))
		return (Object)(((Object)core.PunsafeTrace.invoke(context.childDecoratedSynthesizedLazy(core.PparseTreeOrDieWithoutStackTrace.i_pr, core.Init.core_parseTree__ON__core_ParseResult), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(core.PparseTreeOrDieWithoutStackTrace.i_pr).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult)) ? ((Object)core.PunsafeIO.invoke()) : ((Object)core.Pexit.invoke(Integer.valueOf((int)-1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(core.PparseTreeOrDieWithoutStackTrace.i_pr).synthesized(core.Init.core_parseErrors__ON__core_ParseResult)), (common.StringCatter)(new common.StringCatter("\n\n"))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.PunsafeIO.invoke()); } })); } }))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:parseTreeOrDieWithoutStackTrace", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PparseTreeOrDieWithoutStackTrace.invoke(children[0]);
		}
	};
}