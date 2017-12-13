
package silver.definition.type.syntax;

// top::TypeExpr ::= '(' sig::Signature ')' 
public final class PfunTypeExpr extends silver.definition.type.syntax.NTypeExpr {

	public static final int i__G_2 = 0;
	public static final int i_sig = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.TLParen_t.class,silver.definition.type.syntax.NSignature.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_funTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sig] = new common.Lazy[silver.definition.type.syntax.NSignature.num_inh_attrs];

	}

	public PfunTypeExpr(final Object c__G_2, final Object c_sig, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_sig = c_sig;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.core.TLParen_t getChild__G_2() {
		return (silver.definition.core.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_sig;
	public final silver.definition.type.syntax.NSignature getChild_sig() {
		return (silver.definition.type.syntax.NSignature) (child_sig = common.Util.demand(child_sig));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_sig: return getChild_sig();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_sig: return child_sig;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:funTypeExpr erroneously claimed to forward");
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
		return "silver:definition:type:syntax:funTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "(" ++ sig.pp ++ ")"
		silver.definition.type.syntax.PfunTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PfunTypeExpr.i_sig).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_Signature)), (common.StringCatter)(new common.StringCatter(")")))); } };
		// top.errors := sig.errors
		if(silver.definition.type.syntax.PfunTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.definition.type.syntax.PfunTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.definition.type.syntax.PfunTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PfunTypeExpr.i_sig).synthesized(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_Signature)); } });
		// top.typerep = functionType(head(sig.types), tail(sig.types), [])
		silver.definition.type.syntax.PfunTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PfunTypeExpr.i_sig, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_Signature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PfunTypeExpr.i_sig, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_Signature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.lexicalTypeVariables = sig.lexicalTypeVariables
		silver.definition.type.syntax.PfunTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PfunTypeExpr.i_sig).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_Signature)); } };

	}

	public static final common.NodeFactory<PfunTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfunTypeExpr> {

		@Override
		public PfunTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PfunTypeExpr(children[0], children[1], children[2], annotations[0]);
		}
	};

}
