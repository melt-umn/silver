
package lib.xml.ast;

// top::XMLNode ::= t::String 
public final class PxmlNodeText extends lib.xml.ast.NXMLNode {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlNodeText;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLNode.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLNode.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PxmlNodeText(final Object c_t) {
		super();
		this.child_t = c_t;

	}

	private Object child_t;
	public final common.StringCatter getChild_t() {
		return (common.StringCatter) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlNodeText erroneously claimed to forward");
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
		return "lib:xml:ast:xmlNodeText";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlName = "#text"
		lib.xml.ast.PxmlNodeText.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("#text")); } };
		// top.xmlAttributes = []
		lib.xml.ast.PxmlNodeText.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlAttributes__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.xmlSubNodes = xmlNodeListNil()
		lib.xml.ast.PxmlNodeText.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListNil()); } };
		// top.xmlText = t
		lib.xml.ast.PxmlNodeText.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlNodeText.i_t)); } };
		// top.xmlUnparse = t
		lib.xml.ast.PxmlNodeText.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNode] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlNodeText.i_t)); } };

	}

	public static final common.NodeFactory<PxmlNodeText> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlNodeText> {

		@Override
		public PxmlNodeText invoke(final Object[] children, final Object[] annotations) {
			return new PxmlNodeText(children[0]);
		}
	};

}
