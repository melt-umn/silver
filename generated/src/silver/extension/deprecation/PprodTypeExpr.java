
package silver.extension.deprecation;

// top::TypeExpr ::= 'Production' '(' sig::Signature ')' 
public final class PprodTypeExpr extends silver.definition.type.syntax.NTypeExpr {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_sig = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.extension.deprecation.TProduction_tkwd.class,silver.definition.core.TLParen_t.class,silver.definition.type.syntax.NSignature.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_deprecation_prodTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sig] = new common.Lazy[silver.definition.type.syntax.NSignature.num_inh_attrs];

	}

	public PprodTypeExpr(final Object c__G_3, final Object c__G_2, final Object c_sig, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_sig = c_sig;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.extension.deprecation.TProduction_tkwd getChild__G_3() {
		return (silver.extension.deprecation.TProduction_tkwd) (child__G_3 = common.Util.demand(child__G_3));
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
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_sig: return getChild_sig();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_sig: return child_sig;
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
		return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PfunTypeExpr(context.childAsIsLazy(silver.extension.deprecation.PprodTypeExpr.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.deprecation.PprodTypeExpr.i_sig)), context.childAsIsLazy(silver.extension.deprecation.PprodTypeExpr.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:deprecation:prodTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors <- [ wrn(top.location, "'Production' keyword in types is no longer necessary. Remove it.") ]
		if(silver.extension.deprecation.PprodTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.extension.deprecation.PprodTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.extension.deprecation.PprodTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("'Production' keyword in types is no longer necessary. Remove it.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PprodTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodTypeExpr> {

		@Override
		public PprodTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PprodTypeExpr(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
