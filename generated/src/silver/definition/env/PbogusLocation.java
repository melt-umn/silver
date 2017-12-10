
package silver.definition.env;

public final class PbogusLocation extends common.FunctionNode {



	public static final Class<?> childTypes[] = {  };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_bogusLocation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PbogusLocation() {

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
		return "silver:definition:env:bogusLocation";
	}

	public static core.NLocation invoke() {
		try {
		final common.DecoratedNode context = new PbogusLocation().decorate();
		//loc("??", -1, -1, -1, -1, -1, -1)
		return (core.NLocation)(((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:bogusLocation", t);
		}
	}

	public static final common.NodeFactory<core.NLocation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NLocation> {
		@Override
		public core.NLocation invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbogusLocation.invoke();
		}
	};
}