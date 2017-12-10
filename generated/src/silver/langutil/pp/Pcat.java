
package silver.langutil.pp;

// top::Document ::= d1::Document d2::Document 
public final class Pcat extends silver.langutil.pp.NDocument {

	public static final int i_d1 = 0;
	public static final int i_d2 = 1;


	public static final Class<?> childTypes[] = {silver.langutil.pp.NDocument.class,silver.langutil.pp.NDocument.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_cat;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d1] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];
	childInheritedAttributes[i_d2] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	}

	public Pcat(final Object c_d1, final Object c_d2) {
		super();
		this.child_d1 = c_d1;
		this.child_d2 = c_d2;

	}

	private Object child_d1;
	public final silver.langutil.pp.NDocument getChild_d1() {
		return (silver.langutil.pp.NDocument) (child_d1 = common.Util.demand(child_d1));
	}

	private Object child_d2;
	public final silver.langutil.pp.NDocument getChild_d2() {
		return (silver.langutil.pp.NDocument) (child_d2 = common.Util.demand(child_d2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d1: return getChild_d1();
			case i_d2: return getChild_d2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d1: return child_d1;
			case i_d2: return child_d2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:langutil:pp:cat erroneously claimed to forward");
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
		return "silver:langutil:pp:cat";
	}

	static void initProductionAttributeDefinitions() {
		// d1.inPosition = top.inPosition
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d1][silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document)); } };
		// d1.inDq = top.inDq
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d1][silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document)); } };
		// d1.inCHorizontals = top.inCHorizontals
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d1][silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document)); } };
		// d1.inRemaining = top.inRemaining
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d1][silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document)); } };
		// d2.inPosition = d1.outPosition
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d2][silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.langutil.pp.Pcat.i_d1).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document)); } };
		// d2.inDq = d1.outDq
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d2][silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)context.childDecorated(silver.langutil.pp.Pcat.i_d1).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document)); } };
		// d2.inCHorizontals = d1.outCHorizontals
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d2][silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.langutil.pp.Pcat.i_d1).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document)); } };
		// d2.inRemaining = d1.outRemaining
		silver.langutil.pp.Pcat.childInheritedAttributes[silver.langutil.pp.Pcat.i_d2][silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.langutil.pp.Pcat.i_d1).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document)); } };
		// top.outPosition = d2.outPosition
		silver.langutil.pp.Pcat.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.langutil.pp.Pcat.i_d2).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document)); } };
		// top.outDq = d2.outDq
		silver.langutil.pp.Pcat.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)context.childDecorated(silver.langutil.pp.Pcat.i_d2).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document)); } };
		// top.outCHorizontals = d2.outCHorizontals
		silver.langutil.pp.Pcat.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.langutil.pp.Pcat.i_d2).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document)); } };
		// top.outRemaining = d2.outRemaining
		silver.langutil.pp.Pcat.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.langutil.pp.Pcat.i_d2).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document)); } };
		// top.result = d1.result ++ d2.result
		silver.langutil.pp.Pcat.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.langutil.pp.Pcat.i_d1).synthesized(silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.langutil.pp.Pcat.i_d2).synthesized(silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document))); } };
		// top.horizontals = d1.horizontals ++ d2.horizontals
		silver.langutil.pp.Pcat.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pcat.i_d1, silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document), context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pcat.i_d2, silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document))); } };

	}

	public static final common.NodeFactory<Pcat> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pcat> {

		@Override
		public Pcat invoke(final Object[] children, final Object[] annotations) {
			return new Pcat(children[0], children[1]);
		}
	};

}
