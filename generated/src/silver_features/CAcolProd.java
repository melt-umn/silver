package silver_features;

public class CAcolProd extends common.CollectionAttribute {

	public CAcolProd(final int index) {
		super(index);
	}

	public Object eval(common.DecoratedNode context) {
		silver_features.NColNT result = (silver_features.NColNT)this.getBase().eval(context);
		for(int i = 0; i < this.getPieces().size(); i++){
			result = new silver_features.PcolNode(result, (silver_features.NColNT)this.getPieces().get(i).eval(context));
		}
		return result;
	}

}
