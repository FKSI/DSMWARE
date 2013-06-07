package googleShoppingAPI;

import java.util.List;

/**
 * 
 * @author Francois KY
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */

public class FindItemGoogleShoppingMain {

	/**
	 * Print the result of a query through the Google Shopping API. This class is useful to test the Google Shopping API alone.
	 * In case of production this class shouldn't be included.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		List<String> res = FindItemGoogleShopping.findItemGoogleShopping("Samsung Galaxy 2");

		for(String s : res)
		{
			System.out.println(s);
		}
	}

}
