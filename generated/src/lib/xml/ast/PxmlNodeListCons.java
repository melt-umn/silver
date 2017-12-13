
package lib.xml.ast;

// top::XMLNodeList ::= h::XMLNode t::XMLNodeList 
public final class PxmlNodeListCons extends lib.xml.ast.NXMLNodeList {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {lib.xml.ast.NXMLNode.class,lib.xml.ast.NXMLNodeList.class};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlNodeListCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLNodeList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLNodeList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[lib.xml.ast.NXMLNode.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[lib.xml.ast.NXMLNodeList.num_inh_attrs];

	}

	public PxmlNodeListCons(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final lib.xml.ast.NXMLNode getChild_h() {
		return (lib.xml.ast.NXMLNode) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final lib.xml.ast.NXMLNodeList getChild_t() {
		return (lib.xml.ast.NXMLNodeList) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlNodeListCons erroneously claimed to forward");
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
		return "lib:xml:ast:xmlNodeListCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlSubNodeList = (h :: t.xmlSubNodeList)
		lib.xml.ast.PxmlNodeListCons.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlSubNodeList__ON__lib_xml_ast_XMLNodeList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(lib.xml.ast.PxmlNodeListCons.i_h)), context.childDecoratedSynthesizedLazy(lib.xml.ast.PxmlNodeListCons.i_t, lib.xml.ast.Init.lib_xml_ast_xmlSubNodeList__ON__lib_xml_ast_XMLNodeList))); } };
		// top.xmlText = h.xmlText ++ t.xmlText
		lib.xml.ast.PxmlNodeListCons.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlNodeListCons.i_h).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNode)), (common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlNodeListCons.i_t).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList))); } };
		// top.xmlUnparse = h.xmlUnparse ++ t.xmlUnparse
		lib.xml.ast.PxmlNodeListCons.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNodeList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlNodeListCons.i_h).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNode)), (common.StringCatter)((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlNodeListCons.i_t).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNodeList))); } };

	}

	public static final common.NodeFactory<PxmlNodeListCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlNodeListCons> {

		@Override
		public PxmlNodeListCons invoke(final Object[] children, final Object[] annotations) {
			return new PxmlNodeListCons(children[0], children[1]);
		}
	};

}
