package com.dk.wcAssignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WebServiceClient {
	public static void main(String[] args)
			throws IOException, JSONException, ParseException, ParserConfigurationException, SAXException {

	}

	// This method fetches Doctor names using Better doctor api.
	public String getDoctorsNames(Double latittitude, Double longitude) throws IOException, ParseException {
		String strURL = "https://api.betterdoctor.com/2015-09-22/doctors?location=" + latittitude + "%2C" + longitude
				+ "%2C20&user_location=43.161030%2C-77.610924&skip=0&limit=10&user_key=2ab8ef5b0da418dd470dd6c4a716f2e0";
		URL url = new URL(strURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str;
		StringBuffer stringBuffer = new StringBuffer();
		while ((str = bufferReader.readLine()) != null) {
			stringBuffer.append(str);
			stringBuffer.append("\n");
		}
		String str1 = stringBuffer.toString();
		stringBuffer = new StringBuffer();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(str1);
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray name = (JSONArray) jsonObject.get("data");
		Iterator<?> iterator = name.iterator();
		JSONObject a;
		JSONObject b;
		while (iterator.hasNext()) {
			a = (JSONObject) iterator.next();
			b = (JSONObject) a.get("profile");
			stringBuffer.append(b.get("first_name") + "," + b.get("last_name") + "<br>");

		}
		return stringBuffer.toString();

	}
	// This method fetches Disease information using Better National library of medicine api.
	public String getDiseaseDescription(String disease) throws ParserConfigurationException, SAXException, IOException {
		String strURL1 = "https://wsearch.nlm.nih.gov/ws/query?db=healthTopics&term=title:" + disease;
		System.out.println(strURL1);
		StringBuilder sb = new StringBuilder();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(strURL1);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("document");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			if (temp <= 0) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					//Using replaceAll to remove any HTML tags in XML response.
					sb.append(eElement.getElementsByTagName("content").item(3).getTextContent().replaceAll("\\<[^>]*>",
							"") + "<br>");
					sb.append(eElement.getElementsByTagName("content").item(4).getTextContent().replaceAll("\\<[^>]*>",
							"") + "<br>");
					sb.append(eElement.getElementsByTagName("content").item(5).getTextContent().replaceAll("\\<[^>]*>",
							"") + "<br>");
					sb.append(eElement.getElementsByTagName("content").item(6).getTextContent().replaceAll("\\<[^>]*>",
							"") + "<br>");
					sb.append(eElement.getElementsByTagName("content").item(7).getTextContent().replaceAll("\\<[^>]*>",
							"") + "<br>");

				}
			}
		}

		return sb.toString();
	}

}
