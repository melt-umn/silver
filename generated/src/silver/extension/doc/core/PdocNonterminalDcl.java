
package silver.extension.doc.core;

// top::AGDcl ::= comment::DclComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs ';' 
public final class PdocNonterminalDcl extends silver.definition.core.NAGDcl {

	public static final int i_comment = 0;
	public static final int i_cl = 1;
	public static final int i__G_3 = 2;
	public static final int i_id = 3;
	public static final int i_tl = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.NDclComment.class,silver.definition.core.NClosedOrNot.class,silver.definition.core.TNonTerminal_kwd.class,silver.definition.core.NName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_docNonterminalDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_comment] = new common.Lazy[silver.extension.doc.core.NDclComment.num_inh_attrs];
	childInheritedAttributes[i_cl] = new common.Lazy[silver.definition.core.NClosedOrNot.num_inh_attrs];
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	}

	public PdocNonterminalDcl(final Object c_comment, final Object c_cl, final Object c__G_3, final Object c_id, final Object c_tl, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_comment = c_comment;
		this.child_cl = c_cl;
		this.child__G_3 = c__G_3;
		this.child_id = c_id;
		this.child_tl = c_tl;
		this.child__G_0 = c__G_0;

	}

	private Object child_comment;
	public final silver.extension.doc.core.NDclComment getChild_comment() {
		return (silver.extension.doc.core.NDclComment) (child_comment = common.Util.demand(child_comment));
	}

	private Object child_cl;
	public final silver.definition.core.NClosedOrNot getChild_cl() {
		return (silver.definition.core.NClosedOrNot) (child_cl = common.Util.demand(child_cl));
	}

	private Object child__G_3;
	public final silver.definition.core.TNonTerminal_kwd getChild__G_3() {
		return (silver.definition.core.TNonTerminal_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_comment: return getChild_comment();
			case i_cl: return getChild_cl();
			case i__G_3: return getChild__G_3();
			case i_id: return getChild_id();
			case i_tl: return getChild_tl();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_comment: return child_comment;
			case i_cl: return child_cl;
			case i__G_3: return child__G_3;
			case i_id: return child_id;
			case i_tl: return child_tl;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PnonterminalDcl(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocNonterminalDcl.i_cl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TNonTerminal_kwd((new common.StringCatter("nonterminal")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocNonterminalDcl.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PdocNonterminalDcl.i_tl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:doc:core:docNonterminalDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.docs := [ dclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename, comment) ]
		if(silver.extension.doc.core.PdocNonterminalDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PdocNonterminalDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PdocNonterminalDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PdclCommentItem((new common.StringCatter("nonterminal")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PdocNonterminalDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PdocNonterminalDcl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } }, (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.doc.core.PdocNonterminalDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, context.childDecoratedLazy(silver.extension.doc.core.PdocNonterminalDcl.i_comment))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PdocNonterminalDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdocNonterminalDcl> {

		@Override
		public PdocNonterminalDcl invoke(final Object[] children, final Object[] annotations) {
			return new PdocNonterminalDcl(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
