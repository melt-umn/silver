
package silver.definition.flow.env;

public final class PfilterOccursForReferences extends common.FunctionNode {

	public static final int i_occ = 0;
	public static final int i_e = 1;
	public static final int i_authority = 2;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,common.DecoratedNode.class,common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_filterOccursForReferences;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_occ] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PfilterOccursForReferences(final Object c_occ, final Object c_e, final Object c_authority) {
		this.child_occ = c_occ;
		this.child_e = c_e;
		this.child_authority = c_authority;

	}

	private Object child_occ;
	public final silver.definition.env.NDclInfo getChild_occ() {
		return (silver.definition.env.NDclInfo) (child_occ = common.Util.demand(child_occ));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}

	private Object child_authority;
	public final common.NodeFactory<Boolean> getChild_authority() {
		return (common.NodeFactory<Boolean>) (child_authority = common.Util.demand(child_authority));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_occ: return getChild_occ();
			case i_e: return getChild_e();
			case i_authority: return getChild_authority();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_occ: return child_occ;
			case i_e: return child_e;
			case i_authority: return child_authority;

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
		return "silver:definition:flow:env:filterOccursForReferences";
	}

	public static common.ConsCell invoke(final Object c_occ, final Object c_e, final Object c_authority) {
		try {
		final common.DecoratedNode context = new PfilterOccursForReferences(c_occ, c_e, c_authority).decorate();
		//case getAttrDcl(occ.attrOccurring, e) of at::_ -> if at.isInherited && authority(occ.sourceGrammar) then [ occ.attrOccurring ] else [] | _ -> [] end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4927___fail_4928 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_4933___sv_pv_4932_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_4934___sv_tmp_pv_4935 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4936_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)(__SV_LOCAL_4933___sv_pv_4932_at.eval())); } };
return ((((Boolean)((silver.definition.env.NDclInfo)(__SV_LOCAL_4936_at.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isInherited__ON__silver_definition_env_DclInfo)) && ((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.definition.flow.env.PfilterOccursForReferences.i_authority)).invoke(new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.env.PfilterOccursForReferences.i_occ, silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo)}, null))) ? ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.env.PfilterOccursForReferences.i_occ, silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } }).eval()); }return ((common.ConsCell)(__SV_LOCAL_4927___fail_4928.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.env.PfilterOccursForReferences.i_occ, silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo), context.childAsIsLazy(silver.definition.flow.env.PfilterOccursForReferences.i_e)))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:env:filterOccursForReferences", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfilterOccursForReferences.invoke(children[0], children[1], children[2]);
		}
	};
}