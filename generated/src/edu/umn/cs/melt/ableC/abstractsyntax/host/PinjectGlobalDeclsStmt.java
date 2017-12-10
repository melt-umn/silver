
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Stmt ::= decls::Decls lifted::Stmt 
public final class PinjectGlobalDeclsStmt extends edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt {

	public static final int i_decls = 0;
	public static final int i_lifted = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_injectGlobalDeclsStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_decls] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.num_inh_attrs];
	childInheritedAttributes[i_lifted] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt.num_inh_attrs];

	}

	public PinjectGlobalDeclsStmt(final Object c_decls, final Object c_lifted) {
		super();
		this.child_decls = c_decls;
		this.child_lifted = c_lifted;

	}

	private Object child_decls;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls getChild_decls() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls) (child_decls = common.Util.demand(child_decls));
	}

	private Object child_lifted;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt getChild_lifted() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt) (child_lifted = common.Util.demand(child_lifted));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_decls: return getChild_decls();
			case i_lifted: return getChild_lifted();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_decls: return child_decls;
			case i_lifted: return child_lifted;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:injectGlobalDeclsStmt erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:injectGlobalDeclsStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:injectGlobalDeclsStmt(, decls.host, lifted.host,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt))); } };
		// top.errors := decls.errors ++ lifted.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt))); } });
		// top.pp = silver:langutil:pp:cat(silver:langutil:pp:text("injectGlobalDeclsStmt ",), silver:langutil:pp:cat(braces(nestlines(2, ppImplode(line(,), decls.pps,),),), silver:langutil:pp:cat(silver:langutil:pp:text(" (",), silver:langutil:pp:cat(lifted.pp, silver:langutil:pp:text(")",),),),),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("injectGlobalDeclsStmt ")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pbraces.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pnestlines.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(" (")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(")")))); } })); } })); } })); } })); } };
		// top.defs := (globalDefsDef(decls.defs,) :: lifted.defs)
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAdefs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalDefsDef(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt))); } });
		// top.globalDecls := decls.unfoldedGlobalDecls ++ lifted.globalDecls
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAglobalDecls(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_unfoldedGlobalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt))); } });
		// top.lifted = lifted.lifted
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt)); } };
		// top.functiondefs := lifted.functiondefs
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_functiondefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_functiondefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAfunctiondefs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_functiondefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_functiondefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_functiondefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt)); } });
		// top.freeVariables = lifted.freeVariables
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt)); } };
		// decls.env = globalEnv(top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalEnv.invoke(context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt))); } };
		// decls.isTopLevel = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isTopLevel__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// decls.returnType = nothing(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// lifted.env = addEnv([ globalDefsDef(decls.defs,) ], top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_lifted][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalDefsDef(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsStmt.i_decls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decls))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Stmt))); } };

	}

	public static final common.NodeFactory<PinjectGlobalDeclsStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinjectGlobalDeclsStmt> {

		@Override
		public PinjectGlobalDeclsStmt invoke(final Object[] children, final Object[] annotations) {
			return new PinjectGlobalDeclsStmt(children[0], children[1]);
		}
	};

}
