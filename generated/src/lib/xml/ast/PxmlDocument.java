
package lib.xml.ast;

// top::XMLDocument ::= xmlDTD::XMLDocumentType elements::XMLNodeList 
public final class PxmlDocument extends lib.xml.ast.NXMLDocument {

	public static final int i_xmlDTD = 0;
	public static final int i_elements = 1;


	public static final Class<?> childTypes[] = {lib.xml.ast.NXMLDocumentType.class,lib.xml.ast.NXMLNodeList.class};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlDocument;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLDocument.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLDocument.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_xmlDTD] = new common.Lazy[lib.xml.ast.NXMLDocumentType.num_inh_attrs];
	childInheritedAttributes[i_elements] = new common.Lazy[lib.xml.ast.NXMLNodeList.num_inh_attrs];

	}

	public PxmlDocument(final Object c_xmlDTD, final Object c_elements) {
		super();
		this.child_xmlDTD = c_xmlDTD;
		this.child_elements = c_elements;

	}

	private Object child_xmlDTD;
	public final lib.xml.ast.NXMLDocumentType getChild_xmlDTD() {
		return (lib.xml.ast.NXMLDocumentType) (child_xmlDTD = common.Util.demand(child_xmlDTD));
	}

	private Object child_elements;
	public final lib.xml.ast.NXMLNodeList getChild_elements() {
		return (lib.xml.ast.NXMLNodeList) (child_elements = common.Util.demand(child_elements));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_xmlDTD: return getChild_xmlDTD();
			case i_elements: return getChild_elements();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_xmlDTD: return child_xmlDTD;
			case i_elements: return child_elements;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlDocument erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "lib:xml:ast:xmlDocument";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlDTD = xmlDTD
		lib.xml.ast.PxmlDocument.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlDTD__ON__lib_xml_ast_XMLDocument] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(lib.xml.ast.PxmlDocument.i_xmlDTD).undecorate(); } };
		// top.xmlSubNodes = elements
		lib.xml.ast.PxmlDocument.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocument] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(lib.xml.ast.PxmlDocument.i_elements).undecorate(); } };
		// top.xmlText = xmlDTD.xmlText ++ elements.xmlText
		lib.xml.ast.PxmlDocument.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocument] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlDocument.i_xmlDTD).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocumentType)), (common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlDocument.i_elements).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList))); } };
		// top.xmlUnparse = xmlDTD.xmlUnparse ++ elements.xmlUnparse
		lib.xml.ast.PxmlDocument.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocument] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlDocument.i_xmlDTD).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocumentType)), (common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlDocument.i_elements).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNodeList))); } };

	}

	public static final common.NodeFactory<PxmlDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlDocument> {

		@Override
		public PxmlDocument invoke(final Object[] children, final Object[] annotations) {
			return new PxmlDocument(children[0], children[1]);
		}
	};

}
