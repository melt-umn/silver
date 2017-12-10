
package silver.extension.easyterminal;

// top::RegExpr ::= t::Terminal_t 
public final class PregExprEasyTerm extends silver.definition.concrete_syntax.NRegExpr {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.extension.easyterminal.TTerminal_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_easyterminal_regExprEasyTerm;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PregExprEasyTerm(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.extension.easyterminal.TTerminal_t getChild_t() {
		return (silver.extension.easyterminal.TTerminal_t) (child_t = common.Util.demand(child_t));
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
		return ((silver.definition.concrete_syntax.NRegExpr)new silver.definition.concrete_syntax.PregExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDivide_t((new common.StringCatter("/")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.contextSynthesizedLazy(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDivide_t((new common.StringCatter("/")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.concrete_syntax.NRegExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:easyterminal:regExprEasyTerm";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.lexeme
		silver.extension.easyterminal.PregExprEasyTerm.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_RegExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PregExprEasyTerm.i_t)).lexeme); } };
		// top.terminalRegExprSpec = regexLiteral(substring(1, length(t.lexeme) - 1, t.lexeme))
		silver.extension.easyterminal.PregExprEasyTerm.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegex)silver.definition.regex.PregexLiteral.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PregExprEasyTerm.i_t)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.extension.easyterminal.TTerminal_t)context.childAsIs(silver.extension.easyterminal.PregExprEasyTerm.i_t)).lexeme))); } })); } };

	}

	public static final common.NodeFactory<PregExprEasyTerm> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregExprEasyTerm> {

		@Override
		public PregExprEasyTerm invoke(final Object[] children, final Object[] annotations) {
			return new PregExprEasyTerm(children[0], annotations[0]);
		}
	};

}
