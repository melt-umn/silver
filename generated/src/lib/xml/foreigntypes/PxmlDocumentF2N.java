
package lib.xml.foreigntypes;

public final class PxmlDocumentF2N extends common.FunctionNode {

	public static final int i_doc = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_foreigntypes_xmlDocumentF2N;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PxmlDocumentF2N(final Object c_doc) {
		this.child_doc = c_doc;

	}

	private Object child_doc;
	public final Object getChild_doc() {
		return (Object) (child_doc = common.Util.demand(child_doc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_doc: return getChild_doc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_doc: return child_doc;

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
		return "lib:xml:foreigntypes:xmlDocumentF2N";
	}

	public static lib.xml.ast.NXMLDocument invoke(final Object c_doc) {
		try {
		return (lib.xml.ast.NXMLDocument)common.rawlib.RawXML.documentF2N((org.w3c.dom.Document)((Object)common.Util.demand(c_doc)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:foreigntypes:xmlDocumentF2N", t);
		}
	}

	public static final common.NodeFactory<lib.xml.ast.NXMLDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<lib.xml.ast.NXMLDocument> {
		@Override
		public lib.xml.ast.NXMLDocument invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxmlDocumentF2N.invoke(children[0]);
		}
	};
}