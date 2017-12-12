
package silver.modification.impide;

// top::IdeStmt ::= 'resource' id::Name path::String_t ';' 
public final class PresourceIdeStmt extends silver.modification.impide.NIdeStmt {

	public static final int i__G_3 = 0;
	public static final int i_id = 1;
	public static final int i_path = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_IdeResource.class,silver.definition.core.NName.class,silver.definition.core.TString_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_resourceIdeStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PresourceIdeStmt(final Object c__G_3, final Object c_id, final Object c_path, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child_id = c_id;
		this.child_path = c_path;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.modification.impide.TImpIde_IdeResource getChild__G_3() {
		return (silver.modification.impide.TImpIde_IdeResource) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_path;
	public final silver.definition.core.TString_t getChild_path() {
		return (silver.definition.core.TString_t) (child_path = common.Util.demand(child_path));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i_id: return getChild_id();
			case i_path: return getChild_path();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i_id: return child_id;
			case i_path: return child_path;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:resourceIdeStmt erroneously claimed to forward");
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
		return "silver:modification:impide:resourceIdeStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors := []
		if(silver.modification.impide.PresourceIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] == null)
			silver.modification.impide.PresourceIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt);
		((common.CollectionAttribute)silver.modification.impide.PresourceIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.ideResources = [ pair(id.name, substring(1, length(path.lexeme) - 1, path.lexeme)) ]
		silver.modification.impide.PresourceIdeStmt.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.modification.impide.PresourceIdeStmt.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PresourceIdeStmt.i_path)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PresourceIdeStmt.i_path)).lexeme))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PresourceIdeStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PresourceIdeStmt> {

		@Override
		public PresourceIdeStmt invoke(final Object[] children, final Object[] annotations) {
			return new PresourceIdeStmt(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
