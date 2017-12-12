
package silver.extension.doc.core;

// top::DclCommentComponent ::= '@link' '[' id::QName ']' 
public final class PcomponentLink extends silver.extension.doc.core.NDclCommentComponent {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_id = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TCommentLink_t.class,silver.extension.doc.core.TCommentOpenSquare_t.class,silver.definition.core.NQName.class,silver.extension.doc.core.TCommentCloseSquare_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_componentLink;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDclCommentComponent.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PcomponentLink(final Object c__G_3, final Object c__G_2, final Object c_id, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_id = c_id;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.extension.doc.core.TCommentLink_t getChild__G_3() {
		return (silver.extension.doc.core.TCommentLink_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.extension.doc.core.TCommentOpenSquare_t getChild__G_2() {
		return (silver.extension.doc.core.TCommentOpenSquare_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_0;
	public final silver.extension.doc.core.TCommentCloseSquare_t getChild__G_0() {
		return (silver.extension.doc.core.TCommentCloseSquare_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_id: return getChild_id();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_id: return child_id;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:componentLink erroneously claimed to forward");
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
		return "silver:extension:doc:core:componentLink";
	}

	static void initProductionAttributeDefinitions() {
		// dclInfo = head(treeLookup(id.lookupValue.fullName, top.docEnv))
		silver.extension.doc.core.PcomponentLink.localAttributes[silver.extension.doc.core.Init.dclInfo__ON__silver_extension_doc_core_componentLink] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NDocDclInfo)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.treemap.PtreeLookup.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.doc.core.PcomponentLink.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, context.contextInheritedLazy(silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclCommentComponent))); } })); } };
		// top.body = "[" ++ dclInfo.id ++ "](" ++ dclInfo.path ++ ")"
		silver.extension.doc.core.PcomponentLink.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.doc.core.Init.dclInfo__ON__silver_extension_doc_core_componentLink).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_id__ON__silver_extension_doc_core_DocDclInfo)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("](")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.doc.core.Init.dclInfo__ON__silver_extension_doc_core_componentLink).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_path__ON__silver_extension_doc_core_DocDclInfo)), (common.StringCatter)(new common.StringCatter(")")))))); } };

	}

	public static final common.NodeFactory<PcomponentLink> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcomponentLink> {

		@Override
		public PcomponentLink invoke(final Object[] children, final Object[] annotations) {
			return new PcomponentLink(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
