
package silver.definition.core;

// top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr 
public final class PsnocAnnoAppExprs extends silver.definition.core.NAnnoAppExprs {

	public static final int i_es = 0;
	public static final int i__G_1 = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NAnnoAppExprs.class,silver.definition.core.TComma_t.class,silver.definition.core.NAnnoExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_snocAnnoAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_es] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NAnnoExpr.num_inh_attrs];

	}

	public PsnocAnnoAppExprs(final Object c_es, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_es = c_es;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_es;
	public final silver.definition.core.NAnnoAppExprs getChild_es() {
		return (silver.definition.core.NAnnoAppExprs) (child_es = common.Util.demand(child_es));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NAnnoExpr getChild_e() {
		return (silver.definition.core.NAnnoExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_es: return getChild_es();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_es: return child_es;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:snocAnnoAppExprs erroneously claimed to forward");
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
		return "silver:definition:core:snocAnnoAppExprs";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = es.pp ++ ", " ++ e.pp
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PsnocAnnoAppExprs.i_es).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoAppExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PsnocAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoExpr)))); } };
		// top.isPartial = es.isPartial || e.isPartial
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.core.PsnocAnnoAppExprs.i_es).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoAppExprs)) || ((Boolean)context.childDecorated(silver.definition.core.PsnocAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoExpr))); } };
		// top.errors := es.errors ++ e.errors
		if(silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] == null)
			silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs);
		((common.CollectionAttribute)silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_es, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoExpr))); } });
		// e.remainingFuncAnnotations = top.remainingFuncAnnotations
		silver.definition.core.PsnocAnnoAppExprs.childInheritedAttributes[silver.definition.core.PsnocAnnoAppExprs.i_e][silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs)); } };
		// es.remainingFuncAnnotations = e.missingAnnotations
		silver.definition.core.PsnocAnnoAppExprs.childInheritedAttributes[silver.definition.core.PsnocAnnoAppExprs.i_es][silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PsnocAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoExpr)); } };
		// top.missingAnnotations = es.missingAnnotations
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PsnocAnnoAppExprs.i_es).synthesized(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs)); } };
		// top.partialAnnoTypereps = es.partialAnnoTypereps ++ e.partialAnnoTypereps
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_es, silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoAppExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_e, silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoExpr))); } };
		// top.annoIndexConverted = es.annoIndexConverted ++ e.annoIndexConverted
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_es, silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoAppExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_e, silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoExpr))); } };
		// top.annoIndexSupplied = es.annoIndexSupplied ++ e.annoIndexSupplied
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_es, silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoAppExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_e, silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoExpr))); } };
		// top.exprs = es.exprs ++ e.exprs
		silver.definition.core.PsnocAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_es, silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoAppExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PsnocAnnoAppExprs.i_e, silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoExpr))); } };

	}

	public static final common.NodeFactory<PsnocAnnoAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsnocAnnoAppExprs> {

		@Override
		public PsnocAnnoAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PsnocAnnoAppExprs(children[0], children[1], children[2], annotations[0]);
		}
	};

}
