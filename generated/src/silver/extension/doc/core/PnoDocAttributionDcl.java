
package silver.extension.doc.core;

// top::AGDcl ::= noDoc::NoDclComment_t 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';' 
public final class PnoDocAttributionDcl extends silver.definition.core.NAGDcl {

	public static final int i_noDoc = 0;
	public static final int i__G_7 = 1;
	public static final int i_at = 2;
	public static final int i_attl = 3;
	public static final int i__G_4 = 4;
	public static final int i__G_3 = 5;
	public static final int i_nt = 6;
	public static final int i_nttl = 7;
	public static final int i__G_0 = 8;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TNoDclComment_t.class,silver.definition.core.TAttribute_kwd.class,silver.definition.core.NQName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TOccurs_kwd.class,silver.definition.core.TOn_kwd.class,silver.definition.core.NQName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_noDocAttributionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[9][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_at] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_attl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_nttl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	}

	public PnoDocAttributionDcl(final Object c_noDoc, final Object c__G_7, final Object c_at, final Object c_attl, final Object c__G_4, final Object c__G_3, final Object c_nt, final Object c_nttl, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_noDoc = c_noDoc;
		this.child__G_7 = c__G_7;
		this.child_at = c_at;
		this.child_attl = c_attl;
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_nt = c_nt;
		this.child_nttl = c_nttl;
		this.child__G_0 = c__G_0;

	}

	private Object child_noDoc;
	public final silver.extension.doc.core.TNoDclComment_t getChild_noDoc() {
		return (silver.extension.doc.core.TNoDclComment_t) (child_noDoc = common.Util.demand(child_noDoc));
	}

	private Object child__G_7;
	public final silver.definition.core.TAttribute_kwd getChild__G_7() {
		return (silver.definition.core.TAttribute_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_at;
	public final silver.definition.core.NQName getChild_at() {
		return (silver.definition.core.NQName) (child_at = common.Util.demand(child_at));
	}

	private Object child_attl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_attl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_attl = common.Util.demand(child_attl));
	}

	private Object child__G_4;
	public final silver.definition.core.TOccurs_kwd getChild__G_4() {
		return (silver.definition.core.TOccurs_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TOn_kwd getChild__G_3() {
		return (silver.definition.core.TOn_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_nt;
	public final silver.definition.core.NQName getChild_nt() {
		return (silver.definition.core.NQName) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_nttl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_nttl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_nttl = common.Util.demand(child_nttl));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_noDoc: return getChild_noDoc();
			case i__G_7: return getChild__G_7();
			case i_at: return getChild_at();
			case i_attl: return getChild_attl();
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_nt: return getChild_nt();
			case i_nttl: return getChild_nttl();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_noDoc: return child_noDoc;
			case i__G_7: return child__G_7;
			case i_at: return child_at;
			case i_attl: return child_attl;
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_nt: return child_nt;
			case i_nttl: return child_nttl;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 9;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PattributionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TAttribute_kwd((new common.StringCatter("attribute")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocAttributionDcl.i_at)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocAttributionDcl.i_attl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TOccurs_kwd((new common.StringCatter("occurs")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TOn_kwd((new common.StringCatter("on")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocAttributionDcl.i_nt)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PnoDocAttributionDcl.i_nttl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:doc:core:noDocAttributionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.docs := []
		if(silver.extension.doc.core.PnoDocAttributionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.doc.core.PnoDocAttributionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.doc.core.PnoDocAttributionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PnoDocAttributionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnoDocAttributionDcl> {

		@Override
		public PnoDocAttributionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PnoDocAttributionDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], annotations[0]);
		}
	};

}
