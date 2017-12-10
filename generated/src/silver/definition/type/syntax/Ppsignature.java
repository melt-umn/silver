
package silver.definition.type.syntax;

// top::Signature ::= t::TypeExpr '::=' list::TypeExprs 
public final class Ppsignature extends silver.definition.type.syntax.NSignature {

	public static final int i_t = 0;
	public static final int i__G_1 = 1;
	public static final int i_list = 2;


	public static final Class<?> childTypes[] = {silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TCCEQ_t.class,silver.definition.type.syntax.NTypeExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_psignature;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NSignature.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NSignature.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_list] = new common.Lazy[silver.definition.type.syntax.NTypeExprs.num_inh_attrs];

	}

	public Ppsignature(final Object c_t, final Object c__G_1, final Object c_list, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;
		this.child__G_1 = c__G_1;
		this.child_list = c_list;

	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_1;
	public final silver.definition.core.TCCEQ_t getChild__G_1() {
		return (silver.definition.core.TCCEQ_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_list;
	public final silver.definition.type.syntax.NTypeExprs getChild_list() {
		return (silver.definition.type.syntax.NTypeExprs) (child_list = common.Util.demand(child_list));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i__G_1: return getChild__G_1();
			case i_list: return getChild_list();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i__G_1: return child__G_1;
			case i_list: return child_list;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:psignature erroneously claimed to forward");
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
		return "silver:definition:type:syntax:psignature";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.pp ++ " ::= " ++ list.pp
		silver.definition.type.syntax.Ppsignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_Signature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.Ppsignature.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ::= ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.Ppsignature.i_list).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExprs)))); } };
		// top.errors := t.errors ++ list.errors
		if(silver.definition.type.syntax.Ppsignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_Signature] == null)
			silver.definition.type.syntax.Ppsignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_Signature] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_Signature);
		((common.CollectionAttribute)silver.definition.type.syntax.Ppsignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_Signature]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.Ppsignature.i_t, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.Ppsignature.i_list, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs))); } });
		// top.types = [ t.typerep ] ++ list.types
		silver.definition.type.syntax.Ppsignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_Signature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.Ppsignature.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.Ppsignature.i_list, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_TypeExprs))); } };
		// top.lexicalTypeVariables = t.lexicalTypeVariables ++ list.lexicalTypeVariables
		silver.definition.type.syntax.Ppsignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_Signature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.Ppsignature.i_t, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.Ppsignature.i_list, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs))); } };

	}

	public static final common.NodeFactory<Ppsignature> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Ppsignature> {

		@Override
		public Ppsignature invoke(final Object[] children, final Object[] annotations) {
			return new Ppsignature(children[0], children[1], children[2], annotations[0]);
		}
	};

}
