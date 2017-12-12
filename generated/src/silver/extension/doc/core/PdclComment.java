
package silver.extension.doc.core;

// top::DclComment ::= '{@comment' components::DclCommentComponents '@}' 
public final class PdclComment extends silver.extension.doc.core.NDclComment {

	public static final int i__G_2 = 0;
	public static final int i_components = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TCommentOpen_t.class,silver.extension.doc.core.NDclCommentComponents.class,silver.extension.doc.core.TClose_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_dclComment;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDclComment.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDclComment.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_components] = new common.Lazy[silver.extension.doc.core.NDclCommentComponents.num_inh_attrs];

	}

	public PdclComment(final Object c__G_2, final Object c_components, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_components = c_components;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.extension.doc.core.TCommentOpen_t getChild__G_2() {
		return (silver.extension.doc.core.TCommentOpen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_components;
	public final silver.extension.doc.core.NDclCommentComponents getChild_components() {
		return (silver.extension.doc.core.NDclCommentComponents) (child_components = common.Util.demand(child_components));
	}

	private Object child__G_0;
	public final silver.extension.doc.core.TClose_t getChild__G_0() {
		return (silver.extension.doc.core.TClose_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_components: return getChild_components();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_components: return child_components;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:dclComment erroneously claimed to forward");
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
		return "silver:extension:doc:core:dclComment";
	}

	static void initProductionAttributeDefinitions() {
		// top.body = components.body
		silver.extension.doc.core.PdclComment.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclComment] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.doc.core.PdclComment.i_components).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponents)); } };

	}

	public static final common.NodeFactory<PdclComment> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdclComment> {

		@Override
		public PdclComment invoke(final Object[] children, final Object[] annotations) {
			return new PdclComment(children[0], children[1], children[2], annotations[0]);
		}
	};

}
