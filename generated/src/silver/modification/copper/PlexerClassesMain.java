
package silver.modification.copper;

// top::ClassList ::= n::QName t::ClassList 
public final class PlexerClassesMain extends silver.modification.copper.NClassList {

	public static final int i_n = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.modification.copper.NClassList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_lexerClassesMain;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NClassList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NClassList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.copper.NClassList.num_inh_attrs];

	}

	public PlexerClassesMain(final Object c_n, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;
		this.child_t = c_t;

	}

	private Object child_n;
	public final silver.definition.core.NQName getChild_n() {
		return (silver.definition.core.NQName) (child_n = common.Util.demand(child_n));
	}

	private Object child_t;
	public final silver.modification.copper.NClassList getChild_t() {
		return (silver.modification.copper.NClassList) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:lexerClassesMain erroneously claimed to forward");
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
		return "silver:modification:copper:lexerClassesMain";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = if t.pp == "" then n.pp else n.pp ++ ", " ++ t.pp
		silver.modification.copper.PlexerClassesMain.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ClassList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassesMain.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ClassList)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassesMain.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassesMain.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassesMain.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ClassList))))); } };
		// top.errors := n.lookupLexerClass.errors ++ t.errors
		if(silver.modification.copper.PlexerClassesMain.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList] == null)
			silver.modification.copper.PlexerClassesMain.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList);
		((common.CollectionAttribute)silver.modification.copper.PlexerClassesMain.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PlexerClassesMain.i_n).synthesized(silver.modification.copper.Init.silver_modification_copper_lookupLexerClass__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassesMain.i_t, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList))); } });
		// top.lexerClasses = [ n.lookupLexerClass.dcl.fullName ] ++ t.lexerClasses
		silver.modification.copper.PlexerClassesMain.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_modification_copper_ClassList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PlexerClassesMain.i_n).synthesized(silver.modification.copper.Init.silver_modification_copper_lookupLexerClass__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassesMain.i_t, silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_modification_copper_ClassList))); } };

	}

	public static final common.NodeFactory<PlexerClassesMain> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassesMain> {

		@Override
		public PlexerClassesMain invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassesMain(children[0], children[1], annotations[0]);
		}
	};

}
