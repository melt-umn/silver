
package silver.definition.type.syntax;

// top::TypeExpr ::= tv::IdLower_t 
public final class PtypeVariableTypeExpr extends silver.definition.type.syntax.NTypeExpr {

	public static final int i_tv = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TIdLower_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_typeVariableTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtypeVariableTypeExpr(final Object c_tv, final Object a_core_location) {
		super(a_core_location);
		this.child_tv = c_tv;

	}

	private Object child_tv;
	public final silver.definition.core.TIdLower_t getChild_tv() {
		return (silver.definition.core.TIdLower_t) (child_tv = common.Util.demand(child_tv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tv: return getChild_tv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tv: return child_tv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:typeVariableTypeExpr erroneously claimed to forward");
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
		return "silver:definition:type:syntax:typeVariableTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = tv.lexeme
		silver.definition.type.syntax.PtypeVariableTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.definition.type.syntax.PtypeVariableTypeExpr.i_tv)).lexeme); } };
		// hack = customLookup("type", getTypeDcl(tv.lexeme, top.env), tv.lexeme, top.location)
		silver.definition.type.syntax.PtypeVariableTypeExpr.localAttributes[silver.definition.type.syntax.Init.hack__ON__silver_definition_type_syntax_typeVariableTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDcl.invoke(((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.definition.type.syntax.PtypeVariableTypeExpr.i_tv)).lexeme), context.contextInheritedLazy(silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_TypeExpr))); } }, ((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.definition.type.syntax.PtypeVariableTypeExpr.i_tv)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } })); } };
		// top.typerep = hack.typerep
		silver.definition.type.syntax.PtypeVariableTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.localDecorated(silver.definition.type.syntax.Init.hack__ON__silver_definition_type_syntax_typeVariableTypeExpr).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } };
		// top.errors := hack.errors
		if(silver.definition.type.syntax.PtypeVariableTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.definition.type.syntax.PtypeVariableTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.definition.type.syntax.PtypeVariableTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.type.syntax.Init.hack__ON__silver_definition_type_syntax_typeVariableTypeExpr).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// top.lexicalTypeVariables = [ tv.lexeme ]
		silver.definition.type.syntax.PtypeVariableTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.definition.type.syntax.PtypeVariableTypeExpr.i_tv)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PtypeVariableTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtypeVariableTypeExpr> {

		@Override
		public PtypeVariableTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PtypeVariableTypeExpr(children[0], annotations[0]);
		}
	};

}
