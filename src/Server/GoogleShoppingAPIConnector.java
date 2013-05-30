package Server;

import java.util.List;

import googleShoppingAPI.FindItemGoogleShopping;

public class GoogleShoppingAPIConnector extends APIConnector {
	public List<String> getItems (String searchTerm) throws Exception
	{
		return FindItemGoogleShopping.findItemGoogleShopping(searchTerm);
	}

}
