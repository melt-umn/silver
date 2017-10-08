package monto;

public class ProtocolVersion {
	public final int major;
	public final int minor;
	public final int patch;

	public ProtocolVersion(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
}
