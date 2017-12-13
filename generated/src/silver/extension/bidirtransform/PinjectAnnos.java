
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
		return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13400___fail_13401 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PerrorExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)((common.StringCatter)core.Pfoldl.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13393_s1 = args[0];
final Object __SV_LAMBDA_PARAM_13394_s = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13393_s1)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("-")), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13394_s))));
  }
}), (new common.StringCatter("")), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos)))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv13405___sv_pv_13406_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13407___sv_tmp_pv_13408 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13409___sv_pv_13410_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13411___sv_tmp_pv_13412 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13413___sv_pv_13404_annexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13414___sv_tmp_pv_13415 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13416_annexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13413___sv_pv_13404_annexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13417_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13409___sv_pv_13410_appexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13418_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13405___sv_pv_13406_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13418_e), common.Thunk.transformUndecorate(__SV_LOCAL_13417_appexps), common.Thunk.transformUndecorate(__SV_LOCAL_13416_annexps), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PapplicationEmpty) { final common.Thunk<Object> __SV_LOCAL___pv13431___sv_pv_13430_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13432___sv_tmp_pv_13433 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13434___sv_tmp_pv_13435 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13436_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13431___sv_pv_13430_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13436_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.definition.core.PemptyAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PapplicationExpr) { final common.Thunk<Object> __SV_LOCAL___pv13445___sv_pv_13446_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13447___sv_tmp_pv_13448 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13449___sv_pv_13444_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13450___sv_tmp_pv_13451 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13452_appexps = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13449___sv_pv_13444_appexps.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13453_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13445___sv_pv_13446_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectApplication(common.Thunk.transformUndecorate(__SV_LOCAL_13453_e), common.Thunk.transformUndecorate(__SV_LOCAL_13452_appexps), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PbaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv13464___sv_pv_13463_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13465_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13464___sv_pv_13463_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PchildReference) { final common.Thunk<Object> __SV_LOCAL___pv13467___sv_pv_13466_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13468_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13467___sv_pv_13466_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PforwardReference) { final common.Thunk<Object> __SV_LOCAL___pv13470___sv_pv_13469_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13471_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13470___sv_pv_13469_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PfunctionReference) { final common.Thunk<Object> __SV_LOCAL___pv13473___sv_pv_13472_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13474_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13473___sv_pv_13472_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PglobalValueReference) { final common.Thunk<Object> __SV_LOCAL___pv13476___sv_pv_13475_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13477_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13476___sv_pv_13475_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PintConst) { final common.Thunk<Object> __SV_LOCAL___pv13479___sv_pv_13478_i = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TInt_t)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13480_i = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TInt_t)(__SV_LOCAL___pv13479___sv_pv_13478_i.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PlhsReference) { final common.Thunk<Object> __SV_LOCAL___pv13482___sv_pv_13481_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13483_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13482___sv_pv_13481_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PlocalReference) { final common.Thunk<Object> __SV_LOCAL___pv13485___sv_pv_13484_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13486_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13485___sv_pv_13484_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PnestedExpr) { final common.Thunk<Object> __SV_LOCAL___pv13488___sv_tmp_pv_13489 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13490___sv_pv_13487_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13491___sv_tmp_pv_13492 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13493_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13490___sv_pv_13487_e.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13493_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PproductionReference) { final common.Thunk<Object> __SV_LOCAL___pv13523___sv_pv_13522_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13524_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13523___sv_pv_13522_qn.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PstringConst) { final common.Thunk<Object> __SV_LOCAL___pv13526___sv_pv_13525_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TString_t)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13527_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TString_t)(__SV_LOCAL___pv13526___sv_pv_13525_s.eval())); } };
return context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate(); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PterminalConstructor) { final common.Thunk<Object> __SV_LOCAL___pv13529___sv_pv_13530_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTerminal_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13531___sv_pv_13532_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13533___sv_pv_13534_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13535___sv_pv_13536_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13537___sv_pv_13538_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13539___sv_pv_13540_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(5); } };
final common.Thunk<Object> __SV_LOCAL___pv13541___sv_pv_13542_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(6); } };
final common.Thunk<Object> __SV_LOCAL___pv13543___sv_pv_13528_g = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(7); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13544_g = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13543___sv_pv_13528_g.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13545_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13541___sv_pv_13542_e2.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13546_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13539___sv_pv_13540_f.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13547_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13537___sv_pv_13538_e1.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13548_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13535___sv_pv_13536_d.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13549_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13533___sv_pv_13534_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13550_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13531___sv_pv_13532_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13551_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TTerminal_kwd)(__SV_LOCAL___pv13529___sv_pv_13530_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PterminalConstructor(__SV_LOCAL_13551_a, __SV_LOCAL_13550_b, common.Thunk.transformUndecorate(__SV_LOCAL_13549_c), __SV_LOCAL_13548_d, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13547_e1), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13546_f, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13545_e2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13544_g, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PterminalFunction) { final common.Thunk<Object> __SV_LOCAL___pv13577___sv_pv_13578_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTerminal_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13579___sv_pv_13580_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13581___sv_pv_13582_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13583___sv_pv_13584_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv13585___sv_pv_13586_ex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv13587___sv_pv_13576_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13588_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13587___sv_pv_13576_f.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13589_ex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13585___sv_pv_13586_ex.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13590_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TComma_t)(__SV_LOCAL___pv13583___sv_pv_13584_d.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13591_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13581___sv_pv_13582_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13592_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13579___sv_pv_13580_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13593_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TTerminal_kwd)(__SV_LOCAL___pv13577___sv_pv_13578_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PterminalFunction(__SV_LOCAL_13593_a, __SV_LOCAL_13592_b, common.Thunk.transformUndecorate(__SV_LOCAL_13591_c), __SV_LOCAL_13590_d, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13589_ex), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13588_f, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PtoStringFunction) { final common.Thunk<Object> __SV_LOCAL___pv13608___sv_pv_13609_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TToString_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13610___sv_pv_13611_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13612___sv_pv_13613_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13614___sv_pv_13607_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13615_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TRParen_t)(__SV_LOCAL___pv13614___sv_pv_13607_c.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13616_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13612___sv_pv_13613_e.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13617_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TLParen_t)(__SV_LOCAL___pv13610___sv_pv_13611_b.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13618_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TToString_kwd)(__SV_LOCAL___pv13608___sv_pv_13609_a.eval())); } };
return ((silver.definition.core.NExpr)new silver.definition.core.PtoStringFunction(__SV_LOCAL_13618_a, __SV_LOCAL_13617_b, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13616_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, __SV_LOCAL_13615_c, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PcaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv13629___sv_pv_13630_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13631___sv_pv_13632_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13633___sv_pv_13634_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv13635___sv_pv_13628_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13636_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13635___sv_pv_13628_y.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13637_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13633___sv_pv_13634_e.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13638_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv13631___sv_pv_13632_x.eval())); } };
return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13639_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv13629___sv_pv_13630_es.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13640_e2 = args[0];

    return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_13640_e2)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } }));
  }
}), __SV_LOCAL_13639_es)); } }, __SV_LOCAL_13638_x, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(common.Thunk.transformUndecorate(__SV_LOCAL_13637_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnos.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnos.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(__SV_LOCAL_13636_y), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)(__SV_LOCAL_13400___fail_13401.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PinjectAnnos.i_toFill)); } }).eval());
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
