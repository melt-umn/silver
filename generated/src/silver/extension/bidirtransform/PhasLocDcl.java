
package silver.extension.bidirtransform;

public final class PhasLocDcl extends common.FunctionNode {

	public static final int i_dcl = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_hasLocDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PhasLocDcl(final Object c_dcl) {
		this.child_dcl = c_dcl;

	}

	private Object child_dcl;
	public final common.ConsCell getChild_dcl() {
		return (common.ConsCell) (child_dcl = common.Util.demand(child_dcl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;

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
		return "silver:extension:bidirtransform:hasLocDcl";
	}

	public static Boolean invoke(final Object c_dcl) {
		try {
		final common.DecoratedNode context = new PhasLocDcl(c_dcl).decorate();
		//if null(dcl) then false else if head(dcl).isAnnotation && head(dcl).fullName == "location" then true else hasLocDcl(tail(dcl))
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasLocDcl.i_dcl))) ? false : ((((Boolean)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasLocDcl.i_dcl))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo)) && ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasLocDcl.i_dcl))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)).equals((new common.StringCatter("location")))) ? true : ((Boolean)silver.extension.bidirtransform.PhasLocDcl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasLocDcl.i_dcl))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:hasLocDcl", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhasLocDcl.invoke(children[0]);
		}
	};
}