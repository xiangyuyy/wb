package com.example.demo.concurrent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

	// 测试环境-分销商账户信息
	public static final String PARENTER = "19978_7726";
	public static final String KEY = "ae8c1f56da124afba1c13fd23d99a0e7";
	public static final String URL = "http://121.41.84.251:9090/api/";

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果 @modificationHistory.
	 */
	public static String sendGet(String url, String param) {
		StringBuilder result = new StringBuilder("");
		BufferedReader in = null;
		try {
			String urlNameString = StringHelper.isEmpty(param) ? url : url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			// ////System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			return "400";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder("");
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			out.close();
			// 定义BufferedReader输入流来读取URL的响应
			if (conn.getResponseCode() != 200) {
				return "400";
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			conn.disconnect();
		} catch (Exception e) {
			// //////System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
			return "400";
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 多个参数post
	 *
	 * @param surl
	 * @param params
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @author syf @creationDate. 2015年7月14日 下午1:49:17
	 */
	public static String sendPost(String surl, String[] params) throws IOException {
		URL url = new URL(surl);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter outputstream = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		StringBuilder param = new StringBuilder("");
		if (params != null) {
			for (String param1 : params) {
				param.append(param1);
			}
			outputstream.write(param.toString()); // post的关键所在！
		}
		// remember to clean up
		outputstream.flush();
		outputstream.close();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String sCurrentLine;
		StringBuilder sTotalString = new StringBuilder("");
		InputStream l_urlStream = connection.getInputStream();
		// 传说中的三层包装阿！
		InputStreamReader reader = new InputStreamReader(l_urlStream);
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sTotalString.append(sCurrentLine).append("\r\n");
		}
		reader.close();
		l_reader.close();
		l_urlStream.close();
		// //////System.out.println(sTotalString);
		return sTotalString.toString();
	}

	public static String doRequest(String urlStr, String method) {

		String responseStr = "";

		try {
			URL url = new URL(urlStr);

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			// GET Request Define:
			urlConnection.setRequestMethod(method);
			urlConnection.connect();

			// Connection Response From Test Servlet
			// System.out.println("Connection Response From Test Servlet");
			InputStream inputStream = urlConnection.getInputStream();

			// Convert Stream to String
			responseStr = convertToString(inputStream);
			// System.out.println(responseStr);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	public static String convertToString(InputStream inputStream) throws UnsupportedEncodingException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStreamReader.close();
				inputStream.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String doxmlPostQuery(String urlStr, String xml) {

		String responseStr = "";

		try {
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setUseCaches(false);
			// con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			// con.setRequestProperty("Content-Type", "text/xml");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			OutputStream out = con.getOutputStream();
			out.write(xml.getBytes("utf-8"));
			out.flush();
			out.close();

			responseStr = convertToString(con.getInputStream());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	/**
	 * http 请求
	 *
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 自定义头部 post 请求
	 */
	public static String postJSON(String url, String body, Map<String, String> header) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			if (header != null) {
				// 设置请求头参数
				for (Map.Entry<String, String> entry : header.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setReadTimeout(3000);
			conn.setConnectTimeout(3000);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(body);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 生成header内容map
	 * 
	 * @param interfacename
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getHeaderMap(String interfacename, String param) throws Exception {

		Map<String, String> result = new HashMap<String, String>();

		String token = MD5.sign(KEY + DateUtil.getDateFormatter() + interfacename + param);

		System.out.println(token);

		result.put("token", token);
		result.put("interfacename", interfacename);
		result.put("parenter", PARENTER);

		return result;
	}

	/**
	 * post访问
	 * 
	 * @param url
	 *            url
	 * @param params
	 *            参数
	 * @return
	 *
	 * 		2016-8-10 上午9:24:31
	 * @author sunxx
	 */
	public static String sendStockPost(String url, String params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			// System.out.println(url);
			// System.out.println(params);
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String s = sendGet("https://image.sudian178.com/sd/other/cat.jpg?x-oss-process=image/info", null);
		System.out.println(s);
	}

	public static void downloadFile(String filePath, String url, String method) {
		// System.out.println("fileName---->"+filePath);
		// 创建不同的文件夹目录
		File file = new File(filePath);
		// 判断文件夹是否存在
		if (!file.exists()) {
			// 如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		try {
			// 建立链接
			URL httpUrl = new URL(url);
			conn = (HttpURLConnection) httpUrl.openConnection();
			// 以Post方式提交表单，默认get方式
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// post方式不能使用缓存
			conn.setUseCaches(false);
			// 连接指定的资源
			conn.connect();
			// 获取网络输入流
			inputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			// 判断文件的保存路径后面是否以/结尾
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
			filePath = filePath + "/" + DateUtil.getYesterDateFormatter();
			File ffDir = new File(filePath);
			if (!ffDir.exists()) {
				ffDir.mkdir();
			}
			// 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
			fileOut = new FileOutputStream(filePath + "/zhifubao.zip");
			BufferedOutputStream bos = new BufferedOutputStream(fileOut);

			byte[] buf = new byte[4096];
			int length = bis.read(buf);
			// 保存文件
			while (length != -1) {
				bos.write(buf, 0, length);
				length = bis.read(buf);
			}
			bos.close();
			bis.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("抛出异常！！");
		}
	}
}
