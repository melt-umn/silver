
package silver.extension.bidirtransform;

public final class PhasTrans extends common.FunctionNode {

	public static final int i_rules = 0;
	public static final int i_dcl = 1;
	public static final int i_absGroup = 2;
	public static final int i_cncGroup = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_hasTrans;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PhasTrans(final Object c_rules, final Object c_dcl, final Object c_absGroup, final Object c_cncGroup) {
		this.child_rules = c_rules;
		this.child_dcl = c_dcl;
		this.child_absGroup = c_absGroup;
		this.child_cncGroup = c_cncGroup;

	}

	private Object child_rules;
	public final common.ConsCell getChild_rules() {
		return (common.ConsCell) (child_rules = common.Util.demand(child_rules));
	}

	private Object child_dcl;
	public final common.DecoratedNode getChild_dcl() {
		return (common.DecoratedNode) (child_dcl = common.Util.demand(child_dcl));
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
			case i_rules: return getChild_rules();
			case i_dcl: return getChild_dcl();
			case i_absGroup: return getChild_absGroup();
			case i_cncGroup: return getChild_cncGroup();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rules: return child_rules;
			case i_dcl: return child_dcl;
			case i_absGroup: return child_absGroup;
			case i_cncGroup: return child_cncGroup;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:extension:bidirtransform:hasTrans";
	}

	public static Boolean invoke(final Object c_rules, final Object c_dcl, final Object c_absGroup, final Object c_cncGroup) {
		try {
		final common.DecoratedNode context = new PhasTrans(c_rules, c_dcl, c_absGroup, c_cncGroup).decorate();
		//if null(rules) then false else if dcl.fullName == hd.namedSig.fullName then true else hasTrans(tail(rules), dcl, absGroup, cncGroup)
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasTrans.i_rules))) ? false : (((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PhasTrans.i_dcl)).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)).equals(((common.StringCatter)((common.DecoratedNode)context.localDecorated(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasTrans).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_TransformRule)).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))) ? true : ((Boolean)silver.extension.bidirtransform.PhasTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasTrans.i_rules))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PhasTrans.i_dcl), context.childAsIsLazy(silver.extension.bidirtransform.PhasTrans.i_absGroup), context.childAsIsLazy(silver.extension.bidirtransform.PhasTrans.i_cncGroup))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:hasTrans", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhasTrans.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}