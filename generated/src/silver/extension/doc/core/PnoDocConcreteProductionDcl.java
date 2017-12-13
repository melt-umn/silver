
package silver.extension.doc.core;

// top::AGDcl ::= noDoc::NoDclComment_t 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 
public final class PnoDocConcreteProductionDcl extends silver.definition.core.NAGDcl {

	public static final int i_noDoc = 0;
	public static final int i__G_5 = 1;
	public static final int i__G_4 = 2;
	public static final int i_id = 3;
	public static final int i_ns = 4;
	public static final int i_pm = 5;
	public static final int i_body = 6;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TNoDclComment_t.class,silver.definition.core.TConcrete_kwd.class,silver.definition.core.TProduction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NProductionSignature.class,silver.definition.concrete_syntax.NProductionModifiers.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_noDocConcreteProductionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PnoDocConcreteProductionDcl(final Object c_noDoc, final Object c__G_5, final Object c__G_4, final Object c_id, final Object c_ns, final Object c_pm, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child_noDoc = c_noDoc;
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_pm = c_pm;
		this.child_body = c_body;

	}

	private Object child_noDoc;
	public final silver.extension.doc.core.TNoDclComment_t getChild_noDoc() {
		return (silver.extension.doc.core.TNoDclComment_t) (child_noDoc = common.Util.demand(child_noDoc));
	}

	private Object child__G_5;
	public final silver.definition.core.TConcrete_kwd getChild__G_5() {
		return (silver.definition.core.TConcrete_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.core.TProduction_kwd getChild__G_4() {
		return (silver.definition.core.TProduction_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NProductionSignature getChild_ns() {
		return (silver.definition.core.NProductionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_pm;
	public final silver.definition.concrete_syntax.NProductionModifiers getChild_pm() {
		return (silver.definition.concrete_syntax.NProductionModifiers) (child_pm = common.Util.demand(child_pm));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_noDoc: return getChild_noDoc();
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_pm: return getChild_pm();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_noDoc: return child_noDoc;
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_pm: return child_pm;
			case i_body: return child_body;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.concrete_syntax.PconcreteProductionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TConcrete_kwd((new common.StringCatter("concrete")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TProduction_kwd((new common.StringCatter("production")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocConcreteProductionDcl.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocConcreteProductionDcl.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocConcreteProductionDcl.i_pm)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocConcreteProductionDcl.i_body)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:doc:core:noDocConcreteProductionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.docs := []
		if(silver.extension.doc.core.PnoDocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PnoDocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PnoDocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.docDcls := []
		if(silver.extension.doc.core.PnoDocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PnoDocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PnoDocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PnoDocConcreteProductionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnoDocConcreteProductionDcl> {

		@Override
		public PnoDocConcreteProductionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PnoDocConcreteProductionDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
