
package silver.definition.core;

// top::Expr ::= e::Decorated Expr q::Decorated QNameAttrOccur 
public final class PannoAccessHandler extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_q = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_annoAccessHandler;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PannoAccessHandler(final Object c_e, final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child_q = c_q;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}

	private Object child_q;
	public final common.DecoratedNode getChild_q() {
		return (common.DecoratedNode) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_q: return child_q;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:annoAccessHandler erroneously claimed to forward");
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
		return "silver:definition:core:annoAccessHandler";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ "." ++ q.pp
		silver.definition.core.PannoAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PannoAccessHandler.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PannoAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)))); } };
		// index = findNamedSigElem(q.name, annotationsForNonterminal(q.attrFor, top.env), 0)
		silver.definition.core.PannoAccessHandler.localAttributes[silver.definition.core.Init.index__ON__silver_definition_core_annoAccessHandler] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)silver.definition.env.PfindNamedSigElem.invoke(context.childAsIsSynthesizedLazy(silver.definition.core.PannoAccessHandler.i_q, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PannotationsForNonterminal.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childAsIs(silver.definition.core.PannoAccessHandler.i_q)).inherited(silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr))); } }, Integer.valueOf((int)0))); } };
		// top.typerep = q.typerep
		silver.definition.core.PannoAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childAsIs(silver.definition.core.PannoAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameAttrOccur)); } };
		// top.errors := q.errors
		if(silver.definition.core.PannoAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PannoAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PannoAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childAsIs(silver.definition.core.PannoAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur)); } });

	}

	public static final common.NodeFactory<PannoAccessHandler> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PannoAccessHandler> {

		@Override
		public PannoAccessHandler invoke(final Object[] children, final Object[] annotations) {
			return new PannoAccessHandler(children[0], children[1], annotations[0]);
		}
	};

}
