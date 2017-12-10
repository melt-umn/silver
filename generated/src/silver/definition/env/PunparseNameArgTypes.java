
package silver.definition.env;

public final class PunparseNameArgTypes extends common.FunctionNode {

	public static final int i_np = 0;
	public static final int i_bv = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_unparseNameArgTypes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunparseNameArgTypes(final Object c_np, final Object c_bv) {
		this.child_np = c_np;
		this.child_bv = c_bv;

	}

	private Object child_np;
	public final common.ConsCell getChild_np() {
		return (common.ConsCell) (child_np = common.Util.demand(child_np));
	}

	private Object child_bv;
	public final common.ConsCell getChild_bv() {
		return (common.ConsCell) (child_bv = common.Util.demand(child_bv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_np: return getChild_np();
			case i_bv: return getChild_bv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_np: return child_np;
			case i_bv: return child_bv;

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
		return "silver:definition:env:unparseNameArgTypes";
	}

	public static common.StringCatter invoke(final Object c_np, final Object c_bv) {
		try {
		final common.DecoratedNode context = new PunparseNameArgTypes(c_np, c_bv).decorate();
		//case np of [] -> "" | namedArgType(s, t)::rest -> ";" ++ quoteString(s) ++ "=" ++ decorate t with {boundVariables = bv;}.unparse ++ unparseNameArgTypes(rest, bv) end
		return (common.StringCatter)(new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_3884___sv_tmp_pv_3883 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_3885___sv_pv_3886_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnamedArgType) { final common.Thunk<Object> __SV_LOCAL___pv3888___sv_pv_3889_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv3890___sv_pv_3891_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3892_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_3885___sv_pv_3886_rest.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3893_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3890___sv_pv_3891_t.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3894_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv3888___sv_pv_3889_s.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter(";")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(__SV_LOCAL_3894_s)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("=")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((common.DecoratedNode)__SV_LOCAL_3893_t.eval()).undecorate()).decorate(context, common.Util.populateInh(silver.definition.type.NType.num_inh_attrs, new int[]{silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.env.PunparseNameArgTypes.i_bv)); } }})).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseNameArgTypes.invoke(__SV_LOCAL_3892_rest, context.childAsIsLazy(silver.definition.env.PunparseNameArgTypes.i_bv))))))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:env 'Type.sv', 84, 9, 87, 12, 1827, 2023\n"))));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NNamedArgType)(__SV_LOCAL_3884___sv_tmp_pv_3883.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (common.StringCatter)(new common.StringCatter("")); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:env 'Type.sv', 84, 9, 87, 12, 1827, 2023\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.definition.env.PunparseNameArgTypes.i_np))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:unparseNameArgTypes", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunparseNameArgTypes.invoke(children[0], children[1]);
		}
	};
}