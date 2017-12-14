
package silver.extension.monad;

public final class PnameFromMName extends common.FunctionNode {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = { silver.extension.monad.NMName.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_nameFromMName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_n] = new common.Lazy[silver.extension.monad.NMName.num_inh_attrs];

	}

	public PnameFromMName(final Object c_n) {
		this.child_n = c_n;

	}

	private Object child_n;
	public final silver.extension.monad.NMName getChild_n() {
		return (silver.extension.monad.NMName) (child_n = common.Util.demand(child_n));
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
		return "silver:extension:monad:nameFromMName";
	}

	public static silver.definition.core.NName invoke(final Object c_n) {
		try {
		final common.DecoratedNode context = new PnameFromMName(c_n).decorate();
		//name(n.name, n.location)
		return (silver.definition.core.NName)(((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.childDecoratedSynthesizedLazy(silver.extension.monad.PnameFromMName.i_n, silver.extension.monad.Init.silver_extension_monad_name__ON__silver_extension_monad_MName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NMName)context.childDecorated(silver.extension.monad.PnameFromMName.i_n).undecorate()).getAnno_core_location()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:monad:nameFromMName", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NName> {
		@Override
		public silver.definition.core.NName invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnameFromMName.invoke(children[0]);
		}
	};
}