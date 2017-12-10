
package silver.extension.patternmatching;

public final class PexprFromName extends common.FunctionNode {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = { silver.definition.core.NName.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_exprFromName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PexprFromName(final Object c_n) {
		this.child_n = c_n;

	}

	private Object child_n;
	public final silver.definition.core.NName getChild_n() {
		return (silver.definition.core.NName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:patternmatching:exprFromName";
	}

	public static silver.definition.core.NExpr invoke(final Object c_n) {
		try {
		final common.DecoratedNode context = new PexprFromName(c_n).decorate();
		//baseExpr(qNameId(n,location=n.location),location=n.location)
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)new silver.definition.core.PqNameId(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PexprFromName.i_n)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.patternmatching.PexprFromName.i_n).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.patternmatching.PexprFromName.i_n).undecorate()).getAnno_core_location()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:exprFromName", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexprFromName.invoke(children[0]);
		}
	};
}