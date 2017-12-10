
package silver.analysis.warnings.defs;

public final class PcheckEqDeps extends common.FunctionNode {

	public static final int i_v = 0;
	public static final int i_l = 1;
	public static final int i_prodName = 2;
	public static final int i_prodNt = 3;
	public static final int i_flowEnv = 4;
	public static final int i_realEnv = 5;
	public static final int i_anonResolve = 6;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowVertex.class,core.NLocation.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_checkEqDeps;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_v] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PcheckEqDeps(final Object c_v, final Object c_l, final Object c_prodName, final Object c_prodNt, final Object c_flowEnv, final Object c_realEnv, final Object c_anonResolve) {
		this.child_v = c_v;
		this.child_l = c_l;
		this.child_prodName = c_prodName;
		this.child_prodNt = c_prodNt;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;
		this.child_anonResolve = c_anonResolve;

	}

	private Object child_v;
	public final silver.definition.flow.ast.NFlowVertex getChild_v() {
		return (silver.definition.flow.ast.NFlowVertex) (child_v = common.Util.demand(child_v));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_prodName;
	public final common.StringCatter getChild_prodName() {
		return (common.StringCatter) (child_prodName = common.Util.demand(child_prodName));
	}

	private Object child_prodNt;
	public final common.StringCatter getChild_prodNt() {
		return (common.StringCatter) (child_prodNt = common.Util.demand(child_prodNt));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}

	private Object child_anonResolve;
	public final common.ConsCell getChild_anonResolve() {
		return (common.ConsCell) (child_anonResolve = common.Util.demand(child_anonResolve));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_l: return getChild_l();
			case i_prodName: return getChild_prodName();
			case i_prodNt: return getChild_prodNt();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();
			case i_anonResolve: return getChild_anonResolve();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_l: return child_l;
			case i_prodName: return child_prodName;
			case i_prodNt: return child_prodNt;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;
			case i_anonResolve: return child_anonResolve;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return "silver:analysis:warnings:defs:checkEqDeps";
	}

	public static common.ConsCell invoke(final Object c_v, final Object c_l, final Object c_prodName, final Object c_prodNt, final Object c_flowEnv, final Object c_realEnv, final Object c_anonResolve) {
		try {
		final common.DecoratedNode context = new PcheckEqDeps(c_v, c_l, c_prodName, c_prodNt, c_flowEnv, c_realEnv, c_anonResolve).decorate();
		//case v of lhsInhVertex(_) -> [] | lhsSynVertex(attrName) -> [] | rhsVertex(sigName, attrName) -> if isInherited(attrName, realEnv) then if ! null(lookupInh(prodName, sigName, attrName, flowEnv)) || ! ignoreIfAutoCopyOnLhs(prodNt, realEnv, attrName) || ! sigNotAReference(sigName, realEnv) then [] else [ wrn(l, "Equation has transitive dependency on child " ++ sigName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.") ] else [] | localEqVertex(fName) -> [] | localVertex(fName, attrName) -> if isInherited(attrName, realEnv) then if ! null(lookupLocalInh(prodName, fName, attrName, flowEnv)) || fName == "forward" || ! sigNotAReference(fName, realEnv) then [] else [ wrn(l, "Equation has transitive dependency on local " ++ fName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.") ] else [] | anonEqVertex(fName) -> [] | anonVertex(fName, attrName) -> if isInherited(attrName, realEnv) then if ! null(lookupLocalInh(prodName, fName, attrName, flowEnv)) then [] else let anonl :: Maybe<Location> = lookupBy(stringEq, fName, anonResolve) in if anonl.isJust then [ wrn(anonl.fromJust, "Decoration requires inherited attribute for " ++ attrName ++ ".") ] else [] end else [] end
		return (common.ConsCell)(new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PanonEqVertex) { final common.Thunk<Object> __SV_LOCAL___pv464___sv_pv_463_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_465_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv464___sv_pv_463_fName.eval())); } };
