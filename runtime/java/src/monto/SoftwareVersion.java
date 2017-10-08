package monto;

public class SoftwareVersion {
	public final String id;
	public final String name;
	public final String vendor;
	public final Integer major;
	public final Integer minor;
	public final Integer patch;

	public SoftwareVersion(String id, String name, String vendor, Integer major, Integer minor, Integer patch) {
		this.id = id;
		this.name = name;
		this.vendor = vendor;
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
}
