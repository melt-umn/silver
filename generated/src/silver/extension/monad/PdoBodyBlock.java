
package silver.extension.monad;

// top::DoBodyStmt ::= '{' body::DoBodyStmts '}' 
public final class PdoBodyBlock extends silver.extension.monad.NDoBodyStmt {

	public static final int i__G_2 = 0;
	public static final int i_body = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.TLCurly_t.class,silver.extension.monad.NDoBodyStmts.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_doBodyBlock;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_body] = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	}

	public PdoBodyBlock(final Object c__G_2, final Object c_body, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_body = c_body;
		this.child__G_0 = c__G_0;

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
			case i__G_2: return getChild__G_2();
			case i_body: return getChild_body();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_body: return child_body;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:monad:doBodyBlock erroneously claimed to forward");
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
		return "silver:extension:monad:doBodyBlock";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "{" ++ body.pp ++ "}"
		silver.extension.monad.PdoBodyBlock.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PdoBodyBlock.i_body).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts)), (common.StringCatter)(new common.StringCatter("}")))); } };
		// top.transform = body.transform
		silver.extension.monad.PdoBodyBlock.synthesizedAttributes[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)context.childDecorated(silver.extension.monad.PdoBodyBlock.i_body).synthesized(silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts)); } };

	}

	public static final common.NodeFactory<PdoBodyBlock> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdoBodyBlock> {

		@Override
		public PdoBodyBlock invoke(final Object[] children, final Object[] annotations) {
			return new PdoBodyBlock(children[0], children[1], children[2], annotations[0]);
		}
	};

}
