
package silver.definition.core;

public final class PannoExprContainsAnno extends common.FunctionNode {

	public static final int i_aexpr = 0;
	public static final int i_anno = 1;


	public static final Class<?> childTypes[] = { silver.definition.core.NAnnoExpr.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_annoExprContainsAnno;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_aexpr] = new common.Lazy[silver.definition.core.NAnnoExpr.num_inh_attrs];

	}

	public PannoExprContainsAnno(final Object c_aexpr, final Object c_anno) {
		this.child_aexpr = c_aexpr;
		this.child_anno = c_anno;

	}

	private Object child_aexpr;
	public final silver.definition.core.NAnnoExpr getChild_aexpr() {
		return (silver.definition.core.NAnnoExpr) (child_aexpr = common.Util.demand(child_aexpr));
	}

	private Object child_anno;
	public final common.StringCatter getChild_anno() {
		return (common.StringCatter) (child_anno = common.Util.demand(child_anno));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_aexpr: return getChild_aexpr();
			case i_anno: return getChild_anno();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_aexpr: return child_aexpr;
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
		return "silver:definition:core:annoExprContainsAnno";
	}

	public static Boolean invoke(final Object c_aexpr, final Object c_anno) {
		try {
		final common.DecoratedNode context = new PannoExprContainsAnno(c_aexpr, c_anno).decorate();
		//case aexpr of annoExpr(qn, _, _) -> qn.name == anno | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3064___fail_3065 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PannoExpr) { final common.Thunk<Object> __SV_LOCAL___pv3069___sv_pv_3068_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv3070___sv_tmp_pv_3071 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TEqual_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv3072___sv_tmp_pv_3073 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3074_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3069___sv_pv_3068_qn.eval())); } };
return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_3074_qn.eval())).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)).equals(((common.StringCatter)context.childAsIs(silver.definition.core.PannoExprContainsAnno.i_anno))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_3064___fail_3065.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.core.PannoExprContainsAnno.i_aexpr)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:annoExprContainsAnno", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PannoExprContainsAnno.invoke(children[0], children[1]);
		}
	};
}