
package silver.modification.copper;

// top::ParserComponentModifiers ::= h::ParserComponentModifier t::ParserComponentModifiers 
public final class PconsParserComponentModifier extends silver.modification.copper.NParserComponentModifiers {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.modification.copper.NParserComponentModifier.class,silver.modification.copper.NParserComponentModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_consParserComponentModifier;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NParserComponentModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NParserComponentModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.modification.copper.NParserComponentModifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.copper.NParserComponentModifiers.num_inh_attrs];

	}

	public PconsParserComponentModifier(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.modification.copper.NParserComponentModifier getChild_h() {
		return (silver.modification.copper.NParserComponentModifier) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.copper.NParserComponentModifiers getChild_t() {
		return (silver.modification.copper.NParserComponentModifiers) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:consParserComponentModifier erroneously claimed to forward");
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
		return "silver:modification:copper:consParserComponentModifier";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ t.pp
		silver.modification.copper.PconsParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconsParserComponentModifier.i_h).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconsParserComponentModifier.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifiers))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.modification.copper.PconsParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers] == null)
			silver.modification.copper.PconsParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers);
		((common.CollectionAttribute)silver.modification.copper.PconsParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponentModifier.i_h, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponentModifier.i_t, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers))); } });
		// top.terminalPrefixes = h.terminalPrefixes ++ t.terminalPrefixes
		silver.modification.copper.PconsParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponentModifier.i_h, silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifier), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponentModifier.i_t, silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifiers))); } };
		// top.liftedAGDcls = appendAGDcl(h.liftedAGDcls, t.liftedAGDcls,location=top.location)
		silver.modification.copper.PconsParserComponentModifier.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponentModifier.i_h, silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifier), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponentModifier.i_t, silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifiers), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponentModifiers)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PconsParserComponentModifier> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsParserComponentModifier> {

		@Override
		public PconsParserComponentModifier invoke(final Object[] children, final Object[] annotations) {
			return new PconsParserComponentModifier(children[0], children[1], annotations[0]);
		}
	};

}
