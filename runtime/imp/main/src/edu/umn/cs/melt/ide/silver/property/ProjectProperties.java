package edu.umn.cs.melt.ide.silver.property;

import ide.NIdeProperty;
import ide.PmakeIdeProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import common.ConsCell;

/**
 * Used to read/write properties from/to project.properties file.
 * <p>
 * Property is saved in format:
 * <pre>
 *   name/type=value
 * </pre>
 * The methods provided by this class allows the plug-in developer to update
 * the properties file. If, however, a user modifies the property file 
 * externally, the change made through this class may be overridden. 
 */
public class ProjectProperties {

	private String projectPath;
	
	/** Raw property file */
	private Properties properties;
	
	/** Parsed property file */
	private Map<String, Property> map;
	
	/** The signature of property file when last read */
	private Signature signature;
	
	/** null if not yet computed, otherwise the Silver value of these properties */
	private ConsCell cachedSilverValue;
	

	public static ProjectProperties getPropertyPersister(String projectPath){
		return new ProjectProperties(projectPath);
	}
	
	private ProjectProperties(String projectPath){
		this.projectPath = projectPath;
	}
	
	/**
	 * Get property.
	 * 
	 * @param key	the property name.
	 * @return
	 */
	public Property get(String key){
		init();
		
		return map.get(key);
	}
	
	/**
	 * Set and persist property.
	 * <p>
	 * The property must be already defined in the property file. This method
	 * is not used to add new property for the project.
	 * 
	 * @param property
	 */
	public void set(Property property){
		init();
		
		String key = property.getName() + Property.SPLITTER + Property.Type.getString(property.getType());
		
		properties.setProperty(key, property.getSValue());
		
		persist();
	}
	
	/**
	 * Get all the properties.
	 * 
	 * @return
	 */
	public Set<Entry<String, Property>> getAll() {
		init();
		
		return map.entrySet();
	}
	
	/**
	 *  Get property file 
	 */
	private File getPropertiesFile() {
		return new File(projectPath, "project.properties");
	}
	
	private void init(){		
		File configFile = getPropertiesFile();
		
		if(properties!=null){
			//Check the signature
			Signature newSig = createSignature(configFile);
			
			if(newSig.equals(signature)){
				return;
			} else {
				signature = newSig;
			}
		}

		// invalidate the cached value
		cachedSilverValue = null;
		
		//Not initiated or file has been changed
		FileInputStream fis = null;
		try {			
			properties = new Properties();
			fis = new FileInputStream(configFile);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			//
		} catch (IOException e) {
			//
		} finally {
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					//Ignore
				}
			}
		}
		
		map = new HashMap<String, Property>();
		
		Set<Entry<Object, Object>> set = properties.entrySet();
		
		for(Entry<Object, Object> entry:set){
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			Property prop = Property.parseProperty(key, value);
			
			map.put(prop.getName(), prop);
			
			//System.out.println("Property:" + prop.getName() + ", " + prop.getSValue());
		}
	}
	
	private void persist(){
		File configFile = getPropertiesFile();
		if(configFile==null){
			return;
		}
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(configFile);
			properties.store(fos, null);
		} catch (FileNotFoundException e) {
			//Ignore
		} catch (IOException e) {
			//Ignore
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					//Ignore
				}
			}
		}
	}
	
	private class Signature {
		
		long size;
		
		long modified;
		
		Signature(long size, long modified) {
			this.size = size;
			this.modified = modified;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			Signature other = (Signature)obj;
			if (modified != other.modified)
				return false;
			if (size != other.size)
				return false;
			return true;
		}
		
	}
	
	private Signature createSignature(File f){
		return new Signature(f.length(), f.lastModified());
	}
	
	/**
	 * Converts this properties list to the silver type.
	 * 
	 * @return Value of Silver type [IdeProperty] (java type ConsCell of NIdeProperty)
	 */
	public ConsCell serializeToSilverType() {
		if(cachedSilverValue != null)
			return cachedSilverValue;
		
		Set<Entry<String, Property>> set = this.getAll();
		ConsCell result = ConsCell.nil;
		for(Entry<String, Property> entry : set) {
			final Property prop = entry.getValue();
			final NIdeProperty h = new PmakeIdeProperty(
					new common.StringCatter(entry.getKey()), // == prop.getName()
					new common.StringCatter(prop.getType().name()),
					new common.StringCatter(prop.getSValue())
					);
			result = new ConsCell(h, result);
		}
		return result;
	}
}


