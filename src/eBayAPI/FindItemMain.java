package eBayAPI;

import java.util.List;

import com.ebay.services.finding.SearchItem;

/**
 * 
 * @author Francois KY
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */

public class FindItemMain {

	/**
	 * Print the result of a query through the eBay API. This class is useful to test the eBay API alone.
	 * In case of production this class shouldn't be included.
	 * @throws Exception
	 */
	public static void main(String[] args) {
		
		FindItem fi = new FindItem();
		List<SearchItem> lisres = fi.findItem(args[0],Integer.valueOf(args[1]));
		for(SearchItem item:lisres){
					
			System.out.println("eBay" +  " - "  + item.getTitle() +  " - "  + item.getSellingStatus().getCurrentPrice().getValue() + item.getSellingStatus().getCurrentPrice().getCurrencyId());
		}
	}

}
