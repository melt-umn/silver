
package silver.modification.copper;

// top::AGDcl ::= 'lexer' 'class' id::Name ';' 
public final class PlexerClassDclEmpty extends silver.definition.core.NAGDcl {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_id = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.modification.copper.TLexer_kwd.class,silver.modification.copper.TClass_kwd.class,silver.definition.core.NName.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_lexerClassDclEmpty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PlexerClassDclEmpty(final Object c__G_3, final Object c__G_2, final Object c_id, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_id = c_id;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.modification.copper.TLexer_kwd getChild__G_3() {
		return (silver.modification.copper.TLexer_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.modification.copper.TClass_kwd getChild__G_2() {
		return (silver.modification.copper.TClass_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_id: return getChild_id();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_id: return child_id;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)new silver.modification.copper.PlexerClassDecl(context.childAsIsLazy(silver.modification.copper.PlexerClassDclEmpty.i__G_3), context.childAsIsLazy(silver.modification.copper.PlexerClassDclEmpty.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PlexerClassDclEmpty.i_id)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.copper.NLexerClassModifiers)new silver.modification.copper.PlexerClassModifiersNone(((core.NLocation)((silver.definition.core.TSemi_t)context.childAsIs(silver.modification.copper.PlexerClassDclEmpty.i__G_0)).location))); } }, context.childAsIsLazy(silver.modification.copper.PlexerClassDclEmpty.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:lexerClassDclEmpty";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PlexerClassDclEmpty> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassDclEmpty> {

		@Override
		public PlexerClassDclEmpty invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassDclEmpty(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
