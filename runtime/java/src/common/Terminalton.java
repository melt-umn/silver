package common;


public abstract class Terminalton<T> {
	public abstract T construct(final StringCatter lexeme, final silver.core.NLocation location);
	public abstract String getName();
}
