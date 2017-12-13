
package silver.extension.bidirtransform;

// top::AGDcl ::= qn::QName pfix::String 
public final class PoptOriginAttribute extends silver.definition.core.NAGDcl {

	public static final int i_qn = 0;
	public static final int i_pfix = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_optOriginAttribute;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PoptOriginAttribute(final Object c_qn, final Object c_pfix, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child_pfix = c_pfix;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child_pfix;
	public final common.StringCatter getChild_pfix() {
		return (common.StringCatter) (child_pfix = common.Util.demand(child_pfix));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i_pfix: return getChild_pfix();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i_pfix: return child_pfix;

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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PattrOn(context.localAsIsLazy(silver.extension.bidirtransform.Init.lhsAttr__ON__silver_extension_bidirtransform_optOriginAttribute), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("Origin")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PoptOriginAttrDef(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PoptOriginAttribute.i_qn)), context.childAsIsLazy(silver.extension.bidirtransform.PoptOriginAttribute.i_pfix), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:optOriginAttribute";
	}

	static void initProductionAttributeDefinitions() {
		// lhsAttr = pfix ++ qn.name
		silver.extension.bidirtransform.PoptOriginAttribute.localAttributes[silver.extension.bidirtransform.Init.lhsAttr__ON__silver_extension_bidirtransform_optOriginAttribute] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PoptOriginAttribute.i_pfix)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoptOriginAttribute.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } };


	}

	public static final common.NodeFactory<PoptOriginAttribute> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoptOriginAttribute> {

		@Override
		public PoptOriginAttribute invoke(final Object[] children, final Object[] annotations) {
			return new PoptOriginAttribute(children[0], children[1], annotations[0]);
		}
	};

}
