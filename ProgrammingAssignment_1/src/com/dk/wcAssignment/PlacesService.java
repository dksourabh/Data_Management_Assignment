package com.dk.wcAssignment;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.w3c.dom.Document;

/**
 * @author saxman
 */
public class PlacesService {

	private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/search/json?";

	// KEY!
	private static final String API_KEY_VALUE = "AIzaSyDS9VcjBcjC-f-HZxq6FZTxiQQ4P5cuF6M";

	public static void main(String[] args) throws Exception {

	}

	public static String createApiUrl(String address) {
		String api = "";
		try {
			api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
					+ "&sensor=true";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return api;
	}
	// This method fetches latitude and longitude using Google maps API.
	public static Double[] getLatLongCoordinates(String address) throws Exception {

		String api = createApiUrl(address);
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(httpConnection.getInputStream());
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expression = xpath.compile("/GeocodeResponse/status");

		expression = xpath.compile("//geometry/location/lat");
		String latitude = (String) expression.evaluate(document, XPathConstants.STRING);
		expression = xpath.compile("//geometry/location/lng");
		String longitude = (String) expression.evaluate(document, XPathConstants.STRING);
		return new Double[] { Double.parseDouble(latitude), Double.parseDouble(longitude) };

	}

	// This method searches nearby hospitals using Google places API.
	public static String searchHospitals(String keyword, double lat, double lng, int radius) throws JSONException {

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(BASE_URL);

			sb.append("location=" + String.valueOf(lat) + "," + String.valueOf(lng));
			sb.append("&radius=" + String.valueOf(radius));
			sb.append("&types=" + URLEncoder.encode(keyword, "utf8"));
			sb.append("&sensor=true&key=" + API_KEY_VALUE);
			// System.out.println(sb.toString());
			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int info;
			char[] buff = new char[1024];
			while ((info = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, info);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		StringBuffer sb = new StringBuffer();
		String[] strArr = jsonResults.toString().split("\"name\" :");
		int count = 0;
		for (String str : strArr) {

			if (count > 0) {
				String[] strArr2 = str.split(",");
				sb.append((strArr2[0]) + "<br>");
			}
			count++;
		}

		return sb.toString();
	}
}