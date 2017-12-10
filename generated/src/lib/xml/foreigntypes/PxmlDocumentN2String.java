
package lib.xml.foreigntypes;

public final class PxmlDocumentN2String extends common.FunctionNode {

	public static final int i_doc = 0;


	public static final Class<?> childTypes[] = { lib.xml.ast.NXMLDocument.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_foreigntypes_xmlDocumentN2String;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_doc] = new common.Lazy[lib.xml.ast.NXMLDocument.num_inh_attrs];

	}

	public PxmlDocumentN2String(final Object c_doc) {
		this.child_doc = c_doc;

	}

	private Object child_doc;
	public final lib.xml.ast.NXMLDocument getChild_doc() {
		return (lib.xml.ast.NXMLDocument) (child_doc = common.Util.demand(child_doc));
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
		return "lib:xml:foreigntypes:xmlDocumentN2String";
	}

	public static common.StringCatter invoke(final Object c_doc) {
		try {
		final common.DecoratedNode context = new PxmlDocumentN2String(c_doc).decorate();
		//xmlDocumentF2String(xmlDocumentN2F(doc,),)
		return (common.StringCatter)(((common.StringCatter)lib.xml.foreigntypes.PxmlDocumentF2String.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)lib.xml.foreigntypes.PxmlDocumentN2F.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(lib.xml.foreigntypes.PxmlDocumentN2String.i_doc)))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:foreigntypes:xmlDocumentN2String", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxmlDocumentN2String.invoke(children[0]);
		}
	};
}