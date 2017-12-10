
package silver.extension.patternmatching;

public final class PallConCaseTransform extends common.FunctionNode {

	public static final int i_restExprs = 0;
	public static final int i_failCase = 1;
	public static final int i_retType = 2;
	public static final int i_mrs = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.definition.core.NExpr.class,silver.definition.type.NType.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_allConCaseTransform;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_failCase] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_retType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PallConCaseTransform(final Object c_restExprs, final Object c_failCase, final Object c_retType, final Object c_mrs) {
		this.child_restExprs = c_restExprs;
		this.child_failCase = c_failCase;
		this.child_retType = c_retType;
		this.child_mrs = c_mrs;

	}

	private Object child_restExprs;
	public final common.ConsCell getChild_restExprs() {
		return (common.ConsCell) (child_restExprs = common.Util.demand(child_restExprs));
	}

	private Object child_failCase;
	public final silver.definition.core.NExpr getChild_failCase() {
		return (silver.definition.core.NExpr) (child_failCase = common.Util.demand(child_failCase));
	}

	private Object child_retType;
	public final silver.definition.type.NType getChild_retType() {
		return (silver.definition.type.NType) (child_retType = common.Util.demand(child_retType));
	}

	private Object child_mrs;
	public final common.ConsCell getChild_mrs() {
		return (common.ConsCell) (child_mrs = common.Util.demand(child_mrs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_restExprs: return getChild_restExprs();
			case i_failCase: return getChild_failCase();
			case i_retType: return getChild_retType();
			case i_mrs: return getChild_mrs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_restExprs: return child_restExprs;
			case i_failCase: return child_failCase;
			case i_retType: return child_retType;
			case i_mrs: return child_mrs;

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
		return "silver:extension:patternmatching:allConCaseTransform";
	}

	public static silver.modification.primitivepattern.NPrimPattern invoke(final Object c_restExprs, final Object c_failCase, final Object c_retType, final Object c_mrs) {
		try {
		final common.DecoratedNode context = new PallConCaseTransform(c_restExprs, c_failCase, c_retType, c_mrs).decorate();
		//case head(mrs).headPattern of prodAppPattern(qn, _, _, _) -> prodPattern(qn, '(', convStringsToVarBinders(names, l), ')', '->', subcase,location=l) | intPattern(it) -> integerPattern(it, '->', subcase,location=l) | strPattern(it) -> stringPattern(it, '->', subcase,location=l) | truePattern(_) -> booleanPattern("true", '->', subcase,location=l) | falsePattern(_) -> booleanPattern("false", '->', subcase,location=l) | nilListPattern(_, _) -> nilPattern(subcase,location=l) | consListPattern(h, _, t) -> conslstPattern(head(names), head(tail(names)), subcase,location=l) end
		return (silver.modification.primitivepattern.NPrimPattern)(new common.PatternLazy<common.DecoratedNode, silver.modification.primitivepattern.NPrimPattern>() { public final silver.modification.primitivepattern.NPrimPattern eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PconsListPattern) { final common.Thunk<Object> __SV_LOCAL___pv7374___sv_pv_7375_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7376___sv_tmp_pv_7377 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TColonColon_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv7378___sv_pv_7373_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7379_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7378___sv_pv_7373_t.eval())); } };
return ((silver.modification.primitivepattern.NPrimPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7380_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7374___sv_pv_7375_h.eval())); } };
return ((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PconslstPattern(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)core.Phead.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.names__ON__silver_extension_patternmatching_allConCaseTransform))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.names__ON__silver_extension_patternmatching_allConCaseTransform))); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PfalsePattern) { final common.Thunk<Object> __SV_LOCAL___pv7381___sv_tmp_pv_7382 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TFalse_kwd)scrutinee.childAsIs(0); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PbooleanPattern((new common.StringCatter("false")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TArrow_kwd((new common.StringCatter("->")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PintPattern) { final common.Thunk<Object> __SV_LOCAL___pv7384___sv_pv_7383_it = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TInt_t)scrutinee.childAsIs(0); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7385_it = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TInt_t)(__SV_LOCAL___pv7384___sv_pv_7383_it.eval())); } };
return ((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PintegerPattern(__SV_LOCAL_7385_it, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TArrow_kwd((new common.StringCatter("->")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PnilListPattern) { final common.Thunk<Object> __SV_LOCAL___pv7386___sv_tmp_pv_7387 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.extension.list.TLSqr_t)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7388___sv_tmp_pv_7389 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.extension.list.TRSqr_t)scrutinee.childAsIs(1); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PnilPattern(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PprodAppPattern) { final common.Thunk<Object> __SV_LOCAL___pv7391___sv_pv_7390_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7392___sv_tmp_pv_7393 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv7394___sv_tmp_pv_7395 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv7396___sv_tmp_pv_7397 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7398_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7391___sv_pv_7390_qn.eval())); } };
return ((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PprodPattern(common.Thunk.transformUndecorate(__SV_LOCAL_7398_qn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NVarBinders)silver.extension.patternmatching.PconvStringsToVarBinders.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.names__ON__silver_extension_patternmatching_allConCaseTransform), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TArrow_kwd((new common.StringCatter("->")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PstrPattern) { final common.Thunk<Object> __SV_LOCAL___pv7400___sv_pv_7399_it = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TString_t)scrutinee.childAsIs(0); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7401_it = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.TString_t)(__SV_LOCAL___pv7400___sv_pv_7399_it.eval())); } };
return ((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PstringPattern(__SV_LOCAL_7401_it, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TArrow_kwd((new common.StringCatter("->")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PtruePattern) { final common.Thunk<Object> __SV_LOCAL___pv7402___sv_tmp_pv_7403 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TTrue_kwd)scrutinee.childAsIs(0); } };
 return (silver.modification.primitivepattern.NPrimPattern)((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PbooleanPattern((new common.StringCatter("true")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TArrow_kwd((new common.StringCatter("->")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform)))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.modification.primitivepattern.NPrimPattern)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:patternmatching 'Case.sv', 271, 4, 280, 7, 9925, 10543\n"))));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)((silver.extension.patternmatching.NAbstractMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PallConCaseTransform.i_mrs))).decorate(context, (common.Lazy[])null).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:allConCaseTransform", t);
		}
	}

	public static final common.NodeFactory<silver.modification.primitivepattern.NPrimPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.modification.primitivepattern.NPrimPattern> {
		@Override
		public silver.modification.primitivepattern.NPrimPattern invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PallConCaseTransform.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}