
package silver.extension.convenience;

// top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';' 
public final class PattributeDclInhMultiple extends silver.definition.core.NAGDcl {

	public static final int i__G_9 = 0;
	public static final int i__G_8 = 1;
	public static final int i_a = 2;
	public static final int i_tl = 3;
	public static final int i__G_5 = 4;
	public static final int i_te = 5;
	public static final int i__G_3 = 6;
	public static final int i__G_2 = 7;
	public static final int i_qs = 8;
	public static final int i__G_0 = 9;


	public static final Class<?> childTypes[] = {silver.definition.core.TInherited_kwd.class,silver.definition.core.TAttribute_kwd.class,silver.definition.core.NName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TOccurs_kwd.class,silver.definition.core.TOn_kwd.class,silver.extension.convenience.NQNames.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_attributeDclInhMultiple;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[10][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_qs] = new common.Lazy[silver.extension.convenience.NQNames.num_inh_attrs];

	}

	public PattributeDclInhMultiple(final Object c__G_9, final Object c__G_8, final Object c_a, final Object c_tl, final Object c__G_5, final Object c_te, final Object c__G_3, final Object c__G_2, final Object c_qs, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_9 = c__G_9;
		this.child__G_8 = c__G_8;
		this.child_a = c_a;
		this.child_tl = c_tl;
		this.child__G_5 = c__G_5;
		this.child_te = c_te;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_qs = c_qs;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_9;
	public final silver.definition.core.TInherited_kwd getChild__G_9() {
		return (silver.definition.core.TInherited_kwd) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child__G_8;
	public final silver.definition.core.TAttribute_kwd getChild__G_8() {
		return (silver.definition.core.TAttribute_kwd) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_a;
	public final silver.definition.core.NName getChild_a() {
		return (silver.definition.core.NName) (child_a = common.Util.demand(child_a));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_5;
	public final silver.definition.core.TColonColon_t getChild__G_5() {
		return (silver.definition.core.TColonColon_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_te;
	public final silver.definition.type.syntax.NTypeExpr getChild_te() {
		return (silver.definition.type.syntax.NTypeExpr) (child_te = common.Util.demand(child_te));
	}

	private Object child__G_3;
	public final silver.definition.core.TOccurs_kwd getChild__G_3() {
		return (silver.definition.core.TOccurs_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TOn_kwd getChild__G_2() {
		return (silver.definition.core.TOn_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_qs;
	public final silver.extension.convenience.NQNames getChild_qs() {
		return (silver.extension.convenience.NQNames) (child_qs = common.Util.demand(child_qs));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_9: return getChild__G_9();
			case i__G_8: return getChild__G_8();
			case i_a: return getChild_a();
			case i_tl: return getChild_tl();
			case i__G_5: return getChild__G_5();
			case i_te: return getChild_te();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_qs: return getChild_qs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_9: return child__G_9;
			case i__G_8: return child__G_8;
			case i_a: return child_a;
			case i_tl: return child_tl;
			case i__G_5: return child__G_5;
			case i_te: return child_te;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_qs: return child_qs;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 10;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PattributeDclInh(context.childAsIsLazy(silver.extension.convenience.PattributeDclInhMultiple.i__G_9), context.childAsIsLazy(silver.extension.convenience.PattributeDclInhMultiple.i__G_8), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PattributeDclInhMultiple.i_a)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PattributeDclInhMultiple.i_tl)), context.childAsIsLazy(silver.extension.convenience.PattributeDclInhMultiple.i__G_5), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PattributeDclInhMultiple.i_te)), context.childAsIsLazy(silver.extension.convenience.PattributeDclInhMultiple.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDclsHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.convenience.NQNameWithTL)new silver.extension.convenience.PqNameWithTL(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)new silver.definition.core.PqNameId(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PattributeDclInhMultiple.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.convenience.PattributeDclInhMultiple.i_a).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PattributeDclInhMultiple.i_tl)))); } }, context.childDecoratedSynthesizedLazy(silver.extension.convenience.PattributeDclInhMultiple.i_qs, silver.extension.convenience.Init.silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:convenience:attributeDclInhMultiple";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "inherited attribute " ++ a.name ++ tl.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";"
		silver.extension.convenience.PattributeDclInhMultiple.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("inherited attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PattributeDclInhMultiple.i_a).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PattributeDclInhMultiple.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" :: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PattributeDclInhMultiple.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" occurs on ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PattributeDclInhMultiple.i_qs).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNames)), (common.StringCatter)(new common.StringCatter(";"))))))))); } };

	}

	public static final common.NodeFactory<PattributeDclInhMultiple> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributeDclInhMultiple> {

		@Override
		public PattributeDclInhMultiple invoke(final Object[] children, final Object[] annotations) {
			return new PattributeDclInhMultiple(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], annotations[0]);
		}
	};

}
