
package silver.extension.doc.core;

// top::DclCommentComponent ::= t::CommentText_t 
public final class PcomponentText extends silver.extension.doc.core.NDclCommentComponent {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TCommentText_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_componentText;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcomponentText(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.extension.doc.core.TCommentText_t getChild_t() {
		return (silver.extension.doc.core.TCommentText_t) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:componentText erroneously claimed to forward");
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
		return "silver:extension:doc:core:componentText";
	}

	static void initProductionAttributeDefinitions() {
		// top.body = t.lexeme
		silver.extension.doc.core.PcomponentText.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.doc.core.TCommentText_t)context.childAsIs(silver.extension.doc.core.PcomponentText.i_t)).lexeme); } };

	}

	public static final common.NodeFactory<PcomponentText> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcomponentText> {

		@Override
		public PcomponentText invoke(final Object[] children, final Object[] annotations) {
			return new PcomponentText(children[0], annotations[0]);
		}
	};

}
