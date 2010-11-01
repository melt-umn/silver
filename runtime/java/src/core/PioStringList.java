package core;

public class PioStringList extends core.NIOStringList {

	public static final int i_i = 0;
	public static final int i_sl = 1;

	public static final Class<?> childTypes[] = {Object.class, core.NStringList.class};

	public static common.Lazy forward;
	public static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();

	public static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();

	static{
	PioStringList.inheritedAttributes.put(i_i, new java.util.TreeMap<String, common.Lazy>());
	PioStringList.inheritedAttributes.put(i_sl, new java.util.TreeMap<String, common.Lazy>());
	}

	public PioStringList(Object c_i, Object c_sl){
		super(2);
		this.children[i_i] = c_i;
		this.children[i_sl] = c_sl;

	}

	@Override
	public common.Lazy getSynthesized(String name) {
		return synthesizedAttributes.get(name);
	}

	@Override
	public java.util.Map<String, common.Lazy> getDefinedInheritedAttributes(Object key) {
		return inheritedAttributes.get(key);
	}

	@Override
	public common.Lazy getForward() {
		return forward;
	}

	@Override
	public common.Lazy getForwardInh(String name) {
		return forwardAttributes.get(name);
	}

	@Override
	public common.Lazy getLocal(String name) {
		return localAttributes.get(name);
	}

	@Override
	public String getName() {
		return "core:ioStringList";
	}

}
