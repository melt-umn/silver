package common;

public interface PatternLazy<S,R> {

	public R eval(final DecoratedNode context, final S scrutinee);

}
