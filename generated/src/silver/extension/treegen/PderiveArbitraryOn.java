
package silver.extension.treegen;

public final class PderiveArbitraryOn extends common.FunctionNode {

	public static final int i_id = 0;
	public static final int i_env = 1;


	public static final Class<?> childTypes[] = { silver.definition.core.NQName.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_deriveArbitraryOn;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PderiveArbitraryOn(final Object c_id, final Object c_env) {
		this.child_id = c_id;
		this.child_env = c_env;

	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_env: return child_env;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:extension:treegen:deriveArbitraryOn";
	}

	public static silver.definition.core.NAGDcl invoke(final Object c_id, final Object c_env) {
		try {
		final common.DecoratedNode context = new PderiveArbitraryOn(c_id, c_env).decorate();
		//functionDcl('function', name(getGenArbName(id.lookupType.typerep), l), sig, body,location=l)
		return (silver.definition.core.NAGDcl)(((silver.definition.core.NAGDcl)new silver.definition.core.PfunctionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TFunction_kwd((new common.StringCatter("function")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.treegen.PgetGenArbName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.extension.treegen.PderiveArbitraryOn.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_deriveArbitraryOn)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.sig__ON__silver_extension_treegen_deriveArbitraryOn)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.body__ON__silver_extension_treegen_deriveArbitraryOn)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_deriveArbitraryOn)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:deriveArbitraryOn", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAGDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAGDcl> {
		@Override
		public silver.definition.core.NAGDcl invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PderiveArbitraryOn.invoke(children[0], children[1]);
		}
	};
}