
package silver.extension.bidirtransform;

public final class PmatchAppExpsToPattern extends common.FunctionNode {

	public static final int i_appexps = 0;
	public static final int i_pattern = 1;


	public static final Class<?> childTypes[] = { silver.definition.core.NAppExprs.class,silver.extension.patternmatching.NPatternList.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_matchAppExpsToPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_appexps] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];
	childInheritedAttributes[i_pattern] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PmatchAppExpsToPattern(final Object c_appexps, final Object c_pattern) {
		this.child_appexps = c_appexps;
		this.child_pattern = c_pattern;

	}

	private Object child_appexps;
	public final silver.definition.core.NAppExprs getChild_appexps() {
		return (silver.definition.core.NAppExprs) (child_appexps = common.Util.demand(child_appexps));
	}

	private Object child_pattern;
	public final silver.extension.patternmatching.NPatternList getChild_pattern() {
		return (silver.extension.patternmatching.NPatternList) (child_pattern = common.Util.demand(child_pattern));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_appexps: return getChild_appexps();
			case i_pattern: return getChild_pattern();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_appexps: return child_appexps;
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
		return "silver:extension:bidirtransform:matchAppExpsToPattern";
	}

	public static core.NPair invoke(final Object c_appexps, final Object c_pattern) {
		try {
		final common.DecoratedNode context = new PmatchAppExpsToPattern(c_appexps, c_pattern).decorate();
		//case appexps of snocAppExprs(es, _, e) -> case pattern of patternList_more(p, _, pl) -> joinPair(matchAppExpsToPattern(es, patternList_more(p, ',', leftTailPattern(pl),location=pattern.location)), matchAppExpToPattern(e, lastElemPattern(pl))) end | oneAppExprs(e) -> case pattern of patternList_one(p) -> matchAppExpToPattern(e, p) end | _ -> pair([], []) end
		return (core.NPair)(((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12158___fail_12159 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12167___sv_pv_12166_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12168_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12167___sv_pv_12166_e.eval())); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_one) { final common.Thunk<Object> __SV_LOCAL___pv12173___sv_pv_12172_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12174_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12173___sv_pv_12172_p.eval())); } };
return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12168_e), common.Thunk.transformUndecorate(__SV_LOCAL_12174_p))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 157, 28, 159, 11, 6672, 6760\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_pattern)); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12177___sv_pv_12178_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12179___sv_tmp_pv_12180 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12181___sv_pv_12182_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12176_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12181___sv_pv_12182_e.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12175_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12177___sv_pv_12178_es.eval())); } };
return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_more) { final common.Thunk<Object> __SV_LOCAL___pv12185___sv_pv_12186_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12187___sv_tmp_pv_12188 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12189___sv_pv_12190_pl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12184_pl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12189___sv_pv_12190_pl.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12183_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12185___sv_pv_12186_p.eval())); } };
return ((core.NPair)silver.extension.bidirtransform.PjoinPair.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpsToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12175_es), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_more(common.Thunk.transformUndecorate(__SV_LOCAL_12183_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)silver.extension.bidirtransform.PleftTailPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12184_pl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_pattern).undecorate()).getAnno_core_location()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpToPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12176_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)silver.extension.bidirtransform.PlastElemPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12184_pl))); } })); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 153, 36, 156, 11, 6402, 6643\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_pattern)); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)(__SV_LOCAL_12158___fail_12159.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PmatchAppExpsToPattern.i_appexps)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:matchAppExpsToPattern", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmatchAppExpsToPattern.invoke(children[0], children[1]);
		}
	};
}