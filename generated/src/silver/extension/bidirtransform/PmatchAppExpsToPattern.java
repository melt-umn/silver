
package silver.extension.bidirtransform;

public final class PmatchAppExpsToPattern extends common.FunctionNode {

	public static final int i_appexps = 0;
	public static final int i_pattern = 1;
	public static final int i_env = 2;


	public static final Class<?> childTypes[] = { silver.definition.core.NAppExprs.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_matchAppExpsToPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_appexps] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	}

	public PmatchAppExpsToPattern(final Object c_appexps, final Object c_pattern, final Object c_env) {
		this.child_appexps = c_appexps;
		this.child_pattern = c_pattern;
		this.child_env = c_env;

	}

	private Object child_appexps;
	public final silver.definition.core.NAppExprs getChild_appexps() {
		return (silver.definition.core.NAppExprs) (child_appexps = common.Util.demand(child_appexps));
	}

	private Object child_pattern;
	public final common.ConsCell getChild_pattern() {
		return (common.ConsCell) (child_pattern = common.Util.demand(child_pattern));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_appexps: return getChild_appexps();
			case i_pattern: return getChild_pattern();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_appexps: return child_appexps;
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
		return "silver:extension:bidirtransform:matchAppExpsToPattern";
	}

	public static core.NPair invoke(final Object c_appexps, final Object c_pattern, final Object c_env) {
		try {
		final common.DecoratedNode context = new PmatchAppExpsToPattern(c_appexps, c_pattern, c_env).decorate();
		//case appexps of snocAppExprs(es, _, e) -> joinPair(matchAppExpsToPattern(es, allHead(pattern), env), matchAppExpToPattern(e, last(pattern), env)) | oneAppExprs(e) -> matchAppExpToPattern(e, head(pattern), env) | _ -> pair([], []) end
		return (core.NPair)(((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12213___fail_12214 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12222___sv_pv_12221_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12223_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12222___sv_pv_12221_e.eval())); } };
return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12223_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_pattern))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_env))); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12225___sv_pv_12226_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12227___sv_tmp_pv_12228 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12229___sv_pv_12224_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12230_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12229___sv_pv_12224_e.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12231_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12225___sv_pv_12226_es.eval())); } };
return ((core.NPair)silver.extension.bidirtransform.PjoinPair.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpsToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12231_es), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PallHead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_pattern))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_env))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12230_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)core.Plast.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_pattern))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_env))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)(__SV_LOCAL_12213___fail_12214.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_appexps)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:matchAppExpsToPattern", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmatchAppExpsToPattern.invoke(children[0], children[1], children[2]);
		}
	};
}