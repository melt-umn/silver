
package silver.driver;

public final class PperformActions extends common.FunctionNode {

	public static final int i_unitin = 0;


	public static final Class<?> childTypes[] = { core.NIOVal.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_performActions;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_unitin] = new common.Lazy[core.NIOVal.num_inh_attrs];

	}

	public PperformActions(final Object c_unitin) {
		this.child_unitin = c_unitin;

	}

	private Object child_unitin;
	public final core.NIOVal getChild_unitin() {
		return (core.NIOVal) (child_unitin = common.Util.demand(child_unitin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_unitin: return getChild_unitin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_unitin: return child_unitin;

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
		return "silver:driver:performActions";
	}

	public static core.NIOVal invoke(final Object c_unitin) {
		try {
		final common.DecoratedNode context = new PperformActions(c_unitin).decorate();
		//case unitin.iovalue of left(re) -> ioval(print(re.message ++ "\n", unitin.io), re.code) | right(comp) -> runAll(sortUnits(comp.postOps), unitin.io) end
		return (core.NIOVal)(new common.PatternLazy<common.DecoratedNode, core.NIOVal>() { public final core.NIOVal eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pleft) { final common.Thunk<Object> __SV_LOCAL___pv2338___sv_pv_2337_re = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.driver.NRunError)scrutinee.childAsIs(0); } };
 return (core.NIOVal)((core.NIOVal)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2339_re = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.NRunError)(__SV_LOCAL___pv2338___sv_pv_2337_re.eval())); } };
return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.driver.NRunError)(__SV_LOCAL_2339_re.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.driver.Init.silver_langutil_message__ON__silver_driver_RunError)), (common.StringCatter)(new common.StringCatter("\n"))); } }, context.childDecoratedSynthesizedLazy(silver.driver.PperformActions.i_unitin, core.Init.core_io__ON__core_IOVal))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((silver.driver.NRunError)(__SV_LOCAL_2339_re.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.driver.Init.silver_driver_util_code__ON__silver_driver_RunError)); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pright) { final common.Thunk<Object> __SV_LOCAL___pv2347___sv_pv_2346_comp = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (core.NIOVal)((core.NIOVal)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2348_comp = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2347___sv_pv_2346_comp.eval())); } };
return ((core.NIOVal)silver.driver.util.PrunAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PsortUnits.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_2348_comp.eval())).synthesized(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation)); } })); } }, context.childDecoratedSynthesizedLazy(silver.driver.PperformActions.i_unitin, core.Init.core_io__ON__core_IOVal))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NIOVal)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:driver 'BuildProcess.sv', 45, 9, 48, 5, 1179, 1338\n"))));}}.eval(context, (common.DecoratedNode)((core.NEither)context.childDecorated(silver.driver.PperformActions.i_unitin).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:performActions", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PperformActions.invoke(children[0]);
		}
	};
}