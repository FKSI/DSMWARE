package Server;

import java.util.ArrayList;
import java.util.List;

import com.ebay.services.finding.SearchItem;

import eBayAPI.FindItem;

public class eBayAPIConnector extends APIConnector {
	public List<String> getItems (String searchTerm) 
	{
		FindItem fi = new FindItem();
		List<SearchItem> lisres = fi.findItem(searchTerm,25);
		List<String> results = new ArrayList<String>();
		
		for(SearchItem item:lisres){
			results.add(item.getTitle() +  " - "  + item.getSellingStatus().getCurrentPrice().getValue() + item.getSellingStatus().getCurrentPrice().getCurrencyId());
		}
		return results;
	}

}
