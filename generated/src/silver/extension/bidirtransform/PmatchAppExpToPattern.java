
package silver.extension.bidirtransform;

public final class PmatchAppExpToPattern extends common.FunctionNode {

	public static final int i_appexp = 0;
	public static final int i_pattern = 1;


	public static final Class<?> childTypes[] = { silver.definition.core.NAppExpr.class,silver.extension.patternmatching.NPattern.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_matchAppExpToPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_appexp] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];
	childInheritedAttributes[i_pattern] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	}

	public PmatchAppExpToPattern(final Object c_appexp, final Object c_pattern) {
		this.child_appexp = c_appexp;
		this.child_pattern = c_pattern;

	}

	private Object child_appexp;
	public final silver.definition.core.NAppExpr getChild_appexp() {
		return (silver.definition.core.NAppExpr) (child_appexp = common.Util.demand(child_appexp));
	}

	private Object child_pattern;
	public final silver.extension.patternmatching.NPattern getChild_pattern() {
		return (silver.extension.patternmatching.NPattern) (child_pattern = common.Util.demand(child_pattern));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_appexp: return getChild_appexp();
			case i_pattern: return getChild_pattern();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_appexp: return child_appexp;
			case i_pattern: return child_pattern;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:extension:bidirtransform:matchAppExpToPattern";
	}

	public static core.NPair invoke(final Object c_appexp, final Object c_pattern) {
		try {
		final common.DecoratedNode context = new PmatchAppExpToPattern(c_appexp, c_pattern).decorate();
		//case appexp of missingAppExpr(_) -> pair([], []) | presentAppExpr(e) -> case pattern of wildcPattern(_) -> pair([], []) | _ -> matchExpToPattern(e, pattern) end end
		return (core.NPair)(new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PmissingAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12279___sv_tmp_pv_12280 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TUnderScore_t)scrutinee.childAsIs(0); } };
 return (core.NPair)((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }
else if(scrutineeNode instanceof silver.definition.core.PpresentAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12282___sv_pv_12281_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12283_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12282___sv_pv_12281_e.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12284___fail_12285 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.bidirtransform.PmatchExpToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12283_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PmatchAppExpToPattern.i_pattern)))); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PwildcPattern) { final common.Thunk<Object> __SV_LOCAL___pv12298___sv_tmp_pv_12299 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TUnderScore_t)scrutinee.childAsIs(0); } };
 return (core.NPair)((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)(__SV_LOCAL_12284___fail_12285.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpToPattern.i_pattern)); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 193, 11, 199, 7, 7622, 7842\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpToPattern.i_appexp)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:matchAppExpToPattern", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmatchAppExpToPattern.invoke(children[0], children[1]);
		}
	};
}