
package silver.analysis.warnings.defs;

public final class PraiseImplicitFwdEqFlowTypesForProd extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_prod = 1;
	public static final int i_attrs = 2;
	public static final int i_e = 3;
	public static final int i_fwdFlowDeps = 4;
	public static final int i_myFlow = 5;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PraiseImplicitFwdEqFlowTypesForProd(final Object c_l, final Object c_prod, final Object c_attrs, final Object c_e, final Object c_fwdFlowDeps, final Object c_myFlow) {
		this.child_l = c_l;
		this.child_prod = c_prod;
		this.child_attrs = c_attrs;
		this.child_e = c_e;
		this.child_fwdFlowDeps = c_fwdFlowDeps;
		this.child_myFlow = c_myFlow;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_attrs;
	public final common.ConsCell getChild_attrs() {
		return (common.ConsCell) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}

	private Object child_fwdFlowDeps;
	public final Object getChild_fwdFlowDeps() {
		return (Object) (child_fwdFlowDeps = common.Util.demand(child_fwdFlowDeps));
	}

	private Object child_myFlow;
	public final Object getChild_myFlow() {
		return (Object) (child_myFlow = common.Util.demand(child_myFlow));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_prod: return getChild_prod();
			case i_attrs: return getChild_attrs();
			case i_e: return getChild_e();
			case i_fwdFlowDeps: return getChild_fwdFlowDeps();
			case i_myFlow: return getChild_myFlow();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_prod: return child_prod;
			case i_attrs: return child_attrs;
			case i_e: return child_e;
			case i_fwdFlowDeps: return child_fwdFlowDeps;
			case i_myFlow: return child_myFlow;

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
		return "silver:analysis:warnings:defs:raiseImplicitFwdEqFlowTypesForProd";
	}

	public static common.ConsCell invoke(final Object c_l, final Object c_prod, final Object c_attrs, final Object c_e, final Object c_fwdFlowDeps, final Object c_myFlow) {
		try {
		final common.DecoratedNode context = new PraiseImplicitFwdEqFlowTypesForProd(c_l, c_prod, c_attrs, c_e, c_fwdFlowDeps, c_myFlow).decorate();
		//if null(attrs) then [] else case lookupSyn(prod, head(attrs).attrOccurring, e) of eq::_ -> [] | [] -> if null(diff) then [] else [ wrn(l, "Implicit forward copy equation for attribute " ++ head(attrs).attrOccurring ++ " in production " ++ prod ++ " exceeds flow type because the forward depends on " ++ implode(", ", diff)) ] end ++ raiseImplicitFwdEqFlowTypesForProd(l, prod, tail(attrs), e, fwdFlowDeps, myFlow)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_attrs))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_791___sv_pv_792_eq = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_793___sv_tmp_pv_794 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_790_eq = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDef)(__SV_LOCAL_791___sv_pv_792_eq.eval())); } };
return ((common.ConsCell)core.Pnil.invoke()); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)(((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.analysis.warnings.defs.Init.diff__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForProd))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Implicit forward copy equation for attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_attrs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" in production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_prod)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" exceeds flow type because the forward depends on ")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), context.localAsIsLazy(silver.analysis.warnings.defs.Init.diff__ON__silver_analysis_warnings_defs_raiseImplicitFwdEqFlowTypesForProd)))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:defs 'Inh.sv', 452, 7, 455, 10, 20671, 20995\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.flow.env.PlookupSyn.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_attrs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo)); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_e)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_l)), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_attrs))); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_e), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_fwdFlowDeps), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseImplicitFwdEqFlowTypesForProd.i_myFlow))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:raiseImplicitFwdEqFlowTypesForProd", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PraiseImplicitFwdEqFlowTypesForProd.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}