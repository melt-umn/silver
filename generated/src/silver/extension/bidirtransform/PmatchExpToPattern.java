
package silver.extension.bidirtransform;

public final class PmatchExpToPattern extends common.FunctionNode {

	public static final int i_e = 0;
	public static final int i_pattern = 1;
	public static final int i_env = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.extension.patternmatching.NPattern.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_matchExpToPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_pattern] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	}

	public PmatchExpToPattern(final Object c_e, final Object c_pattern, final Object c_env) {
		this.child_e = c_e;
		this.child_pattern = c_pattern;
		this.child_env = c_env;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
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
			case i_e: return getChild_e();
			case i_pattern: return getChild_pattern();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
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
		return "silver:extension:bidirtransform:matchExpToPattern";
	}

	public static core.NPair invoke(final Object c_e, final Object c_pattern, final Object c_env) {
		try {
		final common.DecoratedNode context = new PmatchExpToPattern(c_e, c_pattern, c_env).decorate();
		//case pattern of prodAppPattern(_, _, pl, _) -> case e of application(e2, _, appexprs, _, _, _) -> matchAppExpsToPattern(appexprs, pl.rawPatternList, env) | _ -> pair([], []) end | varPattern(v) -> pair([ e ], [ v.name ]) | _ -> pair([], []) end
		return (core.NPair)(((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12264___fail_12265 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PprodAppPattern) { final common.Thunk<Object> __SV_LOCAL___pv12273___sv_tmp_pv_12274 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12275___sv_tmp_pv_12276 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12277___sv_pv_12272_pl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12278___sv_tmp_pv_12279 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(3); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12280_pl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12277___sv_pv_12272_pl.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12281___fail_12282 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv12284___sv_pv_12285_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12286___sv_tmp_pv_12287 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12288___sv_pv_12283_appexprs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12289___sv_tmp_pv_12290 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv12291___sv_tmp_pv_12292 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv12293___sv_tmp_pv_12294 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12295_appexprs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12288___sv_pv_12283_appexprs.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12296_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12284___sv_pv_12285_e2.eval())); } };
return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpsToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12295_appexprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_12280_pl.eval())).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList)); } }, context.childAsIsLazy(silver.extension.bidirtransform.PmatchExpToPattern.i_env))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)(__SV_LOCAL_12281___fail_12282.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PmatchExpToPattern.i_e))); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PvarPattern) { final common.Thunk<Object> __SV_LOCAL___pv12311___sv_pv_12310_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12312_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12311___sv_pv_12310_v.eval())); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PmatchExpToPattern.i_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_12312_v.eval())).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)(__SV_LOCAL_12264___fail_12265.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchExpToPattern.i_pattern)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:matchExpToPattern", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmatchExpToPattern.invoke(children[0], children[1], children[2]);
		}
	};
}