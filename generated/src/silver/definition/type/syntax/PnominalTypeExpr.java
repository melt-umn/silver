
package silver.definition.type.syntax;

// top::TypeExpr ::= q::QNameType tl::BracketedOptTypeExprs 
public final class PnominalTypeExpr extends silver.definition.type.syntax.NTypeExpr {

	public static final int i_q = 0;
	public static final int i_tl = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQNameType.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_nominalTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.definition.core.NQNameType.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	}

	public PnominalTypeExpr(final Object c_q, final Object c_tl, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;
		this.child_tl = c_tl;

	}

	private Object child_q;
	public final silver.definition.core.NQNameType getChild_q() {
		return (silver.definition.core.NQNameType) (child_q = common.Util.demand(child_q));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_tl: return getChild_tl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_tl: return child_tl;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:nominalTypeExpr erroneously claimed to forward");
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
		return "silver:definition:type:syntax:nominalTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp ++ tl.pp
		silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameType)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } };
		// top.errors := q.lookupType.errors ++ tl.errors
		if(silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QNameType)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PnominalTypeExpr.i_tl, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } });
		// top.lexicalTypeVariables = tl.lexicalTypeVariables
		silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// top.errors <- if length(tl.types) != length(q.lookupType.dclBoundVars) then [ err(top.location, q.pp ++ " has " ++ toString(length(q.lookupType.dclBoundVars)) ++ " type variables, but there are " ++ toString(length(tl.types)) ++ " supplied here.") ] else []
		if(silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PnominalTypeExpr.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))).equals(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QNameType)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } }))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameType)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QNameType)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } })))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" type variables, but there are ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PnominalTypeExpr.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))))), (common.StringCatter)(new common.StringCatter(" supplied here."))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.typerep = performSubstitution(q.lookupType.typerep, zipVarsAndTypesIntoSubstitution(q.lookupType.dclBoundVars, tl.types))
		silver.definition.type.syntax.PnominalTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QNameType)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PzipVarsAndTypesIntoSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PnominalTypeExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QNameType)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PnominalTypeExpr.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } })); } };

	}

	public static final common.NodeFactory<PnominalTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnominalTypeExpr> {

		@Override
		public PnominalTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PnominalTypeExpr(children[0], children[1], annotations[0]);
		}
	};

}
