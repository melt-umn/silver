
package silver.extension.doc.core;

// top::AGDcl ::= noDoc::NoDclComment_t 'function' id::Name ns::FunctionSignature body::ProductionBody 
public final class PnoDocFunctionDcl extends silver.definition.core.NAGDcl {

	public static final int i_noDoc = 0;
	public static final int i__G_3 = 1;
	public static final int i_id = 2;
	public static final int i_ns = 3;
	public static final int i_body = 4;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TNoDclComment_t.class,silver.definition.core.TFunction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NFunctionSignature.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_noDocFunctionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NFunctionSignature.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PnoDocFunctionDcl(final Object c_noDoc, final Object c__G_3, final Object c_id, final Object c_ns, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child_noDoc = c_noDoc;
		this.child__G_3 = c__G_3;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_body = c_body;

	}

	private Object child_noDoc;
	public final silver.extension.doc.core.TNoDclComment_t getChild_noDoc() {
		return (silver.extension.doc.core.TNoDclComment_t) (child_noDoc = common.Util.demand(child_noDoc));
	}

	private Object child__G_3;
	public final silver.definition.core.TFunction_kwd getChild__G_3() {
		return (silver.definition.core.TFunction_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NFunctionSignature getChild_ns() {
		return (silver.definition.core.NFunctionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_noDoc: return getChild_noDoc();
			case i__G_3: return getChild__G_3();
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_noDoc: return child_noDoc;
			case i__G_3: return child__G_3;
			case i_id: return child_id;
			case i_ns: return child_ns;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PfunctionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TFunction_kwd((new common.StringCatter("function")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocFunctionDcl.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocFunctionDcl.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocFunctionDcl.i_body)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:doc:core:noDocFunctionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.docs := []
		if(silver.extension.doc.core.PnoDocFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PnoDocFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PnoDocFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.docDcls := []
		if(silver.extension.doc.core.PnoDocFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PnoDocFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PnoDocFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PnoDocFunctionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnoDocFunctionDcl> {

		@Override
		public PnoDocFunctionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PnoDocFunctionDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
