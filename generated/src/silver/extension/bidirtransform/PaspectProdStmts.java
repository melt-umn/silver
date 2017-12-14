
package silver.extension.bidirtransform;

// top::AGDcl ::= dcl::Decorated NamedSignature fn::(ProductionStmts ::= Decorated NamedSignature) 
public final class PaspectProdStmts extends silver.definition.core.NAGDcl {

	public static final int i_dcl = 0;
	public static final int i_fn = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_aspectProdStmts;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaspectProdStmts(final Object c_dcl, final Object c_fn, final Object a_core_location) {
		super(a_core_location);
		this.child_dcl = c_dcl;
		this.child_fn = c_fn;

	}

	private Object child_dcl;
	public final common.DecoratedNode getChild_dcl() {
		return (common.DecoratedNode) (child_dcl = common.Util.demand(child_dcl));
	}

	private Object child_fn;
	public final common.NodeFactory<silver.definition.core.NProductionStmts> getChild_fn() {
		return (common.NodeFactory<silver.definition.core.NProductionStmts>) (child_fn = common.Util.demand(child_fn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();
			case i_fn: return getChild_fn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;
			case i_fn: return child_fn;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PfakeAspectProductionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TAspect_kwd((new common.StringCatter("aspect")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TProduction_kwd((new common.StringCatter("production")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PaspectProdStmts.i_dcl, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAspectProductionSignature)new silver.extension.bidirtransform.PnsAspectProdSig(context.childAsIsLazy(silver.extension.bidirtransform.PaspectProdStmts.i_dcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionBody)new silver.definition.core.PproductionBody(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLCurly_t((new common.StringCatter("{")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)((common.NodeFactory<silver.definition.core.NProductionStmts>)context.childAsIs(silver.extension.bidirtransform.PaspectProdStmts.i_fn)).invoke(new Object[]{context.childAsIsLazy(silver.extension.bidirtransform.PaspectProdStmts.i_dcl)}, null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRCurly_t((new common.StringCatter("}")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:aspectProdStmts";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PaspectProdStmts> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectProdStmts> {

		@Override
		public PaspectProdStmts invoke(final Object[] children, final Object[] annotations) {
			return new PaspectProdStmts(children[0], children[1], annotations[0]);
		}
	};

}
