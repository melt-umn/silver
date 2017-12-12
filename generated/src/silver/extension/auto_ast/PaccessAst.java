
package silver.extension.auto_ast;

public final class PaccessAst extends common.FunctionNode {

	public static final int i_ns = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NNamedSignatureElement.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_auto_ast_accessAst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PaccessAst(final Object c_ns, final Object c_l) {
		this.child_ns = c_ns;
		this.child_l = c_l;

	}

	private Object child_ns;
	public final silver.definition.env.NNamedSignatureElement getChild_ns() {
		return (silver.definition.env.NNamedSignatureElement) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;
			case i_l: return child_l;

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
		return "silver:extension:auto_ast:accessAst";
	}

	public static silver.definition.core.NExpr invoke(final Object c_ns, final Object c_l) {
		try {
		final common.DecoratedNode context = new PaccessAst(c_ns, c_l).decorate();
		//access(baseExpr(qName(l, ns.elementName),location=l), '.', qNameAttrOccur(qName(l, "silver:langutil:ast"),location=l),location=l)
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)new silver.definition.core.Paccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.auto_ast.PaccessAst.i_l)), context.childDecoratedSynthesizedLazy(silver.extension.auto_ast.PaccessAst.i_ns, silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.auto_ast.PaccessAst.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDot_t((new common.StringCatter(".")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameAttrOccur)new silver.definition.core.PqNameAttrOccur(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.auto_ast.PaccessAst.i_l)), (new common.StringCatter("silver:langutil:ast")))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.auto_ast.PaccessAst.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.auto_ast.PaccessAst.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:auto_ast:accessAst", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaccessAst.invoke(children[0], children[1]);
		}
	};
}