return ((common.ConsCell)core.Pnil.invoke()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PanonVertex) { final common.Thunk<Object> __SV_LOCAL___pv467___sv_pv_468_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv469___sv_pv_466_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_470_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv469___sv_pv_466_attrName.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_471_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv467___sv_pv_468_fName.eval())); } };
return (((Boolean)silver.analysis.warnings.defs.PisInherited.invoke(__SV_LOCAL_470_attrName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_realEnv))) ? ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupLocalInh.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_prodName), __SV_LOCAL_471_fName, __SV_LOCAL_470_attrName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_flowEnv))); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_472_anonl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, __SV_LOCAL_471_fName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_anonResolve))); } };
return (((Boolean)((core.NMaybe)(__SV_LOCAL_472_anonl.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((core.NMaybe)(__SV_LOCAL_472_anonl.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Decoration requires inherited attribute for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_470_attrName.eval())), (common.StringCatter)(new common.StringCatter(".")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } }).eval())) : ((common.ConsCell)core.Pnil.invoke())); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PlhsInhVertex) { final common.Thunk<Object> __SV_LOCAL___pv478___sv_tmp_pv_479 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PlhsSynVertex) { final common.Thunk<Object> __SV_LOCAL___pv481___sv_pv_480_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_482_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv481___sv_pv_480_attrName.eval())); } };
return ((common.ConsCell)core.Pnil.invoke()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PlocalEqVertex) { final common.Thunk<Object> __SV_LOCAL___pv484___sv_pv_483_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_485_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv484___sv_pv_483_fName.eval())); } };
return ((common.ConsCell)core.Pnil.invoke()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PlocalVertex) { final common.Thunk<Object> __SV_LOCAL___pv487___sv_pv_488_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv489___sv_pv_486_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_490_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv489___sv_pv_486_attrName.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_491_fName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv487___sv_pv_488_fName.eval())); } };
return (((Boolean)silver.analysis.warnings.defs.PisInherited.invoke(__SV_LOCAL_490_attrName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_realEnv))) ? ((((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupLocalInh.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_prodName), __SV_LOCAL_491_fName, __SV_LOCAL_490_attrName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_flowEnv))); } }))) || ((common.StringCatter)(__SV_LOCAL_491_fName.eval())).equals((new common.StringCatter("forward")))) || (!((Boolean)silver.analysis.warnings.defs.PsigNotAReference.invoke(__SV_LOCAL_491_fName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_realEnv))))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Equation has transitive dependency on local ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_491_fName.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'s inherited attribute for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_490_attrName.eval())), (common.StringCatter)(new common.StringCatter(" but this equation appears to be missing.")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))) : ((common.ConsCell)core.Pnil.invoke())); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PrhsVertex) { final common.Thunk<Object> __SV_LOCAL___pv511___sv_pv_512_sigName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv513___sv_pv_510_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_514_attrName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv513___sv_pv_510_attrName.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_515_sigName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv511___sv_pv_512_sigName.eval())); } };
return (((Boolean)silver.analysis.warnings.defs.PisInherited.invoke(__SV_LOCAL_514_attrName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_realEnv))) ? ((((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupInh.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_prodName), __SV_LOCAL_515_sigName, __SV_LOCAL_514_attrName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_flowEnv))); } }))) || (!((Boolean)silver.analysis.warnings.defs.PignoreIfAutoCopyOnLhs.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_prodNt), context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_realEnv), __SV_LOCAL_514_attrName)))) || (!((Boolean)silver.analysis.warnings.defs.PsigNotAReference.invoke(__SV_LOCAL_515_sigName, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_realEnv))))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PcheckEqDeps.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Equation has transitive dependency on child ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_515_sigName.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'s inherited attribute for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_514_attrName.eval())), (common.StringCatter)(new common.StringCatter(" but this equation appears to be missing.")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))) : ((common.ConsCell)core.Pnil.invoke())); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:defs 'Inh.sv', 125, 9, 158, 5, 3837, 6067\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.analysis.warnings.defs.PcheckEqDeps.i_v)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:checkEqDeps", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcheckEqDeps.invoke(children[0], children[1], children[2], children[3], children[4], children[5], children[6]);
		}
	};
}