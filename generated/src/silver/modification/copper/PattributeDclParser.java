
package silver.modification.copper;

// top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::TypeExpr 'action' acode::ActionCode_c ';' 
public final class PattributeDclParser extends silver.definition.core.NAGDcl {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_a = 2;
	public static final int i__G_4 = 3;
	public static final int i_te = 4;
	public static final int i__G_2 = 5;
	public static final int i_acode = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.modification.copper.TParser_kwd.class,silver.definition.core.TAttribute_kwd.class,silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.modification.copper.TAction_kwd.class,silver.modification.copper.NActionCode_c.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_attributeDclParser;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_acode] = new common.Lazy[silver.modification.copper.NActionCode_c.num_inh_attrs];

	}

	public PattributeDclParser(final Object c__G_7, final Object c__G_6, final Object c_a, final Object c__G_4, final Object c_te, final Object c__G_2, final Object c_acode, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_a = c_a;
		this.child__G_4 = c__G_4;
		this.child_te = c_te;
		this.child__G_2 = c__G_2;
		this.child_acode = c_acode;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.modification.copper.TParser_kwd getChild__G_7() {
		return (silver.modification.copper.TParser_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.core.TAttribute_kwd getChild__G_6() {
		return (silver.definition.core.TAttribute_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_a;
	public final silver.definition.core.NName getChild_a() {
		return (silver.definition.core.NName) (child_a = common.Util.demand(child_a));
	}

	private Object child__G_4;
	public final silver.definition.core.TColonColon_t getChild__G_4() {
		return (silver.definition.core.TColonColon_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_te;
	public final silver.definition.type.syntax.NTypeExpr getChild_te() {
		return (silver.definition.type.syntax.NTypeExpr) (child_te = common.Util.demand(child_te));
	}

	private Object child__G_2;
	public final silver.modification.copper.TAction_kwd getChild__G_2() {
		return (silver.modification.copper.TAction_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_acode;
	public final silver.modification.copper.NActionCode_c getChild_acode() {
		return (silver.modification.copper.NActionCode_c) (child_acode = common.Util.demand(child_acode));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i__G_6: return getChild__G_6();
			case i_a: return getChild_a();
			case i__G_4: return getChild__G_4();
			case i_te: return getChild_te();
			case i__G_2: return getChild__G_2();
			case i_acode: return getChild_acode();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i_a: return child_a;
			case i__G_4: return child__G_4;
			case i_te: return child_te;
			case i__G_2: return child__G_2;
			case i_acode: return child_acode;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:attributeDclParser erroneously claimed to forward");
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
		return "silver:modification:copper:attributeDclParser";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "parser attribute " ++ a.name ++ " :: " ++ te.pp ++ " action " ++ acode.pp ++ " ;"
		silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parser attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PattributeDclParser.i_a).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" :: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PattributeDclParser.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" action ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PattributeDclParser.i_acode).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ActionCode_c)), (common.StringCatter)(new common.StringCatter(" ;")))))))); } };
		// fName = top.grammarName ++ ":" ++ a.name
		silver.modification.copper.PattributeDclParser.localAttributes[silver.modification.copper.Init.fName__ON__silver_modification_copper_attributeDclParser] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PattributeDclParser.i_a).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ parserAttrDef(top.grammarName, a.location, fName, te.typerep) ]
		silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PparserAttrDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.copper.PattributeDclParser.i_a).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_attributeDclParser), context.childDecoratedSynthesizedLazy(silver.modification.copper.PattributeDclParser.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors <- if length(getValueDclAll(fName, top.env)) > 1 then [ err(a.location, "Attribute '" ++ fName ++ "' is already bound.") ] else []
		if(silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclAll.invoke(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_attributeDclParser), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.copper.PattributeDclParser.i_a).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.fName__ON__silver_modification_copper_attributeDclParser)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors := te.errors ++ acode.errors
		if(silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PattributeDclParser.i_te, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.modification.copper.PattributeDclParser.i_acode, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ActionCode_c))); } });
		// acode.frame = actionContext()
		silver.modification.copper.PattributeDclParser.childInheritedAttributes[silver.modification.copper.PattributeDclParser.i_acode][silver.modification.copper.Init.silver_definition_core_frame__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.modification.copper.PactionContext()); } };
		// acode.env = newScopeEnv(acode.defs, top.env)
		silver.modification.copper.PattributeDclParser.childInheritedAttributes[silver.modification.copper.PattributeDclParser.i_acode][silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PattributeDclParser.i_acode, silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_ActionCode_c), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// top.syntaxAst = [ syntaxParserAttribute(fName, te.typerep, acode.actionCode) ]
		silver.modification.copper.PattributeDclParser.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxParserAttribute(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_attributeDclParser), context.childDecoratedSynthesizedLazy(silver.modification.copper.PattributeDclParser.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.modification.copper.PattributeDclParser.i_acode, silver.modification.copper.Init.silver_modification_copper_actionCode__ON__silver_modification_copper_ActionCode_c))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PattributeDclParser> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributeDclParser> {

		@Override
		public PattributeDclParser invoke(final Object[] children, final Object[] annotations) {
			return new PattributeDclParser(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
