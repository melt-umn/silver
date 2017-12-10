
package silver.modification.copper_mda;

// top::AGDcl ::= 'copper_mda' testname::Name '(' orig::QName ')' '{' m::ParserComponents '}' 
public final class PcopperMdaDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_7 = 0;
	public static final int i_testname = 1;
	public static final int i__G_5 = 2;
	public static final int i_orig = 3;
	public static final int i__G_3 = 4;
	public static final int i__G_2 = 5;
	public static final int i_m = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.modification.copper_mda.TCopperMDA.class,silver.definition.core.NName.class,silver.definition.core.TLParen_t.class,silver.definition.core.NQName.class,silver.definition.core.TRParen_t.class,silver.definition.core.TLCurly_t.class,silver.modification.copper.NParserComponents.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_mda_copperMdaDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_testname] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_orig] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_m] = new common.Lazy[silver.modification.copper.NParserComponents.num_inh_attrs];

	}

	public PcopperMdaDcl(final Object c__G_7, final Object c_testname, final Object c__G_5, final Object c_orig, final Object c__G_3, final Object c__G_2, final Object c_m, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child_testname = c_testname;
		this.child__G_5 = c__G_5;
		this.child_orig = c_orig;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_m = c_m;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.modification.copper_mda.TCopperMDA getChild__G_7() {
		return (silver.modification.copper_mda.TCopperMDA) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_testname;
	public final silver.definition.core.NName getChild_testname() {
		return (silver.definition.core.NName) (child_testname = common.Util.demand(child_testname));
	}

	private Object child__G_5;
	public final silver.definition.core.TLParen_t getChild__G_5() {
		return (silver.definition.core.TLParen_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_orig;
	public final silver.definition.core.NQName getChild_orig() {
		return (silver.definition.core.NQName) (child_orig = common.Util.demand(child_orig));
	}

	private Object child__G_3;
	public final silver.definition.core.TRParen_t getChild__G_3() {
		return (silver.definition.core.TRParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_m;
	public final silver.modification.copper.NParserComponents getChild_m() {
		return (silver.modification.copper.NParserComponents) (child_m = common.Util.demand(child_m));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i_testname: return getChild_testname();
			case i__G_5: return getChild__G_5();
			case i_orig: return getChild_orig();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_m: return getChild_m();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i_testname: return child_testname;
			case i__G_5: return child__G_5;
			case i_orig: return child_orig;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_m: return child_m;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper_mda:copperMdaDcl erroneously claimed to forward");
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
		return "silver:modification:copper_mda:copperMdaDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ""
		silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.errors := orig.lookupValue.errors ++ m.errors
		if(silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcopperMdaDcl.i_m, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents))); } });
		// top.moduleNames = m.moduleNames
		silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_m).synthesized(silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents)); } };
		// spec = if ! null(orig.lookupValue.errors) then [] else findSpec(orig.lookupValue.fullName, head(searchEnvTree(orig.lookupValue.dcl.sourceGrammar, top.compiledGrammars)).parserSpecs)
		silver.modification.copper_mda.PcopperMdaDcl.localAttributes[silver.modification.copper_mda.Init.spec__ON__silver_modification_copper_mda_copperMdaDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)silver.modification.copper_mda.PfindSpec.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl))); } })).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec)); } }))); } };
		// top.errors <- if ! null(orig.lookupValue.errors) || ! null(spec) then [] else [ err(orig.location, orig.name ++ " is not a parser.") ]
		if(silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }))) || (!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.modification.copper_mda.Init.spec__ON__silver_modification_copper_mda_copperMdaDcl))))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_orig).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is not a parser."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.mdaSpecs = case spec of parserSpec(_, _, fn, snt, hg, pfxs, _)::_ -> [ mdaSpec(top.grammarName, top.grammarName ++ ":" ++ testname.name, snt, hg, m.moduleNames, pfxs) ] | _ -> [] end
		silver.modification.copper_mda.PcopperMdaDcl.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8272___fail_8273 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_8275___sv_tmp_pv_8274 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_8276___sv_tmp_pv_8277 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.concrete_syntax.PparserSpec) { final common.Thunk<Object> __SV_LOCAL___pv8282___sv_tmp_pv_8283 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8284___sv_tmp_pv_8285 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv8286___sv_pv_8287_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv8288___sv_pv_8289_snt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv8290___sv_pv_8291_hg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
final common.Thunk<Object> __SV_LOCAL___pv8292___sv_pv_8281_pfxs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(5); } };
final common.Thunk<Object> __SV_LOCAL___pv8293___sv_tmp_pv_8294 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(6); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8295_pfxs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv8292___sv_pv_8281_pfxs.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8296_hg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv8290___sv_pv_8291_hg.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8297_snt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv8288___sv_pv_8289_snt.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8298_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv8286___sv_pv_8287_fn.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.copper_mda.NMdaSpec)new silver.modification.copper_mda.PmdaSpec(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcopperMdaDcl.i_testname).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } }, __SV_LOCAL_8297_snt, __SV_LOCAL_8296_hg, context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcopperMdaDcl.i_m, silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents), __SV_LOCAL_8295_pfxs)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_8272___fail_8273.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.concrete_syntax.NParserSpec)(__SV_LOCAL_8275___sv_tmp_pv_8274.eval())).decorate(context, (common.Lazy[])null)); }return ((common.ConsCell)(__SV_LOCAL_8272___fail_8273.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(silver.modification.copper_mda.Init.spec__ON__silver_modification_copper_mda_copperMdaDcl))); } }).eval()); } };

	}

	public static final common.NodeFactory<PcopperMdaDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcopperMdaDcl> {

		@Override
		public PcopperMdaDcl invoke(final Object[] children, final Object[] annotations) {
			return new PcopperMdaDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
