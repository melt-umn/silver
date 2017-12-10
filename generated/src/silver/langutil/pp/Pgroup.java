
package silver.langutil.pp;

// top::Document ::= d::Document 
public final class Pgroup extends silver.langutil.pp.NDocument {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = {silver.langutil.pp.NDocument.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_group;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	}

	public Pgroup(final Object c_d) {
		super();
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.langutil.pp.NDocument getChild_d() {
		return (silver.langutil.pp.NDocument) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		throw new common.exceptions.SilverInternalError("Production silver:langutil:pp:group erroneously claimed to forward");
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
		return "silver:langutil:pp:group";
	}

	static void initProductionAttributeDefinitions() {
		// d.inPosition = top.inPosition
		silver.langutil.pp.Pgroup.childInheritedAttributes[silver.langutil.pp.Pgroup.i_d][silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document)); } };
		// d.inDq = enter(top.inPosition + top.inRemaining, top.inDq)
		silver.langutil.pp.Pgroup.childInheritedAttributes[silver.langutil.pp.Pgroup.i_d][silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.langutil.pp.Penter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document)) + ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document))); } }, context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document))); } };
		// d.inCHorizontals = tail(top.inCHorizontals)
		silver.langutil.pp.Pgroup.childInheritedAttributes[silver.langutil.pp.Pgroup.i_d][silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document))); } };
		// d.inRemaining = top.inRemaining
		silver.langutil.pp.Pgroup.childInheritedAttributes[silver.langutil.pp.Pgroup.i_d][silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document)); } };
		// le = leave(d.outPosition, d.outDq)
		silver.langutil.pp.Pgroup.localAttributes[silver.langutil.pp.Init.le__ON__silver_langutil_pp_group] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)silver.langutil.pp.Pleave.invoke(context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pgroup.i_d, silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document), context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pgroup.i_d, silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document))); } };
		// top.outPosition = d.outPosition
		silver.langutil.pp.Pgroup.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.langutil.pp.Pgroup.i_d).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document)); } };
		// top.outDq = le.fst
		silver.langutil.pp.Pgroup.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)context.localDecorated(silver.langutil.pp.Init.le__ON__silver_langutil_pp_group).synthesized(core.Init.core_fst__ON__core_Pair)); } };
		// top.outCHorizontals = (head(top.inCHorizontals) :: tail(d.outCHorizontals))
		silver.langutil.pp.Pgroup.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)core.Phead.invoke(context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pgroup.i_d, silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document))); } })); } };
		// top.outRemaining = d.outRemaining
		silver.langutil.pp.Pgroup.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.langutil.pp.Pgroup.i_d).synthesized(silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document)); } };
		// top.result = d.result
		silver.langutil.pp.Pgroup.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.langutil.pp.Pgroup.i_d).synthesized(silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document)); } };
		// top.horizontals = d.horizontals ++ le.snd
		silver.langutil.pp.Pgroup.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pgroup.i_d, silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.langutil.pp.Init.le__ON__silver_langutil_pp_group).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } };

	}

	public static final common.NodeFactory<Pgroup> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pgroup> {

		@Override
		public Pgroup invoke(final Object[] children, final Object[] annotations) {
			return new Pgroup(children[0]);
		}
	};

}
