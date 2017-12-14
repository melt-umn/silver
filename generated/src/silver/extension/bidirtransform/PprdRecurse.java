
package silver.extension.bidirtransform;

// top::Expr ::= ns::Decorated NamedSignature tName::String allowedTypes::[String] 
public final class PprdRecurse extends silver.definition.core.NExpr {

	public static final int i_ns = 0;
	public static final int i_tName = 1;
	public static final int i_allowedTypes = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_prdRecurse;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprdRecurse(final Object c_ns, final Object c_tName, final Object c_allowedTypes, final Object a_core_location) {
		super(a_core_location);
		this.child_ns = c_ns;
		this.child_tName = c_tName;
		this.child_allowedTypes = c_allowedTypes;

	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_tName;
	public final common.StringCatter getChild_tName() {
		return (common.StringCatter) (child_tName = common.Util.demand(child_tName));
	}

	private Object child_allowedTypes;
	public final common.ConsCell getChild_allowedTypes() {
		return (common.ConsCell) (child_allowedTypes = common.Util.demand(child_allowedTypes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();
			case i_tName: return getChild_tName();
			case i_allowedTypes: return getChild_allowedTypes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;
			case i_tName: return child_tName;
			case i_allowedTypes: return child_allowedTypes;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return ((silver.definition.core.NExpr)new silver.definition.core.Papplication(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PbaseName(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PprdRecurse.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PargTransAttrs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Preverse.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PprdRecurse.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PprdRecurse.i_tName), context.childAsIsLazy(silver.extension.bidirtransform.PprdRecurse.i_allowedTypes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PannoAppExprList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PannExpr((new common.StringCatter("labels")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.list.PemptyList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TLSqr_t((new common.StringCatter("[")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TRSqr_t((new common.StringCatter("]")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PannExpr((new common.StringCatter("redex")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PexprAccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PinhRedexNm.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PprdRecurse.i_tName))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PinhRedexNameSig.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PprdRecurse.i_ns), context.childAsIsLazy(silver.extension.bidirtransform.PprdRecurse.i_allowedTypes))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PannExpr((new common.StringCatter("origin")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PmkOrigin(context.childAsIsLazy(silver.extension.bidirtransform.PprdRecurse.i_ns), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:prdRecurse";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PprdRecurse> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprdRecurse> {

		@Override
		public PprdRecurse invoke(final Object[] children, final Object[] annotations) {
			return new PprdRecurse(children[0], children[1], children[2], annotations[0]);
		}
	};

}
