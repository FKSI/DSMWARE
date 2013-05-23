package bestBuyAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FindItemBestBuy {

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


	public static void findItemBestBuyShopping (String keywords) throws Exception{

	try {
			JSONObject json = new JSONObject(readUrl("http://api.remix.bestbuy.com/v1/products(name=" + keywords.replace(" ", "*%20").toString() + "*"+ ")?show=name,regularPrice&format=json&apiKey=ztqu8spnt3b8gfkg6f88m2f9"));

			JSONArray items = json.getJSONArray("products");
			
			for (int i = 0; i < items.length(); i++) {  
				JSONObject item = items.getJSONObject(i);
				/*JSONObject products = item.getJSONObject("name");
					JSONArray inventories = products.getJSONArray("inventories");
				JSONObject author = products.getJSONObject("author");
				JSONObject prices = (JSONObject) inventories.get(0);
				System.out.println(author.get("name") + " - " + products.get("title").toString() + " - " +  prices.get("price") + prices.get("currency"));*/
				
				System.out.println("BestBuy" + " - " + item.get("name") + " - " + item.get("regularPrice") + "USD");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}



}