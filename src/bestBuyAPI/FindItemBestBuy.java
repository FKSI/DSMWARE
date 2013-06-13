package bestBuyAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Francois KY
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */

public class FindItemBestBuy {

	/**
	 * Returns a string containing the result of the HTTP request given a specified URL.
	 * The urlString argument should be string.
	 * 
	 * @param urlString
	 * @throws Exception
	 */
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


	/**
	 * Returns a given list of string containing the name and the price of each find items.
	 * The outlet name is hardcoded and the currency as well.
	 * This method is fitted JSON parser for BestBuy API.
	 * 
	 * 
	 * @param keywords string matching the user's input
	 * @return a list of string as the result of Best Buy API lookup
	 * @throws Exception
	 */
	public static List<String> findItemBestBuyShopping (String keywords) throws Exception{
		List<String> res = new ArrayList<String>();
		try {
			JSONObject json = new JSONObject(readUrl("http://api.remix.bestbuy.com/v1/products(name=" + keywords.replace(" ", "*%20").toString() + "*"+ ")?show=name,regularPrice&format=json&apiKey=ztqu8spnt3b8gfkg6f88m2f9"));
			JSONArray items = json.getJSONArray("products");

			for (int i = 0; i < items.length(); i++) {  
				JSONObject item = items.getJSONObject(i);
				res.add("BestBuy" + " - " + item.get("name") + " - " + item.get("regularPrice") + " - USD");
			}
		} catch (JSONException e) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.err.println(dateFormat.format(cal.getTime()) + "Something went wrong with the JSON response from BestBuy API.");
		}
		return res;
	}



}