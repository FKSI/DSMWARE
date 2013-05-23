package eBayAPI;

import java.util.List;

import com.ebay.services.finding.SearchItem;

public class FindItemMain {

	public static void main(String[] args) {
		
		FindItem fi = new FindItem();
		List<SearchItem> lisres = fi.findItem(args[0],Integer.valueOf(args[1]));
		for(SearchItem item:lisres){
			
					
			System.out.println("eBay" +  " - "  + item.getTitle() +  " - "  + item.getSellingStatus().getCurrentPrice().getValue() + item.getSellingStatus().getCurrentPrice().getCurrencyId());
		}
	}

}
