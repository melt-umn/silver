
package silver.definition.type.syntax;

// top::TypeExprs ::= t::TypeExpr 
public final class PtypeListSingle extends silver.definition.type.syntax.NTypeExprs {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.definition.type.syntax.NTypeExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_typeListSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PtypeListSingle(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:typeListSingle erroneously claimed to forward");
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
		return "silver:definition:type:syntax:typeListSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.pp
		silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)); } };
		// top.errors := t.errors
		if(silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs] == null)
			silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs);
		((common.CollectionAttribute)silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr)); } });
		// top.types = [ t.typerep ]
		silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_TypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PtypeListSingle.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };

	}

	public static final common.NodeFactory<PtypeListSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtypeListSingle> {

		@Override
		public PtypeListSingle invoke(final Object[] children, final Object[] annotations) {
			return new PtypeListSingle(children[0], annotations[0]);
		}
	};

}
