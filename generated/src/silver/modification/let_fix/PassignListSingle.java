
package silver.modification.let_fix;

// top::LetAssigns ::= ae::AssignExpr 
public final class PassignListSingle extends silver.modification.let_fix.NLetAssigns {

	public static final int i_ae = 0;


	public static final Class<?> childTypes[] = {silver.modification.let_fix.NAssignExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_assignListSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.let_fix.NLetAssigns.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.let_fix.NLetAssigns.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ae] = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];

	}

	public PassignListSingle(final Object c_ae, final Object a_core_location) {
		super(a_core_location);
		this.child_ae = c_ae;

	}

	private Object child_ae;
	public final silver.modification.let_fix.NAssignExpr getChild_ae() {
		return (silver.modification.let_fix.NAssignExpr) (child_ae = common.Util.demand(child_ae));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ae: return getChild_ae();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ae: return child_ae;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:assignListSingle erroneously claimed to forward");
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
		return "silver:modification:let_fix:assignListSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ae.pp
		silver.modification.let_fix.PassignListSingle.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_LetAssigns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignListSingle.i_ae).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr)); } };
		// top.letAssignExprs = ae
		silver.modification.let_fix.PassignListSingle.synthesizedAttributes[silver.modification.let_fix.Init.silver_modification_let_fix_letAssignExprs__ON__silver_modification_let_fix_LetAssigns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.let_fix.PassignListSingle.i_ae).undecorate(); } };

	}

	public static final common.NodeFactory<PassignListSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PassignListSingle> {

		@Override
		public PassignListSingle invoke(final Object[] children, final Object[] annotations) {
			return new PassignListSingle(children[0], annotations[0]);
		}
	};

}
