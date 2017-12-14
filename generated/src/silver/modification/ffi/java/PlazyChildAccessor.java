
package silver.modification.ffi.java;

public final class PlazyChildAccessor extends common.FunctionNode {

	public static final int i_ns = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NNamedSignatureElement.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_java_lazyChildAccessor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	}

	public PlazyChildAccessor(final Object c_ns) {
		this.child_ns = c_ns;

	}

	private Object child_ns;
	public final silver.definition.env.NNamedSignatureElement getChild_ns() {
		return (silver.definition.env.NNamedSignatureElement) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;

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
		return "silver:modification:ffi:java:lazyChildAccessor";
	}

	public static common.StringCatter invoke(final Object c_ns) {
		try {
		final common.DecoratedNode context = new PlazyChildAccessor(c_ns).decorate();
		//"c_" ++ ns.elementName
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("c_")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.java.PlazyChildAccessor.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:ffi:java:lazyChildAccessor", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlazyChildAccessor.invoke(children[0]);
		}
	};
}