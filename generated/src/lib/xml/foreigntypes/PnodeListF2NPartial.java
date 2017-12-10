
package lib.xml.foreigntypes;

public final class PnodeListF2NPartial extends common.FunctionNode {

	public static final int i_nl = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_xml_foreigntypes_nodeListF2NPartial;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnodeListF2NPartial(final Object c_nl) {
		this.child_nl = c_nl;

	}

	private Object child_nl;
	public final Object getChild_nl() {
		return (Object) (child_nl = common.Util.demand(child_nl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nl: return getChild_nl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nl: return child_nl;

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
		return "lib:xml:foreigntypes:nodeListF2NPartial";
	}

	public static common.ConsCell invoke(final Object c_nl) {
		try {
		return (common.ConsCell)common.rawlib.RawXML.nodeListF2NPartial((org.w3c.dom.NodeList)((Object)common.Util.demand(c_nl)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:xml:foreigntypes:nodeListF2NPartial", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnodeListF2NPartial.invoke(children[0]);
		}
	};
}