/**
 * 
 */
package CSP;

import java.util.ArrayList;

/**
 * @author COSNUEMER
 *
 */
public class ExclusiveUnary implements IConstraint {

	private Item item;
	private ArrayList<Bag> bags;
	
	public ExclusiveUnary(Item i, ArrayList<Bag> bags) {
		this.item = i;
		this.bags = bags;
	}
	
	@Override
	public boolean isValid(ArrayList<Assignment> assignments) {
		for(Assignment a : assignments) {
			if(a.getItem().equals(item)) {
				return !(bags.contains(a.getBag()));
			}
		}
		return true;
	}
}


