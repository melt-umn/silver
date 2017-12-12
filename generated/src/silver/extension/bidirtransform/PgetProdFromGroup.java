
package silver.extension.bidirtransform;

public final class PgetProdFromGroup extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_ntlst = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getProdFromGroup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetProdFromGroup(final Object c_s, final Object c_ntlst) {
		this.child_s = c_s;
		this.child_ntlst = c_ntlst;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_ntlst;
	public final common.DecoratedNode getChild_ntlst() {
		return (common.DecoratedNode) (child_ntlst = common.Util.demand(child_ntlst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_ntlst: return getChild_ntlst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_ntlst: return child_ntlst;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:extension:bidirtransform:getProdFromGroup";
	}

	public static common.ConsCell invoke(final Object c_s, final Object c_ntlst) {
		try {
		final common.DecoratedNode context = new PgetProdFromGroup(c_s, c_ntlst).decorate();
		//getProdFromList(s, ntlst.ntList)
		return (common.ConsCell)(((common.ConsCell)silver.extension.bidirtransform.PgetProdFromList.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromGroup.i_s), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PgetProdFromGroup.i_ntlst, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getProdFromGroup", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdFromGroup.invoke(children[0], children[1]);
		}
	};
}