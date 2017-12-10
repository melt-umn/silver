
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Decl ::= include::(Boolean ::= Decorated Env) decl::Decl 
public final class PmaybeDecl extends edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl {

	public static final int i_include = 0;
	public static final int i_decl = 1;


	public static final Class<?> childTypes[] = {common.NodeFactory.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_maybeDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_decl] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl.num_inh_attrs];

	}

	public PmaybeDecl(final Object c_include, final Object c_decl) {
		super();
		this.child_include = c_include;
		this.child_decl = c_decl;

	}

	private Object child_include;
	public final common.NodeFactory<Boolean> getChild_include() {
		return (common.NodeFactory<Boolean>) (child_include = common.Util.demand(child_include));
	}

	private Object child_decl;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl getChild_decl() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl) (child_decl = common.Util.demand(child_decl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_include: return getChild_include();
			case i_decl: return getChild_decl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_include: return child_include;
			case i_decl: return child_decl;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PmaybeDecl.i_include)).invoke(new Object[]{context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl)}, null)) ? context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PmaybeDecl.i_decl).undecorate() : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pdecls(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilDecl()); } })));
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:maybeDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = cat(silver:langutil:pp:text("maybe ",), braces(decl.pp,),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PmaybeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("maybe ")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pbraces.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PmaybeDecl.i_decl, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl))); } })); } };

	}

	public static final common.NodeFactory<PmaybeDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmaybeDecl> {

		@Override
		public PmaybeDecl invoke(final Object[] children, final Object[] annotations) {
			return new PmaybeDecl(children[0], children[1]);
		}
	};

}
