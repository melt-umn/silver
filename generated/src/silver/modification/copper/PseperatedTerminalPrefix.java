
package silver.modification.copper;

// top::TerminalPrefix ::= t::String_t 
public final class PseperatedTerminalPrefix extends silver.modification.copper.NTerminalPrefix {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TString_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_seperatedTerminalPrefix;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PseperatedTerminalPrefix(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.definition.core.TString_t getChild_t() {
		return (silver.definition.core.TString_t) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return ((silver.modification.copper.NTerminalPrefix)new silver.modification.copper.PnewTermTerminalPrefix(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.copper.Init.regex__ON__silver_modification_copper_seperatedTerminalPrefix)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:seperatedTerminalPrefix";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.lexeme
		silver.modification.copper.PseperatedTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.copper.PseperatedTerminalPrefix.i_t)).lexeme); } };
		// seperatorLookup = getValueDcl("_prefix_seperator", top.env)
		silver.modification.copper.PseperatedTerminalPrefix.localAttributes[silver.modification.copper.Init.seperatorLookup__ON__silver_modification_copper_seperatedTerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDcl.invoke((new common.StringCatter("_prefix_seperator")), context.contextInheritedLazy(silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_TerminalPrefix))); } };
		// seperator = case seperatorLookup of prefixSeparatorDcl(sg, sl, s)::_ -> s | _ -> "" end
		silver.modification.copper.PseperatedTerminalPrefix.localAttributes[silver.modification.copper.Init.seperator__ON__silver_modification_copper_seperatedTerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6715___fail_6716 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
return new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6718___sv_tmp_pv_6717 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6719___sv_tmp_pv_6720 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.modification.copper.PprefixSeparatorDcl) { final common.Thunk<Object> __SV_LOCAL___pv6724___sv_pv_6725_sg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6726___sv_pv_6727_sl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6728___sv_pv_6723_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6729_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6728___sv_pv_6723_s.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6730_sl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv6726___sv_pv_6727_sl.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6731_sg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6724___sv_pv_6725_sg.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_6729_s.eval())); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)(__SV_LOCAL_6715___fail_6716.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)(__SV_LOCAL_6718___sv_tmp_pv_6717.eval())).decorate(context, (common.Lazy[])null)); }return ((common.StringCatter)(__SV_LOCAL_6715___fail_6716.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(silver.modification.copper.Init.seperatorLookup__ON__silver_modification_copper_seperatedTerminalPrefix))); } }).eval()); } };
		// givenPrefix = substring(1, length(t.lexeme) - 1, t.lexeme)
		silver.modification.copper.PseperatedTerminalPrefix.localAttributes[silver.modification.copper.Init.givenPrefix__ON__silver_modification_copper_seperatedTerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.copper.PseperatedTerminalPrefix.i_t)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.copper.PseperatedTerminalPrefix.i_t)).lexeme))); } };
		// regex = regExpr('/', regexLiteral(givenPrefix ++ seperator), '/',location=top.location)
		silver.modification.copper.PseperatedTerminalPrefix.localAttributes[silver.modification.copper.Init.regex__ON__silver_modification_copper_seperatedTerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NRegExpr)new silver.definition.concrete_syntax.PregExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDivide_t((new common.StringCatter("/")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegex)silver.definition.regex.PregexLiteral.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.givenPrefix__ON__silver_modification_copper_seperatedTerminalPrefix)), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.seperator__ON__silver_modification_copper_seperatedTerminalPrefix))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDivide_t((new common.StringCatter("/")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } })); } };
		// top.errors <- case seperatorLookup of prefixSeparatorDcl(sg, sl, s)::_ -> [] | _ -> [ wrn(top.location, "Prefix seperator is not defined, using the empty seperator") ] end
		if(silver.modification.copper.PseperatedTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix] == null)
			silver.modification.copper.PseperatedTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix);
		((common.CollectionAttribute)silver.modification.copper.PseperatedTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6732___fail_6733 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Prefix seperator is not defined, using the empty seperator")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6735___sv_tmp_pv_6734 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6736___sv_tmp_pv_6737 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.modification.copper.PprefixSeparatorDcl) { final common.Thunk<Object> __SV_LOCAL___pv6744___sv_pv_6745_sg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6746___sv_pv_6747_sl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6748___sv_pv_6743_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6749_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6748___sv_pv_6743_s.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6750_sl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv6746___sv_pv_6747_sl.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6751_sg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6744___sv_pv_6745_sg.eval())); } };
return ((common.ConsCell)core.Pnil.invoke()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_6732___fail_6733.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)(__SV_LOCAL_6735___sv_tmp_pv_6734.eval())).decorate(context, (common.Lazy[])null)); }return ((common.ConsCell)(__SV_LOCAL_6732___fail_6733.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(silver.modification.copper.Init.seperatorLookup__ON__silver_modification_copper_seperatedTerminalPrefix))); } }).eval()); } });

	}

	public static final common.NodeFactory<PseperatedTerminalPrefix> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PseperatedTerminalPrefix> {

		@Override
		public PseperatedTerminalPrefix invoke(final Object[] children, final Object[] annotations) {
			return new PseperatedTerminalPrefix(children[0], annotations[0]);
		}
	};

}
