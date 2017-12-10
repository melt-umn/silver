
package silver.modification.ffi;

// top::Type ::= fn::String params::[Type] 
public final class PforeignType extends silver.definition.type.NType {

	public static final int i_fn = 0;
	public static final int i_params = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_foreignType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PforeignType(final Object c_fn, final Object c_params) {
		super();
		this.child_fn = c_fn;
		this.child_params = c_params;

	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_params;
	public final common.ConsCell getChild_params() {
		return (common.ConsCell) (child_params = common.Util.demand(child_params));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fn: return getChild_fn();
			case i_params: return getChild_params();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fn: return child_fn;
			case i_params: return child_params;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:ffi:foreignType erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:modification:ffi:foreignType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params))
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PsetUnionTyVarsAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type, context), context.childAsIsLazy(silver.modification.ffi.PforeignType.i_params))); } })); } };
		// top.substituted = foreignType(fn, mapSubst(params, top.substitution))
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_substituted__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.modification.ffi.PforeignType(context.childAsIsLazy(silver.modification.ffi.PforeignType.i_fn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PmapSubst.invoke(context.childAsIsLazy(silver.modification.ffi.PforeignType.i_params), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_substitution__ON__silver_definition_type_Type))); } })); } };
		// top.typepp = fn ++ if ! null(params) then "<" ++ implode(" ", mapTypePP(params, top.boundVariables)) ++ ">" else ""
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.ffi.PforeignType.i_fn)), (common.StringCatter)((!((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.ffi.PforeignType.i_params)))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(" ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PmapTypePP.invoke(context.childAsIsLazy(silver.modification.ffi.PforeignType.i_params), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))); } })), (common.StringCatter)(new common.StringCatter(">")))) : (new common.StringCatter("")))); } };
		// top.unify = case top.unifyWith of foreignType(ofn, op) -> if fn == ofn then unifyAll(params, op) else errorSubst("Tried to unify conflicting foreign types " ++ fn ++ " and " ++ ofn) | _ -> errorSubst("Tried to unify foreign type " ++ fn ++ " with " ++ prettyType(top.unifyWith)) end
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_unify__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8163___fail_8164 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PerrorSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Tried to unify foreign type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.ffi.PforeignType.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_unifyWith__ON__silver_definition_type_Type)))))); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.modification.ffi.PforeignType) { final common.Thunk<Object> __SV_LOCAL___pv8169___sv_pv_8170_ofn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8171___sv_pv_8168_op = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8172_op = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv8171___sv_pv_8168_op.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8173_ofn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv8169___sv_pv_8170_ofn.eval())); } };
return (((common.StringCatter)context.childAsIs(silver.modification.ffi.PforeignType.i_fn)).equals(((common.StringCatter)(__SV_LOCAL_8173_ofn.eval()))) ? ((silver.definition.type.NSubstitution)silver.definition.type.PunifyAll.invoke(context.childAsIsLazy(silver.modification.ffi.PforeignType.i_params), __SV_LOCAL_8172_op)) : ((silver.definition.type.NSubstitution)silver.definition.type.PerrorSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Tried to unify conflicting foreign types ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.ffi.PforeignType.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" and ")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_8173_ofn.eval()))))); } }))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_8163___fail_8164.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NType)context.inherited(silver.definition.type.Init.silver_definition_type_unifyWith__ON__silver_definition_type_Type)).decorate(context, (common.Lazy[])null)); } }).eval()); } };
		// top.unparse = "foreigntype('" ++ fn ++ "', " ++ unparseTypes(params, top.boundVariables) ++ ")"
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("foreigntype('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.ffi.PforeignType.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTypes.invoke(context.childAsIsLazy(silver.modification.ffi.PforeignType.i_params), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))), (common.StringCatter)(new common.StringCatter(")")))))); } };

	}

	public static final common.NodeFactory<PforeignType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforeignType> {

		@Override
		public PforeignType invoke(final Object[] children, final Object[] annotations) {
			return new PforeignType(children[0], children[1]);
		}
	};

}
