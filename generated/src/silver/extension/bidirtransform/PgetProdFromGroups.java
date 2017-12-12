
package silver.extension.bidirtransform;

public final class PgetProdFromGroups extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_absGroup = 1;
	public static final int i_cncGroup = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getProdFromGroups;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetProdFromGroups(final Object c_s, final Object c_absGroup, final Object c_cncGroup) {
		this.child_s = c_s;
		this.child_absGroup = c_absGroup;
		this.child_cncGroup = c_cncGroup;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_absGroup;
	public final common.DecoratedNode getChild_absGroup() {
		return (common.DecoratedNode) (child_absGroup = common.Util.demand(child_absGroup));
	}

	private Object child_cncGroup;
	public final common.DecoratedNode getChild_cncGroup() {
		return (common.DecoratedNode) (child_cncGroup = common.Util.demand(child_cncGroup));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_absGroup: return getChild_absGroup();
			case i_cncGroup: return getChild_cncGroup();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_absGroup: return child_absGroup;
			case i_cncGroup: return child_cncGroup;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:bidirtransform:getProdFromGroups";
	}

	public static common.ConsCell invoke(final Object c_s, final Object c_absGroup, final Object c_cncGroup) {
		try {
		final common.DecoratedNode context = new PgetProdFromGroups(c_s, c_absGroup, c_cncGroup).decorate();
		//if length(absSig) != 0 then [ head(absSig) ] else if length(cncSig) != 0 then [ head(cncSig) ] else []
		return (common.ConsCell)((!((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_getProdFromGroups))).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_getProdFromGroups))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : (!((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_getProdFromGroups))).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_getProdFromGroups))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getProdFromGroups", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdFromGroups.invoke(children[0], children[1], children[2]);
		}
	};
}