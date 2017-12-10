
package lib.xml.foreigntypes;

public final class PnodeListXPathReQueryF extends common.FunctionNode {

	public static final int i_query = 0;
	public static final int i_doc = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_foreigntypes_nodeListXPathReQueryF;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnodeListXPathReQueryF(final Object c_query, final Object c_doc) {
		this.child_query = c_query;
		this.child_doc = c_doc;

	}

	private Object child_query;
	public final common.StringCatter getChild_query() {
		return (common.StringCatter) (child_query = common.Util.demand(child_query));
	}

	private Object child_doc;
	public final Object getChild_doc() {
		return (Object) (child_doc = common.Util.demand(child_doc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_query: return getChild_query();
			case i_doc: return getChild_doc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_query: return child_query;
			case i_doc: return child_doc;

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
		return "lib:xml:foreigntypes:nodeListXPathReQueryF";
	}

	public static Object invoke(final Object c_query, final Object c_doc) {
		try {
		return (Object)common.rawlib.RawXML.xpathQueryNodeSet(((Object)common.Util.demand(c_doc)), ((common.StringCatter)common.Util.demand(c_query)).toString(), null);

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:foreigntypes:nodeListXPathReQueryF", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnodeListXPathReQueryF.invoke(children[0], children[1]);
		}
	};
}