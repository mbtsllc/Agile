package com.mars.web.gov.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;

/**
 * Class for Project to read data from URL
 * @author  tiger
 * @version Demo
 * @date    Jun 23, 2015
 */
public class HttpReader {
	private static Logger logger = LoggerFactory.getLogger(HttpReader.class.getName()) ;
	/**
	 * Get content from URL passed.
	 * @param desiredUrl URL with Http
	 * @return result of URL 
	 * @throws Exception
	 */
	public String getURLInfoByHttp(String desiredUrl)
			throws Exception {
		logger.logMethodBegin("getURLInfoByHttp");
		logger.logInfo("getURLInfoByHttp", 0, String.format("\tCurrentURL:[%s]",desiredUrl));
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			// create the HttpURLConnection
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// just want to do an HTTP GET here
			connection.setRequestMethod("GET");

			// uncomment this if want to write output to this url
			// connection.setDoOutput(true);

			// give it 30 seconds to respond
			connection.setReadTimeout(30 * 1000);
			connection.connect();

			// read the output from the server
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			stringBuilder = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.logError("getURLInfoByHttp", -1, String.format("Exception when read data from URL [%s], with Exceptions:[%s]", desiredUrl,e.getMessage()),e);
			throw e;
		} finally {
			// close the reader; this can throw an exception too, so
			// wrap it in another try/catch block.
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
