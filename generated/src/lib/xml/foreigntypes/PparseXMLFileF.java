
package lib.xml.foreigntypes;

public final class PparseXMLFileF extends common.FunctionNode {

	public static final int i_filename = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_foreigntypes_parseXMLFileF;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PparseXMLFileF(final Object c_filename) {
		this.child_filename = c_filename;

	}

	private Object child_filename;
	public final common.StringCatter getChild_filename() {
		return (common.StringCatter) (child_filename = common.Util.demand(child_filename));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_filename: return getChild_filename();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_filename: return child_filename;

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
		return "lib:xml:foreigntypes:parseXMLFileF";
	}

	public static core.NParseResult invoke(final Object c_filename) {
		try {
		return (core.NParseResult)common.rawlib.RawXML.parseXMLFileF(((common.StringCatter)common.Util.demand(c_filename)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:foreigntypes:parseXMLFileF", t);
		}
	}

	public static final common.NodeFactory<core.NParseResult> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NParseResult> {
		@Override
		public core.NParseResult invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PparseXMLFileF.invoke(children[0]);
		}
	};
}