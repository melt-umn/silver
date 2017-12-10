
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PremoveDefsFromNames extends common.FunctionNode {

	public static final int i_defs = 0;
	public static final int i_names = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_removeDefsFromNames;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PremoveDefsFromNames(final Object c_defs, final Object c_names) {
		this.child_defs = c_defs;
		this.child_names = c_names;

	}

	private Object child_defs;
	public final common.ConsCell getChild_defs() {
		return (common.ConsCell) (child_defs = common.Util.demand(child_defs));
	}

	private Object child_names;
	public final common.ConsCell getChild_names() {
		return (common.ConsCell) (child_names = common.Util.demand(child_names));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_defs: return getChild_defs();
			case i_names: return getChild_names();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_defs: return child_defs;
			case i_names: return child_names;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:removeDefsFromNames";
	}

	public static common.ConsCell invoke(final Object c_defs, final Object c_names) {
		try {
		final common.DecoratedNode context = new PremoveDefsFromNames(c_defs, c_names).decorate();
		//if null(names,) then [] else if ! null(filter(definesVarWithName(_, head(names,).name,), defs,),) then removeDefsFromNames(defs, tail(names,),) else (head(names,) :: removeDefsFromNames(defs, tail(names,),))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_names))) ? ((common.ConsCell)core.Pnil.invoke()) : ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PdefinesVarWithName.factory.invokePartial(new int[]{1}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_names))).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } }}); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_defs))); } }))) ? ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_defs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_names))); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_names))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_defs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PremoveDefsFromNames.i_names))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:removeDefsFromNames", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PremoveDefsFromNames.invoke(children[0], children[1]);
		}
	};
}