
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::TagType ::= ref::Decorated EnumDecl 
public final class PenumTagType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType {

	public static final int i_ref = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_enumTagType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PenumTagType(final Object c_ref) {
		super();
		this.child_ref = c_ref;

	}

	private Object child_ref;
	public final common.DecoratedNode getChild_ref() {
		return (common.DecoratedNode) (child_ref = common.Util.demand(child_ref));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ref: return getChild_ref();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ref: return child_ref;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:enumTagType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:enumTagType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:enumTagType(, ref,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.i_ref))); } };
		// top.pp = case ref.maybename of just(n) -> cat(text("enum ",), n.pp,) | nothing() -> text("int/*anon enum*/",) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.langutil.pp.NDocument>() { public final silver.langutil.pp.NDocument eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv4721___sv_pv_4720_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName)scrutinee.childAsIs(0); } };
 return (silver.langutil.pp.NDocument)((silver.langutil.pp.NDocument)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4722_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL___pv4721___sv_pv_4720_n.eval())); } };
return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("enum ")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL_4722_n.eval())).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (silver.langutil.pp.NDocument)((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("int/*anon enum*/")))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.langutil.pp.NDocument)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Types.sv', 420, 4, 423, 7, 16200, 16333\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.i_ref)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_EnumDecl)).decorate(context, (common.Lazy[])null)); } };
		// top.mangledName = "enum_" ++ case ref.maybename of just(n) -> n.name | nothing() -> "anon" end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("enum_")), (common.StringCatter)new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv4726___sv_pv_4725_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName)scrutinee.childAsIs(0); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4727_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL___pv4726___sv_pv_4725_n.eval())); } };
return ((common.StringCatter)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL_4727_n.eval())).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (common.StringCatter)(new common.StringCatter("anon")); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Types.sv', 426, 4, 429, 7, 16374, 16453\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.i_ref)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_EnumDecl)).decorate(context, (common.Lazy[])null))); } };
		// top.isIntegerType = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PenumTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PenumTagType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PenumTagType> {

		@Override
		public PenumTagType invoke(final Object[] children, final Object[] annotations) {
			return new PenumTagType(children[0]);
		}
	};

}
