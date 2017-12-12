
package silver.driver;

public final class PcomputeEnv extends common.FunctionNode {

	public static final int i_args = 0;
	public static final int i_ioin = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_computeEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcomputeEnv(final Object c_args, final Object c_ioin) {
		this.child_args = c_args;
		this.child_ioin = c_ioin;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;
			case i_ioin: return child_ioin;

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
		return "silver:driver:computeEnv";
	}

	public static core.NIOVal invoke(final Object c_args, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcomputeEnv(c_args, c_ioin).decorate();
		//if ! null(argErrors) then ioval(ioin, left(runError(1, head(argErrors)))) else if a.displayVersion then ioval(benvResult.io, left(runError(1, "Silver Version 0.4.1-dev\n" ++ "SILVER_HOME = " ++ benv.silverHome ++ "\n" ++ "SILVER_GEN = " ++ benv.silverGen ++ "\n" ++ "GRAMMAR_PATH:\n" ++ implode("\n", benv.grammarPath) ++ "\n\n" ++ implode("\n", envErrors)))) else if ! null(envErrors) then ioval(benvResult.io, left(runError(1, implode("\n", envErrors)))) else ioval(benvResult.io, right(pair(a, benv)))
		return (core.NIOVal)(((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.Init.argErrors__ON__silver_driver_computeEnv)))) ? ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.PcomputeEnv.i_ioin), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pleft(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.NRunError)new silver.driver.PrunError(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.localAsIsLazy(silver.driver.Init.argErrors__ON__silver_driver_computeEnv))); } })); } })); } })) : (((Boolean)((common.DecoratedNode)context.localAsIs(silver.driver.Init.a__ON__silver_driver_computeEnv)).synthesized(silver.driver.Init.silver_driver_displayVersion__ON__silver_util_cmdargs_CmdArgs)) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.benvResult__ON__silver_driver_computeEnv).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pleft(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.NRunError)new silver.driver.PrunError(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Silver Version 0.4.1-dev\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("SILVER_HOME = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.driver.Init.benv__ON__silver_driver_computeEnv).synthesized(silver.driver.util.Init.silver_driver_util_silverHome__ON__silver_driver_util_BuildEnv)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("SILVER_GEN = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.driver.Init.benv__ON__silver_driver_computeEnv).synthesized(silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("GRAMMAR_PATH:\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.Init.benv__ON__silver_driver_computeEnv).synthesized(silver.driver.util.Init.silver_driver_util_grammarPath__ON__silver_driver_util_BuildEnv)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), context.localAsIsLazy(silver.driver.Init.envErrors__ON__silver_driver_computeEnv))))))))))))); } })); } })); } })) : ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.Init.envErrors__ON__silver_driver_computeEnv)))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.benvResult__ON__silver_driver_computeEnv).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pleft(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.NRunError)new silver.driver.PrunError(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), context.localAsIsLazy(silver.driver.Init.envErrors__ON__silver_driver_computeEnv))); } })); } })); } })) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.benvResult__ON__silver_driver_computeEnv).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pright(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.localAsIsLazy(silver.driver.Init.a__ON__silver_driver_computeEnv), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.driver.Init.benv__ON__silver_driver_computeEnv)))); } })); } }))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:computeEnv", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcomputeEnv.invoke(children[0], children[1]);
		}
	};
}