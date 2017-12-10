
package silver.definition.core;

public final class PannoAppExprsContainsAnno extends common.FunctionNode {

	public static final int i_toFill = 0;
	public static final int i_anno = 1;


	public static final Class<?> childTypes[] = { silver.definition.core.NAnnoAppExprs.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_annoAppExprsContainsAnno;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PannoAppExprsContainsAnno(final Object c_toFill, final Object c_anno) {
		this.child_toFill = c_toFill;
		this.child_anno = c_anno;

	}

	private Object child_toFill;
	public final silver.definition.core.NAnnoAppExprs getChild_toFill() {
		return (silver.definition.core.NAnnoAppExprs) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_anno;
	public final common.StringCatter getChild_anno() {
		return (common.StringCatter) (child_anno = common.Util.demand(child_anno));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_anno: return getChild_anno();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_anno: return child_anno;

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
		return "silver:definition:core:annoAppExprsContainsAnno";
	}

	public static Boolean invoke(final Object c_toFill, final Object c_anno) {
		try {
		final common.DecoratedNode context = new PannoAppExprsContainsAnno(c_toFill, c_anno).decorate();
		//case toFill of emptyAnnoAppExprs() -> false | snocAnnoAppExprs(xs, _, x) -> annoExprContainsAnno(x, anno) || annoAppExprsContainsAnno(xs, anno) | oneAnnoAppExprs(x) -> annoExprContainsAnno(x, anno) end
		return (Boolean)(new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PemptyAnnoAppExprs) {  return (Boolean)false; }
else if(scrutineeNode instanceof silver.definition.core.PoneAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv3042___sv_pv_3041_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3043_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3042___sv_pv_3041_x.eval())); } };
return ((Boolean)silver.definition.core.PannoExprContainsAnno.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_3043_x), context.childAsIsLazy(silver.definition.core.PannoAppExprsContainsAnno.i_anno))); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv3045___sv_pv_3046_xs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv3047___sv_tmp_pv_3048 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv3049___sv_pv_3044_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3050_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3049___sv_pv_3044_x.eval())); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3051_xs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3045___sv_pv_3046_xs.eval())); } };
return (((Boolean)silver.definition.core.PannoExprContainsAnno.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_3050_x), context.childAsIsLazy(silver.definition.core.PannoAppExprsContainsAnno.i_anno))) || ((Boolean)silver.definition.core.PannoAppExprsContainsAnno.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_3051_xs), context.childAsIsLazy(silver.definition.core.PannoAppExprsContainsAnno.i_anno)))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:core 'DefaultAnnotations.sv', 113, 11, 117, 7, 3572, 3802\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.core.PannoAppExprsContainsAnno.i_toFill)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:annoAppExprsContainsAnno", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PannoAppExprsContainsAnno.invoke(children[0], children[1]);
		}
	};
}