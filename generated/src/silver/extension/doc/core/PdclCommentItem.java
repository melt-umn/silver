
package silver.extension.doc.core;

// top::CommentItem ::= modifiers::String name::String signature::String file::String body::Decorated DclComment 
public final class PdclCommentItem extends silver.extension.doc.core.NCommentItem {

	public static final int i_modifiers = 0;
	public static final int i_name = 1;
	public static final int i_signature = 2;
	public static final int i_file = 3;
	public static final int i_body = 4;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_dclCommentItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NCommentItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NCommentItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdclCommentItem(final Object c_modifiers, final Object c_name, final Object c_signature, final Object c_file, final Object c_body) {
		super();
		this.child_modifiers = c_modifiers;
		this.child_name = c_name;
		this.child_signature = c_signature;
		this.child_file = c_file;
		this.child_body = c_body;

	}

	private Object child_modifiers;
	public final common.StringCatter getChild_modifiers() {
		return (common.StringCatter) (child_modifiers = common.Util.demand(child_modifiers));
	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_signature;
	public final common.StringCatter getChild_signature() {
		return (common.StringCatter) (child_signature = common.Util.demand(child_signature));
	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}

	private Object child_body;
	public final common.DecoratedNode getChild_body() {
		return (common.DecoratedNode) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_modifiers: return getChild_modifiers();
			case i_name: return getChild_name();
			case i_signature: return getChild_signature();
			case i_file: return getChild_file();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_modifiers: return child_modifiers;
			case i_name: return child_name;
			case i_signature: return child_signature;
			case i_file: return child_file;
			case i_body: return child_body;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:dclCommentItem erroneously claimed to forward");
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
		return "silver:extension:doc:core:dclCommentItem";
	}

	static void initProductionAttributeDefinitions() {
		// top.body = body.body
		silver.extension.doc.core.PdclCommentItem.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_CommentItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.extension.doc.core.PdclCommentItem.i_body)).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclComment)); } };
		// top.file = file
		silver.extension.doc.core.PdclCommentItem.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_file__ON__silver_extension_doc_core_CommentItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.extension.doc.core.PdclCommentItem.i_file)); } };

	}

	public static final common.NodeFactory<PdclCommentItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdclCommentItem> {

		@Override
		public PdclCommentItem invoke(final Object[] children, final Object[] annotations) {
			return new PdclCommentItem(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
