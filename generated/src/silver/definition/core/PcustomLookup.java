
package silver.definition.core;

// top::QNameLookup ::= kindOfLookup::String dcls::[DclInfo] name::String l::Location 
public final class PcustomLookup extends silver.definition.core.NQNameLookup {

	public static final int i_kindOfLookup = 0;
	public static final int i_dcls = 1;
	public static final int i_name = 2;
	public static final int i_l = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class,core.NLocation.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_customLookup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NQNameLookup.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NQNameLookup.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PcustomLookup(final Object c_kindOfLookup, final Object c_dcls, final Object c_name, final Object c_l) {
		super();
		this.child_kindOfLookup = c_kindOfLookup;
		this.child_dcls = c_dcls;
		this.child_name = c_name;
		this.child_l = c_l;

	}

	private Object child_kindOfLookup;
	public final common.StringCatter getChild_kindOfLookup() {
		return (common.StringCatter) (child_kindOfLookup = common.Util.demand(child_kindOfLookup));
	}

	private Object child_dcls;
	public final common.ConsCell getChild_dcls() {
		return (common.ConsCell) (child_dcls = common.Util.demand(child_dcls));
	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_kindOfLookup: return getChild_kindOfLookup();
			case i_dcls: return getChild_dcls();
			case i_name: return getChild_name();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_kindOfLookup: return child_kindOfLookup;
			case i_dcls: return child_dcls;
			case i_name: return child_name;
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:customLookup erroneously claimed to forward");
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
		return "silver:definition:core:customLookup";
	}

	static void initProductionAttributeDefinitions() {
		// top.dcls = dcls
		silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.core.PcustomLookup.i_dcls)); } };
		// top.dcl = if null(top.dcls) then error("INTERNAL ERROR: Accessing dcl of " ++ kindOfLookup ++ " " ++ name ++ " at " ++ l.unparse) else head(top.dcls)
		silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))) ? ((silver.definition.env.NDclInfo)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("INTERNAL ERROR: Accessing dcl of ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_kindOfLookup)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" at ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PcustomLookup.i_l).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location))))))); } })) : ((silver.definition.env.NDclInfo)core.Phead.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)))); } };
		// top.fullName = if null(top.dcls) then "undeclared:value:" ++ name else top.dcl.fullName
		silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("undeclared:value:")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_name))) : ((common.StringCatter)((silver.definition.env.NDclInfo)context.synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo))); } };
		// top.typerep = if null(top.dcls) then errorType() else top.dcl.typerep
		silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)((silver.definition.env.NDclInfo)context.synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo))); } };
		// top.dclBoundVars = if null(top.dcls) then [] else top.dcl.dclBoundVars
		silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)((silver.definition.env.NDclInfo)context.synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_dclBoundVars__ON__silver_definition_env_DclInfo))); } };
		// top.errors := (if null(top.dcls) then [ err(l, "Undeclared " ++ kindOfLookup ++ " '" ++ name ++ "'.") ] else []) ++ (if length(top.dcls) > 1 then [ err(l, "Ambiguous reference to " ++ kindOfLookup ++ " '" ++ name ++ "'. Possibilities are:\n" ++ printPossibilities(top.dcls)) ] else [])
		if(silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup] == null)
			silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup);
		((common.CollectionAttribute)silver.definition.core.PcustomLookup.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PcustomLookup.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Undeclared ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_kindOfLookup)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_name)), (common.StringCatter)(new common.StringCatter("'.")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PcustomLookup.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Ambiguous reference to ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_kindOfLookup)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.PcustomLookup.i_name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'. Possibilities are:\n")), (common.StringCatter)((common.StringCatter)silver.definition.core.PprintPossibilities.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } })); } });

	}

	public static final common.NodeFactory<PcustomLookup> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcustomLookup> {

		@Override
		public PcustomLookup invoke(final Object[] children, final Object[] annotations) {
			return new PcustomLookup(children[0], children[1], children[2], children[3]);
		}
	};

}
