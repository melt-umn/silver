
package silver.translation.java.driver;

public final class PincludeName extends common.FunctionNode {

	public static final int i_gram = 0;
	public static final int i_suffix = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_driver_includeName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PincludeName(final Object c_gram, final Object c_suffix) {
		this.child_gram = c_gram;
		this.child_suffix = c_suffix;

	}

	private Object child_gram;
	public final common.StringCatter getChild_gram() {
		return (common.StringCatter) (child_gram = common.Util.demand(child_gram));
	}

	private Object child_suffix;
	public final common.StringCatter getChild_suffix() {
		return (common.StringCatter) (child_suffix = common.Util.demand(child_suffix));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_gram: return getChild_gram();
			case i_suffix: return getChild_suffix();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_gram: return child_gram;
			case i_suffix: return child_suffix;

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
		return "silver:translation:java:driver:includeName";
	}

	public static common.StringCatter invoke(final Object c_gram, final Object c_suffix) {
		try {
		final common.DecoratedNode context = new PincludeName(c_gram, c_suffix).decorate();
		//"      <include name='" ++ grammarToPath(gram) ++ suffix ++ "' />\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("      <include name='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childAsIsLazy(silver.translation.java.driver.PincludeName.i_gram))), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.driver.PincludeName.i_suffix)), (common.StringCatter)(new common.StringCatter("' />\n"))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:driver:includeName", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PincludeName.invoke(children[0], children[1]);
		}
	};
}