
package silver_features.anno;

public final class PordinaryFun extends common.FunctionNode {



	public static final Class<?> childTypes[] = {  };

	public static final int num_local_attrs = Init.count_local__ON__silver_features_anno_ordinaryFun;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PordinaryFun() {

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
		return "silver_features:anno:ordinaryFun";
	}

	public static silver_features.anno.NAnnoNT invoke() {
		try {
		final common.DecoratedNode context = new PordinaryFun().decorate();
		//anAnnoNT(,what=3)
		return (silver_features.anno.NAnnoNT)(((silver_features.anno.NAnnoNT)new silver_features.anno.PanAnnoNT(Integer.valueOf((int)3))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver_features:anno:ordinaryFun", t);
		}
	}

	public static final common.NodeFactory<silver_features.anno.NAnnoNT> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver_features.anno.NAnnoNT> {
		@Override
		public silver_features.anno.NAnnoNT invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PordinaryFun.invoke();
		}
	};
}