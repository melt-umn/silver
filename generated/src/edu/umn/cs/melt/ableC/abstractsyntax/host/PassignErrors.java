
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PassignErrors extends common.FunctionNode {

	public static final int i_lhs = 0;
	public static final int i_rhs = 1;
	public static final int i_loc = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_assignErrors;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PassignErrors(final Object c_lhs, final Object c_rhs, final Object c_loc) {
		this.child_lhs = c_lhs;
		this.child_rhs = c_rhs;
		this.child_loc = c_loc;

	}

	private Object child_lhs;
	public final common.DecoratedNode getChild_lhs() {
		return (common.DecoratedNode) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_rhs;
	public final common.DecoratedNode getChild_rhs() {
		return (common.DecoratedNode) (child_rhs = common.Util.demand(child_rhs));
	}

	private Object child_loc;
	public final core.NLocation getChild_loc() {
		return (core.NLocation) (child_loc = common.Util.demand(child_loc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i_rhs: return getChild_rhs();
			case i_loc: return getChild_loc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_rhs: return child_rhs;
			case i_loc: return child_loc;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:assignErrors";
	}

	public static common.ConsCell invoke(final Object c_lhs, final Object c_rhs, final Object c_loc) {
		try {
		final common.DecoratedNode context = new PassignErrors(c_lhs, c_rhs, c_loc).decorate();
		//(if typeAssignableTo(lhs.typerep, rhs.typerep,) then if containsQualifier(constQualifier(,location=bogusLoc(,)), lhs.typerep,) then [ err(loc, "Assignment of read-only variable",) ] else [] else [ err(loc, "Incompatible type in rhs of assignment, expected " ++ showType(lhs.typerep,) ++ " but found " ++ showType(rhs.typerep,),) ]) ++ if lhs.isLValue then [] else [ err(lhs.location, "lvalue required as left operand of assignment",) ]
		return (common.ConsCell)(((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PtypeAssignableTo.invoke(context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))) ? (((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PcontainsQualifier.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconstQualifier(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.PbogusLoc.invoke()); } })); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_loc)), (new common.StringCatter("Assignment of read-only variable")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_loc)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Incompatible type in rhs of assignment, expected ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.host.PshowType.invoke(context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" but found ")), (common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.host.PshowType.invoke(context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_lhs)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isLValue__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PassignErrors.i_lhs)).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("lvalue required as left operand of assignment")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:assignErrors", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PassignErrors.invoke(children[0], children[1], children[2]);
		}
	};
}