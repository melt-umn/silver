
package silver.definition.core;

// top::Expr ::= e::Expr '.' q::QNameAttrOccur 
public final class Paccess extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i__G_1 = 1;
	public static final int i_q = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.TDot_t.class,silver.definition.core.NQNameAttrOccur.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_access;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_q] = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_inh_attrs];

	}

	public Paccess(final Object c_e, final Object c__G_1, final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child__G_1 = c__G_1;
		this.child_q = c_q;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_1;
	public final silver.definition.core.TDot_t getChild__G_1() {
		return (silver.definition.core.TDot_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_q;
	public final silver.definition.core.NQNameAttrOccur getChild_q() {
		return (silver.definition.core.NQNameAttrOccur) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i__G_1: return getChild__G_1();
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i__G_1: return child__G_1;
			case i_q: return child_q;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)((common.NodeFactory<silver.definition.core.NExpr>)((silver.definition.type.NType)context.childDecorated(silver.definition.core.Paccess.i_q).inherited(silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_accessHandler__ON__silver_definition_type_Type)).invoke(new Object[]{context.childDecoratedLazy(silver.definition.core.Paccess.i_e), context.childDecoratedLazy(silver.definition.core.Paccess.i_q), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }}, null));
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
		return "silver:definition:core:access";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ "." ++ q.pp
		silver.definition.core.Paccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Paccess.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Paccess.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)))); } };
		// top.errors := e.errors ++ forward.errors
		if(silver.definition.core.Paccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.Paccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.Paccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Paccess.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } })); } });
		// q.attrFor = performSubstitution(e.typerep, e.upSubst)
		silver.definition.core.Paccess.childInheritedAttributes[silver.definition.core.Paccess.i_q][silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Paccess.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.definition.core.Paccess.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } };

	}

	public static final common.NodeFactory<Paccess> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Paccess> {

		@Override
		public Paccess invoke(final Object[] children, final Object[] annotations) {
			return new Paccess(children[0], children[1], children[2], annotations[0]);
		}
	};

}
