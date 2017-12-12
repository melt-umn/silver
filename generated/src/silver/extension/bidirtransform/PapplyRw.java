
package silver.extension.bidirtransform;

// top::Expr ::= rwr::Decorated RewriteRule rhsTy::String lhsTy::String elemName::String 
public final class PapplyRw extends silver.definition.core.NExpr {

	public static final int i_rwr = 0;
	public static final int i_rhsTy = 1;
	public static final int i_lhsTy = 2;
	public static final int i_elemName = 3;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_applyRw;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PapplyRw(final Object c_rwr, final Object c_rhsTy, final Object c_lhsTy, final Object c_elemName, final Object a_core_location) {
		super(a_core_location);
		this.child_rwr = c_rwr;
		this.child_rhsTy = c_rhsTy;
		this.child_lhsTy = c_lhsTy;
		this.child_elemName = c_elemName;

	}

	private Object child_rwr;
	public final common.DecoratedNode getChild_rwr() {
		return (common.DecoratedNode) (child_rwr = common.Util.demand(child_rwr));
	}

	private Object child_rhsTy;
	public final common.StringCatter getChild_rhsTy() {
		return (common.StringCatter) (child_rhsTy = common.Util.demand(child_rhsTy));
	}

	private Object child_lhsTy;
	public final common.StringCatter getChild_lhsTy() {
		return (common.StringCatter) (child_lhsTy = common.Util.demand(child_lhsTy));
	}

	private Object child_elemName;
	public final common.StringCatter getChild_elemName() {
		return (common.StringCatter) (child_elemName = common.Util.demand(child_elemName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rwr: return getChild_rwr();
			case i_rhsTy: return getChild_rhsTy();
			case i_lhsTy: return getChild_lhsTy();
			case i_elemName: return getChild_elemName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rwr: return child_rwr;
			case i_rhsTy: return child_rhsTy;
			case i_lhsTy: return child_lhsTy;
			case i_elemName: return child_elemName;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)((common.NodeFactory<silver.definition.core.NExpr>)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRw.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_RewriteRule)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PapplyRw.i_rhsTy)).equals(((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PapplyRw.i_lhsTy))) || (!((Boolean)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRw.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteRule)))) ? ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PbaseName(context.childAsIsLazy(silver.extension.bidirtransform.PapplyRw.i_elemName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PexprAccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PrestoreNm.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRw.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputType__ON__silver_extension_bidirtransform_RewriteRule)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })); } })); } }, context.childAsIsLazy(silver.extension.bidirtransform.PapplyRw.i_elemName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }))); } }}, null));
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
		return "silver:extension:bidirtransform:applyRw";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PapplyRw> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PapplyRw> {

		@Override
		public PapplyRw invoke(final Object[] children, final Object[] annotations) {
			return new PapplyRw(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
