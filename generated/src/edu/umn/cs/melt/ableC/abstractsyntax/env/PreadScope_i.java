
package edu.umn.cs.melt.ableC.abstractsyntax.env;

public final class PreadScope_i extends common.FunctionNode {

	public static final int i_name = 0;
	public static final int i_scope = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_readScope_i;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PreadScope_i(final Object c_name, final Object c_scope) {
		this.child_name = c_name;
		this.child_scope = c_scope;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_scope;
	public final common.ConsCell getChild_scope() {
		return (common.ConsCell) (child_scope = common.Util.demand(child_scope));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_scope: return getChild_scope();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_scope: return child_scope;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:readScope_i";
	}

	public static common.ConsCell invoke(final Object c_name, final Object c_scope) {
		try {
		final common.DecoratedNode context = new PreadScope_i(c_name, c_scope).decorate();
		//case dropWhile(null, map(tm:lookup(name, _,), scope,),) of h::_ -> h | [] -> [] end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_837___sv_pv_836_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_838___sv_tmp_pv_839 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_840_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_837___sv_pv_836_h.eval())); } };
return ((common.ConsCell)(__SV_LOCAL_840_h.eval())); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:env 'EnvPlumbing.sv', 50, 9, 53, 5, 1878, 1968\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)core.PdropWhile.invoke(core.Pnull.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.util.raw.treemap.Plookup.factory.invokePartial(new int[]{0}, new Object[]{context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PreadScope_i.i_name)}); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PreadScope_i.i_scope))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:env:readScope_i", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PreadScope_i.invoke(children[0], children[1]);
		}
	};
}