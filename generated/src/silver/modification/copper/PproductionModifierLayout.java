
package silver.modification.copper;

// top::ProductionModifier ::= 'layout' '{' terms::TermPrecList '}' 
public final class PproductionModifierLayout extends silver.definition.concrete_syntax.NProductionModifier {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_terms = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.modification.copper.TLayout_kwd.class,silver.definition.core.TLCurly_t.class,silver.modification.copper.NTermPrecList.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_productionModifierLayout;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_terms] = new common.Lazy[silver.modification.copper.NTermPrecList.num_inh_attrs];

	}

	public PproductionModifierLayout(final Object c__G_3, final Object c__G_2, final Object c_terms, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_terms = c_terms;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.modification.copper.TLayout_kwd getChild__G_3() {
		return (silver.modification.copper.TLayout_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_terms;
	public final silver.modification.copper.NTermPrecList getChild_terms() {
		return (silver.modification.copper.NTermPrecList) (child_terms = common.Util.demand(child_terms));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_terms: return getChild_terms();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_terms: return child_terms;
			case i__G_0: return child__G_0;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:productionModifierLayout erroneously claimed to forward");
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
		return "silver:modification:copper:productionModifierLayout";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "layout {" ++ terms.pp ++ "}"
		silver.modification.copper.PproductionModifierLayout.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("layout {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PproductionModifierLayout.i_terms).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermPrecList)), (common.StringCatter)(new common.StringCatter("}")))); } };
		// top.productionModifiers = [ prodLayout(terms.precTermList) ]
		silver.modification.copper.PproductionModifierLayout.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifier)new silver.definition.concrete_syntax.ast.PprodLayout(context.childDecoratedSynthesizedLazy(silver.modification.copper.PproductionModifierLayout.i_terms, silver.modification.copper.Init.silver_modification_copper_precTermList__ON__silver_modification_copper_TermPrecList))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := terms.errors
		if(silver.modification.copper.PproductionModifierLayout.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier] == null)
			silver.modification.copper.PproductionModifierLayout.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier);
		((common.CollectionAttribute)silver.modification.copper.PproductionModifierLayout.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PproductionModifierLayout.i_terms).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList)); } });

	}

	public static final common.NodeFactory<PproductionModifierLayout> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionModifierLayout> {

		@Override
		public PproductionModifierLayout invoke(final Object[] children, final Object[] annotations) {
			return new PproductionModifierLayout(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
