
package silver.modification.ffi.env_parser;

// top::ITypeRep ::= 'foreigntype' '(' n::IName ',' ty::ITypeReps ')' 
public final class PaTypeRepForeign extends silver.definition.env.env_parser.NITypeRep {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_n = 2;
	public static final int i__G_2 = 3;
	public static final int i_ty = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.modification.ffi.env_parser.TFTTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITypeReps.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_env_parser_aTypeRepForeign;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.env.env_parser.NITypeReps.num_inh_attrs];

	}

	public PaTypeRepForeign(final Object c__G_5, final Object c__G_4, final Object c_n, final Object c__G_2, final Object c_ty, final Object c__G_0) {
		super();
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_n = c_n;
		this.child__G_2 = c__G_2;
		this.child_ty = c_ty;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.modification.ffi.env_parser.TFTTerm getChild__G_5() {
		return (silver.modification.ffi.env_parser.TFTTerm) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_4() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NIName getChild_n() {
		return (silver.definition.env.env_parser.NIName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ty;
	public final silver.definition.env.env_parser.NITypeReps getChild_ty() {
		return (silver.definition.env.env_parser.NITypeReps) (child_ty = common.Util.demand(child_ty));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_n: return getChild_n();
			case i__G_2: return getChild__G_2();
			case i_ty: return getChild_ty();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_n: return child_n;
			case i__G_2: return child__G_2;
			case i_ty: return child_ty;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:ffi:env_parser:aTypeRepForeign erroneously claimed to forward");
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
		return "silver:modification:ffi:env_parser:aTypeRepForeign";
	}

	static void initProductionAttributeDefinitions() {
		// top.typerep = foreignType(n.aname, ty.typereps)
		silver.modification.ffi.env_parser.PaTypeRepForeign.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.modification.ffi.PforeignType(context.childDecoratedSynthesizedLazy(silver.modification.ffi.env_parser.PaTypeRepForeign.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.modification.ffi.env_parser.PaTypeRepForeign.i_ty, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_typereps__ON__silver_definition_env_env_parser_ITypeReps))); } };

	}

	public static final common.NodeFactory<PaTypeRepForeign> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTypeRepForeign> {

		@Override
		public PaTypeRepForeign invoke(final Object[] children, final Object[] annotations) {
			return new PaTypeRepForeign(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
