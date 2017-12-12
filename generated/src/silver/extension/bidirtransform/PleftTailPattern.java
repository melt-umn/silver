
package silver.extension.bidirtransform;

public final class PleftTailPattern extends common.FunctionNode {

	public static final int i_pl = 0;


	public static final Class<?> childTypes[] = { silver.extension.patternmatching.NPatternList.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_leftTailPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_pl] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PleftTailPattern(final Object c_pl) {
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
		return "silver:extension:bidirtransform:leftTailPattern";
	}

	public static silver.extension.patternmatching.NPatternList invoke(final Object c_pl) {
		try {
		final common.DecoratedNode context = new PleftTailPattern(c_pl).decorate();
		//case pl of patternList_more(p, _, patternList_one(_)) -> patternList_one(p,location=pl.location) | patternList_more(p, _, pl) -> patternList_more(p, ',', leftTailPattern(pl),location=pl.location) | _ -> patternList_nil(location=pl.location) end
		return (silver.extension.patternmatching.NPatternList)(((silver.extension.patternmatching.NPatternList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12389___fail_12390 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_nil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.childDecorated(silver.extension.bidirtransform.PleftTailPattern.i_pl).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.extension.patternmatching.NPatternList>() { public final silver.extension.patternmatching.NPatternList eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_more) { final common.Thunk<Object> __SV_LOCAL___pv12395___sv_pv_12396_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12397___sv_tmp_pv_12398 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12399___sv_tmp_pv_12394 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.extension.patternmatching.NPatternList)((silver.extension.patternmatching.NPatternList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12409___fail_12410 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12400_pl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12399___sv_tmp_pv_12394.eval())); } };
return ((silver.extension.patternmatching.NPatternList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12401_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12395___sv_pv_12396_p.eval())); } };
return ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_more(common.Thunk.transformUndecorate(__SV_LOCAL_12401_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)silver.extension.bidirtransform.PleftTailPattern.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12400_pl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)((common.DecoratedNode)(__SV_LOCAL_12400_pl.eval())).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, silver.extension.patternmatching.NPatternList>() { public final silver.extension.patternmatching.NPatternList eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PpatternList_one) { final common.Thunk<Object> __SV_LOCAL___pv12412___sv_tmp_pv_12413 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.extension.patternmatching.NPatternList)((silver.extension.patternmatching.NPatternList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12414_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12395___sv_pv_12396_p.eval())); } };
return ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_one(common.Thunk.transformUndecorate(__SV_LOCAL_12414_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.childDecorated(silver.extension.bidirtransform.PleftTailPattern.i_pl).undecorate()).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.extension.patternmatching.NPatternList)(__SV_LOCAL_12409___fail_12410.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv12399___sv_tmp_pv_12394.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.extension.patternmatching.NPatternList)(__SV_LOCAL_12389___fail_12390.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PleftTailPattern.i_pl)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:leftTailPattern", t);
		}
	}

	public static final common.NodeFactory<silver.extension.patternmatching.NPatternList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.patternmatching.NPatternList> {
		@Override
		public silver.extension.patternmatching.NPatternList invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PleftTailPattern.invoke(children[0]);
		}
	};
}