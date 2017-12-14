
package lib.xml.ast;

// top::XMLNode ::= name::String attributes::[XMLAttribute] elements::XMLNodeList 
public final class PxmlNodeElement extends lib.xml.ast.NXMLNode {

	public static final int i_name = 0;
	public static final int i_attributes = 1;
	public static final int i_elements = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,lib.xml.ast.NXMLNodeList.class};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlNodeElement;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLNode.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLNode.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_elements] = new common.Lazy[lib.xml.ast.NXMLNodeList.num_inh_attrs];

	}

	public PxmlNodeElement(final Object c_name, final Object c_attributes, final Object c_elements) {
		super();
		this.child_name = c_name;
		this.child_attributes = c_attributes;
		this.child_elements = c_elements;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_attributes;
	public final common.ConsCell getChild_attributes() {
		return (common.ConsCell) (child_attributes = common.Util.demand(child_attributes));
	}

	private Object child_elements;
	public final lib.xml.ast.NXMLNodeList getChild_elements() {
		return (lib.xml.ast.NXMLNodeList) (child_elements = common.Util.demand(child_elements));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_attributes: return getChild_attributes();
			case i_elements: return getChild_elements();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_attributes: return child_attributes;
			case i_elements: return child_elements;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlNodeElement erroneously claimed to forward");
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
		return "lib:xml:ast:xmlNodeElement";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlName = name
		lib.xml.ast.PxmlNodeElement.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlNodeElement.i_name)); } };
		// top.xmlAttributes = attributes
		lib.xml.ast.PxmlNodeElement.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlAttributes__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(lib.xml.ast.PxmlNodeElement.i_attributes)); } };
		// top.xmlSubNodes = elements
		lib.xml.ast.PxmlNodeElement.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(lib.xml.ast.PxmlNodeElement.i_elements).undecorate(); } };
		// top.xmlText = elements.xmlText
		lib.xml.ast.PxmlNodeElement.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlNodeElement.i_elements).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList)); } };
		// top.xmlUnparse = "<" ++ name ++ " " ++ implode(" ", map(xmlUnparseAttr, attributes)) ++ ">" ++ elements.xmlUnparse ++ "</" ++ name ++ ">"
		lib.xml.ast.PxmlNodeElement.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlNodeElement.i_name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(" ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(lib.xml.ast.PxmlUnparseAttr.factory, context.childAsIsLazy(lib.xml.ast.PxmlNodeElement.i_attributes))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(">")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlNodeElement.i_elements).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNodeList)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlNodeElement.i_name)), (common.StringCatter)(new common.StringCatter(">")))))))))); } };

	}

	public static final common.NodeFactory<PxmlNodeElement> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlNodeElement> {

		@Override
		public PxmlNodeElement invoke(final Object[] children, final Object[] annotations) {
			return new PxmlNodeElement(children[0], children[1], children[2]);
		}
	};

}
