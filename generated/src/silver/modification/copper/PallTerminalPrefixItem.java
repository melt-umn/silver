
package silver.modification.copper;

// top::TerminalPrefixItems ::= 
public final class PallTerminalPrefixItem extends silver.modification.copper.NTerminalPrefixItems {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_allTerminalPrefixItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PallTerminalPrefixItem(final Object a_core_location) {
		super(a_core_location);

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:allTerminalPrefixItem erroneously claimed to forward");
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
		return "silver:modification:copper:allTerminalPrefixItem";
	}

	static void initProductionAttributeDefinitions() {
		// med = moduleExportedDefs(top.location, top.compiledGrammars, top.grammarDependencies, [ top.componentGrammarName ], [])
		silver.modification.copper.PallTerminalPrefixItem.localAttributes[silver.modification.copper.Init.med__ON__silver_modification_copper_allTerminalPrefixItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefixItems)context.undecorate()).getAnno_core_location()); } }, context.contextInheritedLazy(silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefixItems), context.contextInheritedLazy(silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_TerminalPrefixItems), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefixItems), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// syntax = foldr(consSyntax, nilSyntax(), med.syntaxAst)
		silver.modification.copper.PallTerminalPrefixItem.localAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.med__ON__silver_modification_copper_allTerminalPrefixItem).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs)); } })); } };
		// syntax.containingGrammar = error("This shouldn't be needed...")
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("This shouldn't be needed...")))); } };
		// syntax.cstEnv = error("This shouldn't be needed...")
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("This shouldn't be needed...")))); } };
		// syntax.cstNTProds = error("This shouldn't be needed...")
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("This shouldn't be needed...")))); } };
		// syntax.prefixesForTerminals = error("This shouldn't be needed...")
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("This shouldn't be needed...")))); } };
		// syntax.univLayout = error("This shouldn't be needed...")
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("This shouldn't be needed...")))); } };
		// top.pp = ""
		silver.modification.copper.PallTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.errors := []
		if(silver.modification.copper.PallTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] == null)
			silver.modification.copper.PallTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems);
		((common.CollectionAttribute)silver.modification.copper.PallTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.prefixItemNames = do (bindList, returnList) {sd::Decorated SyntaxDcl <- syntax.allMarkingTerminals; return qName(top.location, case sd of syntaxTerminal(n, _, _) -> n end);}
		silver.modification.copper.PallTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.monad.PbindList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_Syntax)); } }, (new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6788_sd = args[0];

    return ((common.ConsCell)core.monad.PreturnList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefixItems)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxTerminal) { final common.Thunk<Object> __SV_LOCAL___pv6793___sv_pv_6792_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6794___sv_tmp_pv_6795 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6796___sv_tmp_pv_6797 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6798_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6793___sv_pv_6792_n.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_6798_n.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:modification:copper 'Prefix.sv', 121, 33, 121, 76, 4413, 4456\n"))));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_6788_sd))); } })); } }));
  }
}))); } };

	}

	public static final common.NodeFactory<PallTerminalPrefixItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PallTerminalPrefixItem> {

		@Override
		public PallTerminalPrefixItem invoke(final Object[] children, final Object[] annotations) {
			return new PallTerminalPrefixItem(annotations[0]);
		}
	};

}
