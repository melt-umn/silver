
package silver.extension.patternmatching;

public final class PpatternListVars extends common.FunctionNode {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_patternListVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpatternListVars(final Object c_p) {
		this.child_p = c_p;

	}

	private Object child_p;
	public final common.DecoratedNode getChild_p() {
		return (common.DecoratedNode) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

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
		return "silver:extension:patternmatching:patternListVars";
	}

	public static silver.definition.core.NName invoke(final Object c_p) {
		try {
		final common.DecoratedNode context = new PpatternListVars(c_p).decorate();
		//name(n, p.location)
		return (silver.definition.core.NName)(((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.localAsIsLazy(silver.extension.patternmatching.Init.n__ON__silver_extension_patternmatching_patternListVars), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPattern)((common.DecoratedNode)context.childAsIs(silver.extension.patternmatching.PpatternListVars.i_p)).undecorate()).getAnno_core_location()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:patternListVars", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NName> {
		@Override
		public silver.definition.core.NName invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpatternListVars.invoke(children[0]);
		}
	};
}