
package lib.xml.ast;

public final class PxmlUnparseAttr extends common.FunctionNode {

	public static final int i_xa = 0;


	public static final Class<?> childTypes[] = { lib.xml.ast.NXMLAttribute.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_ast_xmlUnparseAttr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_xa] = new common.Lazy[lib.xml.ast.NXMLAttribute.num_inh_attrs];

	}

	public PxmlUnparseAttr(final Object c_xa) {
		this.child_xa = c_xa;

	}

	private Object child_xa;
	public final lib.xml.ast.NXMLAttribute getChild_xa() {
		return (lib.xml.ast.NXMLAttribute) (child_xa = common.Util.demand(child_xa));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_xa: return getChild_xa();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_xa: return child_xa;

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
		return "lib:xml:ast:xmlUnparseAttr";
	}

	public static common.StringCatter invoke(final Object c_xa) {
		try {
		final common.DecoratedNode context = new PxmlUnparseAttr(c_xa).decorate();
		//xa.xmlUnparse
		return (common.StringCatter)(((common.StringCatter)context.childDecorated(lib.xml.ast.PxmlUnparseAttr.i_xa).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLAttribute)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:ast:xmlUnparseAttr", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxmlUnparseAttr.invoke(children[0]);
		}
	};
}