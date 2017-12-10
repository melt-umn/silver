
package silver.analysis.warnings.defs;

public final class PraiseMissingAttrs extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_fName = 1;
	public static final int i_attrs = 2;
	public static final int i_e = 3;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_raiseMissingAttrs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PraiseMissingAttrs(final Object c_l, final Object c_fName, final Object c_attrs, final Object c_e) {
		this.child_l = c_l;
		this.child_fName = c_fName;
		this.child_attrs = c_attrs;
		this.child_e = c_e;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_fName;
	public final common.StringCatter getChild_fName() {
		return (common.StringCatter) (child_fName = common.Util.demand(child_fName));
	}

	private Object child_attrs;
	public final common.ConsCell getChild_attrs() {
		return (common.ConsCell) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_fName: return getChild_fName();
			case i_attrs: return getChild_attrs();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_fName: return child_fName;
			case i_attrs: return child_attrs;
			case i_e: return child_e;

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
		return "silver:analysis:warnings:defs:raiseMissingAttrs";
	}

	public static common.ConsCell invoke(final Object c_l, final Object c_fName, final Object c_attrs, final Object c_e) {
		try {
		final common.DecoratedNode context = new PraiseMissingAttrs(c_l, c_fName, c_attrs, c_e).decorate();
		//if null(attrs) then [] else (if null(lookupDef(head(attrs).fullName, head(attrs).attrOccurring, e)) then case lookupSyn(fName, head(attrs).attrOccurring, e) of eq::_ -> [] | [] -> [ wrn(l, "production " ++ fName ++ " lacks synthesized equation for " ++ head(attrs).attrOccurring) ] end else []) ++ raiseMissingAttrs(l, fName, tail(attrs), e)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_attrs))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupDef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_attrs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_attrs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo)); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_e))); } })) ? new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_1166___sv_pv_1167_eq = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_1168___sv_tmp_pv_1169 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_1165_eq = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDef)(__SV_LOCAL_1166___sv_pv_1167_eq.eval())); } };
return ((common.ConsCell)core.Pnil.invoke()); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.analysis.warnings.defs.PraiseMissingAttrs.i_fName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" lacks synthesized equation for ")), (common.StringCatter)((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_attrs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:defs 'Syn.sv', 57, 9, 60, 12, 1647, 1856\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.flow.env.PlookupSyn.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_fName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_attrs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo)); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_e)))) : ((common.ConsCell)core.Pnil.invoke())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.analysis.warnings.defs.PraiseMissingAttrs.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_l)), context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_fName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_attrs))); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PraiseMissingAttrs.i_e))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:raiseMissingAttrs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PraiseMissingAttrs.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}