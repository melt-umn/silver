
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::ArrayType ::= size::Decorated Expr 
public final class PvariableArrayType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType {

	public static final int i_size = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_variableArrayType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PvariableArrayType(final Object c_size) {
		super();
		this.child_size = c_size;

	}

	private Object child_size;
	public final common.DecoratedNode getChild_size() {
		return (common.DecoratedNode) (child_size = common.Util.demand(child_size));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_size: return getChild_size();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_size: return child_size;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:variableArrayType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:variableArrayType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = variableArrayType(decorate size.host with {env = size.env; returnType = size.returnType;},)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ArrayType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType.i_size)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, common.Util.populateInh(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs, new int[]{edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType.i_size)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType.i_size)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }})); } })); } };
		// top.pp = size.pp
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ArrayType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableArrayType.i_size)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } };

	}

	public static final common.NodeFactory<PvariableArrayType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PvariableArrayType> {

		@Override
		public PvariableArrayType invoke(final Object[] children, final Object[] annotations) {
			return new PvariableArrayType(children[0]);
		}
	};

}
