
package silver.definition.type.syntax;

// top::TypeExpr ::= 'Decorated' t::TypeExpr 
public final class PrefTypeExpr extends silver.definition.type.syntax.NTypeExpr {

	public static final int i__G_1 = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.type.syntax.TDecorated_tkwd.class,silver.definition.type.syntax.NTypeExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_refTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PrefTypeExpr(final Object c__G_1, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child__G_1;
	public final silver.definition.type.syntax.TDecorated_tkwd getChild__G_1() {
		return (silver.definition.type.syntax.TDecorated_tkwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:refTypeExpr erroneously claimed to forward");
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
		return "silver:definition:type:syntax:refTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "Decorated " ++ t.pp
		silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Decorated ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PrefTypeExpr.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr))); } };
		// top.typerep = decoratedType(t.typerep)
		silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PdecoratedType(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PrefTypeExpr.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } };
		// top.errors := t.errors
		if(silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PrefTypeExpr.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr)); } });
		// top.errors <- case t.typerep of nonterminalType(_, _) -> [] | _ -> [ err(t.location, t.pp ++ " is not a nonterminal, and cannot be Decorated.") ] end
		if(silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] == null)
			silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr);
		((common.CollectionAttribute)silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5857___fail_5858 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.type.syntax.PrefTypeExpr.i_t).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PrefTypeExpr.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)(new common.StringCatter(" is not a nonterminal, and cannot be Decorated."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnonterminalType) { final common.Thunk<Object> __SV_LOCAL___pv5863___sv_tmp_pv_5864 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5865___sv_tmp_pv_5866 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_5857___fail_5858.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NType)context.childDecorated(silver.definition.type.syntax.PrefTypeExpr.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null)); } }).eval()); } });
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.type.syntax.PrefTypeExpr.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PrefTypeExpr.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };

	}

	public static final common.NodeFactory<PrefTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrefTypeExpr> {

		@Override
		public PrefTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PrefTypeExpr(children[0], children[1], annotations[0]);
		}
	};

}
