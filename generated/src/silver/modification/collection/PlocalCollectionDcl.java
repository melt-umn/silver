
package silver.modification.collection;

// top::DclInfo ::= sg::String sl::Location fn::String ty::Type o::Operation 
public final class PlocalCollectionDcl extends silver.definition.env.NDclInfo {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_ty = 3;
	public static final int i_o = 4;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class,silver.modification.collection.NOperation.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_localCollectionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_o] = new common.Lazy[silver.modification.collection.NOperation.num_inh_attrs];

	}

	public PlocalCollectionDcl(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty, final Object c_o) {
		super();
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fn = c_fn;
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
			case i_ty: return child_ty;
			case i_o: return child_o;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return ((silver.definition.env.NDclInfo)new silver.definition.env.PlocalDcl(context.childAsIsLazy(silver.modification.collection.PlocalCollectionDcl.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PlocalCollectionDcl.i_sl)), context.childAsIsLazy(silver.modification.collection.PlocalCollectionDcl.i_fn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PlocalCollectionDcl.i_ty))));
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
		return "silver:modification:collection:localCollectionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.collection.PlocalCollectionDcl.i_sg)); } };
		// top.sourceLocation = sl
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.collection.PlocalCollectionDcl.i_sl).undecorate(); } };
		// top.fullName = fn
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.collection.PlocalCollectionDcl.i_fn)); } };
		// ty.boundVariables = top.boundVariables
		silver.modification.collection.PlocalCollectionDcl.childInheritedAttributes[silver.modification.collection.PlocalCollectionDcl.i_ty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo)); } };
		// top.unparse = "loccol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ", " ++ o.unparse ++ ")"
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("loccol(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PlocalCollectionDcl.i_sl).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.collection.PlocalCollectionDcl.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PlocalCollectionDcl.i_ty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PlocalCollectionDcl.i_o).synthesized(silver.modification.collection.Init.silver_definition_env_unparse__ON__silver_modification_collection_Operation)), (common.StringCatter)(new common.StringCatter(")")))))))))); } };
		// top.typerep = ty
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.collection.PlocalCollectionDcl.i_ty).undecorate(); } };
		// top.refDispatcher = localReference(_,location=_)
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_refDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.core.PlocalReference.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.defDispatcher = errorColNormalValueDef(_, _,location=_)
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PerrorColNormalValueDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.defLHSDispatcher = localDefLHS(_,location=_)
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defLHSDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.core.PlocalDefLHS.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.baseDefDispatcher = baseCollectionValueDef(_, _,location=_)
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_baseDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PbaseCollectionValueDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.appendDefDispatcher = appendCollectionValueDef(_, _,location=_)
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_appendDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PappendCollectionValueDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.substitutedDclInfo = localCollectionDcl(sg, sl, fn, performSubstitution(ty, top.givenSubstitution), o)
		silver.modification.collection.PlocalCollectionDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_substitutedDclInfo__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.modification.collection.PlocalCollectionDcl(context.childAsIsLazy(silver.modification.collection.PlocalCollectionDcl.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PlocalCollectionDcl.i_sl)), context.childAsIsLazy(silver.modification.collection.PlocalCollectionDcl.i_fn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PlocalCollectionDcl.i_ty)), context.contextInheritedLazy(silver.definition.env.Init.silver_definition_env_givenSubstitution__ON__silver_definition_env_DclInfo))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PlocalCollectionDcl.i_o)))); } };

	}

	public static final common.NodeFactory<PlocalCollectionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlocalCollectionDcl> {

		@Override
		public PlocalCollectionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PlocalCollectionDcl(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
