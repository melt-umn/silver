
package silver.definition.flow.ast;

// top::PatternVarProjection ::= child::String typeName::String patternVar::String 
public final class PpatternVarProjection extends silver.definition.flow.ast.NPatternVarProjection {

	public static final int i_child = 0;
	public static final int i_typeName = 1;
	public static final int i_patternVar = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_patternVarProjection;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NPatternVarProjection.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NPatternVarProjection.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PpatternVarProjection(final Object c_child, final Object c_typeName, final Object c_patternVar) {
		super();
		this.child_child = c_child;
		this.child_typeName = c_typeName;
		this.child_patternVar = c_patternVar;

	}

	private Object child_child;
	public final common.StringCatter getChild_child() {
		return (common.StringCatter) (child_child = common.Util.demand(child_child));
	}

	private Object child_typeName;
	public final common.StringCatter getChild_typeName() {
		return (common.StringCatter) (child_typeName = common.Util.demand(child_typeName));
	}

	private Object child_patternVar;
	public final common.StringCatter getChild_patternVar() {
		return (common.StringCatter) (child_patternVar = common.Util.demand(child_patternVar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_child: return getChild_child();
			case i_typeName: return getChild_typeName();
			case i_patternVar: return getChild_patternVar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_child: return child_child;
			case i_typeName: return child_typeName;
			case i_patternVar: return child_patternVar;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:patternVarProjection erroneously claimed to forward");
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
		return "silver:definition:flow:ast:patternVarProjection";
	}

	static void initProductionAttributeDefinitions() {
		// top.unparse = "('" ++ child ++ "', '" ++ typeName ++ "', '" ++ patternVar ++ "')"
		silver.definition.flow.ast.PpatternVarProjection.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_PatternVarProjection] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.ast.PpatternVarProjection.i_child)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.ast.PpatternVarProjection.i_typeName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.ast.PpatternVarProjection.i_patternVar)), (common.StringCatter)(new common.StringCatter("')")))))))); } };

	}

	public static final common.NodeFactory<PpatternVarProjection> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpatternVarProjection> {

		@Override
		public PpatternVarProjection invoke(final Object[] children, final Object[] annotations) {
			return new PpatternVarProjection(children[0], children[1], children[2]);
		}
	};

}
