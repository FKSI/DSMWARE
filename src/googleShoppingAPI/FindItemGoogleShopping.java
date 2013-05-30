package googleShoppingAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FindItemGoogleShopping {

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read); 

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}


	public static List<String> findItemGoogleShopping (String keywords) throws Exception{
		List<String> res = new ArrayList<String>();
	try {
			JSONObject json = new JSONObject(readUrl("https://www.googleapis.com/shopping/search/v1/public/products?key=AIzaSyA60ZmMU9vLguggfj35Wp2onuPfM894TWI&country=US&q=" + keywords.replace(" ", "+") ));

			JSONArray items = json.getJSONArray("items");
			

			for (int i = 0; i < items.length(); i++) {  
				JSONObject item = items.getJSONObject(i);
				JSONObject products = item.getJSONObject("product");
				JSONArray inventories = products.getJSONArray("inventories");
				JSONObject author = products.getJSONObject("author");
				JSONObject prices = (JSONObject) inventories.get(0);
				res.add(author.get("name") + " - " + products.get("title").toString() + " - " +  prices.get("price") + prices.get("currency"));
				

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	return res;
	}



}