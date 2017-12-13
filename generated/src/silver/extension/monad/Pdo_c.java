
package silver.extension.monad;

// top::Expr ::= 'do' '(' bindFn::QName ',' returnFn::QName ')' '{' body::DoBodyStmts '}' 
public final class Pdo_c extends silver.definition.core.NExpr {

	public static final int i__G_8 = 0;
	public static final int i__G_7 = 1;
	public static final int i_bindFn = 2;
	public static final int i__G_5 = 3;
	public static final int i_returnFn = 4;
	public static final int i__G_3 = 5;
	public static final int i__G_2 = 6;
	public static final int i_body = 7;
	public static final int i__G_0 = 8;


	public static final Class<?> childTypes[] = {silver.extension.monad.TDo_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.core.NQName.class,silver.definition.core.TComma_t.class,silver.definition.core.NQName.class,silver.definition.core.TRParen_t.class,silver.definition.core.TLCurly_t.class,silver.extension.monad.NDoBodyStmts.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_do_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[9][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_bindFn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_returnFn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	}

	public Pdo_c(final Object c__G_8, final Object c__G_7, final Object c_bindFn, final Object c__G_5, final Object c_returnFn, final Object c__G_3, final Object c__G_2, final Object c_body, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_8 = c__G_8;
		this.child__G_7 = c__G_7;
		this.child_bindFn = c_bindFn;
		this.child__G_5 = c__G_5;
		this.child_returnFn = c_returnFn;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_body = c_body;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_8;
	public final silver.extension.monad.TDo_kwd getChild__G_8() {
		return (silver.extension.monad.TDo_kwd) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child__G_7;
	public final silver.definition.core.TLParen_t getChild__G_7() {
		return (silver.definition.core.TLParen_t) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_bindFn;
	public final silver.definition.core.NQName getChild_bindFn() {
		return (silver.definition.core.NQName) (child_bindFn = common.Util.demand(child_bindFn));
	}

	private Object child__G_5;
	public final silver.definition.core.TComma_t getChild__G_5() {
		return (silver.definition.core.TComma_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_returnFn;
	public final silver.definition.core.NQName getChild_returnFn() {
		return (silver.definition.core.NQName) (child_returnFn = common.Util.demand(child_returnFn));
	}

	private Object child__G_3;
	public final silver.definition.core.TRParen_t getChild__G_3() {
		return (silver.definition.core.TRParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_body;
	public final silver.extension.monad.NDoBodyStmts getChild_body() {
		return (silver.extension.monad.NDoBodyStmts) (child_body = common.Util.demand(child_body));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_8: return getChild__G_8();
			case i__G_7: return getChild__G_7();
			case i_bindFn: return getChild_bindFn();
			case i__G_5: return getChild__G_5();
			case i_returnFn: return getChild_returnFn();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_body: return getChild_body();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_8: return child__G_8;
			case i__G_7: return child__G_7;
			case i_bindFn: return child_bindFn;
			case i__G_5: return child__G_5;
			case i_returnFn: return child_returnFn;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_body: return child_body;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 9;
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
		return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.extension.monad.Init.localErrors__ON__silver_extension_monad_do_c)))) ? ((silver.definition.core.NExpr)new silver.definition.core.PerrorExpr(context.localAsIsLazy(silver.extension.monad.Init.localErrors__ON__silver_extension_monad_do_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)context.childDecorated(silver.extension.monad.Pdo_c.i_body).synthesized(silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts)));
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
		return "silver:extension:monad:do_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "do (" ++ bindFn.pp ++ ", " ++ returnFn.pp ++ ") {" ++ body.pp ++ "}"
		silver.extension.monad.Pdo_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("do (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.Pdo_c.i_bindFn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.Pdo_c.i_returnFn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.Pdo_c.i_body).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts)), (common.StringCatter)(new common.StringCatter("}")))))))); } };
		// bindLookup = bindFn.lookupValue
		silver.extension.monad.Pdo_c.localAttributes[silver.extension.monad.Init.bindLookup__ON__silver_extension_monad_do_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.extension.monad.Pdo_c.i_bindFn).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)); } };
		// returnLookup = returnFn.lookupValue
		silver.extension.monad.Pdo_c.localAttributes[silver.extension.monad.Init.returnLookup__ON__silver_extension_monad_do_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.extension.monad.Pdo_c.i_returnFn).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)); } };
		// localErrors = bindLookup.errors ++ returnLookup.errors
		silver.extension.monad.Pdo_c.localAttributes[silver.extension.monad.Init.localErrors__ON__silver_extension_monad_do_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.localAsIs(silver.extension.monad.Init.bindLookup__ON__silver_extension_monad_do_c)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.localAsIs(silver.extension.monad.Init.returnLookup__ON__silver_extension_monad_do_c)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })); } };
		// body.bindFn = bindFn
		silver.extension.monad.Pdo_c.childInheritedAttributes[silver.extension.monad.Pdo_c.i_body][silver.extension.monad.Init.silver_extension_monad_bindFn__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.monad.Pdo_c.i_bindFn).undecorate(); } };
		// body.returnFn = returnFn
		silver.extension.monad.Pdo_c.childInheritedAttributes[silver.extension.monad.Pdo_c.i_body][silver.extension.monad.Init.silver_extension_monad_returnFn__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.monad.Pdo_c.i_returnFn).undecorate(); } };
		// body.isFinalVal = true
		silver.extension.monad.Pdo_c.childInheritedAttributes[silver.extension.monad.Pdo_c.i_body][silver.extension.monad.Init.silver_extension_monad_isFinalVal__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<Pdo_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pdo_c> {

		@Override
		public Pdo_c invoke(final Object[] children, final Object[] annotations) {
			return new Pdo_c(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], annotations[0]);
		}
	};

}
