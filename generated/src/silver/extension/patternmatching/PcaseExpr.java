
package silver.extension.patternmatching;

// top::Expr ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type 
public final class PcaseExpr extends silver.definition.core.NExpr {

	public static final int i_es = 0;
	public static final int i_ml = 1;
	public static final int i_failExpr = 2;
	public static final int i_retType = 3;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,silver.definition.core.NExpr.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_caseExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_failExpr] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_retType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PcaseExpr(final Object c_es, final Object c_ml, final Object c_failExpr, final Object c_retType, final Object a_core_location) {
		super(a_core_location);
		this.child_es = c_es;
		this.child_ml = c_ml;
		this.child_failExpr = c_failExpr;
		this.child_retType = c_retType;

	}

	private Object child_es;
	public final common.ConsCell getChild_es() {
		return (common.ConsCell) (child_es = common.Util.demand(child_es));
	}

	private Object child_ml;
	public final common.ConsCell getChild_ml() {
		return (common.ConsCell) (child_ml = common.Util.demand(child_ml));
	}

	private Object child_failExpr;
	public final silver.definition.core.NExpr getChild_failExpr() {
		return (silver.definition.core.NExpr) (child_failExpr = common.Util.demand(child_failExpr));
	}

	private Object child_retType;
	public final silver.definition.type.NType getChild_retType() {
		return (silver.definition.type.NType) (child_retType = common.Util.demand(child_retType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_es: return getChild_es();
			case i_ml: return getChild_ml();
			case i_failExpr: return getChild_failExpr();
			case i_retType: return getChild_retType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_es: return child_es;
			case i_ml: return child_ml;
			case i_failExpr: return child_failExpr;
			case i_retType: return child_retType;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7221___fail_7222 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es))) ? context.childDecorated(silver.extension.patternmatching.PcaseExpr.i_failExpr).undecorate() : (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.varRules__ON__silver_extension_patternmatching_caseExpr))) ? context.localDecorated(silver.extension.patternmatching.Init.allConCase__ON__silver_extension_patternmatching_caseExpr).undecorate() : (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.prodRules__ON__silver_extension_patternmatching_caseExpr))) ? context.localDecorated(silver.extension.patternmatching.Init.allVarCase__ON__silver_extension_patternmatching_caseExpr).undecorate() : context.localDecorated(silver.extension.patternmatching.Init.mixedCase__ON__silver_extension_patternmatching_caseExpr).undecorate()))); } };
