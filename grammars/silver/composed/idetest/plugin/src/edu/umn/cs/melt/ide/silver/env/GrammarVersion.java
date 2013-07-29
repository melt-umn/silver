package edu.umn.cs.melt.ide.silver.env;

/**
 * A version has format {major}[.{minor}[.{revision}]]
 */
public class GrammarVersion {

	private int major;
	
	private int minor;
	
	private int revision;
	
	/**
	 * @param version in format {major}[.{minor}[.{revision}]]
	 */
	public GrammarVersion(String version){
		if(version==null){
			return;
		}
		
		String[] vers = version.split("\\.");
		if(vers.length>0){
			try {
				major = Integer.parseInt(vers[0]);
			} catch (NumberFormatException e) {
				return;
			}
		}
		if(vers.length>1){
			try {
				minor = Integer.parseInt(vers[1]);
			} catch (NumberFormatException e) {
				return;
			}
		}
		if(vers.length>2){
			try {
				revision = Integer.parseInt(vers[2]);
			} catch (NumberFormatException e) {
				return;
			}
		}
	}
	
	public boolean isNewerThan(GrammarVersion v2){
		if((this.major>v2.major) || 
		   (this.major==v2.major && this.minor>v2.minor) || 	
		   (this.major==v2.major && this.minor==v2.minor && this.revision>v2.revision) 
		){
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		return major + "." + minor + "." + revision;
	}
	
	/*
	public static void main(String[] s){
		List<GrammarVersion> list = new ArrayList<GrammarVersion>();
		
		list.add(new GrammarVersion("0.0.0"));
		list.add(new GrammarVersion("0.0.5"));
		list.add(new GrammarVersion("0.0.16"));
		list.add(new GrammarVersion("0.1.2"));
		list.add(new GrammarVersion("0.2.3"));		
		list.add(new GrammarVersion("0.2.10"));
		list.add(new GrammarVersion("0.2.52"));
		list.add(new GrammarVersion("0.3.0"));	
		list.add(new GrammarVersion("1.1.2"));
		list.add(new GrammarVersion("2.0.3"));
		list.add(new GrammarVersion("2.0.3"));
		list.add(new GrammarVersion("2.0"));
		list.add(new GrammarVersion("3.0"));

		Collections.sort(list, new Comparator<GrammarVersion>(){
			@Override
			public int compare(GrammarVersion v1, GrammarVersion v2) {
				return v1.isNewerThan(v2)?1:0;
			}
		});
		
		System.out.println(list);
	}
	*/
}
