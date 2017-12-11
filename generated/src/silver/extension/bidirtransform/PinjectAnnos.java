
package silver.extension.bidirtransform;

// top::Expr ::= toFill::Expr toInject::AnnoAppExprs needAnnos::[String] 
public final class PinjectAnnos extends silver.definition.core.NExpr {

	public static final int i_toFill = 0;
	public static final int i_toInject = 1;
	public static final int i_needAnnos = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.NAnnoAppExprs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_injectAnnos;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_toInject] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PinjectAnnos(final Object c_toFill, final Object c_toInject, final Object c_needAnnos, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_toInject = c_toInject;
		this.child_needAnnos = c_needAnnos;

	}

	private Object child_toFill;
	public final silver.definition.core.NExpr getChild_toFill() {
		return (silver.definition.core.NExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_toInject;
	public final silver.definition.core.NAnnoAppExprs getChild_toInject() {
		return (silver.definition.core.NAnnoAppExprs) (child_toInject = common.Util.demand(child_toInject));
	}

	private Object child_needAnnos;
	public final common.ConsCell getChild_needAnnos() {
		return (common.ConsCell) (child_needAnnos = common.Util.demand(child_needAnnos));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_toInject: return getChild_toInject();
			case i_needAnnos: return getChild_needAnnos();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_toInject: return child_toInject;
			case i_needAnnos: return child_needAnnos;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13165___fail_13166 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PerrorExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)((common.StringCatter)core.Pfoldl.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13158_s1 = args[0];
final Object __SV_LAMBDA_PARAM_13159_s = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13158_s1)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("-")), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13159_s))));
  }
}), (new common.StringCatter("")), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos)))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv13170___sv_pv_13171_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13172___sv_tmp_pv_13173 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13174___sv_pv_13175_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13176___sv_tmp_pv_13177 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13178___sv_pv_13169_annexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13179___sv_tmp_pv_13180 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13181_annexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13178___sv_pv_13169_annexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13182_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13174___sv_pv_13175_appexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13183_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13170___sv_pv_13171_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13183_e), common.Thunk.transformUndecorate(__SV_LOCAL_13182_appexps), common.Thunk.transformUndecorate(__SV_LOCAL_13181_annexps), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PapplicationEmpty) { final common.Thunk<Object> __SV_LOCAL___pv13196___sv_pv_13195_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13197___sv_tmp_pv_13198 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13199___sv_tmp_pv_13200 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13201_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13196___sv_pv_13195_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13201_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.definition.core.PemptyAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PapplicationExpr) { final common.Thunk<Object> __SV_LOCAL___pv13210___sv_pv_13211_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13212___sv_tmp_pv_13213 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13214___sv_pv_13209_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13215___sv_tmp_pv_13216 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13217_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13214___sv_pv_13209_appexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13218_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13210___sv_pv_13211_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13218_e), common.Thunk.transformUndecorate(__SV_LOCAL_13217_appexps), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PbaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv13229___sv_pv_13228_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13230_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13229___sv_pv_13228_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PchildReference) { final common.Thunk<Object> __SV_LOCAL___pv13232___sv_pv_13231_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13233_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13232___sv_pv_13231_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PforwardReference) { final common.Thunk<Object> __SV_LOCAL___pv13235___sv_pv_13234_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13236_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13235___sv_pv_13234_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PfunctionReference) { final common.Thunk<Object> __SV_LOCAL___pv13238___sv_pv_13237_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13239_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13238___sv_pv_13237_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PglobalValueReference) { final common.Thunk<Object> __SV_LOCAL___pv13241___sv_pv_13240_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13242_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13241___sv_pv_13240_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PintConst) { final common.Thunk<Object> __SV_LOCAL___pv13244___sv_pv_13243_i = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TInt_t)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13245_i = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TInt_t)(__SV_LOCAL___pv13244___sv_pv_13243_i.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PlhsReference) { final common.Thunk<Object> __SV_LOCAL___pv13247___sv_pv_13246_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13248_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13247___sv_pv_13246_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PlocalReference) { final common.Thunk<Object> __SV_LOCAL___pv13250___sv_pv_13249_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13251_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13250___sv_pv_13249_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PnestedExpr) { final common.Thunk<Object> __SV_LOCAL___pv13253___sv_tmp_pv_13254 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13255___sv_pv_13252_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13256___sv_tmp_pv_13257 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13258_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13255___sv_pv_13252_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13258_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PproductionReference) { final common.Thunk<Object> __SV_LOCAL___pv13288___sv_pv_13287_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13289_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13288___sv_pv_13287_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PstringConst) { final common.Thunk<Object> __SV_LOCAL___pv13291___sv_pv_13290_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TString_t)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13292_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TString_t)(__SV_LOCAL___pv13291___sv_pv_13290_s.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PterminalConstructor) { final common.Thunk<Object> __SV_LOCAL___pv13294___sv_pv_13295_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTerminal_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13296___sv_pv_13297_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13298___sv_pv_13299_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13300___sv_pv_13301_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13302___sv_pv_13303_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13304___sv_pv_13305_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(5); } };
final common.Thunk<Object> __SV_LOCAL___pv13306___sv_pv_13307_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(6); } };
final common.Thunk<Object> __SV_LOCAL___pv13308___sv_pv_13293_g = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(7); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13309_g = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13308___sv_pv_13293_g.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13310_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13306___sv_pv_13307_e2.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13311_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13304___sv_pv_13305_f.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13312_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13302___sv_pv_13303_e1.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13313_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13300___sv_pv_13301_d.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13314_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13298___sv_pv_13299_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13315_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13296___sv_pv_13297_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13316_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TTerminal_kwd)(__SV_LOCAL___pv13294___sv_pv_13295_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PterminalConstructor(__SV_LOCAL_13316_a, __SV_LOCAL_13315_b, common.Thunk.transformUndecorate(__SV_LOCAL_13314_c), __SV_LOCAL_13313_d, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13312_e1), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13311_f, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13310_e2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13309_g, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PterminalFunction) { final common.Thunk<Object> __SV_LOCAL___pv13342___sv_pv_13343_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTerminal_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13344___sv_pv_13345_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13346___sv_pv_13347_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13348___sv_pv_13349_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13350___sv_pv_13351_ex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13352___sv_pv_13341_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13353_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13352___sv_pv_13341_f.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13354_ex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13350___sv_pv_13351_ex.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13355_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13348___sv_pv_13349_d.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13356_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13346___sv_pv_13347_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13357_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13344___sv_pv_13345_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13358_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TTerminal_kwd)(__SV_LOCAL___pv13342___sv_pv_13343_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PterminalFunction(__SV_LOCAL_13358_a, __SV_LOCAL_13357_b, common.Thunk.transformUndecorate(__SV_LOCAL_13356_c), __SV_LOCAL_13355_d, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13354_ex), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13353_f, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PtoStringFunction) { final common.Thunk<Object> __SV_LOCAL___pv13373___sv_pv_13374_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TToString_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13375___sv_pv_13376_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13377___sv_pv_13378_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13379___sv_pv_13372_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13380_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13379___sv_pv_13372_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13381_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13377___sv_pv_13378_e.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13382_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13375___sv_pv_13376_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13383_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TToString_kwd)(__SV_LOCAL___pv13373___sv_pv_13374_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PtoStringFunction(__SV_LOCAL_13383_a, __SV_LOCAL_13382_b, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13381_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13380_c, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PcaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv13394___sv_pv_13395_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13396___sv_pv_13397_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13398___sv_pv_13399_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13400___sv_pv_13393_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13401_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13400___sv_pv_13393_y.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13402_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13398___sv_pv_13399_e.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13403_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv13396___sv_pv_13397_x.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13404_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv13394___sv_pv_13395_es.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13405_e2 = args[0];

    return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_13405_e2)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } }));
  }
}), __SV_LOCAL_13404_es)); } }, __SV_LOCAL_13403_x, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13402_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(__SV_LOCAL_13401_y), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)(__SV_LOCAL_13165___fail_13166.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill)); } }).eval());
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:extension:bidirtransform:injectAnnos";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PinjectAnnos> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinjectAnnos> {

		@Override
		public PinjectAnnos invoke(final Object[] children, final Object[] annotations) {
			return new PinjectAnnos(children[0], children[1], children[2], annotations[0]);
		}
	};

}
