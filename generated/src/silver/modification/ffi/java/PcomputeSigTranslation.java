
package silver.modification.ffi.java;

public final class PcomputeSigTranslation extends common.FunctionNode {

	public static final int i_str = 0;
	public static final int i_sig = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.definition.env.NNamedSignature.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_java_computeSigTranslation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sig] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PcomputeSigTranslation(final Object c_str, final Object c_sig) {
		this.child_str = c_str;
		this.child_sig = c_sig;

	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}

	private Object child_sig;
	public final silver.definition.env.NNamedSignature getChild_sig() {
		return (silver.definition.env.NNamedSignature) (child_sig = common.Util.demand(child_sig));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_str: return getChild_str();
			case i_sig: return getChild_sig();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_str: return child_str;
			case i_sig: return child_sig;

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
		return "silver:modification:ffi:java:computeSigTranslation";
	}

	public static common.StringCatter invoke(final Object c_str, final Object c_sig) {
		try {
		final common.DecoratedNode context = new PcomputeSigTranslation(c_str, c_sig).decorate();
		//substituteAll(substituteAll(str, map(wrapStrictNotation, sig.inputNames), map(strictChildAccessor, sig.inputElements)), map(wrapLazyNotation, sig.inputNames), map(lazyChildAccessor, sig.inputElements))
		return (common.StringCatter)(((common.StringCatter)silver.modification.ffi.util.PsubstituteAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.modification.ffi.util.PsubstituteAll.invoke(context.childAsIsLazy(silver.modification.ffi.java.PcomputeSigTranslation.i_str), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.ffi.util.PwrapStrictNotation.factory, context.childDecoratedSynthesizedLazy(silver.modification.ffi.java.PcomputeSigTranslation.i_sig, silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.ffi.java.PstrictChildAccessor.factory, context.childDecoratedSynthesizedLazy(silver.modification.ffi.java.PcomputeSigTranslation.i_sig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.ffi.util.PwrapLazyNotation.factory, context.childDecoratedSynthesizedLazy(silver.modification.ffi.java.PcomputeSigTranslation.i_sig, silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.ffi.java.PlazyChildAccessor.factory, context.childDecoratedSynthesizedLazy(silver.modification.ffi.java.PcomputeSigTranslation.i_sig, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:ffi:java:computeSigTranslation", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcomputeSigTranslation.invoke(children[0], children[1]);
		}
	};
}