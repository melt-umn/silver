
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// top::Constructor ::= n::String tms::TypeNames 
public final class Pconstructor extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor {

	public static final int i_n = 0;
	public static final int i_tms = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeNames.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_constructor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_tms] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeNames.num_inh_attrs];

	}

	public Pconstructor(final Object c_n, final Object c_tms, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;
		this.child_tms = c_tms;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_tms;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeNames getChild_tms() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeNames) (child_tms = common.Util.demand(child_tms));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_tms: return getChild_tms();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_tms: return child_tms;

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
		return ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PallocConstructor(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Pconstructor.i_n), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Pconstructor.i_tms)), (new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_10131_ty = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n        ({proto_typedef ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_10131_ty)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n          (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_10131_ty)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("*) malloc (sizeof(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_10131_ty)), (common.StringCatter)(new common.StringCatter("));})")))))))); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor)context.undecorate()).getAnno_core_location()); } }));
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:constructor";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pconstructor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pconstructor> {

		@Override
		public Pconstructor invoke(final Object[] children, final Object[] annotations) {
			return new Pconstructor(children[0], children[1], annotations[0]);
		}
	};

}
