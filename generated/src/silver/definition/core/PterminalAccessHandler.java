
package silver.definition.core;

// top::Expr ::= e::Decorated Expr q::Decorated QNameAttrOccur 
public final class PterminalAccessHandler extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_q = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_terminalAccessHandler;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PterminalAccessHandler(final Object c_e, final Object c_q, final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:terminalAccessHandler erroneously claimed to forward");
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
		return "silver:definition:core:terminalAccessHandler";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ "." ++ q.pp
		silver.definition.core.PterminalAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)))); } };
		// top.errors := if q.name == "lexeme" || q.name == "location" || q.name == "filename" || q.name == "line" || q.name == "column" then [] else [ err(q.location, q.name ++ " is not a terminal attribute") ]
		if(silver.definition.core.PterminalAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PterminalAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PterminalAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((((((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("lexeme"))) || ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("location")))) || ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("filename")))) || ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("line")))) || ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("column")))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQNameAttrOccur)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)(new common.StringCatter(" is not a terminal attribute"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.typerep = if q.name == "lexeme" || q.name == "filename" then stringType() else if q.name == "line" || q.name == "column" then intType() else if q.name == "location" then nonterminalType("core:Location", []) else errorType()
		silver.definition.core.PterminalAccessHandler.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("lexeme"))) || ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("filename")))) ? ((silver.definition.type.NType)new silver.definition.type.PstringType()) : ((((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("line"))) || ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("column")))) ? ((silver.definition.type.NType)new silver.definition.type.PintType()) : (((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PterminalAccessHandler.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)).equals((new common.StringCatter("location"))) ? ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("core:Location")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke())))); } };

	}

	public static final common.NodeFactory<PterminalAccessHandler> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalAccessHandler> {

		@Override
		public PterminalAccessHandler invoke(final Object[] children, final Object[] annotations) {
			return new PterminalAccessHandler(children[0], children[1], annotations[0]);
		}
	};

}
