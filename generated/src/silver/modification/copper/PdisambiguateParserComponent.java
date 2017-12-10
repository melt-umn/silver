
package silver.modification.copper;

// top::ParserComponent ::= 'prefer' t::QName 'over' ts::TermList ';' 
public final class PdisambiguateParserComponent extends silver.modification.copper.NParserComponent {

	public static final int i__G_4 = 0;
	public static final int i_t = 1;
	public static final int i__G_2 = 2;
	public static final int i_ts = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPrefer_t.class,silver.definition.core.NQName.class,silver.modification.copper.TOver_t.class,silver.modification.copper.NTermList.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_disambiguateParserComponent;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NParserComponent.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NParserComponent.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ts] = new common.Lazy[silver.modification.copper.NTermList.num_inh_attrs];

	}

	public PdisambiguateParserComponent(final Object c__G_4, final Object c_t, final Object c__G_2, final Object c_ts, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_t = c_t;
		this.child__G_2 = c__G_2;
		this.child_ts = c_ts;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.modification.copper.TPrefer_t getChild__G_4() {
		return (silver.modification.copper.TPrefer_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_t;
	public final silver.definition.core.NQName getChild_t() {
		return (silver.definition.core.NQName) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_2;
	public final silver.modification.copper.TOver_t getChild__G_2() {
		return (silver.modification.copper.TOver_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ts;
	public final silver.modification.copper.NTermList getChild_ts() {
		return (silver.modification.copper.NTermList) (child_ts = common.Util.demand(child_ts));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_t: return getChild_t();
			case i__G_2: return getChild__G_2();
			case i_ts: return getChild_ts();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_t: return child_t;
			case i__G_2: return child__G_2;
			case i_ts: return child_ts;
			case i__G_0: return child__G_0;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:disambiguateParserComponent erroneously claimed to forward");
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
		return "silver:modification:copper:disambiguateParserComponent";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "prefer " ++ t.pp ++ " over " ++ ts.pp
		silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prefer ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PdisambiguateParserComponent.i_t).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" over ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PdisambiguateParserComponent.i_ts).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermList))))); } };
		// top.errors := t.lookupType.errors ++ ts.errors
		if(silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] == null)
			silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent);
		((common.CollectionAttribute)silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PdisambiguateParserComponent.i_t).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguateParserComponent.i_ts, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList))); } });
		// top.moduleNames = []
		silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.terminalPrefixes = []
		silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.liftedAGDcls = disambiguationGroupDcl('disambiguate', termListCons(t, ',', ts,location=top.location), actionCode_c('{', productionStmtsSnoc(productionStmtsNil(location=top.location), pluckDef('pluck', baseExpr(t,location=top.location), ';',location=top.location),location=top.location), '}',location=top.location),location=top.location)
		silver.modification.copper.PdisambiguateParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.modification.copper.PdisambiguationGroupDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.modification.copper.TDisambiguation_kwd((new common.StringCatter("disambiguate")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.copper.NTermList)new silver.modification.copper.PtermListCons(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PdisambiguateParserComponent.i_t)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PdisambiguateParserComponent.i_ts)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.copper.NActionCode_c)new silver.modification.copper.PactionCode_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLCurly_t((new common.StringCatter("{")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsSnoc(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsNil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.modification.copper.PpluckDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.modification.copper.TPluck_kwd((new common.StringCatter("pluck")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PdisambiguateParserComponent.i_t)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRCurly_t((new common.StringCatter("}")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PdisambiguateParserComponent> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdisambiguateParserComponent> {

		@Override
		public PdisambiguateParserComponent invoke(final Object[] children, final Object[] annotations) {
			return new PdisambiguateParserComponent(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
