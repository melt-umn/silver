
package silver.extension.doc.core;

public final class PtoMarkdown extends common.FunctionNode {

	public static final int i_c = 0;


	public static final Class<?> childTypes[] = { silver.extension.doc.core.NCommentItem.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_toMarkdown;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_c] = new common.Lazy[silver.extension.doc.core.NCommentItem.num_inh_attrs];

	}

	public PtoMarkdown(final Object c_c) {
		this.child_c = c_c;

	}

	private Object child_c;
	public final silver.extension.doc.core.NCommentItem getChild_c() {
		return (silver.extension.doc.core.NCommentItem) (child_c = common.Util.demand(child_c));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:doc:core:toMarkdown";
	}

	public static common.StringCatter invoke(final Object c_c) {
		try {
		final common.DecoratedNode context = new PtoMarkdown(c_c).decorate();
		//case c of dclCommentItem(mod, name, sig, file, body) -> let signature :: String = if 0 == length(sig) then "" else "\n###### `" ++ sig ++ "`" in "\n\n#### _" ++ mod ++ "_ `" ++ name ++ "`" ++ signature ++ "\n> " ++ body.body ++ "\nIn file: " ++ file end | bodilessDclCommentItem(mod, name, sig, file) -> let signature :: String = if 0 == length(sig) then "" else "\n###### `" ++ sig ++ "`" in "\n\n#### _" ++ mod ++ "_ `" ++ name ++ "`" ++ signature ++ "\nIn file: " ++ file end end
		return (common.StringCatter)(new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.doc.core.PbodilessDclCommentItem) { final common.Thunk<Object> __SV_LOCAL___pv11331___sv_pv_11332_mod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11333___sv_pv_11334_name = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv11335___sv_pv_11336_sig = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv11337___sv_pv_11330_file = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(3); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11338_file = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11337___sv_pv_11330_file.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11339_sig = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11335___sv_pv_11336_sig.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11340_name = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11333___sv_pv_11334_name.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11341_mod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11331___sv_pv_11332_mod.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11345_signature = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer.valueOf((int)0).equals(Integer.valueOf(((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11339_sig.eval()))).length())) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\n###### `")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11339_sig.eval())), (common.StringCatter)(new common.StringCatter("`"))))); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n#### _")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11341_mod.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_ `")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11340_name.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("`")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11345_signature.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nIn file: ")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_11338_file.eval()))))))))); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.doc.core.PdclCommentItem) { final common.Thunk<Object> __SV_LOCAL___pv11349___sv_pv_11350_mod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11351___sv_pv_11352_name = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv11353___sv_pv_11354_sig = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv11355___sv_pv_11356_file = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv11357___sv_pv_11348_body = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(4); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11358_body = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv11357___sv_pv_11348_body.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11359_file = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11355___sv_pv_11356_file.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11360_sig = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11353___sv_pv_11354_sig.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11361_name = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11351___sv_pv_11352_name.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11362_mod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11349___sv_pv_11350_mod.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11368_signature = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer.valueOf((int)0).equals(Integer.valueOf(((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11360_sig.eval()))).length())) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\n###### `")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11360_sig.eval())), (common.StringCatter)(new common.StringCatter("`"))))); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n#### _")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11362_mod.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_ `")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11361_name.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("`")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11368_signature.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_11358_body.eval())).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclComment)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nIn file: ")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_11359_file.eval()))))))))))); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 81, 9, 105, 19, 2091, 2670\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.doc.core.PtoMarkdown.i_c)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:toMarkdown", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoMarkdown.invoke(children[0]);
		}
	};
}