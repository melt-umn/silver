
package edu.umn.cs.melt.ableC.abstractsyntax.construction;

public final class PmkDeclGeneral extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_typ = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_typ] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmkDeclGeneral(final Object c_n, final Object c_typ, final Object c_l) {
		this.child_n = c_n;
		this.child_typ = c_typ;
		this.child_l = c_l;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_typ;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_typ() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_typ = common.Util.demand(child_typ));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_typ: return getChild_typ();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_typ: return child_typ;
			case i_l: return child_l;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:mkDeclGeneral";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object c_n, final Object c_typ, final Object c_l) {
		try {
		final common.DecoratedNode context = new PmkDeclGeneral(c_n, c_typ, c_l).decorate();
		//declStmt(variableDecls([], nilAttribute(,), bty, consDeclarator(declarator(name(n,location=l), baseTypeExpr(,), nilAttribute(,), nothingInitializer(,),), nilDeclarator(,),),),)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvariableDecls(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDeclarators)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsDeclarator(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDeclarator)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pdeclarator(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDeclGeneral.i_n), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDeclGeneral.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeInitializer)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingInitializer()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDeclarators)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilDeclarator()); } })); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:mkDeclGeneral", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkDeclGeneral.invoke(children[0], children[1], children[2]);
		}
	};
}