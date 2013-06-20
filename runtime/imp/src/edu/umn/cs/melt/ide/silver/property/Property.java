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

	private boolean required; //not required by default
	
	private String display;
	
	private String defaultVal;
	
	private String sValue;
	
	private int iValue;
	
	/**
	 * Type of property. Mainly determining the behavior of validation.
	 */
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
		this(name, type, sValue, iValue, "", false, "");
	}
	
	private Property(
		String name, Type type, String sValue, int iValue,
		String defaultVal, boolean required, String display) {
		this.name = name;
		this.type = type;
		this.sValue = sValue;
		this.iValue = iValue;
		this.defaultVal = defaultVal;
		this.required = required;
		this.display = display;
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

	public static Property makePathProperty(String name, String path, 
		String defaultVal, String display, boolean required){
		return new Property(name, Type.PATH, path, 0, defaultVal, required, display);
	}
	
	public static Property makeURLProperty(String name, String url, 
	String defaultVal, String display, boolean required){
		return new Property(name, Type.URL, url, 0, defaultVal, required, display);
	}
	
	public static Property makeStringProperty(String name, String str, 
	String defaultVal, String display, boolean required){
		return new Property(name, Type.STRING, str, 0, defaultVal, required, display);
	}
	
	public static Property makeIntegerProperty(String name, int val, 
	String defaultVal, String display, boolean required){
		return new Property(name, Type.INTEGER, "", val, defaultVal, required, display);
	}
	
	public String getName() {
		return name;
	}

	/**
	 * The type of this property, see 
	 * {@link edu.umn.cs.melt.ide.silver.property.Property.Type Type}.
	 * @return
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Get the value in form of String. Will convert to String if it's not
	 * string-typed value.
	 */
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

	/**
	 * Get the value in form of integer. Return always 0 if it's not an 
	 * integer-convertible value.
	 */
	public int getIValue() {
		return iValue;
	}

	public void setIValue(int iValue) {
		this.iValue = iValue;
	}
	
	/**
	 * Whether this property is required. Used by validation.
	 * @return true if this property is required, i.e. must be non-empty.
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * The name of this property to be displayed in UI.
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * The defaultValue of this property. Used also for non-string property.
	 */
	public String getDefault() {
		return defaultVal;
	}
	
	/**
	 * Reset to default value
	 */
	public void reset(){
		if(type!=Type.INTEGER){
			setSValue(defaultVal);
		} else {
			try {
				setIValue(Integer.parseInt(defaultVal));
			} catch (NumberFormatException e) {
				//Shouldn't happen. Ignored.
			}
		}
	}
}
