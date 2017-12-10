
package lib.xml.ast;

// top::XMLAttribute ::= name::String value::String 
public final class PxmlAttribute extends lib.xml.ast.NXMLAttribute {

	public static final int i_name = 0;
	public static final int i_value = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlAttribute;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[lib.xml.ast.NXMLAttribute.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[lib.xml.ast.NXMLAttribute.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PxmlAttribute(final Object c_name, final Object c_value) {
		super();
		this.child_name = c_name;
		this.child_value = c_value;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_value;
	public final common.StringCatter getChild_value() {
		return (common.StringCatter) (child_value = common.Util.demand(child_value));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_value: return getChild_value();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_value: return child_value;

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
		throw new common.exceptions.SilverInternalError("Production lib:xml:ast:xmlAttribute erroneously claimed to forward");
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
		return "lib:xml:ast:xmlAttribute";
	}

	static void initProductionAttributeDefinitions() {
		// top.xmlName = name
		lib.xml.ast.PxmlAttribute.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLAttribute] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlAttribute.i_name)); } };
		// top.xmlAttributeValue = value
		lib.xml.ast.PxmlAttribute.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlAttributeValue__ON__lib_xml_ast_XMLAttribute] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlAttribute.i_value)); } };
		// top.xmlUnparse = name ++ "=\"" ++ value ++ "\""
		lib.xml.ast.PxmlAttribute.synthesizedAttributes[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLAttribute] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlAttribute.i_name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(lib.xml.ast.PxmlAttribute.i_value)), (common.StringCatter)(new common.StringCatter("\""))))); } };

	}

	public static final common.NodeFactory<PxmlAttribute> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PxmlAttribute> {

		@Override
		public PxmlAttribute invoke(final Object[] children, final Object[] annotations) {
			return new PxmlAttribute(children[0], children[1]);
		}
	};

}
