
package silver.extension.doc.core;

// top::AGDcl ::= comment::DclComment 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 
public final class PdocConcreteProductionDcl extends silver.definition.core.NAGDcl {

	public static final int i_comment = 0;
	public static final int i__G_5 = 1;
	public static final int i__G_4 = 2;
	public static final int i_id = 3;
	public static final int i_ns = 4;
	public static final int i_pm = 5;
	public static final int i_body = 6;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.NDclComment.class,silver.definition.core.TConcrete_kwd.class,silver.definition.core.TProduction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NProductionSignature.class,silver.definition.concrete_syntax.NProductionModifiers.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_docConcreteProductionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_comment] = new common.Lazy[silver.extension.doc.core.NDclComment.num_inh_attrs];
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PdocConcreteProductionDcl(final Object c_comment, final Object c__G_5, final Object c__G_4, final Object c_id, final Object c_ns, final Object c_pm, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child_comment = c_comment;
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_pm = c_pm;
		this.child_body = c_body;

	}

	private Object child_comment;
	public final silver.extension.doc.core.NDclComment getChild_comment() {
		return (silver.extension.doc.core.NDclComment) (child_comment = common.Util.demand(child_comment));
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
			case i_comment: return getChild_comment();
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
			case i_comment: return child_comment;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.concrete_syntax.PconcreteProductionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TConcrete_kwd((new common.StringCatter("concrete")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TProduction_kwd((new common.StringCatter("production")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_pm)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_body)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:doc:core:docConcreteProductionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.docs := [ dclCommentItem("concrete production", id.name, ns.pp, id.location.filename, comment) ]
		if(silver.extension.doc.core.PdocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PdocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PdocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PdclCommentItem((new common.StringCatter("concrete production")), context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_ns, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.doc.core.PdocConcreteProductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, context.childDecoratedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_comment))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// top.docDcls := [ pair(top.grammarName ++ ":" ++ id.name, productionDocDclInfoP(id.name, id.location.filename, top.grammarName ++ ":" ++ id.name)) ]
		if(silver.extension.doc.core.PdocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PdocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PdocConcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PdocConcreteProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NDocDclInfo)new silver.extension.doc.core.PproductionDocDclInfoP(context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PdocConcreteProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.doc.core.PdocConcreteProductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PdocConcreteProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PdocConcreteProductionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdocConcreteProductionDcl> {

		@Override
		public PdocConcreteProductionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PdocConcreteProductionDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
