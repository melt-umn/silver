
package silver.extension.bidirtransform;

// top::ProductionSignature ::= lhsName::String lhsType::String rhsName::String rhsType::String 
public final class PmkProdSig extends silver.definition.core.NProductionSignature {

	public static final int i_lhsName = 0;
	public static final int i_lhsType = 1;
	public static final int i_rhsName = 2;
	public static final int i_rhsType = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_mkProdSig;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionSignature.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmkProdSig(final Object c_lhsName, final Object c_lhsType, final Object c_rhsName, final Object c_rhsType, final Object a_core_location) {
		super(a_core_location);
		this.child_lhsName = c_lhsName;
		this.child_lhsType = c_lhsType;
		this.child_rhsName = c_rhsName;
		this.child_rhsType = c_rhsType;

	}

	private Object child_lhsName;
	public final common.StringCatter getChild_lhsName() {
		return (common.StringCatter) (child_lhsName = common.Util.demand(child_lhsName));
	}

	private Object child_lhsType;
	public final common.StringCatter getChild_lhsType() {
		return (common.StringCatter) (child_lhsType = common.Util.demand(child_lhsType));
	}

	private Object child_rhsName;
	public final common.StringCatter getChild_rhsName() {
		return (common.StringCatter) (child_rhsName = common.Util.demand(child_rhsName));
	}

	private Object child_rhsType;
	public final common.StringCatter getChild_rhsType() {
		return (common.StringCatter) (child_rhsType = common.Util.demand(child_rhsType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhsName: return getChild_lhsName();
			case i_lhsType: return getChild_lhsType();
			case i_rhsName: return getChild_rhsName();
			case i_rhsType: return getChild_rhsType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhsName: return child_lhsName;
			case i_lhsType: return child_lhsType;
			case i_rhsName: return child_rhsName;
			case i_rhsType: return child_rhsType;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.definition.core.NProductionSignature)new silver.definition.core.PproductionSignature(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionLHS)new silver.definition.core.PproductionLHS(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PmkProdSig.i_lhsName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PsTyExpr(context.childAsIsLazy(silver.extension.bidirtransform.PmkProdSig.i_lhsType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TCCEQ_t((new common.StringCatter("::=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionRHS)new silver.definition.core.PproductionRHSCons(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionRHSElem)new silver.definition.core.PproductionRHSElem(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PmkProdSig.i_rhsName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PsTyExpr(context.childAsIsLazy(silver.extension.bidirtransform.PmkProdSig.i_rhsType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionRHS)new silver.definition.core.PproductionRHSNil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:mkProdSig";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PmkProdSig> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmkProdSig> {

		@Override
		public PmkProdSig invoke(final Object[] children, final Object[] annotations) {
			return new PmkProdSig(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
