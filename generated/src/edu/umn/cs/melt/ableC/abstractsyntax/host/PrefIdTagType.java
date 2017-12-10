
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::TagType ::= kwd::StructOrEnumOrUnion name::String refId::String 
public final class PrefIdTagType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType {

	public static final int i_kwd = 0;
	public static final int i_name = 1;
	public static final int i_refId = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_refIdTagType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_kwd] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion.num_inh_attrs];

	}

	public PrefIdTagType(final Object c_kwd, final Object c_name, final Object c_refId) {
		super();
		this.child_kwd = c_kwd;
		this.child_name = c_name;
		this.child_refId = c_refId;

	}

	private Object child_kwd;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion getChild_kwd() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion) (child_kwd = common.Util.demand(child_kwd));
	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_refId;
	public final common.StringCatter getChild_refId() {
		return (common.StringCatter) (child_refId = common.Util.demand(child_refId));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_kwd: return getChild_kwd();
			case i_name: return getChild_name();
			case i_refId: return getChild_refId();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_kwd: return child_kwd;
			case i_name: return child_name;
			case i_refId: return child_refId;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:refIdTagType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:refIdTagType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:refIdTagType(, kwd, name, refId,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_kwd)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_name), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_refId))); } };
		// top.pp = ppConcat([ kwd.pp, space(,), text(name,) ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_kwd, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructOrEnumOrUnion), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_name))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// top.mangledName = kwd.mangledName ++ "_" ++ name ++ "_" ++ substitute(":", "_", refId,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_kwd).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructOrEnumOrUnion)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter(":")), (new common.StringCatter("_")), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.i_refId))))))); } };
		// top.isIntegerType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TagType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PrefIdTagType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrefIdTagType> {

		@Override
		public PrefIdTagType invoke(final Object[] children, final Object[] annotations) {
			return new PrefIdTagType(children[0], children[1], children[2]);
		}
	};

}
