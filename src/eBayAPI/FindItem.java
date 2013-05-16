package eBayAPI;

import java.util.List;
import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.SearchItem;


public class FindItem {

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

