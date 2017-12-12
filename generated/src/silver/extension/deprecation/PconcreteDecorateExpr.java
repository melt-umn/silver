
package silver.extension.deprecation;

// top::Expr ::= q::NameTick 
public final class PconcreteDecorateExpr extends silver.definition.core.NExpr {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {silver.extension.deprecation.NNameTick.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_deprecation_concreteDecorateExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.extension.deprecation.NNameTick.num_inh_attrs];

	}

	public PconcreteDecorateExpr(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.extension.deprecation.NNameTick getChild_q() {
		return (silver.extension.deprecation.NNameTick) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.deprecation.NNameTick)context.childDecorated(silver.extension.deprecation.PconcreteDecorateExpr.i_q).undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.extension.deprecation.PconcreteDecorateExpr.i_q, silver.extension.deprecation.Init.silver_definition_core_name__ON__silver_extension_deprecation_NameTick))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.deprecation.NNameTick)context.childDecorated(silver.extension.deprecation.PconcreteDecorateExpr.i_q).undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:deprecation:concreteDecorateExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.extension.deprecation.PconcreteDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.deprecation.PconcreteDecorateExpr.i_q).synthesized(silver.extension.deprecation.Init.silver_definition_env_pp__ON__silver_extension_deprecation_NameTick)); } };
		// top.errors <- [ wrn(top.location, "Tick suffixes no longer do ANYTHING. Remove it!") ]
		if(silver.extension.deprecation.PconcreteDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.extension.deprecation.PconcreteDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.extension.deprecation.PconcreteDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Tick suffixes no longer do ANYTHING. Remove it!")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PconcreteDecorateExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconcreteDecorateExpr> {

		@Override
		public PconcreteDecorateExpr invoke(final Object[] children, final Object[] annotations) {
			return new PconcreteDecorateExpr(children[0], annotations[0]);
		}
	};

}
