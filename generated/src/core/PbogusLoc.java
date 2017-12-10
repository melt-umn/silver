
package core;

public final class PbogusLoc extends common.FunctionNode {



	public static final Class<?> childTypes[] = {  };

	public static final int num_local_attrs = Init.count_local__ON__core_bogusLoc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PbogusLoc() {

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
		return "core:bogusLoc";
	}

	public static core.NLocation invoke() {
		try {
		final common.DecoratedNode context = new PbogusLoc().decorate();
		//txtLoc("Invalid or undefined bogus location")
		return (core.NLocation)(((core.NLocation)new core.PtxtLoc((new common.StringCatter("Invalid or undefined bogus location")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:bogusLoc", t);
		}
	}

	public static final common.NodeFactory<core.NLocation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NLocation> {
		@Override
		public core.NLocation invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbogusLoc.invoke();
		}
	};
}