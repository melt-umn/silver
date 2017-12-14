
package silver.analysis.warnings.defs;

public final class PisAutocopy extends common.FunctionNode {

	public static final int i_attr = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_isAutocopy;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisAutocopy(final Object c_attr, final Object c_e) {
		this.child_attr = c_attr;
		this.child_e = c_e;

	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attr: return getChild_attr();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attr: return child_attr;
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
		return "silver:analysis:warnings:defs:isAutocopy";
	}

	public static Boolean invoke(final Object c_attr, final Object c_e) {
		try {
		final common.DecoratedNode context = new PisAutocopy(c_attr, c_e).decorate();
		//case getAttrDclAll(attr, e) of at::_ -> at.isAutocopy | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_434___fail_435 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.ConsCell, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_437___sv_pv_436_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_438___sv_tmp_pv_439 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_440_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)(__SV_LOCAL_437___sv_pv_436_at.eval())); } };
return ((Boolean)((silver.definition.env.NDclInfo)(__SV_LOCAL_440_at.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.modification.autocopyattr.Init.silver_modification_autocopyattr_isAutocopy__ON__silver_definition_env_DclInfo)); } }).eval()); }return ((Boolean)(__SV_LOCAL_434___fail_435.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.env.PgetAttrDclAll.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PisAutocopy.i_attr), context.childAsIsLazy(silver.analysis.warnings.defs.PisAutocopy.i_e)))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:isAutocopy", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisAutocopy.invoke(children[0], children[1]);
		}
	};
}