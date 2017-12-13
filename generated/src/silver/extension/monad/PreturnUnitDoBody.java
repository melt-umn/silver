
package silver.extension.monad;

// top::DoBodyStmt ::= 
public final class PreturnUnitDoBody extends silver.extension.monad.NDoBodyStmt {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_returnUnitDoBody;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PreturnUnitDoBody(final Object a_core_location) {
		super(a_core_location);

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:monad:returnUnitDoBody erroneously claimed to forward");
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
		return "silver:extension:monad:returnUnitDoBody";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "return unit();"
		silver.extension.monad.PreturnUnitDoBody.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("return unit();")); } };
		// top.transform = returnUnitExpr(top.returnFn,location=top.location)
		silver.extension.monad.PreturnUnitDoBody.synthesizedAttributes[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.monad.PreturnUnitExpr(context.contextInheritedLazy(silver.extension.monad.Init.silver_extension_monad_returnFn__ON__silver_extension_monad_DoBodyStmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmt)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PreturnUnitDoBody> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PreturnUnitDoBody> {

		@Override
		public PreturnUnitDoBody invoke(final Object[] children, final Object[] annotations) {
			return new PreturnUnitDoBody(annotations[0]);
		}
	};

}
