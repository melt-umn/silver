
package silver.extension.monad;

// top::DoBodyStmts ::= h::DoBodyStmt 
public final class PoneDoBodyStmt extends silver.extension.monad.NDoBodyStmts {

	public static final int i_h = 0;


	public static final Class<?> childTypes[] = {silver.extension.monad.NDoBodyStmt.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_oneDoBodyStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	}

	public PoneDoBodyStmt(final Object c_h, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;

	}

	private Object child_h;
	public final silver.extension.monad.NDoBodyStmt getChild_h() {
		return (silver.extension.monad.NDoBodyStmt) (child_h = common.Util.demand(child_h));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:monad:oneDoBodyStmt erroneously claimed to forward");
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
		return "silver:extension:monad:oneDoBodyStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp
		silver.extension.monad.PoneDoBodyStmt.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.monad.PoneDoBodyStmt.i_h).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt)); } };
		// top.transform = h.transform
		silver.extension.monad.PoneDoBodyStmt.synthesizedAttributes[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)context.childDecorated(silver.extension.monad.PoneDoBodyStmt.i_h).synthesized(silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt)); } };

	}

	public static final common.NodeFactory<PoneDoBodyStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneDoBodyStmt> {

		@Override
		public PoneDoBodyStmt invoke(final Object[] children, final Object[] annotations) {
			return new PoneDoBodyStmt(children[0], annotations[0]);
		}
	};

}
