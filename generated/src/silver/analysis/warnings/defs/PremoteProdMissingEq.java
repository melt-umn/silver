
package silver.analysis.warnings.defs;

public final class PremoteProdMissingEq extends common.FunctionNode {

	public static final int i_prod = 0;
	public static final int i_sigName = 1;
	public static final int i_attrName = 2;
	public static final int i_realEnv = 3;
	public static final int i_flowEnv = 4;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_remoteProdMissingEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_prod] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PremoteProdMissingEq(final Object c_prod, final Object c_sigName, final Object c_attrName, final Object c_realEnv, final Object c_flowEnv) {
		this.child_prod = c_prod;
		this.child_sigName = c_sigName;
		this.child_attrName = c_attrName;
		this.child_realEnv = c_realEnv;
		this.child_flowEnv = c_flowEnv;

	}

	private Object child_prod;
	public final silver.definition.env.NDclInfo getChild_prod() {
		return (silver.definition.env.NDclInfo) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_sigName;
	public final common.StringCatter getChild_sigName() {
		return (common.StringCatter) (child_sigName = common.Util.demand(child_sigName));
	}

	private Object child_attrName;
	public final common.StringCatter getChild_attrName() {
		return (common.StringCatter) (child_attrName = common.Util.demand(child_attrName));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_sigName: return getChild_sigName();
			case i_attrName: return getChild_attrName();
			case i_realEnv: return getChild_realEnv();
			case i_flowEnv: return getChild_flowEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_sigName: return child_sigName;
			case i_attrName: return child_attrName;
			case i_realEnv: return child_realEnv;
			case i_flowEnv: return child_flowEnv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:analysis:warnings:defs:remoteProdMissingEq";
	}

	public static Boolean invoke(final Object c_prod, final Object c_sigName, final Object c_attrName, final Object c_realEnv, final Object c_flowEnv) {
		try {
		final common.DecoratedNode context = new PremoteProdMissingEq(c_prod, c_sigName, c_attrName, c_realEnv, c_flowEnv).decorate();
		//null(lookupInh(prod.fullName, sigName, attrName, flowEnv)) && ignoreIfAutoCopyOnLhs(prod.namedSignature.outputElement.typerep.typeName, realEnv, attrName)
		return (Boolean)((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupInh.invoke(context.childDecoratedSynthesizedLazy(silver.analysis.warnings.defs.PremoteProdMissingEq.i_prod, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo), context.childAsIsLazy(silver.analysis.warnings.defs.PremoteProdMissingEq.i_sigName), context.childAsIsLazy(silver.analysis.warnings.defs.PremoteProdMissingEq.i_attrName), context.childAsIsLazy(silver.analysis.warnings.defs.PremoteProdMissingEq.i_flowEnv))); } })) && ((Boolean)silver.analysis.warnings.defs.PignoreIfAutoCopyOnLhs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.analysis.warnings.defs.PremoteProdMissingEq.i_prod).synthesized(silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PremoteProdMissingEq.i_realEnv), context.childAsIsLazy(silver.analysis.warnings.defs.PremoteProdMissingEq.i_attrName)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:remoteProdMissingEq", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PremoteProdMissingEq.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}