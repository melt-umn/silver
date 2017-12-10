
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::FunctionType ::= args::[Type] variadic::Boolean 
public final class PprotoFunctionType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType {

	public static final int i_args = 0;
	public static final int i_variadic = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,Boolean.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_protoFunctionType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprotoFunctionType(final Object c_args, final Object c_variadic) {
		super();
		this.child_args = c_args;
		this.child_variadic = c_variadic;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_variadic;
	public final Boolean getChild_variadic() {
		return (Boolean) (child_variadic = common.Util.demand(child_variadic));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();
			case i_variadic: return getChild_variadic();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;
			case i_variadic: return child_variadic;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:protoFunctionType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:protoFunctionType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = protoFunctionType(map(\ t::Type  -> t.host, args,), variadic,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4652_t = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4652_t)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type));
  }
}), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_variadic))); } };
		// top.withoutExtensionQualifiers = protoFunctionType(map(\ t::Type  -> t.withoutExtensionQualifiers, args,), variadic,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4653_t = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4653_t)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type));
  }
}), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_variadic))); } };
		// top.mergeQualifiers = \ t2::FunctionType  -> case t2 of protoFunctionType(args2, _) -> protoFunctionType(zipWith(\ arg1::Type arg2::Type  -> arg1.mergeQualifiers(arg2,), args, args2,), variadic,) | _ -> protoFunctionType(args, variadic,) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<Object>() {
  public final Object invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4654_t2 = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4655___fail_4656 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_variadic))); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType) { final common.Thunk<Object> __SV_LOCAL___pv4658___sv_pv_4657_args2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4659___sv_tmp_pv_4660 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4661_args2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv4658___sv_pv_4657_args2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke((new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4662_arg1 = args[0];
final Object __SV_LAMBDA_PARAM_4663_arg2 = args[1];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4662_arg1)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).invoke(new Object[]{((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4663_arg2))}, null));
  }
}), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args), __SV_LOCAL_4661_args2)); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_variadic))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)(__SV_LOCAL_4655___fail_4656.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)common.Util.demand(__SV_LAMBDA_PARAM_4654_t2)).decorate(context, (common.Lazy[])null)); } }).eval());
  }
}); } };
		// top.lpp = notext(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// top.rpp = parens(if null(args,) then text("void",) else ppImplode(text(", ",), argpp,),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pparens.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))) ? ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("void")))) : ((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(", ")))); } }, context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.argpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_protoFunctionType)))); } })); } };
		// argpp = zipWith(cat, map((.lpp), args,), map((.rpp), args,),) ++ if variadic then [ text("...",) ] else []
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.argpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_protoFunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(silver.langutil.pp.Pcat.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type, context), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type, context), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_variadic)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("...")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } })); } };
		// top.mangledName = implode("_", map((.mangledName), args,),) ++ if variadic then "_variadic" else ""
		edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("_")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type, context), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))); } })), (common.StringCatter)(((Boolean)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_variadic)) ? (new common.StringCatter("_variadic")) : (new common.StringCatter("")))); } };
		// top.errors := concat(map((.errors), args,),)
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pconcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type, context), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PprotoFunctionType.i_args))); } })); } });

	}

	public static final common.NodeFactory<PprotoFunctionType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprotoFunctionType> {

		@Override
		public PprotoFunctionType invoke(final Object[] children, final Object[] annotations) {
			return new PprotoFunctionType(children[0], children[1]);
		}
	};

}
