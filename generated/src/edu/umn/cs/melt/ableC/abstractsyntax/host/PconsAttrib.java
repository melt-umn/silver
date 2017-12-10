
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Attribs ::= h::Attrib t::Attribs 
public final class PconsAttrib extends edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NAttrib.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_consAttrib;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttrib.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs.num_inh_attrs];

	}

	public PconsAttrib(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttrib getChild_h() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttrib) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:consAttrib erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:consAttrib";
	}

	static void initProductionAttributeDefinitions() {
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:consAttrib(, h.lifted, t.lifted,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_h, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attrib), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_t, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs))); } };
		// top.host = if h.isHostAttrib then consAttrib(h.host, t.host,) else t.host
		edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_h).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isHostAttrib__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attrib)) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_h, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attrib), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_t, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs))) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_t).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs))); } };
		// top.pp = case t of consAttrib(_, _) -> silver:langutil:pp:cat(h.pp, silver:langutil:pp:cat(silver:langutil:pp:text(", ",), t.pp,),) | nilAttrib() -> h.pp end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.langutil.pp.NDocument>() { public final silver.langutil.pp.NDocument eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib) { final common.Thunk<Object> __SV_LOCAL___pv6835___sv_tmp_pv_6836 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6837___sv_tmp_pv_6838 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (silver.langutil.pp.NDocument)((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_h, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attrib), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(", ")))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_t, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attribs))); } })); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttrib) {  return (silver.langutil.pp.NDocument)((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_h).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attrib)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.langutil.pp.NDocument)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Attribute.sv', 68, 4, 71, 7, 1593, 1683\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib.i_t)); } };

	}

	public static final common.NodeFactory<PconsAttrib> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsAttrib> {

		@Override
		public PconsAttrib invoke(final Object[] children, final Object[] annotations) {
			return new PconsAttrib(children[0], children[1]);
		}
	};

}
