
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::AsmClobbers ::= s::String 
public final class PoneAsmClobbers extends edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_oneAsmClobbers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PoneAsmClobbers(final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmClobbers erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmClobbers";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmClobbers(, s,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmClobbers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmClobbers(, s,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmClobbers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers)context.undecorate()).getAnno_core_location()); } })); } };
		// top.exists = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_exists__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmClobbers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.pp = text(s,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmClobbers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmClobbers.i_s))); } };

	}

	public static final common.NodeFactory<PoneAsmClobbers> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneAsmClobbers> {

		@Override
		public PoneAsmClobbers invoke(final Object[] children, final Object[] annotations) {
			return new PoneAsmClobbers(children[0], annotations[0]);
		}
	};

}
