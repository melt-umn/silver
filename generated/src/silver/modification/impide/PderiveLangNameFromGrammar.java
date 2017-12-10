
package silver.modification.impide;

public final class PderiveLangNameFromGrammar extends common.FunctionNode {

	public static final int i_gram = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_deriveLangNameFromGrammar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PderiveLangNameFromGrammar(final Object c_gram) {
		this.child_gram = c_gram;

	}

	private Object child_gram;
	public final common.StringCatter getChild_gram() {
		return (common.StringCatter) (child_gram = common.Util.demand(child_gram));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_gram: return getChild_gram();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_gram: return child_gram;

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
		return "silver:modification:impide:deriveLangNameFromGrammar";
	}

	public static common.StringCatter invoke(final Object c_gram) {
		try {
		final common.DecoratedNode context = new PderiveLangNameFromGrammar(c_gram).decorate();
		//toUpperCase(head(explode(":", gram)))
		return (common.StringCatter)(((common.StringCatter)silver.modification.impide.PtoUpperCase.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter(":")), context.childAsIsLazy(silver.modification.impide.PderiveLangNameFromGrammar.i_gram))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:deriveLangNameFromGrammar", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PderiveLangNameFromGrammar.invoke(children[0]);
		}
	};
}