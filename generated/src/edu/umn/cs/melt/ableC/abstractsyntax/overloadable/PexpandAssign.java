
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PexpandAssign extends common.FunctionNode {

	public static final int i_lhs = 0;
	public static final int i_rhs = 1;
	public static final int i_ty = 2;
	public static final int i_prod = 3;
	public static final int i_loc = 4;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,common.NodeFactory.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_lhs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PexpandAssign(final Object c_lhs, final Object c_rhs, final Object c_ty, final Object c_prod, final Object c_loc) {
		this.child_lhs = c_lhs;
		this.child_rhs = c_rhs;
		this.child_ty = c_ty;
		this.child_prod = c_prod;
		this.child_loc = c_loc;

	}

	private Object child_lhs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_lhs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_rhs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_rhs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_rhs = common.Util.demand(child_rhs));
	}

	private Object child_ty;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_ty() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_ty = common.Util.demand(child_ty));
	}

	private Object child_prod;
	public final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> getChild_prod() {
		return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>) (child_prod = common.Util.demand(child_prod));
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
			case i_ty: return getChild_ty();
			case i_prod: return getChild_prod();
			case i_loc: return getChild_loc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_rhs: return child_rhs;
			case i_ty: return child_ty;
			case i_prod: return child_prod;
			case i_loc: return child_loc;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:expandAssign";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object c_lhs, final Object c_rhs, final Object c_ty, final Object c_prod, final Object c_loc) {
		try {
		final common.DecoratedNode context = new PexpandAssign(c_lhs, c_rhs, c_ty, c_prod, c_loc).decorate();
		//host:stmtExpr(mkDecl(tmpName, host:pointerType(host:nilQualifier(,), ty,), host:unaryOpExpr(host:addressOfOp(,location=loc), lhs,location=loc), loc,), eqExpr(host:dereferenceExpr(host:declRefExpr(host:name(tmpName,location=loc),location=loc),location=loc), prod(host:dereferenceExpr(host:declRefExpr(host:name(tmpName,location=loc),location=loc),location=loc), rhs, loc,),location=loc),location=loc)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstmtExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_ty)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PunaryOpExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp)new edu.umn.cs.melt.ableC.abstractsyntax.host.PaddressOfOp(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_lhs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdereferenceExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_prod)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdereferenceExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_rhs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc))}, null)); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.i_loc)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:expandAssign", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexpandAssign.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}