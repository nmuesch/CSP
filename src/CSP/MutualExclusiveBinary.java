package CSP;

import java.util.ArrayList;

public class MutualExclusiveBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public MutualExclusiveBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	@Override
	public boolean isValid(ArrayList<Assignment> assignments) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
