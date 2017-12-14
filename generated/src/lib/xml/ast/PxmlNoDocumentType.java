
package lib.xml.ast;

// top::XMLDocumentType ::= 
public final class PxmlNoDocumentType extends lib.xml.ast.NXMLDocumentType {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlNoDocumentType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLDocumentType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLDocumentType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PxmlNoDocumentType() {
		super();

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
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlNoDocumentType erroneously claimed to forward");
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
		return "lib:xml:ast:xmlNoDocumentType";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlName = ""
		lib.xml.ast.PxmlNoDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.xmlSubNodes = xmlNodeListNil()
		lib.xml.ast.PxmlNoDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListNil()); } };
		// top.xmlText = ""
		lib.xml.ast.PxmlNoDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.xmlUnparse = ""
		lib.xml.ast.PxmlNoDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };

	}

	public static final common.NodeFactory<PxmlNoDocumentType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlNoDocumentType> {

		@Override
		public PxmlNoDocumentType invoke(final Object[] children, final Object[] annotations) {
			return new PxmlNoDocumentType();
		}
	};

}
