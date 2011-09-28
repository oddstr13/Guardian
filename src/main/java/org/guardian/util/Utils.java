package org.guardian.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class Utils
{
	/**
	 * Downloads a file from a url and gives progress messages.
	 */
	public static void download(Logger log, URL url, File file) throws IOException {
		if (!file.getParentFile().exists())
			file.getParentFile().mkdir();
		if (file.exists())
			file.delete();
		file.createNewFile();
		final int size = url.openConnection().getContentLength();
		log.info("Downloading " + file.getName() + " (" + size / 1024 + "kb) ...");
		final InputStream in = url.openStream();
		final OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		final byte[] buffer = new byte[1024];
		int len, downloaded = 0, msgs = 0;
		final long start = System.currentTimeMillis();
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
			downloaded += len;
			if ((int)((System.currentTimeMillis() - start) / 500) > msgs) {
				log.info((int)(downloaded / (double)size * 100d) + "%");
				msgs++;
			}
		}
		in.close();
		out.close();
		log.info("Download finished");
	}

	/**
	 * Checks if inputted string is an integer
	 */
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (final NumberFormatException ex) {}
		return false;
	}

	/**
	 * Java version of PHP's join(array, delimiter). Takes any kind of collection (List, HashMap etc)
	 */
	public static String join(Collection<?> s, String delimiter) {
		final StringBuffer buffer = new StringBuffer();
		final Iterator<?> iter = s.iterator();
		while (iter.hasNext()) {
			buffer.append(iter.next());
			if (iter.hasNext())
				buffer.append(delimiter);
		}
		return buffer.toString();
	}

	/**
	 * Joins a string array
	 */
	public static String join(String[] s, String delimiter) {
		if (s == null || s.length == 0)
			return "";
		final int len = s.length;
		final StringBuffer buffer = new StringBuffer(s[0]);
		for (int i = 1; i < len; i++)
			buffer.append(delimiter).append(s[i]);
		return buffer.toString();
	}

	/**
	 * Concatenate any number of arrays of the same type
	 */
	public static <T> T[] concat(T[] first, T[]... rest) {
		// Read rest
		int totalLength = first.length;
		for (final T[] array : rest)
			totalLength += array.length;
		// Concat with arraycopy
		final T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (final T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * Returns a string of spaces
	 */
	public static String spaces(int count) {
		final StringBuilder filled = new StringBuilder(count);
		for (int i = 0; i < count; i++)
			filled.append(' ');
		return filled.toString();
	}

	/**
	 * Returns the content of a web page as string.
	 */
	public static String readURL(URL url) throws IOException {
		final StringBuilder content = new StringBuilder();
		final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			content.append(inputLine);
		in.close();
		return content.toString();
	}
}
