
package silver.extension.easyterminal;

// top::EasyTerminalRef ::= t::Terminal_t 
public final class PeasyTerminalRef extends silver.extension.easyterminal.NEasyTerminalRef {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.extension.easyterminal.TTerminal_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_easyterminal_easyTerminalRef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.easyterminal.NEasyTerminalRef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.easyterminal.NEasyTerminalRef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PeasyTerminalRef(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.extension.easyterminal.TTerminal_t getChild_t() {
		return (silver.extension.easyterminal.TTerminal_t) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:easyterminal:easyTerminalRef erroneously claimed to forward");
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
		return "silver:extension:easyterminal:easyTerminalRef";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.lexeme
		silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_definition_env_pp__ON__silver_extension_easyterminal_EasyTerminalRef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).lexeme); } };
		// top.easyString = substring(1, length(t.lexeme) - 1, t.lexeme)
		silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_extension_easyterminal_easyString__ON__silver_extension_easyterminal_EasyTerminalRef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).lexeme))); } };
		// regHack = regexLiteral(top.easyString)
		silver.extension.easyterminal.PeasyTerminalRef.localAttributes[silver.extension.easyterminal.Init.regHack__ON__silver_extension_easyterminal_easyTerminalRef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegex)silver.definition.regex.PregexLiteral.invoke(context.contextSynthesizedLazy(silver.extension.easyterminal.Init.silver_extension_easyterminal_easyString__ON__silver_extension_easyterminal_EasyTerminalRef))); } };
		// top.dcls = getTerminalRegexDclAll(regHack.regString, top.env)
		silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.easyterminal.PgetTerminalRegexDclAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.extension.easyterminal.Init.regHack__ON__silver_extension_easyterminal_easyTerminalRef).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex)); } }, context.contextInheritedLazy(silver.extension.easyterminal.Init.silver_definition_env_env__ON__silver_extension_easyterminal_EasyTerminalRef))); } };
		// top.errors := if null(top.dcls) then [ err(t.location, "Could not find terminal declaration for " ++ t.lexeme) ] else if length(top.dcls) > 1 then [ err(t.location, "Found ambiguous possibilities for " ++ t.lexeme ++ "\n" ++ printPossibilities(top.dcls)) ] else []
		if(silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef] == null)
			silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef] = new silver.definition.core.CAerrors(silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef);
		((common.CollectionAttribute)silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).location), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Could not find terminal declaration for ")), (common.StringCatter)((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).lexeme)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((((Integer)core.PlistLength.invoke(context.contextSynthesizedLazy(silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).location), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Found ambiguous possibilities for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PeasyTerminalRef.i_t)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)silver.definition.core.PprintPossibilities.invoke(context.contextSynthesizedLazy(silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef)))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });
		// top.typerep = if null(top.dcls) then errorType() else head(top.dcls).typerep
		silver.extension.easyterminal.PeasyTerminalRef.synthesizedAttributes[silver.extension.easyterminal.Init.silver_definition_env_typerep__ON__silver_extension_easyterminal_EasyTerminalRef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)((silver.definition.env.NDclInfo)core.Phead.invoke(context.contextSynthesizedLazy(silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo))); } };

	}

	public static final common.NodeFactory<PeasyTerminalRef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PeasyTerminalRef> {

		@Override
		public PeasyTerminalRef invoke(final Object[] children, final Object[] annotations) {
			return new PeasyTerminalRef(children[0], annotations[0]);
		}
	};

}
