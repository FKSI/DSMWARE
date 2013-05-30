package Server;

import java.util.List;

public class BestBuyAPIConnector extends APIConnector {

	public List<String> getItems (String searchTerm) throws Exception 
	{
		return bestBuyAPI.FindItemBestBuy.findItemBestBuyShopping(searchTerm);
	}

}
