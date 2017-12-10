
package silver.extension.bidirtransform;

// top::AGDcls ::= 'origins' 'attribute' qns::QNameList '->>' subAg::AGDcls 
public final class PoriginAttributeDcl extends silver.definition.core.NAGDcls {

	public static final int i__G_4 = 0;
	public static final int i__G_3 = 1;
	public static final int i_qns = 2;
	public static final int i__G_1 = 3;
	public static final int i_subAg = 4;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.TOrigins_kwd.class,silver.definition.core.TAttribute_kwd.class,silver.extension.bidirtransform.NQNameList.class,silver.extension.bidirtransform.TDblArrow_kwd.class,silver.definition.core.NAGDcls.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_originAttributeDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcls.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcls.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qns] = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_inh_attrs];
	childInheritedAttributes[i_subAg] = new common.Lazy[silver.definition.core.NAGDcls.num_inh_attrs];

	}

	public PoriginAttributeDcl(final Object c__G_4, final Object c__G_3, final Object c_qns, final Object c__G_1, final Object c_subAg, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_qns = c_qns;
		this.child__G_1 = c__G_1;
		this.child_subAg = c_subAg;

	}

	private Object child__G_4;
	public final silver.extension.bidirtransform.TOrigins_kwd getChild__G_4() {
		return (silver.extension.bidirtransform.TOrigins_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TAttribute_kwd getChild__G_3() {
		return (silver.definition.core.TAttribute_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_qns;
	public final silver.extension.bidirtransform.NQNameList getChild_qns() {
		return (silver.extension.bidirtransform.NQNameList) (child_qns = common.Util.demand(child_qns));
	}

	private Object child__G_1;
	public final silver.extension.bidirtransform.TDblArrow_kwd getChild__G_1() {
		return (silver.extension.bidirtransform.TDblArrow_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_subAg;
	public final silver.definition.core.NAGDcls getChild_subAg() {
		return (silver.definition.core.NAGDcls) (child_subAg = common.Util.demand(child_subAg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_qns: return getChild_qns();
			case i__G_1: return getChild__G_1();
			case i_subAg: return getChild_subAg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_qns: return child_qns;
			case i__G_1: return child__G_1;
			case i_subAg: return child_subAg;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:originAttributeDcl erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:originAttributeDcl";
	}

	static void initProductionAttributeDefinitions() {

		// addAttrs = originAttributes(qns)
		silver.extension.bidirtransform.PoriginAttributeDcl.localAttributes[silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PoriginAttributes(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_qns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcls)context.undecorate()).getAnno_core_location()); } })); } };
		// addAttrs.env = subAg.env
		silver.extension.bidirtransform.PoriginAttributeDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcls)); } };
		// top.moduleNames = []
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.mdaSpecs = []
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// subAg.compiledGrammars = top.compiledGrammars
		silver.extension.bidirtransform.PoriginAttributeDcl.childInheritedAttributes[silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcls)); } };
		// subAg.config = top.config
		silver.extension.bidirtransform.PoriginAttributeDcl.childInheritedAttributes[silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg][silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_AGDcls)); } };
		// subAg.grammarName = top.grammarName
		silver.extension.bidirtransform.PoriginAttributeDcl.childInheritedAttributes[silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcls)); } };
		// subAg.flowEnv = top.flowEnv
		silver.extension.bidirtransform.PoriginAttributeDcl.childInheritedAttributes[silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcls)); } };
		// subAg.env = appendEnv(top.env, toEnv(addAttrs.defs))
		silver.extension.bidirtransform.PoriginAttributeDcl.childInheritedAttributes[silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnv.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcls), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PtoEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl)); } })); } })); } };
		// top.defs = subAg.defs
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcls)); } };
		// top.ideSpecs = addAttrs.ideSpecs ++ subAg.ideSpecs
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls))); } };
		// top.syntaxAst = addAttrs.syntaxAst ++ subAg.syntaxAst
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls))); } };
		// top.parserSpecs = addAttrs.parserSpecs ++ subAg.parserSpecs
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls))); } };
		// top.flowDefs = addAttrs.flowDefs ++ subAg.flowDefs
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcls))); } };
		// top.docs := addAttrs.docs ++ subAg.docs
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls))); } });
		// top.docsHeader = addAttrs.docsHeader ++ subAg.docsHeader
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls))); } };
		// top.docsSplit = addAttrs.docsSplit ++ subAg.docsSplit
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls))); } };
		// top.docsNoDoc = addAttrs.docsNoDoc || subAg.docsNoDoc
		silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl)) || ((Boolean)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls))); } };
		// top.docDcls := addAttrs.docDcls ++ subAg.docDcls
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls))); } });
		// top.genFiles := addAttrs.genFiles ++ subAg.genFiles
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CAgenFiles(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcls))); } });
		// top.setupInh := addAttrs.setupInh ++ subAg.setupInh
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CAsetupInh(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcls))); } });
		// top.initProd := addAttrs.initProd ++ subAg.initProd
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CAinitProd(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcls))); } });
		// top.initValues := addAttrs.initValues ++ subAg.initValues
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CAinitValues(silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcls))); } });
		// top.postInit := addAttrs.postInit ++ subAg.postInit
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CApostInit(silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcls))); } });
		// top.initWeaving := addAttrs.initWeaving ++ subAg.initWeaving
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CAinitWeaving(silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcls))); } });
		// top.valueWeaving := addAttrs.valueWeaving ++ subAg.valueWeaving
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcls] = new silver.translation.java.core.CAvalueWeaving(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg).synthesized(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcls))); } });
		// top.errors := addAttrs.errors ++ subAg.errors
		if(silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls] == null)
			silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.extension.bidirtransform.PoriginAttributeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.addAttrs__ON__silver_extension_bidirtransform_originAttributeDcl).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl)); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributeDcl.i_subAg, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls))); } });

	}

	public static final common.NodeFactory<PoriginAttributeDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoriginAttributeDcl> {

		@Override
		public PoriginAttributeDcl invoke(final Object[] children, final Object[] annotations) {
			return new PoriginAttributeDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
