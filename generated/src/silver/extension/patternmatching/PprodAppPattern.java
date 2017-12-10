
package silver.extension.patternmatching;

// top::Pattern ::= prod::QName '(' ps::PatternList ')' 
public final class PprodAppPattern extends silver.extension.patternmatching.NPattern {

	public static final int i_prod = 0;
	public static final int i__G_2 = 1;
	public static final int i_ps = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TLParen_t.class,silver.extension.patternmatching.NPatternList.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_prodAppPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_prod] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ps] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PprodAppPattern(final Object c_prod, final Object c__G_2, final Object c_ps, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_prod = c_prod;
		this.child__G_2 = c__G_2;
		this.child_ps = c_ps;
		this.child__G_0 = c__G_0;

	}

	private Object child_prod;
	public final silver.definition.core.NQName getChild_prod() {
		return (silver.definition.core.NQName) (child_prod = common.Util.demand(child_prod));
	}

	private Object child__G_2;
	public final silver.definition.core.TLParen_t getChild__G_2() {
		return (silver.definition.core.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ps;
	public final silver.extension.patternmatching.NPatternList getChild_ps() {
		return (silver.extension.patternmatching.NPatternList) (child_ps = common.Util.demand(child_ps));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i__G_2: return getChild__G_2();
			case i_ps: return getChild_ps();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i__G_2: return child__G_2;
			case i_ps: return child_ps;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:prodAppPattern erroneously claimed to forward");
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
		return "silver:extension:patternmatching:prodAppPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = prod.pp ++ "(" ++ ps.pp ++ ")"
		silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_prod).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_ps).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList)), (common.StringCatter)(new common.StringCatter(")"))))); } };
		// top.errors := ps.errors
		if(silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] == null)
			silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern);
		((common.CollectionAttribute)silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_ps).synthesized(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList)); } });
		// parms = length(prod.lookupValue.typerep.inputTypes)
		silver.extension.patternmatching.PprodAppPattern.localAttributes[silver.extension.patternmatching.Init.parms__ON__silver_extension_patternmatching_prodAppPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_prod).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } })); } };
		// top.errors <- if null(prod.lookupValue.dcls) || length(ps.patternList) == parms then [] else [ err(prod.location, prod.pp ++ " has " ++ toString(parms) ++ " parameters but " ++ toString(length(ps.patternList)) ++ " patterns were provided") ]
		if(silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] == null)
			silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern);
		((common.CollectionAttribute)silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_prod).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) || ((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PprodAppPattern.i_ps, silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList))).equals(((Integer)context.localAsIs(silver.extension.patternmatching.Init.parms__ON__silver_extension_patternmatching_prodAppPattern)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_prod).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_prod).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.localAsIs(silver.extension.patternmatching.Init.parms__ON__silver_extension_patternmatching_prodAppPattern)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" parameters but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PprodAppPattern.i_ps, silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList))))), (common.StringCatter)(new common.StringCatter(" patterns were provided"))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.patternIsVariable = false
		silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternIsVariable__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.patternVariableName = nothing()
		silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternVariableName__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// top.patternSubPatternList = ps.patternList
		silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_ps).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList)); } };
		// top.patternSortKey = prod.lookupValue.fullName
		silver.extension.patternmatching.PprodAppPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.patternmatching.PprodAppPattern.i_prod).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } };

	}

	public static final common.NodeFactory<PprodAppPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodAppPattern> {

		@Override
		public PprodAppPattern invoke(final Object[] children, final Object[] annotations) {
			return new PprodAppPattern(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
