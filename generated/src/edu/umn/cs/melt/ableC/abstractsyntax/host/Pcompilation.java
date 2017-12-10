
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Compilation ::= srcAst::Root 
public final class Pcompilation extends edu.umn.cs.melt.ableC.abstractsyntax.host.NCompilation {

	public static final int i_srcAst = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NCompilation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NCompilation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_srcAst] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot.num_inh_attrs];

	}

	public Pcompilation(final Object c_srcAst) {
		super();
		this.child_srcAst = c_srcAst;

	}

	private Object child_srcAst;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot getChild_srcAst() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot) (child_srcAst = common.Util.demand(child_srcAst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_srcAst: return getChild_srcAst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_srcAst: return child_srcAst;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:compilation erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:compilation";
	}

	static void initProductionAttributeDefinitions() {
		// srcAst.env = top.env
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.i_srcAst][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } };
		// hostAst = srcAst.host
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.i_srcAst).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } };
		// hostAst.env = top.env
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } };
		// liftedAst = hostAst.lifted
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } };
		// liftedAst.env = top.env
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } };
		// top.srcErrorFilters := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAsrcErrorFilters(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.hostErrorFilters := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAhostErrorFilters(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.liftedErrorFilters := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAliftedErrorFilters(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// srcErrors = foldr(\ f::(Boolean ::= Message) e::[Message]  -> filter(f, e,), srcAst.errors, top.srcErrorFilters,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.srcErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfoldr.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6100_f = args[0];
final Object __SV_LAMBDA_PARAM_6101_e = args[1];

    return ((common.ConsCell)core.Pfilter.invoke(((common.NodeFactory<Boolean>)common.Util.demand(__SV_LAMBDA_PARAM_6100_f)), ((common.ConsCell)common.Util.demand(__SV_LAMBDA_PARAM_6101_e))));
  }
}), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.i_srcAst, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root), context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation))); } };
		// hostErrors = foldr(\ f::(Boolean ::= Message) e::[Message]  -> filter(f, e,), hostAst.errors, top.hostErrorFilters,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfoldr.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6107_f = args[0];
final Object __SV_LAMBDA_PARAM_6108_e = args[1];

    return ((common.ConsCell)core.Pfilter.invoke(((common.NodeFactory<Boolean>)common.Util.demand(__SV_LAMBDA_PARAM_6107_f)), ((common.ConsCell)common.Util.demand(__SV_LAMBDA_PARAM_6108_e))));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } }, context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation))); } };
		// liftedErrors = foldr(\ f::(Boolean ::= Message) e::[Message]  -> filter(f, e,), liftedAst.errors, top.liftedErrorFilters,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfoldr.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6114_f = args[0];
final Object __SV_LAMBDA_PARAM_6115_e = args[1];

    return ((common.ConsCell)core.Pfilter.invoke(((common.NodeFactory<Boolean>)common.Util.demand(__SV_LAMBDA_PARAM_6114_f)), ((common.ConsCell)common.Util.demand(__SV_LAMBDA_PARAM_6115_e))));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } }, context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedErrorFilters__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation))); } };
		// top.errors := if ! null(srcErrors,) then srcErrors else if ! fullErrorCheck then [] else if ! null(hostErrors,) then [ nested(loc("", -1, -1, -1, -1, -1, -1,), "Errors in host tree:", hostErrors,) ] else if ! null(liftedErrors,) then [ nested(loc("", -1, -1, -1, -1, -1, -1,), "Errors in lifted tree:", liftedErrors,) ] else []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.srcErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation)))) ? ((common.ConsCell)context.localAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.srcErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation)) : ((!((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.Init.fullErrorCheck.eval())) ? ((common.ConsCell)core.Pnil.invoke()) : ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Pnested(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc((new common.StringCatter("")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))); } }, (new common.StringCatter("Errors in host tree:")), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Pnested(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc((new common.StringCatter("")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))); } }, (new common.StringCatter("Errors in lifted tree:")), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedErrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))))); } });
		// top.srcAst = srcAst
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.i_srcAst).undecorate(); } };
		// top.hostAst = hostAst
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).undecorate(); } };
		// top.liftedAst = liftedAst
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).undecorate(); } };
		// top.srcPP = srcAst.pp
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.i_srcAst).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } };
		// top.hostPP = hostAst.pp
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } };
		// top.liftedPP = liftedAst.pp
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compilation).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Root)); } };
		// top.finalPP = top.liftedPP
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_finalPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } };

	}

	public static final common.NodeFactory<Pcompilation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pcompilation> {

		@Override
		public Pcompilation invoke(final Object[] children, final Object[] annotations) {
			return new Pcompilation(children[0]);
		}
	};

}
