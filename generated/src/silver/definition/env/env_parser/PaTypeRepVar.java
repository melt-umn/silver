
package silver.definition.env.env_parser;

// top::ITypeRep ::= t::ITyVar 
public final class PaTypeRepVar extends silver.definition.env.env_parser.NITypeRep {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TITyVar.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aTypeRepVar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaTypeRepVar(final Object c_t) {
		super();
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.definition.env.env_parser.TITyVar getChild_t() {
		return (silver.definition.env.env_parser.TITyVar) (child_t = common.Util.demand(child_t));
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aTypeRepVar erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aTypeRepVar";
	}

	static void initProductionAttributeDefinitions() {
		// res = getTypeDcl(t.lexeme, top.env)
		silver.definition.env.env_parser.PaTypeRepVar.localAttributes[silver.definition.env.env_parser.Init.res__ON__silver_definition_env_env_parser_aTypeRepVar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDcl.invoke(((common.StringCatter)((silver.definition.env.env_parser.TITyVar)context.childAsIs(silver.definition.env.env_parser.PaTypeRepVar.i_t)).lexeme), context.contextInheritedLazy(silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_ITypeRep))); } };
		// top.typerep = if null(res) then error("INTERNAL ERROR: interface file for " ++ top.grammarName ++ " lacks type for tyvar " ++ t.lexeme ++ " on line " ++ toString(t.line) ++ " column " ++ toString(t.column)) else head(res).typerep
		silver.definition.env.env_parser.PaTypeRepVar.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.env.env_parser.Init.res__ON__silver_definition_env_env_parser_aTypeRepVar))) ? ((silver.definition.type.NType)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("INTERNAL ERROR: interface file for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.env.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_env_env_parser_ITypeRep)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" lacks type for tyvar ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.env.env_parser.TITyVar)context.childAsIs(silver.definition.env.env_parser.PaTypeRepVar.i_t)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" on line ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.definition.env.env_parser.TITyVar)context.childAsIs(silver.definition.env.env_parser.PaTypeRepVar.i_t)).getLine()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" column ")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.definition.env.env_parser.TITyVar)context.childAsIs(silver.definition.env.env_parser.PaTypeRepVar.i_t)).getColumn())))))))))); } })) : ((silver.definition.type.NType)((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.env.env_parser.Init.res__ON__silver_definition_env_env_parser_aTypeRepVar))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo))); } };

	}

	public static final common.NodeFactory<PaTypeRepVar> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTypeRepVar> {

		@Override
		public PaTypeRepVar invoke(final Object[] children, final Object[] annotations) {
			return new PaTypeRepVar(children[0]);
		}
	};

}
