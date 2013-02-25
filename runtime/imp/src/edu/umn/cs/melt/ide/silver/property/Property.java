package edu.umn.cs.melt.ide.silver.property;

/**
 * A class corresponding to the property entry in project.properties file.
 * <p>
 * The entry has the following format:
 * <pre>
 *   name/type=value
 */
public class Property {

	public static String SPLITTER = "/";
	
	private String name;
	
	private Type type;
	
	private String sValue;
	
	private int iValue;
	
	public static enum Type {
		STRING, PATH, URL, INTEGER;
		
		static Type getType(String name){
			if("string".equals(name)){
				return STRING;
			} else if("path".equals(name)){
				return PATH;
			} else if("url".equals(name)){
				return URL;
			} else {//if("integer".equals(name))
				return INTEGER;
			}
		}
		
		static String getString(Type type){
			switch(type){
			case STRING:
				return "string";
			case PATH:
				return "path";
			case URL:
				return "url";
			case INTEGER:
				return "integer";
			}
			
			return "string";
		}
	}
	
	private Property(String name, Type type, String sValue, int iValue) {
		this.name = name;
		this.type = type;
		this.sValue = sValue;
		this.iValue = iValue;
	}
	
	/**
	 * Parse property with format:
     * <pre>
     *   name:type=value
     * where type should be a legal value listed in Property.Type enumeration.
     * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static Property parseProperty(String key, String value){
		//Split name:type into two parts
		String[] strs = key.split(SPLITTER);

		if(strs.length==2){
			Type type = Type.getType(strs[1]);
			switch(type){
			case STRING:
				return makeStringProperty(strs[0], value);
			case PATH:
				return makePathProperty(strs[0], value);
			case URL:
				return makeURLProperty(strs[0], value);
			case INTEGER:
				return makeIntegerProperty(strs[0], Integer.parseInt(value));
			}
		}

		//Unknown type, default to String
		return makeStringProperty(key, value);
	}
	
	public static Property makePathProperty(String name, String path){
		return new Property(name, Type.PATH, path, 0);
	}
	
	public static Property makeURLProperty(String name, String url){
		return new Property(name, Type.URL, url, 0);
	}
	
	public static Property makeStringProperty(String name, String str){
		return new Property(name, Type.STRING, str, 0);
	}
	
	public static Property makeIntegerProperty(String name, int val){
		return new Property(name, Type.INTEGER, "", val);
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public String getSValue() {
		if(type!=Type.INTEGER){
			return sValue;
		} else {
			return String.valueOf(iValue);
		}
	}

	public void setSValue(String sValue) {
		this.sValue = sValue;
	}

	public int getIValue() {
		return iValue;
	}

	public void setIValue(int iValue) {
		this.iValue = iValue;
	}
	
}

