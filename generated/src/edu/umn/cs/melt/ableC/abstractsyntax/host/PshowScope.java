
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PshowScope extends common.FunctionNode {

	public static final int i_scope = 0;
	public static final int i_showFunc = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_showScope;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PshowScope(final Object c_scope, final Object c_showFunc) {
		this.child_scope = c_scope;
		this.child_showFunc = c_showFunc;

	}

	private Object child_scope;
	public final common.ConsCell getChild_scope() {
		return (common.ConsCell) (child_scope = common.Util.demand(child_scope));
	}

	private Object child_showFunc;
	public final common.NodeFactory<silver.langutil.pp.NDocument> getChild_showFunc() {
		return (common.NodeFactory<silver.langutil.pp.NDocument>) (child_showFunc = common.Util.demand(child_showFunc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_scope: return getChild_scope();
			case i_showFunc: return getChild_showFunc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_scope: return child_scope;
			case i_showFunc: return child_showFunc;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:showScope";
	}

	public static silver.langutil.pp.NDocument invoke(final Object c_scope, final Object c_showFunc) {
		try {
		final common.DecoratedNode context = new PshowScope(c_scope, c_showFunc).decorate();
		//ppImplode(line(,), map(showFunc, scope,),)
		return (silver.langutil.pp.NDocument)(((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PshowScope.i_showFunc), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PshowScope.i_scope))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:showScope", t);
		}
	}

	public static final common.NodeFactory<silver.langutil.pp.NDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.langutil.pp.NDocument> {
		@Override
		public silver.langutil.pp.NDocument invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PshowScope.invoke(children[0], children[1]);
		}
	};
}