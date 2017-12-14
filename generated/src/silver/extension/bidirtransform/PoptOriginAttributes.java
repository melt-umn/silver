
package silver.extension.bidirtransform;

// top::AGDcl ::= qns::[QName] pfix::String 
public final class PoptOriginAttributes extends silver.definition.core.NAGDcl {

	public static final int i_qns = 0;
	public static final int i_pfix = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_optOriginAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PoptOriginAttributes(final Object c_qns, final Object c_pfix, final Object a_core_location) {
		super(a_core_location);
		this.child_qns = c_qns;
		this.child_pfix = c_pfix;

	}

	private Object child_qns;
	public final common.ConsCell getChild_qns() {
		return (common.ConsCell) (child_qns = common.Util.demand(child_qns));
	}

	private Object child_pfix;
	public final common.StringCatter getChild_pfix() {
		return (common.StringCatter) (child_pfix = common.Util.demand(child_pfix));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qns: return getChild_qns();
			case i_pfix: return getChild_pfix();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qns: return child_qns;
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
		return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PoptOriginAttributes.i_qns))) ? ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PoptOriginAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PoptOriginAttributes.i_qns))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PoptOriginAttributes.i_pfix), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PoptOriginAttributes(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PoptOriginAttributes.i_qns))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PoptOriginAttributes.i_pfix), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:optOriginAttributes";
	}

	static void initProductionAttributeDefinitions() {


	}

	public static final common.NodeFactory<PoptOriginAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoptOriginAttributes> {

		@Override
		public PoptOriginAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PoptOriginAttributes(children[0], children[1], annotations[0]);
		}
	};

}
