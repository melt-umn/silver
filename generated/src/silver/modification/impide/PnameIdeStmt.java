
package silver.modification.impide;

// top::IdeStmt ::= 'name' ideName::String_t ';' 
public final class PnameIdeStmt extends silver.modification.impide.NIdeStmt {

	public static final int i__G_2 = 0;
	public static final int i_ideName = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_ProdInfo_Name_t.class,silver.definition.core.TString_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_nameIdeStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnameIdeStmt(final Object c__G_2, final Object c_ideName, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_ideName = c_ideName;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.modification.impide.TImpIde_ProdInfo_Name_t getChild__G_2() {
		return (silver.modification.impide.TImpIde_ProdInfo_Name_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ideName;
	public final silver.definition.core.TString_t getChild_ideName() {
		return (silver.definition.core.TString_t) (child_ideName = common.Util.demand(child_ideName));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_ideName: return getChild_ideName();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_ideName: return child_ideName;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:nameIdeStmt erroneously claimed to forward");
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
		return "silver:modification:impide:nameIdeStmt";
	}

	static void initProductionAttributeDefinitions() {
		// iName = substring(1, length(ideName.lexeme) - 1, ideName.lexeme)
		silver.modification.impide.PnameIdeStmt.localAttributes[silver.modification.impide.Init.iName__ON__silver_modification_impide_nameIdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PnameIdeStmt.i_ideName)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PnameIdeStmt.i_ideName)).lexeme))); } };
		// top.errors := if iName == "" then [ wrn(ideName.location, "The name of IDE product is empty. A default name will be used.") ] else if isDigit(substring(0, 1, iName)) then [ err(ideName.location, "The name of IDE product cannot be started with digital.") ] else []
		if(silver.modification.impide.PnameIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] == null)
			silver.modification.impide.PnameIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt);
		((common.CollectionAttribute)silver.modification.impide.PnameIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.localAsIs(silver.modification.impide.Init.iName__ON__silver_modification_impide_nameIdeStmt)).equals((new common.StringCatter(""))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(((core.NLocation)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PnameIdeStmt.i_ideName)).location), (new common.StringCatter("The name of IDE product is empty. A default name will be used.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : (((Boolean)core.PisDigit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), Integer.valueOf((int)1), context.localAsIsLazy(silver.modification.impide.Init.iName__ON__silver_modification_impide_nameIdeStmt))); } })) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PnameIdeStmt.i_ideName)).location), (new common.StringCatter("The name of IDE product cannot be started with digital.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });
		// top.ideNames = [ iName ]
		silver.modification.impide.PnameIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.localAsIsLazy(silver.modification.impide.Init.iName__ON__silver_modification_impide_nameIdeStmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PnameIdeStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnameIdeStmt> {

		@Override
		public PnameIdeStmt invoke(final Object[] children, final Object[] annotations) {
			return new PnameIdeStmt(children[0], children[1], children[2], annotations[0]);
		}
	};

}
