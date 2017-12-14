
package silver.translation.java.core;

public final class PmakeClassName extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_makeClassName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeClassName(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		return "silver:translation:java:core:makeClassName";
	}

	public static common.StringCatter invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new PmakeClassName(c_s).decorate();
		//substituteLast(".", ".P", makeName(s))
		return (common.StringCatter)(((common.StringCatter)silver.translation.java.core.PsubstituteLast.invoke((new common.StringCatter(".")), (new common.StringCatter(".P")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeName.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeClassName.i_s))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:makeClassName", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeClassName.invoke(children[0]);
		}
	};
}