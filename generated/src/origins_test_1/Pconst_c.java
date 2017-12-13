
package origins_test_1;

// ic::Factor_c ::= i::IntLit_t 
public final class Pconst_c extends origins_test_1.NFactor_c {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = {origins_test_1.TIntLit_t.class};

	public static final int num_local_attrs = Init.count_local__ON__origins_test_1_const_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[origins_test_1.NFactor_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[origins_test_1.NFactor_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pconst_c(final Object c_i) {
		super();
		this.child_i = c_i;

	}

	private Object child_i;
	public final origins_test_1.TIntLit_t getChild_i() {
		return (origins_test_1.TIntLit_t) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		throw new common.exceptions.SilverInternalError("Production origins_test_1:const_c erroneously claimed to forward");
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
		return "origins_test_1:const_c";
	}

	static void initProductionAttributeDefinitions() {
		// ic.pp = i.lexeme
		origins_test_1.Pconst_c.synthesizedAttributes[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((origins_test_1.TIntLit_t)context.childAsIs(origins_test_1.Pconst_c.i_i)).lexeme); } };
		// ic.ast_Expr = const(toInt(i.lexeme))
		origins_test_1.Pconst_c.synthesizedAttributes[origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((origins_test_1.NExpr)new origins_test_1.Pconst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((origins_test_1.TIntLit_t)context.childAsIs(origins_test_1.Pconst_c.i_i)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new origins_test_1.Porigin_Factor_c(context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })); } };

	}

	public static final common.NodeFactory<Pconst_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pconst_c> {

		@Override
		public Pconst_c invoke(final Object[] children, final Object[] annotations) {
			return new Pconst_c(children[0]);
		}
	};

}
