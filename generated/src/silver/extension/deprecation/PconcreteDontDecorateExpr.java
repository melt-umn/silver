
package silver.extension.deprecation;

// top::Expr ::= q::NameTickTick 
public final class PconcreteDontDecorateExpr extends silver.definition.core.NExpr {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {silver.extension.deprecation.NNameTickTick.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_deprecation_concreteDontDecorateExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.extension.deprecation.NNameTickTick.num_inh_attrs];

	}

	public PconcreteDontDecorateExpr(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.extension.deprecation.NNameTickTick getChild_q() {
		return (silver.extension.deprecation.NNameTickTick) (child_q = common.Util.demand(child_q));
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
		return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.deprecation.NNameTickTick)context.childDecorated(silver.extension.deprecation.PconcreteDontDecorateExpr.i_q).undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.extension.deprecation.PconcreteDontDecorateExpr.i_q, silver.extension.deprecation.Init.silver_definition_core_name__ON__silver_extension_deprecation_NameTickTick))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.deprecation.NNameTickTick)context.childDecorated(silver.extension.deprecation.PconcreteDontDecorateExpr.i_q).undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:deprecation:concreteDontDecorateExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.extension.deprecation.PconcreteDontDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.deprecation.PconcreteDontDecorateExpr.i_q).synthesized(silver.extension.deprecation.Init.silver_definition_env_pp__ON__silver_extension_deprecation_NameTickTick)); } };
		// top.errors <- [ wrn(top.location, "Double tick suffixes no longer do ANYTHING. Remove it!") ]
		if(silver.extension.deprecation.PconcreteDontDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.extension.deprecation.PconcreteDontDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.extension.deprecation.PconcreteDontDecorateExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Double tick suffixes no longer do ANYTHING. Remove it!")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PconcreteDontDecorateExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconcreteDontDecorateExpr> {

		@Override
		public PconcreteDontDecorateExpr invoke(final Object[] children, final Object[] annotations) {
			return new PconcreteDontDecorateExpr(children[0], annotations[0]);
		}
	};

}
