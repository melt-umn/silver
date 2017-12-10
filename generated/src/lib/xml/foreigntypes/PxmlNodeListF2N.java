
package lib.xml.foreigntypes;

public final class PxmlNodeListF2N extends common.FunctionNode {

	public static final int i_nl = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_foreigntypes_xmlNodeListF2N;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PxmlNodeListF2N(final Object c_nl) {
		this.child_nl = c_nl;

	}

	private Object child_nl;
	public final Object getChild_nl() {
		return (Object) (child_nl = common.Util.demand(child_nl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nl: return getChild_nl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nl: return child_nl;

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
		return "lib:xml:foreigntypes:xmlNodeListF2N";
	}

	public static lib.xml.ast.NXMLNodeList invoke(final Object c_nl) {
		try {
		return (lib.xml.ast.NXMLNodeList)common.rawlib.RawXML.nodeListF2N((org.w3c.dom.NodeList)((Object)common.Util.demand(c_nl)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:foreigntypes:xmlNodeListF2N", t);
		}
	}

	public static final common.NodeFactory<lib.xml.ast.NXMLNodeList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<lib.xml.ast.NXMLNodeList> {
		@Override
		public lib.xml.ast.NXMLNodeList invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxmlNodeListF2N.invoke(children[0]);
		}
	};
}