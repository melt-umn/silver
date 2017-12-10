
package silver.definition.flow.env_parser;

// top::IPatternVarProjection ::= '(' child::IName ',' typeName::IName ',' patternVar::IName ')' 
public final class PaPatternVarProjection extends silver.definition.flow.env_parser.NIPatternVarProjection {

	public static final int i__G_6 = 0;
	public static final int i_child = 1;
	public static final int i__G_4 = 2;
	public static final int i_typeName = 3;
	public static final int i__G_2 = 4;
	public static final int i_patternVar = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_aPatternVarProjection;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIPatternVarProjection.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIPatternVarProjection.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_child] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_typeName] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_patternVar] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];

	}

	public PaPatternVarProjection(final Object c__G_6, final Object c_child, final Object c__G_4, final Object c_typeName, final Object c__G_2, final Object c_patternVar, final Object c__G_0) {
		super();
		this.child__G_6 = c__G_6;
		this.child_child = c_child;
		this.child__G_4 = c__G_4;
		this.child_typeName = c_typeName;
		this.child__G_2 = c__G_2;
		this.child_patternVar = c_patternVar;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_6() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_child;
	public final silver.definition.env.env_parser.NIName getChild_child() {
		return (silver.definition.env.env_parser.NIName) (child_child = common.Util.demand(child_child));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TComma_t getChild__G_4() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_typeName;
	public final silver.definition.env.env_parser.NIName getChild_typeName() {
		return (silver.definition.env.env_parser.NIName) (child_typeName = common.Util.demand(child_typeName));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_patternVar;
	public final silver.definition.env.env_parser.NIName getChild_patternVar() {
		return (silver.definition.env.env_parser.NIName) (child_patternVar = common.Util.demand(child_patternVar));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i_child: return getChild_child();
			case i__G_4: return getChild__G_4();
			case i_typeName: return getChild_typeName();
			case i__G_2: return getChild__G_2();
			case i_patternVar: return getChild_patternVar();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i_child: return child_child;
			case i__G_4: return child__G_4;
			case i_typeName: return child_typeName;
			case i__G_2: return child__G_2;
			case i_patternVar: return child_patternVar;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:aPatternVarProjection erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:aPatternVarProjection";
	}

	static void initProductionAttributeDefinitions() {
		// top.aPatternVarProjection = [ patternVarProjection(child.aname, typeName.aname, patternVar.aname) ]
		silver.definition.flow.env_parser.PaPatternVarProjection.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjection] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NPatternVarProjection)new silver.definition.flow.ast.PpatternVarProjection(context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaPatternVarProjection.i_child, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaPatternVarProjection.i_typeName, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaPatternVarProjection.i_patternVar, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaPatternVarProjection> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaPatternVarProjection> {

		@Override
		public PaPatternVarProjection invoke(final Object[] children, final Object[] annotations) {
			return new PaPatternVarProjection(children[0], children[1], children[2], children[3], children[4], children[5], children[6]);
		}
	};

}
