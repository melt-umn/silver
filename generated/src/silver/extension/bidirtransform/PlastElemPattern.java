
package silver.extension.bidirtransform;

public final class PlastElemPattern extends common.FunctionNode {

	public static final int i_pl = 0;


	public static final Class<?> childTypes[] = { silver.extension.patternmatching.NPatternList.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_lastElemPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_pl] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PlastElemPattern(final Object c_pl) {
		this.child_pl = c_pl;

	}

	private Object child_pl;
	public final silver.extension.patternmatching.NPatternList getChild_pl() {
		return (silver.extension.patternmatching.NPatternList) (child_pl = common.Util.demand(child_pl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pl: return getChild_pl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pl: return child_pl;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:bidirtransform:lastElemPattern";
	}

	public static silver.extension.patternmatching.NPattern invoke(final Object c_pl) {
		try {
		final common.DecoratedNode context = new PlastElemPattern(c_pl).decorate();
		//case pl of patternList_one(p) -> p | patternList_more(p, _, patternList_nil()) -> p | patternList_more(_, _, pl) -> lastElemPattern(pl) | _ -> wildcPattern('_',location=pl.location) end
		return (silver.extension.patternmatching.NPattern)(((silver.extension.patternmatching.NPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12349___fail_12350 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)new silver.extension.patternmatching.PwildcPattern(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TUnderScore_t((new common.StringCatter("_")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.childDecorated(silver.extension.bidirtransform.PlastElemPattern.i_pl).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.extension.patternmatching.NPattern>() { public final silver.extension.patternmatching.NPattern eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_more) { final common.Thunk<Object> __SV_LOCAL___pv12355___sv_pv_12356_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12357___sv_tmp_pv_12358 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12359___sv_tmp_pv_12354 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.extension.patternmatching.NPattern)((silver.extension.patternmatching.NPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12361___fail_12362 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12360_pl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12359___sv_tmp_pv_12354.eval())); } };
return ((silver.extension.patternmatching.NPattern)silver.extension.bidirtransform.PlastElemPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12360_pl))); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, silver.extension.patternmatching.NPattern>() { public final silver.extension.patternmatching.NPattern eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_nil) {  return (silver.extension.patternmatching.NPattern)((silver.extension.patternmatching.NPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12367_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12355___sv_pv_12356_p.eval())); } };
return ((silver.extension.patternmatching.NPattern)((common.DecoratedNode)__SV_LOCAL_12367_p.eval()).undecorate()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.extension.patternmatching.NPattern)(__SV_LOCAL_12361___fail_12362.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv12359___sv_tmp_pv_12354.eval()))); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_one) { final common.Thunk<Object> __SV_LOCAL___pv12369___sv_pv_12368_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.extension.patternmatching.NPattern)((silver.extension.patternmatching.NPattern)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12370_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12369___sv_pv_12368_p.eval())); } };
return ((silver.extension.patternmatching.NPattern)((common.DecoratedNode)__SV_LOCAL_12370_p.eval()).undecorate()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.extension.patternmatching.NPattern)(__SV_LOCAL_12349___fail_12350.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PlastElemPattern.i_pl)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:lastElemPattern", t);
		}
	}

	public static final common.NodeFactory<silver.extension.patternmatching.NPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.patternmatching.NPattern> {
		@Override
		public silver.extension.patternmatching.NPattern invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlastElemPattern.invoke(children[0]);
		}
	};
}