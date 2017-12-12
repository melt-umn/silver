
package silver.modification.impide;

// top::IdePropertyOption ::= 'default' '=' str::String_t 
public final class PidePropertyOption_defaultVal extends silver.modification.impide.NIdePropertyOption {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i_str = 2;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_PropOption_Default_t.class,silver.definition.core.TEqual_t.class,silver.definition.core.TString_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_idePropertyOption_defaultVal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdePropertyOption.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdePropertyOption.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PidePropertyOption_defaultVal(final Object c__G_2, final Object c__G_1, final Object c_str) {
		super();
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_str = c_str;

	}

	private Object child__G_2;
	public final silver.modification.impide.TImpIde_PropOption_Default_t getChild__G_2() {
		return (silver.modification.impide.TImpIde_PropOption_Default_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.definition.core.TEqual_t getChild__G_1() {
		return (silver.definition.core.TEqual_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_str;
	public final silver.definition.core.TString_t getChild_str() {
		return (silver.definition.core.TString_t) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_str: return child_str;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:idePropertyOption_defaultVal erroneously claimed to forward");
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
		return "silver:modification:impide:idePropertyOption_defaultVal";
	}

	static void initProductionAttributeDefinitions() {
		// top.propDefault = [ substring(1, length(str.lexeme) - 1, str.lexeme) ]
		silver.modification.impide.PidePropertyOption_defaultVal.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOption] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PidePropertyOption_defaultVal.i_str)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PidePropertyOption_defaultVal.i_str)).lexeme))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PidePropertyOption_defaultVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PidePropertyOption_defaultVal> {

		@Override
		public PidePropertyOption_defaultVal invoke(final Object[] children, final Object[] annotations) {
			return new PidePropertyOption_defaultVal(children[0], children[1], children[2]);
		}
	};

}
