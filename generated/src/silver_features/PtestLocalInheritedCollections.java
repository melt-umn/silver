
package silver_features;

public final class PtestLocalInheritedCollections extends common.FunctionNode {



	public static final Class<?> childTypes[] = {  };

	public static final int num_local_attrs = Init.count_local__ON__silver_features_testLocalInheritedCollections;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtestLocalInheritedCollections() {

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
		return "silver_features:testLocalInheritedCollections";
	}

	public static common.StringCatter invoke() {
		try {
		final common.DecoratedNode context = new PtestLocalInheritedCollections().decorate();
		//t.colSyn
		return (common.StringCatter)(((common.StringCatter)context.localDecorated(silver_features.Init.t__ON__silver_features_testLocalInheritedCollections).synthesized(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver_features:testLocalInheritedCollections", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtestLocalInheritedCollections.invoke();
		}
	};
}