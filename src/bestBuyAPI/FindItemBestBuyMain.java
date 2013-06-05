package bestBuyAPI;

import java.util.List;

/**
 * 
 * @author Francois KY
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */
public class FindItemBestBuyMain {

	
	/**
	 * Print the result of a query through the BestBuy API. This class is useful to test the BestBuy API alone.
	 * In case of production this class shouldn't be included.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		List<String> res = FindItemBestBuy.findItemBestBuyShopping("Samsung Galaxy S III 32Gb");
		for (String r : res ){
			System.out.println(r);
		}
	}

}
