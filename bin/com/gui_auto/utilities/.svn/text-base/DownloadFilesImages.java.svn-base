package com.gui_auto.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class DownloadFilesImages 
{
	private static final Logger LOG = Logger.getLogger(DownloadFilesImages.class);
	private WebDriver driver;
	private String localDownloadPath = "InputTestData/TempDownload";
	private boolean mimicWebDriverCookieState = true;
	private boolean followRedirects = true;
	private int httpStatusOfLastDownloadAttempt = 0;

	public DownloadFilesImages(WebDriver driverObject) {
		this.driver = driverObject;
	}

	/**
	 * Specify if the FileDownloader class should follow redirects when trying to download a file
	 *
	 * @param value
	 */
	public void followRedirectsWhenDownloading(boolean value) {
		this.followRedirects = value;
	}

	/**
	 * Get the current location that files will be downloaded to.
	 *
	 * @return The filepath that the file will be downloaded to.
	 */
	public String localDownloadPath() {
		return this.localDownloadPath;
	}

	/**
	 * Set the path that files will be downloaded to.
	 *
	 * @param filePath The filepath that the file will be downloaded to.
	 */
	public void localDownloadPath(String filePath) {
		this.localDownloadPath = filePath;
	}

	/**
	 * Download the file specified in the href attribute of a WebElement
	 *
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public String downloadFile(WebElement element, String fileName, String fileType) throws Exception {
		return downloader(element, "href", fileName, fileType);
	}

	/**
	 * Download the image specified in the src attribute of a WebElement
	 *
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public String downloadImage(WebElement element, String fileName) throws Exception {
		return downloader(element, "src", fileName, "jpeg");
	}

	/**
	 * Gets the HTTP status code of the last download file attempt
	 *
	 * @return
	 */
	public int getHTTPStatusOfLastDownloadAttempt() {
		return this.httpStatusOfLastDownloadAttempt;
	}
	
	/**
     * Mimic the cookie state of WebDriver (Defaults to true)
     * This will enable you to access files that are only available when logged in.
     * If set to false the connection will be made as an anonymouse user
     *
     * @param value
     */
    public void mimicWebDriverCookieState(boolean value) {
        this.mimicWebDriverCookieState = value;
    }
 
    /**
     * Load in all the cookies WebDriver currently knows about so that we can mimic the browser cookie state
     *
     * @param seleniumCookieSet
     * @return
     */
    private BasicCookieStore mimicCookieState(Set<Cookie> seleniumCookieSet) {
        BasicCookieStore mimicWebDriverCookieStore = new BasicCookieStore();
        for (Cookie seleniumCookie : seleniumCookieSet) {
            BasicClientCookie duplicateCookie = new BasicClientCookie(seleniumCookie.getName(), seleniumCookie.getValue());
            duplicateCookie.setDomain(seleniumCookie.getDomain());
            duplicateCookie.setSecure(seleniumCookie.isSecure());
            duplicateCookie.setExpiryDate(seleniumCookie.getExpiry());
            duplicateCookie.setPath(seleniumCookie.getPath());
            mimicWebDriverCookieStore.addCookie(duplicateCookie);
        }
 
        return mimicWebDriverCookieStore;
    }

	/**
	 * Perform the file/image download.
	 *
	 * @param element
	 * @param attribute
	 * @return
	 * @throws IOException
	 * @throws NullPointerException
	 */
	@SuppressWarnings("deprecation")
	private String downloader(WebElement element, String attribute, String fileName, String fileType) throws IOException, NullPointerException, URISyntaxException {
		String fileToDownloadLocation = element.getAttribute(attribute);
		if (fileToDownloadLocation.trim().equals("")) throw new NullPointerException("The element you have specified does not link to anything!");
		
		File downloadedFile = new File(this.localDownloadPath +"\\"+ fileName +"."+ fileType);
		if (downloadedFile.canWrite() == false) downloadedFile.setWritable(true);

		HttpClient client = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();
		
		LOG.info("Mimic WebDriver cookie state: " + this.mimicWebDriverCookieState);
        if (this.mimicWebDriverCookieState) {
            localContext.setAttribute(ClientContext.COOKIE_STORE, mimicCookieState(this.driver.manage().getCookies()));
        }
		
		URL fileToDownload = new URL(fileToDownloadLocation);
		HttpGet httpget = new HttpGet(fileToDownload.toURI());
		HttpParams httpRequestParameters = httpget.getParams();
		httpRequestParameters.setParameter(ClientPNames.HANDLE_REDIRECTS, this.followRedirects);
		httpget.setParams(httpRequestParameters);

		System.out.println("Sending GET request for: " + httpget.getURI());				
		HttpResponse response = client.execute(httpget, localContext);
		this.httpStatusOfLastDownloadAttempt = response.getStatusLine().getStatusCode();
		System.out.println("HTTP GET request status: " + this.httpStatusOfLastDownloadAttempt);
		System.out.println("Downloading file: ");
		FileUtils.copyInputStreamToFile(response.getEntity().getContent(), downloadedFile);
		response.getEntity().getContent().close();

		String downloadedFileAbsolutePath = downloadedFile.getAbsolutePath();
		System.out.println("File downloaded to '" + downloadedFileAbsolutePath + "'");
		
		return downloadedFileAbsolutePath;
	}
}
