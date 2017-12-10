
package silver.modification.let_fix;

// top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end' 
public final class Pletp_c extends silver.definition.core.NExpr {

	public static final int i__G_4 = 0;
	public static final int i_la = 1;
	public static final int i__G_2 = 2;
	public static final int i_e = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.modification.let_fix.TLet_kwd.class,silver.modification.let_fix.NLetAssigns.class,silver.modification.let_fix.TIn_kwd.class,silver.definition.core.NExpr.class,silver.modification.let_fix.TEnd_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_letp_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_la] = new common.Lazy[silver.modification.let_fix.NLetAssigns.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public Pletp_c(final Object c__G_4, final Object c_la, final Object c__G_2, final Object c_e, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_la = c_la;
		this.child__G_2 = c__G_2;
		this.child_e = c_e;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.modification.let_fix.TLet_kwd getChild__G_4() {
		return (silver.modification.let_fix.TLet_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_la;
	public final silver.modification.let_fix.NLetAssigns getChild_la() {
		return (silver.modification.let_fix.NLetAssigns) (child_la = common.Util.demand(child_la));
	}

	private Object child__G_2;
	public final silver.modification.let_fix.TIn_kwd getChild__G_2() {
		return (silver.modification.let_fix.TIn_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_0;
	public final silver.modification.let_fix.TEnd_kwd getChild__G_0() {
		return (silver.modification.let_fix.TEnd_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_la: return getChild_la();
			case i__G_2: return getChild__G_2();
			case i_e: return getChild_e();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_la: return child_la;
			case i__G_2: return child__G_2;
			case i_e: return child_e;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return ((silver.definition.core.NExpr)new silver.modification.let_fix.Pletp(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.Pletp_c.i_la, silver.modification.let_fix.Init.silver_modification_let_fix_letAssignExprs__ON__silver_modification_let_fix_LetAssigns), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.let_fix.Pletp_c.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:let_fix:letp_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "let " ++ la.pp ++ " in " ++ e.pp ++ " end"
		silver.modification.let_fix.Pletp_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("let ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.Pletp_c.i_la).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_LetAssigns)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" in ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.Pletp_c.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(" end")))))); } };

	}

	public static final common.NodeFactory<Pletp_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pletp_c> {

		@Override
		public Pletp_c invoke(final Object[] children, final Object[] annotations) {
			return new Pletp_c(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
