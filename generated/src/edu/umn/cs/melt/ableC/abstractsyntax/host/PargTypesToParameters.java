
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PargTypesToParameters extends common.FunctionNode {

	public static final int i_args = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_argTypesToParameters;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PargTypesToParameters(final Object c_args) {
		this.child_args = c_args;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:argTypesToParameters";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters invoke(final Object c_args) {
		try {
		final common.DecoratedNode context = new PargTypesToParameters(c_args).decorate();
		//case args of h::t -> consParameters(parameterDecl([], directTypeExpr(h,), baseTypeExpr(,), nothingName(,), nilAttribute(,),), argTypesToParameters(t,),) | [] -> nilParameters(,) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(new common.PatternLazy<common.ConsCell, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_4666___sv_pv_4667_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_4668___sv_pv_4665_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4669_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_4668___sv_pv_4665_t.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4670_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_4666___sv_pv_4667_h.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsParameters(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdirectTypeExpr(__SV_LOCAL_4670_h)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)edu.umn.cs.melt.ableC.abstractsyntax.host.PargTypesToParameters.invoke(__SV_LOCAL_4669_t)); } })); } }).eval()); } }).eval()); }
else if(scrutinee.nil()) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilParameters()); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Types.sv', 360, 4, 366, 7, 14284, 14502\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PargTypesToParameters.i_args))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:argTypesToParameters", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PargTypesToParameters.invoke(children[0]);
		}
	};
}