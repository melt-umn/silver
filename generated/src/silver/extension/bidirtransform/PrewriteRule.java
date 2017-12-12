
package silver.extension.bidirtransform;

// rule::RewriteRule ::= rhs::Expr inName::String inType::Type outType::Type inProd::RewriteProduction hasProd::Boolean restore::Boolean 
public final class PrewriteRule extends silver.extension.bidirtransform.NRewriteRule {

	public static final int i_rhs = 0;
	public static final int i_inName = 1;
	public static final int i_inType = 2;
	public static final int i_outType = 3;
	public static final int i_inProd = 4;
	public static final int i_hasProd = 5;
	public static final int i_restore = 6;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,common.StringCatter.class,silver.definition.type.NType.class,silver.definition.type.NType.class,silver.extension.bidirtransform.NRewriteProduction.class,Boolean.class,Boolean.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteRule;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rhs] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_inType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_outType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_inProd] = new common.Lazy[silver.extension.bidirtransform.NRewriteProduction.num_inh_attrs];

	}

	public PrewriteRule(final Object c_rhs, final Object c_inName, final Object c_inType, final Object c_outType, final Object c_inProd, final Object c_hasProd, final Object c_restore, final Object a_core_location) {
		super(a_core_location);
		this.child_rhs = c_rhs;
		this.child_inName = c_inName;
		this.child_inType = c_inType;
		this.child_outType = c_outType;
		this.child_inProd = c_inProd;
		this.child_hasProd = c_hasProd;
		this.child_restore = c_restore;

	}

	private Object child_rhs;
	public final silver.definition.core.NExpr getChild_rhs() {
		return (silver.definition.core.NExpr) (child_rhs = common.Util.demand(child_rhs));
	}

	private Object child_inName;
	public final common.StringCatter getChild_inName() {
		return (common.StringCatter) (child_inName = common.Util.demand(child_inName));
	}

	private Object child_inType;
	public final silver.definition.type.NType getChild_inType() {
		return (silver.definition.type.NType) (child_inType = common.Util.demand(child_inType));
	}

	private Object child_outType;
	public final silver.definition.type.NType getChild_outType() {
		return (silver.definition.type.NType) (child_outType = common.Util.demand(child_outType));
	}

	private Object child_inProd;
	public final silver.extension.bidirtransform.NRewriteProduction getChild_inProd() {
		return (silver.extension.bidirtransform.NRewriteProduction) (child_inProd = common.Util.demand(child_inProd));
	}

	private Object child_hasProd;
	public final Boolean getChild_hasProd() {
		return (Boolean) (child_hasProd = common.Util.demand(child_hasProd));
	}

	private Object child_restore;
	public final Boolean getChild_restore() {
		return (Boolean) (child_restore = common.Util.demand(child_restore));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rhs: return getChild_rhs();
			case i_inName: return getChild_inName();
			case i_inType: return getChild_inType();
			case i_outType: return getChild_outType();
			case i_inProd: return getChild_inProd();
			case i_hasProd: return getChild_hasProd();
			case i_restore: return getChild_restore();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rhs: return child_rhs;
			case i_inName: return child_inName;
			case i_inType: return child_inType;
			case i_outType: return child_outType;
			case i_inProd: return child_inProd;
			case i_hasProd: return child_hasProd;
			case i_restore: return child_restore;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:rewriteRule erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:rewriteRule";
	}

	static void initProductionAttributeDefinitions() {
		// rhs.config = rule.config
		silver.extension.bidirtransform.PrewriteRule.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRule.i_rhs][silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rhs.downSubst = rule.downSubst
		silver.extension.bidirtransform.PrewriteRule.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRule.i_rhs][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rule.upSubst = rhs.upSubst
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRule.i_rhs).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// rhs.finalSubst = rule.upSubst
		silver.extension.bidirtransform.PrewriteRule.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRule.i_rhs][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rhs.defaultInheritedAnnos = []
		silver.extension.bidirtransform.PrewriteRule.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRule.i_rhs][silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// rule.errors := []
		if(silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule] == null)
			silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule);
		((common.CollectionAttribute)silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rule.errors <- inProd.errors
		if(silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule] == null)
			silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule);
		((common.CollectionAttribute)silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PrewriteRule.i_inProd).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction)); } });
		// rhsNs = case rhs of application(e, _, _, _, _, _) -> case e of baseExpr(qn) -> just(head(getProdFromGroups(qn.name, rule.absGroup, rule.cncGroup))) | _ -> nothing() end | _ -> nothing() end
		silver.extension.bidirtransform.PrewriteRule.localAttributes[silver.extension.bidirtransform.Init.rhsNs__ON__silver_extension_bidirtransform_rewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12521___fail_12522 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv12527___sv_pv_12526_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12528___sv_tmp_pv_12529 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12530___sv_tmp_pv_12531 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12532___sv_tmp_pv_12533 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv12534___sv_tmp_pv_12535 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv12536___sv_tmp_pv_12537 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12538_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12527___sv_pv_12526_e.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12539___fail_12540 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PbaseExpr) { final common.Thunk<Object> __SV_LOCAL___pv12547___sv_pv_12546_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12548_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12547___sv_pv_12546_qn.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdFromGroups.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_12548_qn.eval())).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } }, context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteRule), context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteRule))); } })); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_12539___fail_12540.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL_12538_e.eval()))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_12521___fail_12522.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PrewriteRule.i_rhs)); } }).eval()); } };
		// rule.hasProduction = hasProd
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_hasProduction__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childAsIs(silver.extension.bidirtransform.PrewriteRule.i_hasProd)); } };
		// rule.typerep = outType
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PrewriteRule.i_outType).undecorate(); } };
		// rule.inputType = inType
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputType__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PrewriteRule.i_inType).undecorate(); } };
		// rule.inputProduction = inProd
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputProduction__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PrewriteRule.i_inProd).undecorate(); } };
		// rule.shouldRestore = restore
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childAsIs(silver.extension.bidirtransform.PrewriteRule.i_restore)); } };
		// rule.outputStmt = if ! hasProd then (\ e::Expr  -> fillExpr(rhs, [ e ], [ inName ],location=e.location)) else (\ e::Expr  -> case e of application(_, _, aexpr, _, _, _) -> fillExpr(rhs, pullOutAppExprs(aexpr), inProd.inputNames,location=e.location) end)
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.childAsIs(silver.extension.bidirtransform.PrewriteRule.i_hasProd))) ? (new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12556_e = args[0];

    return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfillExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRule.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_12556_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrewriteRule.i_inName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_12556_e)).getAnno_core_location()); } }));
  }
}) : (new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12557_e = args[0];

    return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv12559___sv_tmp_pv_12560 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12561___sv_tmp_pv_12562 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12563___sv_pv_12558_aexpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12564___sv_tmp_pv_12565 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv12566___sv_tmp_pv_12567 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv12568___sv_tmp_pv_12569 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12570_aexpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12563___sv_pv_12558_aexpr.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfillExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRule.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PpullOutAppExprs.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12570_aexpr))); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRule.i_inProd, silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProduction), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_12557_e)).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'RewriteRule.sv', 166, 12, 168, 15, 5389, 5546\n"))));}}.eval(context, (common.DecoratedNode)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_12557_e)).decorate(context, (common.Lazy[])null));
  }
})); } };
		// rule.restoreStmt = (\ e::Expr  -> case e of application(_, _, aexpr, _, _, _) -> restoreExpr(rhs, pullOutAppExprs(aexpr), inProd.inputNames, rhsNs.fromJust,location=e.location) end)
		silver.extension.bidirtransform.PrewriteRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_restoreStmt__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12581_e = args[0];

    return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv12583___sv_tmp_pv_12584 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12585___sv_tmp_pv_12586 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12587___sv_pv_12582_aexpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12588___sv_tmp_pv_12589 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv12590___sv_tmp_pv_12591 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv12592___sv_tmp_pv_12593 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12594_aexpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12587___sv_pv_12582_aexpr.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PrestoreExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRule.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PpullOutAppExprs.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12594_aexpr))); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRule.i_inProd, silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProduction), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.localDecorated(silver.extension.bidirtransform.Init.rhsNs__ON__silver_extension_bidirtransform_rewriteRule).synthesized(core.Init.core_fromJust__ON__core_Maybe)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_12581_e)).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'RewriteRule.sv', 172, 12, 174, 15, 5608, 5784\n"))));}}.eval(context, (common.DecoratedNode)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_12581_e)).decorate(context, (common.Lazy[])null));
  }
}); } };

	}

	public static final common.NodeFactory<PrewriteRule> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteRule> {

		@Override
		public PrewriteRule invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteRule(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
