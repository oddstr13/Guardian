package org.guardian.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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

	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (final NumberFormatException ex) {}
		return false;
	}
}
