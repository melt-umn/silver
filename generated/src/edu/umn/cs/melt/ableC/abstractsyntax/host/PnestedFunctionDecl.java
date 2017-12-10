
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::FunctionDecl ::= storage::[StorageClass] fnquals::SpecialSpecifiers bty::BaseTypeExpr mty::TypeModifierExpr name::Name attrs::Attributes decls::Decls body::Stmt 
public final class PnestedFunctionDecl extends edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionDecl {

	public static final int i_storage = 0;
	public static final int i_fnquals = 1;
	public static final int i_bty = 2;
	public static final int i_mty = 3;
	public static final int i_name = 4;
	public static final int i_attrs = 5;
	public static final int i_decls = 6;
	public static final int i_body = 7;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NSpecialSpecifiers.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_nestedFunctionDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionDecl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionDecl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fnquals] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NSpecialSpecifiers.num_inh_attrs];
	childInheritedAttributes[i_bty] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_mty] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_inh_attrs];
	childInheritedAttributes[i_name] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];
	childInheritedAttributes[i_attrs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];
	childInheritedAttributes[i_decls] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt.num_inh_attrs];

	}

	public PnestedFunctionDecl(final Object c_storage, final Object c_fnquals, final Object c_bty, final Object c_mty, final Object c_name, final Object c_attrs, final Object c_decls, final Object c_body) {
		super();
		this.child_storage = c_storage;
		this.child_fnquals = c_fnquals;
		this.child_bty = c_bty;
		this.child_mty = c_mty;
		this.child_name = c_name;
		this.child_attrs = c_attrs;
		this.child_decls = c_decls;
		this.child_body = c_body;

	}

	private Object child_storage;
	public final common.ConsCell getChild_storage() {
		return (common.ConsCell) (child_storage = common.Util.demand(child_storage));
	}

	private Object child_fnquals;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NSpecialSpecifiers getChild_fnquals() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NSpecialSpecifiers) (child_fnquals = common.Util.demand(child_fnquals));
	}

	private Object child_bty;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr getChild_bty() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr) (child_bty = common.Util.demand(child_bty));
	}

	private Object child_mty;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr getChild_mty() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr) (child_mty = common.Util.demand(child_mty));
	}

	private Object child_name;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_name() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_name = common.Util.demand(child_name));
	}

	private Object child_attrs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_attrs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child_decls;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls getChild_decls() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls) (child_decls = common.Util.demand(child_decls));
	}

	private Object child_body;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt getChild_body() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_storage: return getChild_storage();
			case i_fnquals: return getChild_fnquals();
			case i_bty: return getChild_bty();
			case i_mty: return getChild_mty();
			case i_name: return getChild_name();
			case i_attrs: return getChild_attrs();
			case i_decls: return getChild_decls();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_storage: return child_storage;
			case i_fnquals: return child_fnquals;
			case i_bty: return child_bty;
			case i_mty: return child_mty;
			case i_name: return child_name;
			case i_attrs: return child_attrs;
			case i_decls: return child_decls;
			case i_body: return child_body;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PfunctionDecl(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_storage), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_fnquals)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_bty)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_mty)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_name)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_attrs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_decls)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_body))));
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:nestedFunctionDecl";
	}

	static void initProductionAttributeDefinitions() {
		// decls.isTopLevel = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PnestedFunctionDecl.i_decls][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isTopLevel__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PnestedFunctionDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnestedFunctionDecl> {

		@Override
		public PnestedFunctionDecl invoke(final Object[] children, final Object[] annotations) {
			return new PnestedFunctionDecl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7]);
		}
	};

}
