
package silver_features;

// top::ColNT ::= 
public final class PcolTest3 extends silver_features.NColNT {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_colTest3;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NColNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcolTest3() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver_features:colTest3 erroneously claimed to forward");
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
		return "silver_features:colTest3";
	}

	static void initProductionAttributeDefinitions() {
		// top.colList := []
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colList__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colList__ON__silver_features_ColNT] = new silver_features.CAcolList(silver_features.Init.silver_features_colList__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colList__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.colList <- [ "one", "two" ]
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colList__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colList__ON__silver_features_ColNT] = new silver_features.CAcolList(silver_features.Init.silver_features_colList__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colList__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("one")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("two")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } });
		// top.colOr := false
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT] = new silver_features.CAcolOr(silver_features.Init.silver_features_colOr__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } });
		// top.colOr <- false
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT] = new silver_features.CAcolOr(silver_features.Init.silver_features_colOr__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } });
		// top.colAnd := false
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT] = new silver_features.CAcolAnd(silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } });
		// top.colAnd <- true
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT] = new silver_features.CAcolAnd(silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } });
		// top.colFun := just(1,)
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT] = new silver_features.CAcolFun(silver_features.Init.silver_features_colFun__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(Integer.valueOf((int)1))); } });
		// top.colFun <- just(2,)
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT] = new silver_features.CAcolFun(silver_features.Init.silver_features_colFun__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(Integer.valueOf((int)2))); } });
		// top.colProd := colLeaf(,)
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT] = new silver_features.CAcolProd(silver_features.Init.silver_features_colProd__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.NColNT)new silver_features.PcolLeaf()); } });
		// top.colProd <- colProdLeaf(,)
		if(silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT] == null)
			silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT] = new silver_features.CAcolProd(silver_features.Init.silver_features_colProd__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolTest3.synthesizedAttributes[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.NColNT)new silver_features.PcolProdLeaf()); } });

	}

	public static final common.NodeFactory<PcolTest3> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcolTest3> {

		@Override
		public PcolTest3 invoke(final Object[] children, final Object[] annotations) {
			return new PcolTest3();
		}
	};

}
