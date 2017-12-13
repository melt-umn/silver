
package silver.analysis.warnings.defs;

public final class PraiseImplicitFwdEqFlowTypesForAttr extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_attr = 1;
	public static final int i_prods = 2;
	public static final int i_e = 3;
	public static final int i_depsForThisAttr = 4;
	public static final int i_myGraphs = 5;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForAttr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PraiseImplicitFwdEqFlowTypesForAttr(final Object c_l, final Object c_attr, final Object c_prods, final Object c_e, final Object c_depsForThisAttr, final Object c_myGraphs) {
		this.child_l = c_l;
		this.child_attr = c_attr;
		this.child_prods = c_prods;
		this.child_e = c_e;
		this.child_depsForThisAttr = c_depsForThisAttr;
		this.child_myGraphs = c_myGraphs;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_prods;
	public final common.ConsCell getChild_prods() {
		return (common.ConsCell) (child_prods = common.Util.demand(child_prods));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}

	private Object child_depsForThisAttr;
	public final Object getChild_depsForThisAttr() {
		return (Object) (child_depsForThisAttr = common.Util.demand(child_depsForThisAttr));
	}

	private Object child_myGraphs;
	public final Object getChild_myGraphs() {
		return (Object) (child_myGraphs = common.Util.demand(child_myGraphs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_attr: return getChild_attr();
			case i_prods: return getChild_prods();
			case i_e: return getChild_e();
			case i_depsForThisAttr: return getChild_depsForThisAttr();
			case i_myGraphs: return getChild_myGraphs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_attr: return child_attr;
			case i_prods: return child_prods;
			case i_e: return child_e;
			case i_depsForThisAttr: return child_depsForThisAttr;
			case i_myGraphs: return child_myGraphs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return "silver:analysis:warnings:defs:raiseImplicitFwdEqFlowTypesForAttr";
	}

	public static common.ConsCell invoke(final Object c_l, final Object c_attr, final Object c_prods, final Object c_e, final Object c_depsForThisAttr, final Object c_myGraphs) {
		try {
		final common.DecoratedNode context = new PraiseImplicitFwdEqFlowTypesForAttr(c_l, c_attr, c_prods, c_e, c_depsForThisAttr, c_myGraphs).decorate();
		//if null(prods) then [] else case lookupSyn(headProdName, attr, e), lookupFwd(headProdName, e) of _::_, _ -> [] | [], fwdFD::_ -> if null(diff) then [] else [ wrn(l, "Implicit forward copy equation for attribute " ++ attr ++ " on production " ++ headProdName ++ " exceeds the flow type for this attribute because the forward additionally depends on " ++ implode(", ", diff)) ] | [], [] -> [] end ++ raiseImplicitFwdEqFlowTypesForAttr(l, attr, tail(prods), e, depsForThisAttr, myGraphs)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_prods))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_747___sv_tmp_pv_748 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_749___sv_tmp_pv_750 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)core.Pnil.invoke()); }
else if(scrutinee.nil()) { return (common.ConsCell)new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_743___sv_pv_744_fwdFD = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_745___sv_tmp_pv_746 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_742_fwdFD = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDef)(__SV_LOCAL_743___sv_pv_744_fwdFD.eval())); } };
return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.analysis.warnings.defs.Init.diff__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForAttr))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Implicit forward copy equation for attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_attr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" on production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.analysis.warnings.defs.Init.headProdName__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForAttr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" exceeds the flow type for this attribute because the forward additionally depends on ")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), context.localAsIsLazy(silver.analysis.warnings.defs.Init.diff__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForAttr)))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:defs 'Inh.sv', 421, 7, 426, 10, 18653, 19233\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.flow.env.PlookupFwd.invoke(context.localAsIsLazy(silver.analysis.warnings.defs.Init.headProdName__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForAttr), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_e)))); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:defs 'Inh.sv', 421, 7, 426, 10, 18653, 19233\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.flow.env.PlookupSyn.invoke(context.localAsIsLazy(silver.analysis.warnings.defs.Init.headProdName__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForAttr), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_attr), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_e)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_l)), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_attr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_prods))); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_e), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_depsForThisAttr), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForAttr.i_myGraphs))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:raiseImplicitFwdEqFlowTypesForAttr", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PraiseImplicitFwdEqFlowTypesForAttr.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}