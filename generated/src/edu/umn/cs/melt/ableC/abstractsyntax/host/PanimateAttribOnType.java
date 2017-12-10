
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PanimateAttribOnType extends common.FunctionNode {

	public static final int i_attr = 0;
	public static final int i_ty = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_animateAttribOnType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_attr] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PanimateAttribOnType(final Object c_attr, final Object c_ty) {
		this.child_attr = c_attr;
		this.child_ty = c_ty;

	}

	private Object child_attr;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs getChild_attr() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_ty;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_ty() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_ty = common.Util.demand(child_ty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attr: return getChild_attr();
			case i_ty: return getChild_ty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attr: return child_attr;
			case i_ty: return child_ty;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:animateAttribOnType";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object c_attr, final Object c_ty) {
		try {
		final common.DecoratedNode context = new PanimateAttribOnType(c_attr, c_ty).decorate();
		//case attr of consAttrib(appliedAttrib(attribName(name("__vector_size__")), consExpr(realConstant(integerConstant(num, _, _)), nilExpr())), t) -> animateAttribOnType(t, vectorType(ty, toInt(num),),) | consAttrib(appliedAttrib(attribName(name("refId")), _), t) -> animateAttribOnType(t, ty,) | consAttrib(h, t) -> case animateAttribOnType(t, ty,) of attributedType(consAttribute(gccAttribute(l), attr), ty) -> attributedType(consAttribute(gccAttribute(l,), attr,), ty,) | attributedType(attr, ty) -> attributedType(consAttribute(gccAttribute(consAttrib(h, nilAttrib(,),),), attr,), ty,) | ty -> attributedType(consAttribute(gccAttribute(consAttrib(h, nilAttrib(,),),), nilAttribute(,),), ty,) end | nilAttrib() -> ty end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib) { final common.Thunk<Object> __SV_LOCAL___pv7542___sv_tmp_pv_7543 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7544___sv_pv_7541_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7588___fail_7589 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7545_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7544___sv_pv_7541_t.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7546_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7542___sv_tmp_pv_7543.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7548___fail_7549 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7547_ty = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_7545_t), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.i_ty)))); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribute)new edu.umn.cs.melt.ableC.abstractsyntax.host.PgccAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib(common.Thunk.transformUndecorate(__SV_LOCAL_7546_h), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttrib()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute()); } })); } }, __SV_LOCAL_7547_ty)); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType) { final common.Thunk<Object> __SV_LOCAL___pv7551___sv_tmp_pv_7552 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7553___sv_pv_7550_ty = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7556___fail_7557 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7554_ty = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7553___sv_pv_7550_ty.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7555_attr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7551___sv_tmp_pv_7552.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribute)new edu.umn.cs.melt.ableC.abstractsyntax.host.PgccAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib(common.Thunk.transformUndecorate(__SV_LOCAL_7546_h), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttrib()); } })); } })); } }, common.Thunk.transformUndecorate(__SV_LOCAL_7555_attr))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_7554_ty))); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute) { final common.Thunk<Object> __SV_LOCAL___pv7579___sv_tmp_pv_7578 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7580___sv_pv_7581_attr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PgccAttribute) { final common.Thunk<Object> __SV_LOCAL___pv7583___sv_pv_7584_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7585_ty = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7553___sv_pv_7550_ty.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7586_attr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7580___sv_pv_7581_attr.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7587_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7583___sv_pv_7584_l.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribute)new edu.umn.cs.melt.ableC.abstractsyntax.host.PgccAttribute(common.Thunk.transformUndecorate(__SV_LOCAL_7587_l))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_7586_attr))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_7585_ty))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7556___fail_7557.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7579___sv_tmp_pv_7578.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7556___fail_7557.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7551___sv_tmp_pv_7552.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7548___fail_7549.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_7545_t), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.i_ty)))).decorate(context, (common.Lazy[])null)); } }).eval()); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PappliedAttrib) { final common.Thunk<Object> __SV_LOCAL___pv7602___sv_tmp_pv_7601 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7603___sv_tmp_pv_7604 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PattribName) { final common.Thunk<Object> __SV_LOCAL___pv7607___sv_tmp_pv_7606 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.Pname) { final common.Thunk<Object> __SV_LOCAL___pv7610___sv_tmp_pv_7609 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.StringCatter, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.StringCatter scrutineeIter) {final common.StringCatter scrutinee = scrutineeIter; if(scrutinee.equals("__vector_size__")) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsExpr) { final common.Thunk<Object> __SV_LOCAL___pv7613___sv_tmp_pv_7612 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7614___sv_tmp_pv_7615 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrealConstant) { final common.Thunk<Object> __SV_LOCAL___pv7618___sv_tmp_pv_7617 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PintegerConstant) { final common.Thunk<Object> __SV_LOCAL___pv7620___sv_pv_7621_num = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7622___sv_tmp_pv_7623 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv7624___sv_tmp_pv_7625 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilExpr) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7627_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7544___sv_pv_7541_t.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7628_num = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv7620___sv_pv_7621_num.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_7627_t), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.i_ty)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)(__SV_LOCAL_7628_num.eval())).toString()); } })); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7614___sv_tmp_pv_7615.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7618___sv_tmp_pv_7617.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7613___sv_tmp_pv_7612.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7603___sv_tmp_pv_7604.eval()))); }
else if(scrutinee.equals("refId")) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7629_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7544___sv_pv_7541_t.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_7629_t), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.i_ty)))); } }).eval()); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.StringCatter)((common.StringCatter)(__SV_LOCAL___pv7610___sv_tmp_pv_7609.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7607___sv_tmp_pv_7606.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7602___sv_tmp_pv_7601.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_7588___fail_7589.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7542___sv_tmp_pv_7543.eval()))); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttrib) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.i_ty).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'AttributeAnimation.sv', 28, 9, 54, 5, 817, 1934\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PanimateAttribOnType.i_attr)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:animateAttribOnType", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PanimateAttribOnType.invoke(children[0], children[1]);
		}
	};
}