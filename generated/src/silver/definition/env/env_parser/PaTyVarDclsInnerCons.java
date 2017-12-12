
package silver.definition.env.env_parser;

// top::ITyVarDclsInner ::= t1::ITyVar ',' t2::ITyVarDclsInner 
public final class PaTyVarDclsInnerCons extends silver.definition.env.env_parser.NITyVarDclsInner {

	public static final int i_t1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_t2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TITyVar.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITyVarDclsInner.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aTyVarDclsInnerCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NITyVarDclsInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NITyVarDclsInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t2] = new common.Lazy[silver.definition.env.env_parser.NITyVarDclsInner.num_inh_attrs];

	}

	public PaTyVarDclsInnerCons(final Object c_t1, final Object c__G_1, final Object c_t2) {
		super();
		this.child_t1 = c_t1;
		this.child__G_1 = c__G_1;
		this.child_t2 = c_t2;

	}

	private Object child_t1;
	public final silver.definition.env.env_parser.TITyVar getChild_t1() {
		return (silver.definition.env.env_parser.TITyVar) (child_t1 = common.Util.demand(child_t1));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t2;
	public final silver.definition.env.env_parser.NITyVarDclsInner getChild_t2() {
		return (silver.definition.env.env_parser.NITyVarDclsInner) (child_t2 = common.Util.demand(child_t2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t1: return getChild_t1();
			case i__G_1: return getChild__G_1();
			case i_t2: return getChild_t2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t1: return child_t1;
			case i__G_1: return child__G_1;
			case i_t2: return child_t2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aTyVarDclsInnerCons erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aTyVarDclsInnerCons";
	}

	static void initProductionAttributeDefinitions() {
		// tv = freshTyVar()
		silver.definition.env.env_parser.PaTyVarDclsInnerCons.localAttributes[silver.definition.env.env_parser.Init.tv__ON__silver_definition_env_env_parser_aTyVarDclsInnerCons] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)silver.definition.type.PfreshTyVar.invoke()); } };
		// top.defs = (lexTyVarDef("IFACE", bogusLocation(), t1.lexeme, skolemType(tv)) :: t2.defs)
		silver.definition.env.env_parser.PaTyVarDclsInnerCons.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_ITyVarDclsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PlexTyVarDef.invoke((new common.StringCatter("IFACE")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)silver.definition.env.PbogusLocation.invoke()); } }, ((common.StringCatter)((silver.definition.env.env_parser.TITyVar)context.childAsIs(silver.definition.env.env_parser.PaTyVarDclsInnerCons.i_t1)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PskolemType(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.env.env_parser.Init.tv__ON__silver_definition_env_env_parser_aTyVarDclsInnerCons)))); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTyVarDclsInnerCons.i_t2, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_ITyVarDclsInner))); } };
		// top.tyvars = [ tv ] ++ t2.tyvars
		silver.definition.env.env_parser.PaTyVarDclsInnerCons.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_tyvars__ON__silver_definition_env_env_parser_ITyVarDclsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.env.env_parser.Init.tv__ON__silver_definition_env_env_parser_aTyVarDclsInnerCons)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTyVarDclsInnerCons.i_t2, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_tyvars__ON__silver_definition_env_env_parser_ITyVarDclsInner))); } };

	}

	public static final common.NodeFactory<PaTyVarDclsInnerCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTyVarDclsInnerCons> {

		@Override
		public PaTyVarDclsInnerCons invoke(final Object[] children, final Object[] annotations) {
			return new PaTyVarDclsInnerCons(children[0], children[1], children[2]);
		}
	};

}
