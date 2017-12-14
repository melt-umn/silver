
package paper_dc_3;

// r::Root_c ::= e::Expr_c 
public final class Proot_c extends paper_dc_3.NRoot_c {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {paper_dc_3.NExpr_c.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_root_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NRoot_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NRoot_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[paper_dc_3.NExpr_c.num_inh_attrs];

	}

	public Proot_c(final Object c_e) {
		super();
		this.child_e = c_e;

	}

	private Object child_e;
	public final paper_dc_3.NExpr_c getChild_e() {
		return (paper_dc_3.NExpr_c) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:root_c erroneously claimed to forward");
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
		return "paper_dc_3:root_c";
	}

	static void initProductionAttributeDefinitions() {
		// r.pp = e.pp
		paper_dc_3.Proot_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Root_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(paper_dc_3.Proot_c.i_e).synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Expr_c)); } };
		// r.ast_Root = root(e.ast_Expr,origin=origin_Root_c(r), redex=nothing(), labels=[])
		paper_dc_3.Proot_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_ast_Root__ON__paper_dc_3_Root_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NRoot_a)new paper_dc_3.Proot(context.childDecoratedSynthesizedLazy(paper_dc_3.Proot_c.i_e, paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Root_c(context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })); } };

	}

	public static final common.NodeFactory<Proot_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Proot_c> {

		@Override
		public Proot_c invoke(final Object[] children, final Object[] annotations) {
			return new Proot_c(children[0]);
		}
	};

}
