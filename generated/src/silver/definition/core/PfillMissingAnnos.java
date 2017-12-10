
package silver.definition.core;

public final class PfillMissingAnnos extends common.FunctionNode {

	public static final int i_toFill = 0;
	public static final int i_namedTypes = 1;
	public static final int i_annos = 2;


	public static final Class<?> childTypes[] = { silver.definition.core.NAnnoAppExprs.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_fillMissingAnnos;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PfillMissingAnnos(final Object c_toFill, final Object c_namedTypes, final Object c_annos) {
		this.child_toFill = c_toFill;
		this.child_namedTypes = c_namedTypes;
		this.child_annos = c_annos;

	}

	private Object child_toFill;
	public final silver.definition.core.NAnnoAppExprs getChild_toFill() {
		return (silver.definition.core.NAnnoAppExprs) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_namedTypes;
	public final common.ConsCell getChild_namedTypes() {
		return (common.ConsCell) (child_namedTypes = common.Util.demand(child_namedTypes));
	}

	private Object child_annos;
	public final common.ConsCell getChild_annos() {
		return (common.ConsCell) (child_annos = common.Util.demand(child_annos));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_namedTypes: return getChild_namedTypes();
			case i_annos: return getChild_annos();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_namedTypes: return child_namedTypes;
			case i_annos: return child_annos;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:definition:core:fillMissingAnnos";
	}

	public static silver.definition.core.NAnnoAppExprs invoke(final Object c_toFill, final Object c_namedTypes, final Object c_annos) {
		try {
		final common.DecoratedNode context = new PfillMissingAnnos(c_toFill, c_namedTypes, c_annos).decorate();
		//if missing then case recAnns of emptyAnnoAppExprs() -> oneAnnoAppExprs(thisAnno,location=toFill.location) | _ -> snocAnnoAppExprs(recAnns, ',', thisAnno,location=toFill.location) end else recAnns
		return (silver.definition.core.NAnnoAppExprs)((((Boolean)context.localAsIs(silver.definition.core.Init.missing__ON__silver_definition_core_fillMissingAnnos)) ? ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3032___fail_3033 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PsnocAnnoAppExprs(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.recAnns__ON__silver_definition_core_fillMissingAnnos)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.thisAnno__ON__silver_definition_core_fillMissingAnnos)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.definition.core.PfillMissingAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAnnoAppExprs>() { public final silver.definition.core.NAnnoAppExprs eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PemptyAnnoAppExprs) {  return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PoneAnnoAppExprs(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.thisAnno__ON__silver_definition_core_fillMissingAnnos)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.definition.core.PfillMissingAnnos.i_toFill).undecorate()).getAnno_core_location()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAnnoAppExprs)(__SV_LOCAL_3032___fail_3033.eval()));}}.eval(context, (common.DecoratedNode)context.localDecorated(silver.definition.core.Init.recAnns__ON__silver_definition_core_fillMissingAnnos)); } }).eval()) : context.localDecorated(silver.definition.core.Init.recAnns__ON__silver_definition_core_fillMissingAnnos).undecorate()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:fillMissingAnnos", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAnnoAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAnnoAppExprs> {
		@Override
		public silver.definition.core.NAnnoAppExprs invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfillMissingAnnos.invoke(children[0], children[1], children[2]);
		}
	};
}