
package silver.definition.core;

// top::AnnoExpr ::= qn::QName '=' e::AppExpr 
public final class PannoExpr extends silver.definition.core.NAnnoExpr {

	public static final int i_qn = 0;
	public static final int i__G_1 = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TEqual_t.class,silver.definition.core.NAppExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_annoExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PannoExpr(final Object c_qn, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_1;
	public final silver.definition.core.TEqual_t getChild__G_1() {
		return (silver.definition.core.TEqual_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NAppExpr getChild_e() {
		return (silver.definition.core.NAppExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:annoExpr erroneously claimed to forward");
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
		return "silver:definition:core:annoExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = qn.pp ++ "=" ++ e.pp
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PannoExpr.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("=")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PannoExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExpr)))); } };
		// fq = extractNamedArg(qn.name, top.remainingFuncAnnotations)
		silver.definition.core.PannoExpr.localAttributes[silver.definition.core.Init.fq__ON__silver_definition_core_annoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)silver.definition.type.PextractNamedArg.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PannoExpr.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoExpr))); } };
		// e.appExprIndex = findNamedArgType(qn.name, top.funcAnnotations, 0)
		silver.definition.core.PannoExpr.childInheritedAttributes[silver.definition.core.PannoExpr.i_e][silver.definition.core.Init.silver_definition_core_appExprIndex__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)silver.definition.type.PfindNamedArgType.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PannoExpr.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_funcAnnotations__ON__silver_definition_core_AnnoExpr), Integer.valueOf((int)0))); } };
		// e.appExprTyperep = if fq.fst.isJust then fq.fst.fromJust.argType else errorType()
		silver.definition.core.PannoExpr.childInheritedAttributes[silver.definition.core.PannoExpr.i_e][silver.definition.core.Init.silver_definition_core_appExprTyperep__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((core.NMaybe)context.localDecorated(silver.definition.core.Init.fq__ON__silver_definition_core_annoExpr).synthesized(core.Init.core_fst__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((silver.definition.type.NType)((silver.definition.type.NNamedArgType)((core.NMaybe)context.localDecorated(silver.definition.core.Init.fq__ON__silver_definition_core_annoExpr).synthesized(core.Init.core_fst__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_argType__ON__silver_definition_type_NamedArgType)) : ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke())); } };
		// top.missingAnnotations = fq.snd
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.fq__ON__silver_definition_core_annoExpr).synthesized(core.Init.core_snd__ON__core_Pair)); } };
		// top.partialAnnoTypereps = e.missingTypereps
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PannoExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_core_missingTypereps__ON__silver_definition_core_AppExpr)); } };
		// top.errors := (if fq.fst.isJust then [] else [ err(qn.location, "Named parameter '" ++ qn.name ++ "' is not appropriate for '" ++ top.appExprApplied ++ "'") ]) ++ e.errors
		if(silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoExpr] == null)
			silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoExpr);
		((common.CollectionAttribute)silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)((core.NMaybe)context.localDecorated(silver.definition.core.Init.fq__ON__silver_definition_core_annoExpr).synthesized(core.Init.core_fst__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PannoExpr.i_qn).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Named parameter '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PannoExpr.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' is not appropriate for '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AnnoExpr)), (common.StringCatter)(new common.StringCatter("'")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PannoExpr.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr))); } });
		// top.isPartial = e.isPartial
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(silver.definition.core.PannoExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExpr)); } };
		// top.exprs = e.exprs
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PannoExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AppExpr)); } };
		// top.annoIndexConverted = if e.isPartial then [ e.appExprIndex ] else []
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.core.PannoExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExpr)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.definition.core.PannoExpr.i_e).inherited(silver.definition.core.Init.silver_definition_core_appExprIndex__ON__silver_definition_core_AppExpr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// top.annoIndexSupplied = if e.isPartial then [] else [ e.appExprIndex ]
		silver.definition.core.PannoExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.core.PannoExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExpr)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.definition.core.PannoExpr.i_e).inherited(silver.definition.core.Init.silver_definition_core_appExprIndex__ON__silver_definition_core_AppExpr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };

	}

	public static final common.NodeFactory<PannoExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PannoExpr> {

		@Override
		public PannoExpr invoke(final Object[] children, final Object[] annotations) {
			return new PannoExpr(children[0], children[1], children[2], annotations[0]);
		}
	};

}
