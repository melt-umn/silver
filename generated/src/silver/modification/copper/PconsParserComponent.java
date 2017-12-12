
package silver.modification.copper;

// top::ParserComponents ::= c1::ParserComponent c2::ParserComponents 
public final class PconsParserComponent extends silver.modification.copper.NParserComponents {

	public static final int i_c1 = 0;
	public static final int i_c2 = 1;


	public static final Class<?> childTypes[] = {silver.modification.copper.NParserComponent.class,silver.modification.copper.NParserComponents.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_consParserComponent;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NParserComponents.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NParserComponents.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c1] = new common.Lazy[silver.modification.copper.NParserComponent.num_inh_attrs];
	childInheritedAttributes[i_c2] = new common.Lazy[silver.modification.copper.NParserComponents.num_inh_attrs];

	}

	public PconsParserComponent(final Object c_c1, final Object c_c2, final Object a_core_location) {
		super(a_core_location);
		this.child_c1 = c_c1;
		this.child_c2 = c_c2;

	}

	private Object child_c1;
	public final silver.modification.copper.NParserComponent getChild_c1() {
		return (silver.modification.copper.NParserComponent) (child_c1 = common.Util.demand(child_c1));
	}

	private Object child_c2;
	public final silver.modification.copper.NParserComponents getChild_c2() {
		return (silver.modification.copper.NParserComponents) (child_c2 = common.Util.demand(child_c2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c1: return getChild_c1();
			case i_c2: return getChild_c2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c1: return child_c1;
			case i_c2: return child_c2;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:consParserComponent erroneously claimed to forward");
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
		return "silver:modification:copper:consParserComponent";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = c1.pp ++ ", " ++ c2.pp
		silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconsParserComponent.i_c1).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponent)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconsParserComponent.i_c2).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponents)))); } };
		// top.moduleNames = c1.moduleNames ++ c2.moduleNames
		silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c1, silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponent), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c2, silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents))); } };
		// top.errors := c1.errors ++ c2.errors
		if(silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents] == null)
			silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents);
		((common.CollectionAttribute)silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c1, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c2, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents))); } });
		// top.terminalPrefixes = c1.terminalPrefixes ++ c2.terminalPrefixes
		silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c1, silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponent), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c2, silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponents))); } };
		// top.liftedAGDcls = appendAGDcl(c1.liftedAGDcls, c2.liftedAGDcls,location=top.location)
		silver.modification.copper.PconsParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c1, silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponent), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsParserComponent.i_c2, silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponents), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponents)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PconsParserComponent> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsParserComponent> {

		@Override
		public PconsParserComponent invoke(final Object[] children, final Object[] annotations) {
			return new PconsParserComponent(children[0], children[1], annotations[0]);
		}
	};

}
