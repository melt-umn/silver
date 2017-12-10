
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::StructDecl ::= attrs::Attributes name::MaybeName dcls::StructItemList 
public final class PstructDecl extends edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl {

	public static final int i_attrs = 0;
	public static final int i_name = 1;
	public static final int i_dcls = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_attrs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];
	childInheritedAttributes[i_name] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.num_inh_attrs];
	childInheritedAttributes[i_dcls] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList.num_inh_attrs];

	}

	public PstructDecl(final Object c_attrs, final Object c_name, final Object c_dcls, final Object a_core_location) {
		super(a_core_location);
		this.child_attrs = c_attrs;
		this.child_name = c_name;
		this.child_dcls = c_dcls;

	}

	private Object child_attrs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_attrs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child_name;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName getChild_name() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName) (child_name = common.Util.demand(child_name));
	}

	private Object child_dcls;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList getChild_dcls() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList) (child_dcls = common.Util.demand(child_dcls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attrs: return getChild_attrs();
			case i_name: return getChild_name();
			case i_dcls: return getChild_dcls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attrs: return child_attrs;
			case i_name: return child_name;
			case i_dcls: return child_dcls;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:structDecl erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:structDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:structDecl(, attrs.host, name.host, dcls.host,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_attrs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:structDecl(, attrs.lifted, name.lifted, dcls.lifted,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_attrs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl)context.undecorate()).getAnno_core_location()); } })); } };
		// top.maybename = name.maybename
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)); } };
		// top.pp = ppConcat([ text("struct ",), ppAttributes(attrs,), name.pp, text(" {",), nestlines(2, terminate(cat(semi(,), line(,),), dcls.pps,),), text("}",) ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("struct ")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)edu.umn.cs.melt.ableC.abstractsyntax.host.PppAttributes.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_attrs)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(" {")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pnestlines.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pterminate.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Psemi.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); } })); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("}")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } };
		// top.errors := dcls.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList)); } });
		// top.globalDecls := dcls.globalDecls
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAglobalDecls(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList)); } });
		// maybeAttribRefIdName = orElse(attrs.maybeRefId, top.givenRefId,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.maybeAttribRefIdName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_attrs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybeRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_givenRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl))); } };
		// top.refId = fromMaybe(name.tagRefId, maybeAttribRefIdName,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PfromMaybe.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.maybeAttribRefIdName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl)))); } };
		// top.moduleName = attrs.moduleName
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_attrs).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes)); } };
		// top.tagEnv = addEnv(dcls.localdefs, emptyEnv(,),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagEnv__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_localdefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv.invoke()); } })); } };
		// preDefs = if name.tagHasForwardDcl || ! name.hasName then [] else [ tagDef(name.maybename.fromJust.name, refIdTagItem(structSEU(,), top.refId,),) ]
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.preDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagHasForwardDcl__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)) || (!((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hasName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.ableC.abstractsyntax.env.PtagDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdTagItem(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructSEU()); } }, context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// postDefs = [ refIdDef(top.refId, structRefIdItem(top,),) ]
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdDef(context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PstructRefIdItem(context)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.defs := preDefs ++ dcls.defs ++ postDefs
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAdefs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.preDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl))); } })); } });
		// top.freeVariables = dcls.freeVariables
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList)); } };
		// dcls.env = openScope(addEnv(preDefs, top.env,),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_dcls][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructItemList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.preDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_structDecl), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl))); } })); } };
		// top.errors <- if ! name.tagHasForwardDcl || null(lookupRefId(top.refId, top.env,),) then [] else [ err(top.location, "Redeclaration of struct " ++ name.maybename.fromJust.name,) ]
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagHasForwardDcl__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName))) || ((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupRefId.invoke(context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl))); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Redeclaration of struct ")), (common.StringCatter)((common.StringCatter)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PstructDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PstructDecl> {

		@Override
		public PstructDecl invoke(final Object[] children, final Object[] annotations) {
			return new PstructDecl(children[0], children[1], children[2], annotations[0]);
		}
	};

}
