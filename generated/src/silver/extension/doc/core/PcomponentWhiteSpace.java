
package silver.extension.doc.core;

// top::DclCommentComponent ::= w::WhiteSpace 
public final class PcomponentWhiteSpace extends silver.extension.doc.core.NDclCommentComponent {

	public static final int i_w = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TWhiteSpace.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_componentWhiteSpace;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcomponentWhiteSpace(final Object c_w, final Object a_core_location) {
		super(a_core_location);
		this.child_w = c_w;

	}

	private Object child_w;
	public final silver.definition.core.TWhiteSpace getChild_w() {
		return (silver.definition.core.TWhiteSpace) (child_w = common.Util.demand(child_w));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_w: return getChild_w();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_w: return child_w;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:componentWhiteSpace erroneously claimed to forward");
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
		return "silver:extension:doc:core:componentWhiteSpace";
	}

	static void initProductionAttributeDefinitions() {
		// top.body = w.lexeme
		silver.extension.doc.core.PcomponentWhiteSpace.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TWhiteSpace)context.childAsIs(silver.extension.doc.core.PcomponentWhiteSpace.i_w)).lexeme); } };

	}

	public static final common.NodeFactory<PcomponentWhiteSpace> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcomponentWhiteSpace> {

		@Override
		public PcomponentWhiteSpace invoke(final Object[] children, final Object[] annotations) {
			return new PcomponentWhiteSpace(children[0], annotations[0]);
		}
	};

}
