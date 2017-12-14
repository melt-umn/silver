
package silver.extension.treegen;

public final class PderiveEqOn extends common.FunctionNode {

	public static final int i_id = 0;
	public static final int i_env = 1;
	public static final int i_fenv = 2;
	public static final int i_l = 3;


	public static final Class<?> childTypes[] = { silver.definition.core.NQName.class,common.DecoratedNode.class,common.DecoratedNode.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_deriveEqOn;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PderiveEqOn(final Object c_id, final Object c_env, final Object c_fenv, final Object c_l) {
		this.child_id = c_id;
		this.child_env = c_env;
		this.child_fenv = c_fenv;
		this.child_l = c_l;

	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}

	private Object child_fenv;
	public final common.DecoratedNode getChild_fenv() {
		return (common.DecoratedNode) (child_fenv = common.Util.demand(child_fenv));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_env: return getChild_env();
			case i_fenv: return getChild_fenv();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_env: return child_env;
			case i_fenv: return child_fenv;
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:extension:treegen:deriveEqOn";
	}

	public static silver.definition.core.NAGDcl invoke(final Object c_id, final Object c_env, final Object c_fenv, final Object c_l) {
		try {
		final common.DecoratedNode context = new PderiveEqOn(c_id, c_env, c_fenv, c_l).decorate();
		//functionDcl('function', name(getCheckEqName(id.lookupType.typerep), l), sig, body,location=l)
		return (silver.definition.core.NAGDcl)(((silver.definition.core.NAGDcl)new silver.definition.core.PfunctionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TFunction_kwd((new common.StringCatter("function")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.treegen.PgetCheckEqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.extension.treegen.PderiveEqOn.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PderiveEqOn.i_l)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.sig__ON__silver_extension_treegen_deriveEqOn)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.body__ON__silver_extension_treegen_deriveEqOn)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PderiveEqOn.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:deriveEqOn", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAGDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAGDcl> {
		@Override
		public silver.definition.core.NAGDcl invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PderiveEqOn.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}