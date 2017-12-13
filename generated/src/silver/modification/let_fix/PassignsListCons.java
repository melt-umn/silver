
package silver.modification.let_fix;

// top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns 
public final class PassignsListCons extends silver.modification.let_fix.NLetAssigns {

	public static final int i_ae = 0;
	public static final int i__G_1 = 1;
	public static final int i_list = 2;


	public static final Class<?> childTypes[] = {silver.modification.let_fix.NAssignExpr.class,silver.definition.core.TComma_t.class,silver.modification.let_fix.NLetAssigns.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_assignsListCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.let_fix.NLetAssigns.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.let_fix.NLetAssigns.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ae] = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];
	childInheritedAttributes[i_list] = new common.Lazy[silver.modification.let_fix.NLetAssigns.num_inh_attrs];

	}

	public PassignsListCons(final Object c_ae, final Object c__G_1, final Object c_list, final Object a_core_location) {
		super(a_core_location);
		this.child_ae = c_ae;
		this.child__G_1 = c__G_1;
		this.child_list = c_list;

	}

	private Object child_ae;
	public final silver.modification.let_fix.NAssignExpr getChild_ae() {
		return (silver.modification.let_fix.NAssignExpr) (child_ae = common.Util.demand(child_ae));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_list;
	public final silver.modification.let_fix.NLetAssigns getChild_list() {
		return (silver.modification.let_fix.NLetAssigns) (child_list = common.Util.demand(child_list));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ae: return getChild_ae();
			case i__G_1: return getChild__G_1();
			case i_list: return getChild_list();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ae: return child_ae;
			case i__G_1: return child__G_1;
			case i_list: return child_list;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:assignsListCons erroneously claimed to forward");
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
		return "silver:modification:let_fix:assignsListCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ae.pp ++ ", " ++ list.pp
		silver.modification.let_fix.PassignsListCons.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_LetAssigns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignsListCons.i_ae).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignsListCons.i_list).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_LetAssigns)))); } };
		// top.letAssignExprs = appendAssignExpr(ae, list.letAssignExprs,location=top.location)
		silver.modification.let_fix.PassignsListCons.synthesizedAttributes[silver.modification.let_fix.Init.silver_modification_let_fix_letAssignExprs__ON__silver_modification_let_fix_LetAssigns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.modification.let_fix.NAssignExpr)new silver.modification.let_fix.PappendAssignExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.let_fix.PassignsListCons.i_ae)), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignsListCons.i_list, silver.modification.let_fix.Init.silver_modification_let_fix_letAssignExprs__ON__silver_modification_let_fix_LetAssigns), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.let_fix.NLetAssigns)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PassignsListCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PassignsListCons> {

		@Override
		public PassignsListCons invoke(final Object[] children, final Object[] annotations) {
			return new PassignsListCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
