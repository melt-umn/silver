
package lib.xml.ast;

// top::XMLDocumentType ::= xmlDTDName::String entities::XMLNodeList 
public final class PxmlDocumentType extends lib.xml.ast.NXMLDocumentType {

	public static final int i_xmlDTDName = 0;
	public static final int i_entities = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,lib.xml.ast.NXMLNodeList.class};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlDocumentType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLDocumentType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLDocumentType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_entities] = new common.Lazy[lib.xml.ast.NXMLNodeList.num_inh_attrs];

	}

	public PxmlDocumentType(final Object c_xmlDTDName, final Object c_entities) {
		super();
		this.child_xmlDTDName = c_xmlDTDName;
		this.child_entities = c_entities;

	}

	private Object child_xmlDTDName;
	public final common.StringCatter getChild_xmlDTDName() {
		return (common.StringCatter) (child_xmlDTDName = common.Util.demand(child_xmlDTDName));
	}

	private Object child_entities;
	public final lib.xml.ast.NXMLNodeList getChild_entities() {
		return (lib.xml.ast.NXMLNodeList) (child_entities = common.Util.demand(child_entities));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_xmlDTDName: return getChild_xmlDTDName();
			case i_entities: return getChild_entities();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_xmlDTDName: return child_xmlDTDName;
			case i_entities: return child_entities;

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
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlDocumentType erroneously claimed to forward");
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
		return "lib:xml:ast:xmlDocumentType";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlName = xmlDTDName
		lib.xml.ast.PxmlDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlDocumentType.i_xmlDTDName)); } };
		// top.xmlSubNodes = entities
		lib.xml.ast.PxmlDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(lib.xml.ast.PxmlDocumentType.i_entities).undecorate(); } };
		// top.xmlText = xmlDTDName ++ entities.xmlText
		lib.xml.ast.PxmlDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlDocumentType.i_xmlDTDName)), (common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlDocumentType.i_entities).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList))); } };
		// top.xmlUnparse = "<!DOCTYPE " ++ xmlDTDName ++ ">\n"
		lib.xml.ast.PxmlDocumentType.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocumentType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<!DOCTYPE ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlDocumentType.i_xmlDTDName)), (common.StringCatter)(new common.StringCatter(">\n")))); } };

	}

	public static final common.NodeFactory<PxmlDocumentType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlDocumentType> {

		@Override
		public PxmlDocumentType invoke(final Object[] children, final Object[] annotations) {
			return new PxmlDocumentType(children[0], children[1]);
		}
	};

}
