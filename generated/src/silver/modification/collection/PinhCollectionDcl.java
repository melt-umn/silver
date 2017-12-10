
package silver.modification.collection;

// top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation 
public final class PinhCollectionDcl extends silver.definition.env.NDclInfo {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_bound = 3;
	public static final int i_ty = 4;
	public static final int i_o = 5;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.StringCatter.class,common.DecoratedNode.class,silver.definition.type.NType.class,silver.modification.collection.NOperation.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_inhCollectionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_o] = new common.Lazy[silver.modification.collection.NOperation.num_inh_attrs];

	}

	public PinhCollectionDcl(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_bound, final Object c_ty, final Object c_o) {
		super();
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fn = c_fn;
		this.child_bound = c_bound;
		this.child_ty = c_ty;
		this.child_o = c_o;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_bound;
	public final common.ConsCell getChild_bound() {
		return (common.ConsCell) (child_bound = common.Util.demand(child_bound));
	}

	private Object child_ty;
	public final silver.definition.type.NType getChild_ty() {
		return (silver.definition.type.NType) (child_ty = common.Util.demand(child_ty));
	}

	private Object child_o;
	public final silver.modification.collection.NOperation getChild_o() {
		return (silver.modification.collection.NOperation) (child_o = common.Util.demand(child_o));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fn: return getChild_fn();
			case i_bound: return getChild_bound();
			case i_ty: return getChild_ty();
			case i_o: return getChild_o();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;
			case i_fn: return child_fn;
			case i_bound: return child_bound;
			case i_ty: return child_ty;
			case i_o: return child_o;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.env.NDclInfo)new silver.definition.env.PinhDcl(context.childAsIsLazy(silver.modification.collection.PinhCollectionDcl.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PinhCollectionDcl.i_sl)), context.childAsIsLazy(silver.modification.collection.PinhCollectionDcl.i_fn), context.childAsIsLazy(silver.modification.collection.PinhCollectionDcl.i_bound), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PinhCollectionDcl.i_ty))));
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
		return "silver:modification:collection:inhCollectionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.collection.PinhCollectionDcl.i_sg)); } };
		// top.sourceLocation = sl
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.collection.PinhCollectionDcl.i_sl).undecorate(); } };
		// top.fullName = fn
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.collection.PinhCollectionDcl.i_fn)); } };
		// ty.boundVariables = top.boundVariables ++ bound
		silver.modification.collection.PinhCollectionDcl.childInheritedAttributes[silver.modification.collection.PinhCollectionDcl.i_ty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.contextInheritedLazy(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo), context.childAsIsLazy(silver.modification.collection.PinhCollectionDcl.i_bound))); } };
		// top.unparse = "inhcol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ", " ++ o.unparse ++ ")"
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("inhcol(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PinhCollectionDcl.i_sl).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.collection.PinhCollectionDcl.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTyVars.invoke(context.childAsIsLazy(silver.modification.collection.PinhCollectionDcl.i_bound), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.collection.PinhCollectionDcl.i_ty).inherited(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PinhCollectionDcl.i_ty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PinhCollectionDcl.i_o).synthesized(silver.modification.collection.Init.silver_definition_env_unparse__ON__silver_modification_collection_Operation)), (common.StringCatter)(new common.StringCatter(")")))))))))))); } };
		// top.typerep = ty
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.collection.PinhCollectionDcl.i_ty).undecorate(); } };
		// top.dclBoundVars = bound
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_dclBoundVars__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.modification.collection.PinhCollectionDcl.i_bound)); } };
		// top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _,location=_)
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_decoratedAccessHandler__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.core.PinhDecoratedAccessHandler.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _,location=_), _, _, _)
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_undecoratedAccessHandler__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.core.PaccessBounceDecorate.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PinhDecoratedAccessHandler.factory.invokeNamedPartial(new int[]{0}, null, null); } }}); } };
		// top.attrDefDispatcher = \ dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr l::Location  -> errorAttributeDef([ err(l, attr.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.") ], dl, attr, e,location=l)
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_attrDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<silver.definition.core.NProductionStmt>() {
  public final silver.definition.core.NProductionStmt invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_7097_dl = args[0];
final Object __SV_LAMBDA_PARAM_7098_attr = args[1];
final Object __SV_LAMBDA_PARAM_7099_e = args[2];
final Object __SV_LAMBDA_PARAM_7100_l = args[3];

    return ((silver.definition.core.NProductionStmt)new silver.definition.core.PerrorAttributeDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)common.Util.demand(__SV_LAMBDA_PARAM_7100_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7098_attr)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)(new common.StringCatter(" is a collection attribute, and you must use ':=' or '<-', not '='."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7097_dl)), ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7098_attr)), ((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_7099_e)), ((core.NLocation)common.Util.demand(__SV_LAMBDA_PARAM_7100_l))));
  }
}); } };
		// top.attrBaseDefDispatcher = inhBaseColAttributeDef(_, _, _,location=_)
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_attrBaseDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PinhBaseColAttributeDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.attrAppendDefDispatcher = inhAppendColAttributeDef(_, _, _,location=_)
		silver.modification.collection.PinhCollectionDcl.synthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_attrAppendDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PinhAppendColAttributeDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };

	}

	public static final common.NodeFactory<PinhCollectionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinhCollectionDcl> {

		@Override
		public PinhCollectionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PinhCollectionDcl(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
