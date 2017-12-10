
package silver.modification.copper;

// top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' s::TerminalPrefix 
public final class PprefixParserComponentModifier extends silver.modification.copper.NParserComponentModifier {

	public static final int i__G_3 = 0;
	public static final int i_ts = 1;
	public static final int i__G_1 = 2;
	public static final int i_s = 3;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPrefix_t.class,silver.modification.copper.NTerminalPrefixItems.class,silver.definition.core.TWith_kwd.class,silver.modification.copper.NTerminalPrefix.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_prefixParserComponentModifier;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NParserComponentModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NParserComponentModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ts] = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_inh_attrs];

	}

	public PprefixParserComponentModifier(final Object c__G_3, final Object c_ts, final Object c__G_1, final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child_ts = c_ts;
		this.child__G_1 = c__G_1;
		this.child_s = c_s;

	}

	private Object child__G_3;
	public final silver.modification.copper.TPrefix_t getChild__G_3() {
		return (silver.modification.copper.TPrefix_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_ts;
	public final silver.modification.copper.NTerminalPrefixItems getChild_ts() {
		return (silver.modification.copper.NTerminalPrefixItems) (child_ts = common.Util.demand(child_ts));
	}

	private Object child__G_1;
	public final silver.definition.core.TWith_kwd getChild__G_1() {
		return (silver.definition.core.TWith_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_s;
	public final silver.modification.copper.NTerminalPrefix getChild_s() {
		return (silver.modification.copper.NTerminalPrefix) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i_ts: return getChild_ts();
			case i__G_1: return getChild__G_1();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i_ts: return child_ts;
			case i__G_1: return child__G_1;
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:prefixParserComponentModifier erroneously claimed to forward");
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
		return "silver:modification:copper:prefixParserComponentModifier";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "prefix " ++ ts.pp ++ " with " ++ s.pp
		silver.modification.copper.PprefixParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prefix ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PprefixParserComponentModifier.i_ts).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PprefixParserComponentModifier.i_s).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix))))); } };
		// top.errors := ts.errors ++ s.errors
		if(silver.modification.copper.PprefixParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier] == null)
			silver.modification.copper.PprefixParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier);
		((common.CollectionAttribute)silver.modification.copper.PprefixParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PprefixParserComponentModifier.i_ts, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems), context.childDecoratedSynthesizedLazy(silver.modification.copper.PprefixParserComponentModifier.i_s, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix))); } });
		// top.terminalPrefixes = do (bindList, returnList) {t::QName <- ts.prefixItemNames; td::Decorated QName = decorate t with {config = top.config; grammarName = top.grammarName; env = top.env;}; return pair(td.lookupType.fullName, makeCopperName(s.prefixFullName));}
		silver.modification.copper.PprefixParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.monad.PbindList.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PprefixParserComponentModifier.i_ts, silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems), (new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6691_t = args[0];

    return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6699_td = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)common.Util.demand(__SV_LAMBDA_PARAM_6691_t)).decorate(context, common.Util.populateInh(silver.definition.core.NQName.num_inh_attrs, new int[]{silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_QName, silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_QName, silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ParserComponentModifier)); } }, new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponentModifier)); } }, new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponentModifier)); } }})); } };
return ((common.ConsCell)core.monad.PreturnList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL_6699_td.eval())).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PprefixParserComponentModifier.i_s, silver.modification.copper.Init.silver_modification_copper_prefixFullName__ON__silver_modification_copper_TerminalPrefix))); } })); } })); } }).eval());
  }
}))); } };
		// top.liftedAGDcls = s.liftedAGDcls
		silver.modification.copper.PprefixParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)context.childDecorated(silver.modification.copper.PprefixParserComponentModifier.i_s).synthesized(silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_TerminalPrefix)); } };

	}

	public static final common.NodeFactory<PprefixParserComponentModifier> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprefixParserComponentModifier> {

		@Override
		public PprefixParserComponentModifier invoke(final Object[] children, final Object[] annotations) {
			return new PprefixParserComponentModifier(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
