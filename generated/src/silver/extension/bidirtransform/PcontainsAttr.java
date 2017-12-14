
package silver.extension.bidirtransform;

public final class PcontainsAttr extends common.FunctionNode {

	public static final int i_dcl = 0;
	public static final int i_hasAttr = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_containsAttr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcontainsAttr(final Object c_dcl, final Object c_hasAttr) {
		this.child_dcl = c_dcl;
		this.child_hasAttr = c_hasAttr;

	}

	private Object child_dcl;
	public final common.ConsCell getChild_dcl() {
		return (common.ConsCell) (child_dcl = common.Util.demand(child_dcl));
	}

	private Object child_hasAttr;
	public final common.StringCatter getChild_hasAttr() {
		return (common.StringCatter) (child_hasAttr = common.Util.demand(child_hasAttr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();
			case i_hasAttr: return getChild_hasAttr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;
			case i_hasAttr: return child_hasAttr;

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
		return "silver:extension:bidirtransform:containsAttr";
	}

	public static Boolean invoke(final Object c_dcl, final Object c_hasAttr) {
		try {
		final common.DecoratedNode context = new PcontainsAttr(c_dcl, c_hasAttr).decorate();
		//if null(dcl) then false else if unFull(head(dcl).attrOccurring) == unFull(hasAttr) then true else containsAttr(tail(dcl), hasAttr)
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcontainsAttr.i_dcl))) ? false : (((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcontainsAttr.i_dcl))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo)); } })).equals(((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcontainsAttr.i_hasAttr)))) ? true : ((Boolean)silver.extension.bidirtransform.PcontainsAttr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcontainsAttr.i_dcl))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PcontainsAttr.i_hasAttr))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:containsAttr", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcontainsAttr.invoke(children[0], children[1]);
		}
	};
}