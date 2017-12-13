
package silver.extension.auto_ast;

public final class PhasAst extends common.FunctionNode {

	public static final int i_ns = 0;
	public static final int i_env = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NNamedSignatureElement.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_auto_ast_hasAst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	}

	public PhasAst(final Object c_ns, final Object c_env) {
		this.child_ns = c_ns;
		this.child_env = c_env;

	}

	private Object child_ns;
	public final silver.definition.env.NNamedSignatureElement getChild_ns() {
		return (silver.definition.env.NNamedSignatureElement) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;
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
		return "silver:extension:auto_ast:hasAst";
	}

	public static Boolean invoke(final Object c_ns, final Object c_env) {
		try {
		final common.DecoratedNode context = new PhasAst(c_ns, c_env).decorate();
		//ns.typerep.isDecorable && ! null(getOccursDcl("silver:langutil:ast", ns.typerep.typeName, env))
		return (Boolean)((((Boolean)((silver.definition.type.NType)context.childDecorated(silver.extension.auto_ast.PhasAst.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) && (!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetOccursDcl.invoke((new common.StringCatter("silver:langutil:ast")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.childDecorated(silver.extension.auto_ast.PhasAst.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.childAsIsLazy(silver.extension.auto_ast.PhasAst.i_env))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:auto_ast:hasAst", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhasAst.invoke(children[0], children[1]);
		}
	};
}