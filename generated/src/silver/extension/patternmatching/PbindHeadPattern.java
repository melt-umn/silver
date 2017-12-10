
package silver.extension.patternmatching;

public final class PbindHeadPattern extends common.FunctionNode {

	public static final int i_headExpr = 0;
	public static final int i_headType = 1;
	public static final int i_rule = 2;


	public static final Class<?> childTypes[] = { silver.definition.core.NExpr.class,silver.definition.type.NType.class,silver.extension.patternmatching.NAbstractMatchRule.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_bindHeadPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_headExpr] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_headType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_rule] = new common.Lazy[silver.extension.patternmatching.NAbstractMatchRule.num_inh_attrs];

	}

	public PbindHeadPattern(final Object c_headExpr, final Object c_headType, final Object c_rule) {
		this.child_headExpr = c_headExpr;
		this.child_headType = c_headType;
		this.child_rule = c_rule;

	}

	private Object child_headExpr;
	public final silver.definition.core.NExpr getChild_headExpr() {
		return (silver.definition.core.NExpr) (child_headExpr = common.Util.demand(child_headExpr));
	}

	private Object child_headType;
	public final silver.definition.type.NType getChild_headType() {
		return (silver.definition.type.NType) (child_headType = common.Util.demand(child_headType));
	}

	private Object child_rule;
	public final silver.extension.patternmatching.NAbstractMatchRule getChild_rule() {
		return (silver.extension.patternmatching.NAbstractMatchRule) (child_rule = common.Util.demand(child_rule));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_headExpr: return getChild_headExpr();
			case i_headType: return getChild_headType();
			case i_rule: return getChild_rule();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_headExpr: return child_headExpr;
			case i_headType: return child_headType;
			case i_rule: return child_rule;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:patternmatching:bindHeadPattern";
	}

	public static silver.extension.patternmatching.NAbstractMatchRule invoke(final Object c_headExpr, final Object c_headType, final Object c_rule) {
		try {
		final common.DecoratedNode context = new PbindHeadPattern(c_headExpr, c_headType, c_rule).decorate();
		//case rule of matchRule(headPat::restPat, e) -> matchRule(restPat, case headPat.patternVariableName of just(pvn) -> makeLet(rule.location, pvn, headType, headExpr, e) | nothing() -> e end,location=rule.location) end
		return (silver.extension.patternmatching.NAbstractMatchRule)(new common.PatternLazy<common.DecoratedNode, silver.extension.patternmatching.NAbstractMatchRule>() { public final silver.extension.patternmatching.NAbstractMatchRule eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PmatchRule) { final common.Thunk<Object> __SV_LOCAL___pv7396___sv_tmp_pv_7395 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7397___sv_pv_7398_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (silver.extension.patternmatching.NAbstractMatchRule)new common.PatternLazy<common.ConsCell, silver.extension.patternmatching.NAbstractMatchRule>() { public final silver.extension.patternmatching.NAbstractMatchRule eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_7399___sv_pv_7400_headPat = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_7401___sv_pv_7402_restPat = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((silver.extension.patternmatching.NAbstractMatchRule)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7403_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7397___sv_pv_7398_e.eval())); } };
return ((silver.extension.patternmatching.NAbstractMatchRule)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7404_restPat = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_7401___sv_pv_7402_restPat.eval())); } };
return ((silver.extension.patternmatching.NAbstractMatchRule)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7405_headPat = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL_7399___sv_pv_7400_headPat.eval())); } };
return ((silver.extension.patternmatching.NAbstractMatchRule)new silver.extension.patternmatching.PmatchRule(__SV_LOCAL_7404_restPat, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv7414___sv_pv_7413_pvn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7415_pvn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv7414___sv_pv_7413_pvn.eval())); } };
return ((silver.definition.core.NExpr)silver.extension.patternmatching.PmakeLet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NAbstractMatchRule)context.childDecorated(silver.extension.patternmatching.PbindHeadPattern.i_rule).undecorate()).getAnno_core_location()); } }, __SV_LOCAL_7415_pvn, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PbindHeadPattern.i_headType)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PbindHeadPattern.i_headExpr)), common.Thunk.transformUndecorate(__SV_LOCAL_7403_e))); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (silver.definition.core.NExpr)((silver.definition.core.NExpr)((common.DecoratedNode)__SV_LOCAL_7403_e.eval()).undecorate()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:patternmatching 'Case.sv', 306, 8, 309, 11, 11429, 11575\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)((common.DecoratedNode)(__SV_LOCAL_7405_headPat.eval())).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternVariableName__ON__silver_extension_patternmatching_Pattern)).decorate(context, (common.Lazy[])null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NAbstractMatchRule)context.childDecorated(silver.extension.patternmatching.PbindHeadPattern.i_rule).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); }return ((silver.extension.patternmatching.NAbstractMatchRule)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:patternmatching 'Case.sv', 303, 9, 310, 5, 11342, 11606\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv7396___sv_tmp_pv_7395.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.extension.patternmatching.NAbstractMatchRule)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:patternmatching 'Case.sv', 303, 9, 310, 5, 11342, 11606\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.patternmatching.PbindHeadPattern.i_rule)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:bindHeadPattern", t);
		}
	}

	public static final common.NodeFactory<silver.extension.patternmatching.NAbstractMatchRule> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.patternmatching.NAbstractMatchRule> {
		@Override
		public silver.extension.patternmatching.NAbstractMatchRule invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbindHeadPattern.invoke(children[0], children[1], children[2]);
		}
	};
}