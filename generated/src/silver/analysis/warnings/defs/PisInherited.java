
package silver.analysis.warnings.defs;

public final class PisInherited extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_isInherited;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisInherited(final Object c_a, final Object c_e) {
		this.child_a = c_a;
		this.child_e = c_e;

	}

	private Object child_a;
	public final common.StringCatter getChild_a() {
		return (common.StringCatter) (child_a = common.Util.demand(child_a));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_e: return child_e;

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
		return "silver:analysis:warnings:defs:isInherited";
	}

	public static Boolean invoke(final Object c_a, final Object c_e) {
		try {
		final common.DecoratedNode context = new PisInherited(c_a, c_e).decorate();
		//case getAttrDclAll(a, e) of at::_ -> at.isInherited | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_444___fail_445 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.ConsCell, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_447___sv_pv_446_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_448___sv_tmp_pv_449 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_450_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)(__SV_LOCAL_447___sv_pv_446_at.eval())); } };
return ((Boolean)((silver.definition.env.NDclInfo)(__SV_LOCAL_450_at.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isInherited__ON__silver_definition_env_DclInfo)); } }).eval()); }return ((Boolean)(__SV_LOCAL_444___fail_445.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.env.PgetAttrDclAll.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PisInherited.i_a), context.childAsIsLazy(silver.analysis.warnings.defs.PisInherited.i_e)))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:isInherited", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisInherited.invoke(children[0], children[1]);
		}
	};
}