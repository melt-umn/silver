package monto;

/**
 * A type for a pair. Why is this not a builtin?
 */
public class Pair<A, B> {
	public final A first;
	public final B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
}
