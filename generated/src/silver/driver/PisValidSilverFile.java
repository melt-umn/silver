
package silver.driver;

public final class PisValidSilverFile extends common.FunctionNode {

	public static final int i_f = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_isValidSilverFile;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisValidSilverFile(final Object c_f) {
		this.child_f = c_f;

	}

	private Object child_f;
	public final common.StringCatter getChild_f() {
		return (common.StringCatter) (child_f = common.Util.demand(child_f));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;

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
		return "silver:driver:isValidSilverFile";
	}

	public static Boolean invoke(final Object c_f) {
		try {
		final common.DecoratedNode context = new PisValidSilverFile(c_f).decorate();
		//endsWith(".sv", f) && ! startsWith(".", f)
		return (Boolean)((((Boolean)core.PendsWith.invoke((new common.StringCatter(".sv")), context.childAsIsLazy(silver.driver.PisValidSilverFile.i_f))) && (!((Boolean)core.PstartsWith.invoke((new common.StringCatter(".")), context.childAsIsLazy(silver.driver.PisValidSilverFile.i_f))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:isValidSilverFile", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisValidSilverFile.invoke(children[0]);
		}
	};
}