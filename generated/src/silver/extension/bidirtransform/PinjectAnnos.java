
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
		return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13147___fail_13148 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PerrorExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)((common.StringCatter)core.Pfoldl.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13140_s1 = args[0];
final Object __SV_LAMBDA_PARAM_13141_s = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13140_s1)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("-")), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13141_s))));
  }
}), (new common.StringCatter("")), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos)))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv13152___sv_pv_13153_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13154___sv_tmp_pv_13155 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13156___sv_pv_13157_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13158___sv_tmp_pv_13159 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13160___sv_pv_13151_annexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13161___sv_tmp_pv_13162 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13163_annexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13160___sv_pv_13151_annexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13164_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13156___sv_pv_13157_appexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13165_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13152___sv_pv_13153_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13165_e), common.Thunk.transformUndecorate(__SV_LOCAL_13164_appexps), common.Thunk.transformUndecorate(__SV_LOCAL_13163_annexps), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PapplicationEmpty) { final common.Thunk<Object> __SV_LOCAL___pv13178___sv_pv_13177_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13179___sv_tmp_pv_13180 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13181___sv_tmp_pv_13182 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13183_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13178___sv_pv_13177_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13183_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.definition.core.PemptyAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PapplicationExpr) { final common.Thunk<Object> __SV_LOCAL___pv13192___sv_pv_13193_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13194___sv_tmp_pv_13195 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13196___sv_pv_13191_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13197___sv_tmp_pv_13198 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13199_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13196___sv_pv_13191_appexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13200_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13192___sv_pv_13193_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13200_e), common.Thunk.transformUndecorate(__SV_LOCAL_13199_appexps), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PbaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv13211___sv_pv_13210_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13212_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13211___sv_pv_13210_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PchildReference) { final common.Thunk<Object> __SV_LOCAL___pv13214___sv_pv_13213_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13215_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13214___sv_pv_13213_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PforwardReference) { final common.Thunk<Object> __SV_LOCAL___pv13217___sv_pv_13216_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13218_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13217___sv_pv_13216_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PfunctionReference) { final common.Thunk<Object> __SV_LOCAL___pv13220___sv_pv_13219_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13221_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13220___sv_pv_13219_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PglobalValueReference) { final common.Thunk<Object> __SV_LOCAL___pv13223___sv_pv_13222_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13224_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13223___sv_pv_13222_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PintConst) { final common.Thunk<Object> __SV_LOCAL___pv13226___sv_pv_13225_i = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TInt_t)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13227_i = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TInt_t)(__SV_LOCAL___pv13226___sv_pv_13225_i.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PlhsReference) { final common.Thunk<Object> __SV_LOCAL___pv13229___sv_pv_13228_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13230_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13229___sv_pv_13228_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PlocalReference) { final common.Thunk<Object> __SV_LOCAL___pv13232___sv_pv_13231_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13233_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13232___sv_pv_13231_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PnestedExpr) { final common.Thunk<Object> __SV_LOCAL___pv13235___sv_tmp_pv_13236 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13237___sv_pv_13234_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13238___sv_tmp_pv_13239 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13240_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13237___sv_pv_13234_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13240_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PproductionReference) { final common.Thunk<Object> __SV_LOCAL___pv13270___sv_pv_13269_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13271_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13270___sv_pv_13269_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PstringConst) { final common.Thunk<Object> __SV_LOCAL___pv13273___sv_pv_13272_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TString_t)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13274_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TString_t)(__SV_LOCAL___pv13273___sv_pv_13272_s.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PterminalConstructor) { final common.Thunk<Object> __SV_LOCAL___pv13276___sv_pv_13277_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTerminal_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13278___sv_pv_13279_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13280___sv_pv_13281_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13282___sv_pv_13283_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13284___sv_pv_13285_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13286___sv_pv_13287_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(5); } };
final common.Thunk<Object> __SV_LOCAL___pv13288___sv_pv_13289_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(6); } };
final common.Thunk<Object> __SV_LOCAL___pv13290___sv_pv_13275_g = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(7); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13291_g = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13290___sv_pv_13275_g.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13292_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13288___sv_pv_13289_e2.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13293_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13286___sv_pv_13287_f.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13294_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13284___sv_pv_13285_e1.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13295_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13282___sv_pv_13283_d.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13296_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13280___sv_pv_13281_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13297_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13278___sv_pv_13279_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13298_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TTerminal_kwd)(__SV_LOCAL___pv13276___sv_pv_13277_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PterminalConstructor(__SV_LOCAL_13298_a, __SV_LOCAL_13297_b, common.Thunk.transformUndecorate(__SV_LOCAL_13296_c), __SV_LOCAL_13295_d, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13294_e1), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13293_f, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13292_e2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13291_g, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PterminalFunction) { final common.Thunk<Object> __SV_LOCAL___pv13324___sv_pv_13325_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTerminal_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13326___sv_pv_13327_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13328___sv_pv_13329_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13330___sv_pv_13331_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13332___sv_pv_13333_ex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13334___sv_pv_13323_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13335_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13334___sv_pv_13323_f.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13336_ex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13332___sv_pv_13333_ex.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13337_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13330___sv_pv_13331_d.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13338_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13328___sv_pv_13329_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13339_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13326___sv_pv_13327_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13340_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TTerminal_kwd)(__SV_LOCAL___pv13324___sv_pv_13325_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PterminalFunction(__SV_LOCAL_13340_a, __SV_LOCAL_13339_b, common.Thunk.transformUndecorate(__SV_LOCAL_13338_c), __SV_LOCAL_13337_d, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13336_ex), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13335_f, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PtoStringFunction) { final common.Thunk<Object> __SV_LOCAL___pv13355___sv_pv_13356_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TToString_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13357___sv_pv_13358_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13359___sv_pv_13360_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13361___sv_pv_13354_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13362_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13361___sv_pv_13354_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13363_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13359___sv_pv_13360_e.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13364_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13357___sv_pv_13358_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13365_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TToString_kwd)(__SV_LOCAL___pv13355___sv_pv_13356_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PtoStringFunction(__SV_LOCAL_13365_a, __SV_LOCAL_13364_b, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13363_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13362_c, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PcaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv13376___sv_pv_13377_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13378___sv_pv_13379_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13380___sv_pv_13381_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13382___sv_pv_13375_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13383_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13382___sv_pv_13375_y.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13384_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13380___sv_pv_13381_e.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13385_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv13378___sv_pv_13379_x.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13386_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv13376___sv_pv_13377_es.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13387_e2 = args[0];

    return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_13387_e2)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } }));
  }
}), __SV_LOCAL_13386_es)); } }, __SV_LOCAL_13385_x, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13384_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(__SV_LOCAL_13383_y), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)(__SV_LOCAL_13147___fail_13148.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill)); } }).eval());
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
