package core;

public class Init{

	private static boolean init = false;
	private static boolean postInit = false;

	public static void init(){
		if(core.Init.init) return;

		core.Init.setupInheritedAttributes();

		core.Init.init = true;


		core.Init.initAspectAttributeDefinitions();
		core.Init.initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(core.Init.postInit) return;

		core.Init.postInit = true;



		common.Decorator.applyDecorators(core.NStringList.decorators, PemptyString.class);
		common.Decorator.applyDecorators(core.NStringList.decorators, Pempty_string.class);
		common.Decorator.applyDecorators(core.NStringList.decorators, PconsString.class);
		common.Decorator.applyDecorators(core.NStringList.decorators, Pcons_string.class);
		common.Decorator.applyDecorators(core.NIOBoolean.decorators, PioBoolean.class);
		common.Decorator.applyDecorators(core.NIOString.decorators, PioString.class);
		common.Decorator.applyDecorators(core.NIOInteger.decorators, PioInteger.class);
		common.Decorator.applyDecorators(core.NIOStringList.decorators, PioStringList.class);
		common.Decorator.applyDecorators(core.NAnyTypeList.decorators, Pinternal_nil_AnyTypeList.class);
		common.Decorator.applyDecorators(core.NAnyTypeList.decorators, Pinternal_cons_AnyTypeList.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		//PRODUCTION emptyString top::StringList ::= 
		//top.patProdName = "core:emptyString";
		core.PemptyString.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:emptyString"));
			}
		});
		//top.patChildList = [AnyType];
		core.PemptyString.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)new core.Pnil_AnyTypeList().doReturn());
			}
		});
		//forwards to empty_string()
		core.PemptyString.forward = new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new core.Pempty_string());
			}
		};
		//PRODUCTION empty_string top::StringList ::= 
		//top.patProdName = "core:empty_string";
		core.Pempty_string.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:empty_string"));
			}
		});
		//top.patChildList = [AnyType];
		core.Pempty_string.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)new core.Pnil_AnyTypeList().doReturn());
			}
		});
		//top.empty = true;
		core.Pempty_string.synthesizedAttributes.put("core:empty", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return true;
			}
		});
		//top.sh = error("Head not defined on an empty core string list");
		core.Pempty_string.synthesizedAttributes.put("core:sh", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Head not defined on an empty core string list"))));
			}
		});
		//top.st = error("Tail not defined on an empty core string list");
		core.Pempty_string.synthesizedAttributes.put("core:st", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Tail not defined on an empty core string list"))));
			}
		});
		//PRODUCTION consString top::StringList ::= h::String t::StringList
		//top.patProdName = "core:consString";
		core.PconsString.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:consString"));
			}
		});
		//top.patChildList = cons (cast ( h, AnyType ), [ cast ( t, AnyType ) ]);
		core.PconsString.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((new common.AnyType(((StringBuffer)context.child(core.PconsString.i_h))))); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((new common.AnyType((((common.DecoratedNode)context.child(core.PconsString.i_t)).undecorate())))); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pnil_AnyTypeList().doReturn()); } })).doReturn()); } })).doReturn());
			}
		});
		//forwards to cons_string(h, t)
		core.PconsString.forward = new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new core.Pcons_string(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((StringBuffer)context.child(core.PconsString.i_h)); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return (((common.DecoratedNode)context.child(core.PconsString.i_t)).undecorate()); } })));
			}
		};
		//PRODUCTION cons_string top::StringList ::= h::String t::StringList
		//top.patProdName = "core:cons_string";
		core.Pcons_string.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:cons_string"));
			}
		});
		//top.patChildList = cons (cast ( h, AnyType ), [ cast ( t, AnyType ) ]);
		core.Pcons_string.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((new common.AnyType(((StringBuffer)context.child(core.Pcons_string.i_h))))); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((new common.AnyType((((common.DecoratedNode)context.child(core.Pcons_string.i_t)).undecorate())))); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pnil_AnyTypeList().doReturn()); } })).doReturn()); } })).doReturn());
			}
		});
		//top.empty = false;
		core.Pcons_string.synthesizedAttributes.put("core:empty", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return false;
			}
		});
		//top.sh = h;
		core.Pcons_string.synthesizedAttributes.put("core:sh", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((StringBuffer)context.child(core.Pcons_string.i_h));
			}
		});
		//top.st = t'';
		core.Pcons_string.synthesizedAttributes.put("core:st", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (((common.DecoratedNode)context.child(core.Pcons_string.i_t)).undecorate());
			}
		});
		//PRODUCTION ioBoolean top::IOBoolean ::= i::IO b::Boolean
		//top.patProdName = "core:ioBoolean";
		core.PioBoolean.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:ioBoolean"));
			}
		});
		//top.patChildList = error("Error: cannot pattern match on production or IO types.\n");
		core.PioBoolean.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Error: cannot pattern match on production or IO types.\n"))));
			}
		});
		//top.io = i;
		core.PioBoolean.synthesizedAttributes.put("core:io", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((Object)context.child(core.PioBoolean.i_i));
			}
		});
		//top.bValue = b;
		core.PioBoolean.synthesizedAttributes.put("core:bValue", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((Boolean)context.child(core.PioBoolean.i_b));
			}
		});
		//PRODUCTION ioString top::IOString ::= i::IO s::String
		//top.patProdName = "core:ioString";
		core.PioString.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:ioString"));
			}
		});
		//top.patChildList = error("Error: cannot pattern match on production or IO types.\n");
		core.PioString.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Error: cannot pattern match on production or IO types.\n"))));
			}
		});
		//top.io = i;
		core.PioString.synthesizedAttributes.put("core:io", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((Object)context.child(core.PioString.i_i));
			}
		});
		//top.sValue = s;
		core.PioString.synthesizedAttributes.put("core:sValue", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((StringBuffer)context.child(core.PioString.i_s));
			}
		});
		//PRODUCTION ioInteger top::IOInteger ::= i::IO int::Integer
		//top.patProdName = "core:ioInteger";
		core.PioInteger.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:ioInteger"));
			}
		});
		//top.patChildList = error("Error: cannot pattern match on production or IO types.\n");
		core.PioInteger.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Error: cannot pattern match on production or IO types.\n"))));
			}
		});
		//top.io = i;
		core.PioInteger.synthesizedAttributes.put("core:io", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((Object)context.child(core.PioInteger.i_i));
			}
		});
		//top.iValue = int;
		core.PioInteger.synthesizedAttributes.put("core:iValue", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((Integer)context.child(core.PioInteger.i_int));
			}
		});
		//PRODUCTION ioStringList top::IOStringList ::= i::IO sl::StringList
		//top.patProdName = "core:ioStringList";
		core.PioStringList.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:ioStringList"));
			}
		});
		//top.patChildList = error("Error: cannot pattern match on production or IO types.\n");
		core.PioStringList.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Error: cannot pattern match on production or IO types.\n"))));
			}
		});
		//top.io = i;
		core.PioStringList.synthesizedAttributes.put("core:io", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((Object)context.child(core.PioStringList.i_i));
			}
		});
		//top.stringList = sl;
		core.PioStringList.synthesizedAttributes.put("core:stringList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (((common.DecoratedNode)context.child(core.PioStringList.i_sl)).undecorate());
			}
		});
		//FUNCTION nil_AnyTypeList Decorated AnyTypeList ::= 
		//return decorate internal_nil_AnyTypeList() with {};
		core.Pnil_AnyTypeList.synthesizedAttributes.put("__return", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((new core.Pinternal_nil_AnyTypeList()).decorate(context, common.Util.populateMap(new String[]{}, new common.Lazy[]{})));
			}
		});
		//PRODUCTION internal_nil_AnyTypeList l::AnyTypeList ::= 
		//l.patProdName = "core:internal_nil_AnyTypeList";
		core.Pinternal_nil_AnyTypeList.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:internal_nil_AnyTypeList"));
			}
		});
		//l.patChildList = [AnyType];
		core.Pinternal_nil_AnyTypeList.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)new core.Pnil_AnyTypeList().doReturn());
			}
		});
		//l.empty_AnyTypeList = true;
		core.Pinternal_nil_AnyTypeList.synthesizedAttributes.put("core:empty_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return true;
			}
		});
		//l.length_AnyTypeList = 0;
		core.Pinternal_nil_AnyTypeList.synthesizedAttributes.put("core:length_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return new Integer(0);
			}
		});
		//l.head_AnyTypeList = error("Accessing head on value of type [ ] not allowed.\n");
		core.Pinternal_nil_AnyTypeList.synthesizedAttributes.put("core:head_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Accessing head on value of type [ ] not allowed.\n"))));
			}
		});
		//l.tail_AnyTypeList = error("Accessing tail on value of type [ ] not allowed.\n");
		core.Pinternal_nil_AnyTypeList.synthesizedAttributes.put("core:tail_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (common.Util.error((new StringBuffer("Accessing tail on value of type [ ] not allowed.\n"))));
			}
		});
		//FUNCTION cons_AnyTypeList Decorated AnyTypeList ::= h::AnyType t::Decorated AnyTypeList
		//return decorate internal_cons_AnyTypeList(h, t) with {};
		core.Pcons_AnyTypeList.synthesizedAttributes.put("__return", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((new core.Pinternal_cons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.AnyType)context.child(core.Pcons_AnyTypeList.i_h)); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)context.child(core.Pcons_AnyTypeList.i_t)); } }))).decorate(context, common.Util.populateMap(new String[]{}, new common.Lazy[]{})));
			}
		});
		//PRODUCTION internal_cons_AnyTypeList l::AnyTypeList ::= h::AnyType t::Decorated AnyTypeList
		//l.patProdName = "core:internal_cons_AnyTypeList";
		core.Pinternal_cons_AnyTypeList.synthesizedAttributes.put("core:patProdName", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new StringBuffer("core:internal_cons_AnyTypeList"));
			}
		});
		//l.patChildList = cons (h, [ cast ( t, AnyType ) ]);
		core.Pinternal_cons_AnyTypeList.synthesizedAttributes.put("core:patChildList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return (((common.AnyType)context.child(core.Pinternal_cons_AnyTypeList.i_h))); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((new common.AnyType(((common.DecoratedNode)context.child(core.Pinternal_cons_AnyTypeList.i_t))))); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pnil_AnyTypeList().doReturn()); } })).doReturn()); } })).doReturn());
			}
		});
		//l.empty_AnyTypeList = false;
		core.Pinternal_cons_AnyTypeList.synthesizedAttributes.put("core:empty_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return false;
			}
		});
		//l.length_AnyTypeList = 1 + t.length_AnyTypeList;
		core.Pinternal_cons_AnyTypeList.synthesizedAttributes.put("core:length_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (new Integer(new Integer(1) + ((Integer)((common.DecoratedNode)context.child(core.Pinternal_cons_AnyTypeList.i_t)).synthesized("core:length_AnyTypeList"))));
			}
		});
		//l.head_AnyTypeList = h;
		core.Pinternal_cons_AnyTypeList.synthesizedAttributes.put("core:head_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.AnyType)context.child(core.Pinternal_cons_AnyTypeList.i_h));
			}
		});
		//l.tail_AnyTypeList = t;
		core.Pinternal_cons_AnyTypeList.synthesizedAttributes.put("core:tail_AnyTypeList", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return ((common.DecoratedNode)context.child(core.Pinternal_cons_AnyTypeList.i_t));
			}
		});
		//FUNCTION append_AnyTypeList Decorated AnyTypeList ::= l1::Decorated AnyTypeList l2::Decorated AnyTypeList
		//return if l1.empty_AnyTypeList then l2 else cons_AnyTypeList(l1.head_AnyTypeList, append_AnyTypeList(l1.tail_AnyTypeList, l2));
		core.Pappend_AnyTypeList.synthesizedAttributes.put("__return", new common.Lazy(){
			public Object eval(common.DecoratedNode context) {
				return (((Boolean)((common.DecoratedNode)context.child(core.Pappend_AnyTypeList.i_l1)).synthesized("core:empty_AnyTypeList")) ? ((common.DecoratedNode)context.child(core.Pappend_AnyTypeList.i_l2)) : ((common.DecoratedNode)new core.Pcons_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.AnyType)((common.DecoratedNode)context.child(core.Pappend_AnyTypeList.i_l1)).synthesized("core:head_AnyTypeList")); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)new core.Pappend_AnyTypeList(new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.child(core.Pappend_AnyTypeList.i_l1)).synthesized("core:tail_AnyTypeList")); } }), new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return ((common.DecoratedNode)context.child(core.Pappend_AnyTypeList.i_l2)); } })).doReturn()); } })).doReturn()));
			}
		});
	}

	private static void initAspectAttributeDefinitions(){
		core.NMain.occurs.add("core:patProdName");
		core.NMain.occurs.add("core:patChildList");
		core.NMain.occurs.add("core:ioIn");
		core.NMain.occurs.add("core:ioOut");
		core.NStringList.occurs.add("core:patProdName");
		core.NStringList.occurs.add("core:patChildList");
		core.NStringList.occurs.add("core:sh");
		core.NStringList.occurs.add("core:st");
		core.NStringList.occurs.add("core:empty");
		core.NIOBoolean.occurs.add("core:patProdName");
		core.NIOBoolean.occurs.add("core:patChildList");
		core.NIOBoolean.occurs.add("core:io");
		core.NIOBoolean.occurs.add("core:bValue");
		core.NIOInteger.occurs.add("core:patProdName");
		core.NIOInteger.occurs.add("core:patChildList");
		core.NIOInteger.occurs.add("core:io");
		core.NIOInteger.occurs.add("core:iValue");
		core.NIOString.occurs.add("core:patProdName");
		core.NIOString.occurs.add("core:patChildList");
		core.NIOString.occurs.add("core:io");
		core.NIOString.occurs.add("core:sValue");
		core.NIOStringList.occurs.add("core:patProdName");
		core.NIOStringList.occurs.add("core:patChildList");
		core.NIOStringList.occurs.add("core:io");
		core.NIOStringList.occurs.add("core:stringList");
		core.NAnyTypeList.occurs.add("core:patProdName");
		core.NAnyTypeList.occurs.add("core:patChildList");
		core.NAnyTypeList.occurs.add("core:empty_AnyTypeList");
		core.NAnyTypeList.occurs.add("core:head_AnyTypeList");
		core.NAnyTypeList.occurs.add("core:tail_AnyTypeList");
		core.NAnyTypeList.occurs.add("core:length_AnyTypeList");
	}
}
