
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PdoValueRedeclarationCheckNoCompatible extends common.FunctionNode {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_doValueRedeclarationCheckNoCompatible;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PdoValueRedeclarationCheckNoCompatible(final Object c_n) {
		this.child_n = c_n;

	}

	private Object child_n;
	public final common.DecoratedNode getChild_n() {
		return (common.DecoratedNode) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:doValueRedeclarationCheckNoCompatible";
	}

	public static common.ConsCell invoke(final Object c_n) {
		try {
		final common.DecoratedNode context = new PdoValueRedeclarationCheckNoCompatible(c_n).decorate();
		//case n.valueLocalLookup of [] -> [] | v::_ -> [ err(n.location, "Redeclaration of " ++ n.name ++ ". Original (from line " ++ toString(v.sourceLocation.line) ++ ")",) ] end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6287___sv_pv_6286_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6288___sv_tmp_pv_6289 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6290_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem)(__SV_LOCAL_6287___sv_pv_6286_v.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueRedeclarationCheckNoCompatible.i_n)).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Redeclaration of ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueRedeclarationCheckNoCompatible.i_n)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(". Original (from line ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem)(__SV_LOCAL_6290_v.eval())).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_sourceLocation__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_line__ON__core_Location)))), (common.StringCatter)(new common.StringCatter(")")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Name.sv', 153, 9, 159, 5, 6072, 6274\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueRedeclarationCheckNoCompatible.i_n)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:doValueRedeclarationCheckNoCompatible", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdoValueRedeclarationCheckNoCompatible.invoke(children[0]);
		}
	};
}