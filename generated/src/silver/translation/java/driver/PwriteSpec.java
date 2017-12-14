
package silver.translation.java.driver;

public final class PwriteSpec extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_r = 1;
	public static final int i_silverGen = 2;


	public static final Class<?> childTypes[] = { Object.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_driver_writeSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PwriteSpec(final Object c_i, final Object c_r, final Object c_silverGen) {
		this.child_i = c_i;
		this.child_r = c_r;
		this.child_silverGen = c_silverGen;

	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}

	private Object child_r;
	public final common.DecoratedNode getChild_r() {
		return (common.DecoratedNode) (child_r = common.Util.demand(child_r));
	}

	private Object child_silverGen;
	public final common.StringCatter getChild_silverGen() {
		return (common.StringCatter) (child_silverGen = common.Util.demand(child_silverGen));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_r: return getChild_r();
			case i_silverGen: return getChild_silverGen();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
			case i_r: return child_r;
			case i_silverGen: return child_silverGen;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:translation:java:driver:writeSpec";
	}

	public static Object invoke(final Object c_i, final Object c_r, final Object c_silverGen) {
		try {
		final common.DecoratedNode context = new PwriteSpec(c_i, c_r, c_silverGen).decorate();
		//writeFiles(srcPath, r.genFiles, printio)
		return (Object)(((Object)silver.translation.java.driver.PwriteFiles.invoke(context.localAsIsLazy(silver.translation.java.driver.Init.srcPath__ON__silver_translation_java_driver_writeSpec), context.childAsIsSynthesizedLazy(silver.translation.java.driver.PwriteSpec.i_r, silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_driver_util_RootSpec), context.localAsIsLazy(silver.translation.java.driver.Init.printio__ON__silver_translation_java_driver_writeSpec))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:driver:writeSpec", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwriteSpec.invoke(children[0], children[1], children[2]);
		}
	};
}