return new common.PatternLazy<common.ConsCell, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_7224___sv_tmp_pv_7223 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_7225___sv_tmp_pv_7226 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PmatchRule) { final common.Thunk<Object> __SV_LOCAL___pv7237___sv_tmp_pv_7236 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7238___sv_pv_7239_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (silver.definition.core.NExpr)new common.PatternLazy<common.ConsCell, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7240_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7238___sv_pv_7239_e.eval())); } };
return ((silver.definition.core.NExpr)((common.DecoratedNode)__SV_LOCAL_7240_e.eval()).undecorate()); } }).eval()); }return ((silver.definition.core.NExpr)(__SV_LOCAL_7221___fail_7222.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv7237___sv_tmp_pv_7236.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)(__SV_LOCAL_7221___fail_7222.eval()));}}.eval(context, (common.DecoratedNode)((silver.extension.patternmatching.NAbstractMatchRule)(__SV_LOCAL_7224___sv_tmp_pv_7223.eval())).decorate(context, (common.Lazy[])null)); }return ((silver.definition.core.NExpr)(__SV_LOCAL_7221___fail_7222.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.patternmatching.PcaseExpr.i_ml))); } }).eval());
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
		return "silver:extension:patternmatching:caseExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = error("Internal error: pretty of intermediate data structure")
		silver.extension.patternmatching.PcaseExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal error: pretty of intermediate data structure")))); } };
		// top.errors <- case ml of matchRule([], e)::_::_ -> [ err(top.location, "Pattern has overlapping cases!") ] | _ -> [] end
		if(silver.extension.patternmatching.PcaseExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.extension.patternmatching.PcaseExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.extension.patternmatching.PcaseExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7241___fail_7242 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_7244___sv_tmp_pv_7243 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_7245___sv_tmp_pv_7246 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PmatchRule) { final common.Thunk<Object> __SV_LOCAL___pv7251___sv_tmp_pv_7250 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7252___sv_pv_7253_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.ConsCell)new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (common.ConsCell)new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_7254___sv_tmp_pv_7255 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_7256___sv_tmp_pv_7257 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7258_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7252___sv_pv_7253_e.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Pattern has overlapping cases!")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }).eval()); }return ((common.ConsCell)(__SV_LOCAL_7241___fail_7242.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL_7245___sv_tmp_pv_7246.eval()))); }return ((common.ConsCell)(__SV_LOCAL_7241___fail_7242.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv7251___sv_tmp_pv_7250.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_7241___fail_7242.eval()));}}.eval(context, (common.DecoratedNode)((silver.extension.patternmatching.NAbstractMatchRule)(__SV_LOCAL_7244___sv_tmp_pv_7243.eval())).decorate(context, (common.Lazy[])null)); }return ((common.ConsCell)(__SV_LOCAL_7241___fail_7242.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.patternmatching.PcaseExpr.i_ml))); } }).eval()); } });
		// partMRs = partition((.isVarMatchRule), ml)
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.partMRs__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.Ppartition.invoke(new common.AttributeSection.Undecorated(silver.extension.patternmatching.Init.silver_extension_patternmatching_isVarMatchRule__ON__silver_extension_patternmatching_AbstractMatchRule, context), context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_ml))); } };
		// varRules = partMRs.fst
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.varRules__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.patternmatching.Init.partMRs__ON__silver_extension_patternmatching_caseExpr).synthesized(core.Init.core_fst__ON__core_Pair)); } };
		// prodRules = partMRs.snd
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.prodRules__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.patternmatching.Init.partMRs__ON__silver_extension_patternmatching_caseExpr).synthesized(core.Init.core_snd__ON__core_Pair)); } };
		// allConCase = matchPrimitive(head(es), typerepTypeExpr(retType,location=top.location), foldPrimPatterns(map(allConCaseTransform(tail(es), failExpr, retType, _), groupMRules(prodRules))), failExpr,location=top.location)
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.allConCase__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.modification.primitivepattern.PmatchPrimitive(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PtyperepTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_retType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NPrimPatterns)silver.extension.patternmatching.PfoldPrimPatterns.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.extension.patternmatching.PallConCaseTransform.factory.invokePartial(new int[]{0, 1, 2}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_failExpr)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_retType))}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.patternmatching.PgroupMRules.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.prodRules__ON__silver_extension_patternmatching_caseExpr))); } })); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_failExpr)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } };
		// allVarCase = caseExpr(tail(es), map(bindHeadPattern(head(es), freshType(), _), ml), failExpr, retType,location=top.location)
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.allVarCase__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.extension.patternmatching.PbindHeadPattern.factory.invokePartial(new int[]{0, 1}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } }}); } }, context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_ml))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_failExpr)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_retType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } };
		// freshFailName = "__fail_" ++ toString(genInt())
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.freshFailName__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("__fail_")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// mixedCase = makeLet(top.location, freshFailName, retType, caseExpr(es, varRules, failExpr, retType,location=top.location), caseExpr(es, prodRules, baseExpr(qName(top.location, freshFailName),location=top.location), retType,location=top.location))
		silver.extension.patternmatching.PcaseExpr.localAttributes[silver.extension.patternmatching.Init.mixedCase__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.patternmatching.PmakeLet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.extension.patternmatching.Init.freshFailName__ON__silver_extension_patternmatching_caseExpr), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_retType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es), context.localAsIsLazy(silver.extension.patternmatching.Init.varRules__ON__silver_extension_patternmatching_caseExpr), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_failExpr)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_retType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(context.childAsIsLazy(silver.extension.patternmatching.PcaseExpr.i_es), context.localAsIsLazy(silver.extension.patternmatching.Init.prodRules__ON__silver_extension_patternmatching_caseExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.extension.patternmatching.Init.freshFailName__ON__silver_extension_patternmatching_caseExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PcaseExpr.i_retType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } })); } };

	}

	public static final common.NodeFactory<PcaseExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcaseExpr> {

		@Override
		public PcaseExpr invoke(final Object[] children, final Object[] annotations) {
			return new PcaseExpr(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
