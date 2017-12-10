package silver_features;

public class CAcolAnd extends common.CollectionAttribute {

	public CAcolAnd(final int index) {
		super(index);
	}

	public Object eval(common.DecoratedNode context) {
		Boolean result = (Boolean)this.getBase().eval(context);
		for(int i = 0; i < this.getPieces().size(); i++){
			result = (result && (Boolean)this.getPieces().get(i).eval(context));
		}
		return result;
	}

}
