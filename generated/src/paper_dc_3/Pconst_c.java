
package paper_dc_3;

// ic::Factor_c ::= l::IntLit_t 
public final class Pconst_c extends paper_dc_3.NFactor_c {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {paper_dc_3.TIntLit_t.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_const_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NFactor_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NFactor_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pconst_c(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final paper_dc_3.TIntLit_t getChild_l() {
		return (paper_dc_3.TIntLit_t) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:const_c erroneously claimed to forward");
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
		return "paper_dc_3:const_c";
	}

	static void initProductionAttributeDefinitions() {
		// ic.pp = l.lexeme
		paper_dc_3.Pconst_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((paper_dc_3.TIntLit_t)context.childAsIs(paper_dc_3.Pconst_c.i_l)).lexeme); } };
		// ic.ast_Expr = const(toInt(l.lexeme),origin=origin_Factor_c(ic), redex=nothing(), labels=[])
		paper_dc_3.Pconst_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)new paper_dc_3.Pconst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((paper_dc_3.TIntLit_t)context.childAsIs(paper_dc_3.Pconst_c.i_l)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.transformed.Porigin_Factor_c(context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })); } };

	}

	public static final common.NodeFactory<Pconst_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pconst_c> {

		@Override
		public Pconst_c invoke(final Object[] children, final Object[] annotations) {
			return new Pconst_c(children[0]);
		}
	};

}
