
package silver.extension.bidirtransform;

public final class PmatchAppExpToPattern extends common.FunctionNode {

	public static final int i_appexp = 0;
	public static final int i_pattern = 1;
	public static final int i_env = 2;


	public static final Class<?> childTypes[] = { silver.definition.core.NAppExpr.class,silver.extension.patternmatching.NPattern.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_matchAppExpToPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_appexp] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];
	childInheritedAttributes[i_pattern] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	}

	public PmatchAppExpToPattern(final Object c_appexp, final Object c_pattern, final Object c_env) {
		this.child_appexp = c_appexp;
		this.child_pattern = c_pattern;
		this.child_env = c_env;

	}

	private Object child_appexp;
	public final silver.definition.core.NAppExpr getChild_appexp() {
		return (silver.definition.core.NAppExpr) (child_appexp = common.Util.demand(child_appexp));
	}

	private Object child_pattern;
	public final silver.extension.patternmatching.NPattern getChild_pattern() {
		return (silver.extension.patternmatching.NPattern) (child_pattern = common.Util.demand(child_pattern));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_appexp: return getChild_appexp();
			case i_pattern: return getChild_pattern();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_appexp: return child_appexp;
			case i_pattern: return child_pattern;
			case i_env: return child_env;

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
		return "silver:extension:bidirtransform:matchAppExpToPattern";
	}

	public static core.NPair invoke(final Object c_appexp, final Object c_pattern, final Object c_env) {
		try {
		final common.DecoratedNode context = new PmatchAppExpToPattern(c_appexp, c_pattern, c_env).decorate();
		//case appexp of missingAppExpr(_) -> pair([], []) | presentAppExpr(e) -> matchExpToPattern(decorate e with {env = env;}, pattern, env) end
		return (core.NPair)(new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PmissingAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12248___sv_tmp_pv_12249 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TUnderScore_t)scrutinee.childAsIs(0); } };
 return (core.NPair)((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }
else if(scrutineeNode instanceof silver.definition.core.PpresentAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12251___sv_pv_12250_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12252_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12251___sv_pv_12250_e.eval())); } };
return ((core.NPair)silver.extension.bidirtransform.PmatchExpToPattern.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)((common.DecoratedNode)__SV_LOCAL_12252_e.eval()).undecorate()).decorate(context, common.Util.populateInh(silver.definition.core.NExpr.num_inh_attrs, new int[]{silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PmatchAppExpToPattern.i_env)); } }})); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PmatchAppExpToPattern.i_pattern)), context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpToPattern.i_env))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 170, 11, 173, 7, 7069, 7226\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpToPattern.i_appexp)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:matchAppExpToPattern", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmatchAppExpToPattern.invoke(children[0], children[1], children[2]);
		}
	};
}