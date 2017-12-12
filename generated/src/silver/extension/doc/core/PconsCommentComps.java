
package silver.extension.doc.core;

// top::DclCommentComponents ::= h::DclCommentComponent t::DclCommentComponents 
public final class PconsCommentComps extends silver.extension.doc.core.NDclCommentComponents {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.NDclCommentComponent.class,silver.extension.doc.core.NDclCommentComponents.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_consCommentComps;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponents.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponents.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.doc.core.NDclCommentComponents.num_inh_attrs];

	}

	public PconsCommentComps(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.extension.doc.core.NDclCommentComponent getChild_h() {
		return (silver.extension.doc.core.NDclCommentComponent) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.extension.doc.core.NDclCommentComponents getChild_t() {
		return (silver.extension.doc.core.NDclCommentComponents) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:consCommentComps erroneously claimed to forward");
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
		return "silver:extension:doc:core:consCommentComps";
	}

	static void initProductionAttributeDefinitions() {
		// top.body = h.body ++ t.body
		silver.extension.doc.core.PconsCommentComps.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsCommentComps.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponent)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsCommentComps.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponents))); } };

	}

	public static final common.NodeFactory<PconsCommentComps> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsCommentComps> {

		@Override
		public PconsCommentComps invoke(final Object[] children, final Object[] annotations) {
			return new PconsCommentComps(children[0], children[1], annotations[0]);
		}
	};

}
