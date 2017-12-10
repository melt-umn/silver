
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::NumericConstant ::= num::String suffix::FloatSuffix 
public final class PhexFloatConstant extends edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant {

	public static final int i_num = 0;
	public static final int i_suffix = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NFloatSuffix.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_hexFloatConstant;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_suffix] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFloatSuffix.num_inh_attrs];

	}

	public PhexFloatConstant(final Object c_num, final Object c_suffix, final Object a_core_location) {
		super(a_core_location);
		this.child_num = c_num;
		this.child_suffix = c_suffix;

	}

	private Object child_num;
	public final common.StringCatter getChild_num() {
		return (common.StringCatter) (child_num = common.Util.demand(child_num));
	}

	private Object child_suffix;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NFloatSuffix getChild_suffix() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NFloatSuffix) (child_suffix = common.Util.demand(child_suffix));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_num: return getChild_num();
			case i_suffix: return getChild_suffix();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_num: return child_num;
			case i_suffix: return child_suffix;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:hexFloatConstant erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:hexFloatConstant";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:hexFloatConstant(, num, suffix,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant)new edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.i_num), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.i_suffix)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:hexFloatConstant(, num, suffix,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant)new edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.i_num), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.i_suffix)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NNumericConstant)context.undecorate()).getAnno_core_location()); } })); } };
		// top.pp = text(num,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.i_num))); } };
		// top.errors := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.constanttyperep = realType(suffix.constfloattyperep,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_constanttyperep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_NumericConstant] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PrealType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PhexFloatConstant.i_suffix, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_constfloattyperep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FloatSuffix))); } };

	}

	public static final common.NodeFactory<PhexFloatConstant> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PhexFloatConstant> {

		@Override
		public PhexFloatConstant invoke(final Object[] children, final Object[] annotations) {
			return new PhexFloatConstant(children[0], children[1], annotations[0]);
		}
	};

}
