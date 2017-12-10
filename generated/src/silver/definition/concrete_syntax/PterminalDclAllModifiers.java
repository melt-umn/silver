
package silver.definition.concrete_syntax;

// top::AGDcl ::= t::TerminalKeywordModifier 'terminal' id::Name r::RegExpr tm::TerminalModifiers ';' 
public final class PterminalDclAllModifiers extends silver.definition.core.NAGDcl {

	public static final int i_t = 0;
	public static final int i__G_4 = 1;
	public static final int i_id = 2;
	public static final int i_r = 3;
	public static final int i_tm = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NTerminalKeywordModifier.class,silver.definition.core.TTerminal_kwd.class,silver.definition.core.NName.class,silver.definition.concrete_syntax.NRegExpr.class,silver.definition.concrete_syntax.NTerminalModifiers.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_terminalDclAllModifiers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.concrete_syntax.NTerminalKeywordModifier.num_inh_attrs];
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_inh_attrs];
	childInheritedAttributes[i_tm] = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_inh_attrs];

	}

	public PterminalDclAllModifiers(final Object c_t, final Object c__G_4, final Object c_id, final Object c_r, final Object c_tm, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;
		this.child__G_4 = c__G_4;
		this.child_id = c_id;
		this.child_r = c_r;
		this.child_tm = c_tm;
		this.child__G_0 = c__G_0;

	}

	private Object child_t;
	public final silver.definition.concrete_syntax.NTerminalKeywordModifier getChild_t() {
		return (silver.definition.concrete_syntax.NTerminalKeywordModifier) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_4;
	public final silver.definition.core.TTerminal_kwd getChild__G_4() {
		return (silver.definition.core.TTerminal_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_r;
	public final silver.definition.concrete_syntax.NRegExpr getChild_r() {
		return (silver.definition.concrete_syntax.NRegExpr) (child_r = common.Util.demand(child_r));
	}

	private Object child_tm;
	public final silver.definition.concrete_syntax.NTerminalModifiers getChild_tm() {
		return (silver.definition.concrete_syntax.NTerminalModifiers) (child_tm = common.Util.demand(child_tm));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i__G_4: return getChild__G_4();
			case i_id: return getChild_id();
			case i_r: return getChild_r();
			case i_tm: return getChild_tm();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i__G_4: return child__G_4;
			case i_id: return child_id;
			case i_r: return child_r;
			case i_tm: return child_tm;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)new silver.definition.concrete_syntax.PterminalDclDefault(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PterminalDclAllModifiers.i_t)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PterminalDclAllModifiers.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PterminalDclAllModifiers.i_r)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PterminalDclAllModifiers.i_tm)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:concrete_syntax:terminalDclAllModifiers";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PterminalDclAllModifiers> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalDclAllModifiers> {

		@Override
		public PterminalDclAllModifiers invoke(final Object[] children, final Object[] annotations) {
			return new PterminalDclAllModifiers(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
