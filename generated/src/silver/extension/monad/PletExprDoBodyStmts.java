
package silver.extension.monad;

// top::DoBodyStmts ::= n::MName '::' t::TypeExpr '=' e::Expr ';' rest::DoBodyStmts 
public final class PletExprDoBodyStmts extends silver.extension.monad.NDoBodyStmts {

	public static final int i_n = 0;
	public static final int i__G_5 = 1;
	public static final int i_t = 2;
	public static final int i__G_3 = 3;
	public static final int i_e = 4;
	public static final int i__G_1 = 5;
	public static final int i_rest = 6;


	public static final Class<?> childTypes[] = {silver.extension.monad.NMName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TEqual_t.class,silver.definition.core.NExpr.class,silver.definition.core.TSemi_t.class,silver.extension.monad.NDoBodyStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_letExprDoBodyStmts;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.extension.monad.NMName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_rest] = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	}

	public PletExprDoBodyStmts(final Object c_n, final Object c__G_5, final Object c_t, final Object c__G_3, final Object c_e, final Object c__G_1, final Object c_rest, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;
		this.child__G_5 = c__G_5;
		this.child_t = c_t;
		this.child__G_3 = c__G_3;
		this.child_e = c_e;
		this.child__G_1 = c__G_1;
		this.child_rest = c_rest;

	}

	private Object child_n;
	public final silver.extension.monad.NMName getChild_n() {
		return (silver.extension.monad.NMName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_5;
	public final silver.definition.core.TColonColon_t getChild__G_5() {
		return (silver.definition.core.TColonColon_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_3;
	public final silver.definition.core.TEqual_t getChild__G_3() {
		return (silver.definition.core.TEqual_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_1;
	public final silver.definition.core.TSemi_t getChild__G_1() {
		return (silver.definition.core.TSemi_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_rest;
	public final silver.extension.monad.NDoBodyStmts getChild_rest() {
		return (silver.extension.monad.NDoBodyStmts) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i__G_5: return getChild__G_5();
			case i_t: return getChild_t();
			case i__G_3: return getChild__G_3();
			case i_e: return getChild_e();
			case i__G_1: return getChild__G_1();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i__G_5: return child__G_5;
			case i_t: return child_t;
			case i__G_3: return child__G_3;
			case i_e: return child_e;
			case i__G_1: return child__G_1;
			case i_rest: return child_rest;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:monad:letExprDoBodyStmts erroneously claimed to forward");
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
		return "silver:extension:monad:letExprDoBodyStmts";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = n.pp ++ "::" ++ t.pp ++ " = " ++ e.pp ++ "; " ++ rest.pp
		silver.extension.monad.PletExprDoBodyStmts.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PletExprDoBodyStmts.i_n).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_MName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PletExprDoBodyStmts.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PletExprDoBodyStmts.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("; ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PletExprDoBodyStmts.i_rest).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts)))))))); } };
		// top.transform = letp(assignExpr(nameFromMName(n), '::', t, '=', e,location=top.location), rest.transform,location=top.location)
		silver.extension.monad.PletExprDoBodyStmts.synthesizedAttributes[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.modification.let_fix.Pletp(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.let_fix.NAssignExpr)new silver.modification.let_fix.PassignExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.extension.monad.PnameFromMName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PletExprDoBodyStmts.i_n)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PletExprDoBodyStmts.i_t)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TEqual_t((new common.StringCatter("=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PletExprDoBodyStmts.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmts)context.undecorate()).getAnno_core_location()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.monad.PletExprDoBodyStmts.i_rest, silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmts)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PletExprDoBodyStmts> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PletExprDoBodyStmts> {

		@Override
		public PletExprDoBodyStmts invoke(final Object[] children, final Object[] annotations) {
			return new PletExprDoBodyStmts(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
