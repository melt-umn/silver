
package silver.definition.type;

public final class PfreshTyVar extends common.FunctionNode {



	public static final Class<?> childTypes[] = {  };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_freshTyVar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfreshTyVar() {

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		return "silver:definition:type:freshTyVar";
	}

	public static silver.definition.type.NTyVar invoke() {
		try {
		final common.DecoratedNode context = new PfreshTyVar().decorate();
		//tyVar(genInt())
		return (silver.definition.type.NTyVar)(((silver.definition.type.NTyVar)new silver.definition.type.PtyVar(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.PgenInt.invoke()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:freshTyVar", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NTyVar> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NTyVar> {
		@Override
		public silver.definition.type.NTyVar invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfreshTyVar.invoke();
		}
	};
}