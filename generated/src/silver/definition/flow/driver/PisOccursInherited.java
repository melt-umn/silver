
package silver.definition.flow.driver;

public final class PisOccursInherited extends common.FunctionNode {

	public static final int i_occs = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_isOccursInherited;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_occs] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PisOccursInherited(final Object c_occs, final Object c_e) {
		this.child_occs = c_occs;
		this.child_e = c_e;

	}

	private Object child_occs;
	public final silver.definition.env.NDclInfo getChild_occs() {
		return (silver.definition.env.NDclInfo) (child_occs = common.Util.demand(child_occs));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_occs: return getChild_occs();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_occs: return child_occs;
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
		return "silver:definition:flow:driver:isOccursInherited";
	}

	public static Boolean invoke(final Object c_occs, final Object c_e) {
		try {
		final common.DecoratedNode context = new PisOccursInherited(c_occs, c_e).decorate();
		//case getAttrDcl(occs.attrOccurring, e) of at::_ -> at.isInherited | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6053___fail_6054 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.ConsCell, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6058___sv_pv_6057_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6059___sv_tmp_pv_6060 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6061_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)(__SV_LOCAL_6058___sv_pv_6057_at.eval())); } };
return ((Boolean)((silver.definition.env.NDclInfo)(__SV_LOCAL_6061_at.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isInherited__ON__silver_definition_env_DclInfo)); } }).eval()); }return ((Boolean)(__SV_LOCAL_6053___fail_6054.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PisOccursInherited.i_occs, silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo), context.childAsIsLazy(silver.definition.flow.driver.PisOccursInherited.i_e)))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:isOccursInherited", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisOccursInherited.invoke(children[0], children[1]);
		}
	};
}