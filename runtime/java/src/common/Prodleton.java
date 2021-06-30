package common;


public abstract class Prodleton<T> {
	public abstract T reify(
        final silver.core.NAST origAST,
        final common.ConsCell rules,
        final common.TypeRep resultType,
        final silver.core.NAST[] childASTs,
        final String[] annotationNames,
        final silver.core.NAST[] annotationASTs);

	public abstract String getProdName();
	public abstract String getNTName();

    public abstract String[] getOccursInh();
    public abstract String[] getChildTypes();
    public abstract Lazy[][] getChildInheritedAttributes();
}
