
package silver.driver;

public final class PcompileFiles extends common.FunctionNode {

	public static final int i_svParser = 0;
	public static final int i_gpath = 1;
	public static final int i_files = 2;
	public static final int i_ioin = 3;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.StringCatter.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_compileFiles;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcompileFiles(final Object c_svParser, final Object c_gpath, final Object c_files, final Object c_ioin) {
		this.child_svParser = c_svParser;
		this.child_gpath = c_gpath;
		this.child_files = c_files;
		this.child_ioin = c_ioin;

	}

	private Object child_svParser;
	public final common.NodeFactory<core.NParseResult> getChild_svParser() {
		return (common.NodeFactory<core.NParseResult>) (child_svParser = common.Util.demand(child_svParser));
	}

	private Object child_gpath;
	public final common.StringCatter getChild_gpath() {
		return (common.StringCatter) (child_gpath = common.Util.demand(child_gpath));
	}

	private Object child_files;
	public final common.ConsCell getChild_files() {
		return (common.ConsCell) (child_files = common.Util.demand(child_files));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_svParser: return getChild_svParser();
			case i_gpath: return getChild_gpath();
			case i_files: return getChild_files();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_svParser: return child_svParser;
			case i_gpath: return child_gpath;
			case i_files: return child_files;
			case i_ioin: return child_ioin;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:driver:compileFiles";
	}

	public static core.NIOVal invoke(final Object c_svParser, final Object c_gpath, final Object c_files, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcompileFiles(c_svParser, c_gpath, c_files, c_ioin).decorate();
		//if null(files) then ioval(ioin, pair([], [])) else case r of parseSucceeded(rtree, _) -> ioval(recurse.io, pair((rtree :: recurse.iovalue.fst), recurse.iovalue.snd)) | parseFailed(errval, _) -> ioval(recurse.io, pair(recurse.iovalue.fst, (errval :: recurse.iovalue.snd))) end
		return (core.NIOVal)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PcompileFiles.i_files))) ? ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.PcompileFiles.i_ioin), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })) : new common.PatternLazy<common.DecoratedNode, core.NIOVal>() { public final core.NIOVal eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.PparseFailed) { final common.Thunk<Object> __SV_LOCAL___pv2259___sv_pv_2258_errval = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2260___sv_tmp_pv_2261 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (core.NIOVal)((core.NIOVal)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2262_errval = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2259___sv_pv_2258_errval.eval())); } };
return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileFiles).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileFiles).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_2262_errval), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileFiles).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } })); } })); } }).eval()); }
else if(scrutineeNode instanceof core.PparseSucceeded) { final common.Thunk<Object> __SV_LOCAL___pv2274___sv_pv_2273_rtree = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.NRoot)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2275___sv_tmp_pv_2276 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (core.NIOVal)((core.NIOVal)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2277_rtree = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NRoot)(__SV_LOCAL___pv2274___sv_pv_2273_rtree.eval())); } };
return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileFiles).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(__SV_LOCAL_2277_rtree, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileFiles).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileFiles).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NIOVal)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:driver 'CompileFiles.sv', 28, 14, 33, 12, 1056, 1332\n"))));}}.eval(context, (common.DecoratedNode)context.localDecorated(silver.driver.Init.r__ON__silver_driver_compileFiles))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:compileFiles", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompileFiles.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}