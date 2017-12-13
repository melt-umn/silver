
package silver.definition.concrete_syntax;

// top::ProductionModifier ::= 'operator' '=' n::QName 
public final class PproductionModifierOperator extends silver.definition.concrete_syntax.NProductionModifier {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i_n = 2;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.TOperator_kwd.class,silver.definition.core.TEqual_t.class,silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_productionModifierOperator;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PproductionModifierOperator(final Object c__G_2, final Object c__G_1, final Object c_n, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_n = c_n;

	}

	private Object child__G_2;
	public final silver.definition.concrete_syntax.TOperator_kwd getChild__G_2() {
		return (silver.definition.concrete_syntax.TOperator_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.definition.core.TEqual_t getChild__G_1() {
		return (silver.definition.core.TEqual_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_n;
	public final silver.definition.core.NQName getChild_n() {
		return (silver.definition.core.NQName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_n: return child_n;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:productionModifierOperator erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:productionModifierOperator";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "operator = " ++ n.pp
		silver.definition.concrete_syntax.PproductionModifierOperator.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("operator = ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierOperator.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName))); } };
		// top.productionModifiers = [ prodOperator(n.lookupType.fullName) ]
		silver.definition.concrete_syntax.PproductionModifierOperator.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifier)new silver.definition.concrete_syntax.ast.PprodOperator(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierOperator.i_n).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := n.lookupType.errors ++ if ! n.lookupType.typerep.isTerminal then [ err(n.location, n.pp ++ " is not a terminal.") ] else []
		if(silver.definition.concrete_syntax.PproductionModifierOperator.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier] == null)
			silver.definition.concrete_syntax.PproductionModifierOperator.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PproductionModifierOperator.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierOperator.i_n).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierOperator.i_n).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isTerminal__ON__silver_definition_type_Type))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierOperator.i_n).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierOperator.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is not a terminal."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } })); } });

	}

	public static final common.NodeFactory<PproductionModifierOperator> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionModifierOperator> {

		@Override
		public PproductionModifierOperator invoke(final Object[] children, final Object[] annotations) {
			return new PproductionModifierOperator(children[0], children[1], children[2], annotations[0]);
		}
	};

}
