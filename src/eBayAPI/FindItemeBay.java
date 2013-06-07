package eBayAPI;

import java.util.List;
import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.SearchItem;

/**
 * 
 * @author Francois KY
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */

public class FindItemeBay {

	/**
	 * Returns the list of string containing the items matching the input of the user.
	 * Each string will contain the outler/seller name, the price and the currency.
	 * 
	 * @param keywords 
	 * @param nresults this value allows a control of number of results the eBayAPI should return.
	 * @return a list of string
	 */
	public List<SearchItem> findItem(String keywords,int nresults){
		
		try {

			ClientConfig config = new ClientConfig();
			
			config.setEndPointAddress("http://svcs.sandbox.ebay.com/services/search/FindingService/v1");
			config.setApplicationId("Eurecom6c-3fdd-4491-82e3-75d7bf1b682");

			FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);

			FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();

			request.setKeywords(keywords);
			
			PaginationInput pi = new PaginationInput();
			pi.setEntriesPerPage(nresults);
			
			request.setPaginationInput(pi);

			FindItemsByKeywordsResponse result = serviceClient.findItemsByKeywords(request);

			System.out.println("Find " + result.getSearchResult().getCount() + " items." );
			List<SearchItem> items = result.getSearchResult().getItem();
	
			return items;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}	
}

