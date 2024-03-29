/*
 * Copyright (c) 2010 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.restfb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A collection of string-handling utility methods.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public abstract class StringUtils {
  /**
   * Default charset to use for encoding/decoding strings.
   */
  static final String ENCODING_CHARSET = "UTF-8";

  private static final Logger logger = Logger.getLogger(StringUtils.class);

  /**
   * Prevents instantiation.
   */
  private StringUtils() {}

  /**
   * Is {@code string} blank (null or only whitespace)?
   * 
   * @param string
   *          The string to check.
   * @return {@code true} if {@code string} is blank, {@code false} otherwise.
   */
  static boolean isBlank(String string) {
    return string == null || "".equals(string.trim());
  }

  /**
   * Returns a trimmed version of {@code string}, or {@code null} if {@code
   * string} is {@code null} or the trimmed version is a blank string.
   * 
   * @param string
   *          The string to trim.
   * @return A trimmed version of {@code string}, or {@code null} if {@code
   *         string} is {@code null} or the trimmed version is a blank string.
   */
  static String trimToNull(String string) {
    if (isBlank(string))
      return null;
    return string.trim();
  }

  /**
   * Returns a trimmed version of {@code string}, or an empty string if {@code
   * string} is {@code null} or the trimmed version is a blank string.
   * 
   * @param string
   *          The string to trim.
   * @return A trimmed version of {@code string}, or an empty string if {@code
   *         string} is {@code null} or the trimmed version is a blank string.
   */
  static String trimToEmpty(String string) {
    if (isBlank(string))
      return "";
    return string.trim();
  }

  /**
   * URL-encodes a string.
   * <p>
   * Assumes {@code string} is in {@value #ENCODING_CHARSET} format.
   * 
   * @param input
   *          The string to URL-encode.
   * @return The URL-encoded version of the input string, or {@code null} if
   *         {@code string} is {@code null}.
   * @throws IllegalStateException
   *           If unable to URL-encode because the JVM doesn't support
   *           {@value #ENCODING_CHARSET}.
   */
  public static String urlEncode(String string) {
    if (string == null)
      return null;
    try {
      return URLEncoder.encode(string, ENCODING_CHARSET);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Platform doesn't support "
          + ENCODING_CHARSET, e);
    }
  }

  /**
   * Converts {@code string} to a byte array.
   * <p>
   * Assumes {@code string} is in {@value #ENCODING_CHARSET} format.
   * 
   * @param string
   *          The string to convert to a byte array.
   * @return A byte array representation of {@code string}.
   * 
   * @throws NullPointerException
   *           If {@code string} is {@code null}.
   * @throws IllegalStateException
   *           If unable to URL-encode because the JVM doesn't support
   *           {@value #ENCODING_CHARSET}.
   */
  static byte[] toBytes(String string) {
    if (string == null)
      throw new NullPointerException("Parameter 'string' cannot be null.");

    try {
      return string.getBytes(ENCODING_CHARSET);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Platform doesn't support "
          + ENCODING_CHARSET, e);
    }
  }

  /**
   * Builds and returns a string representation of the given {@code inputStream}
   * .
   * 
   * @param inputStream
   *          The stream from which a string representation is built.
   * 
   * @return A string representation of the given {@code inputStream}.
   * @throws IOException
   *           If an error occurs while processing the {@code inputStream}.
   */
  static String fromInputStream(InputStream inputStream) throws IOException {
    if (inputStream == null)
      return null;

    BufferedReader reader = null;

    try {
      reader =
          new BufferedReader(new InputStreamReader(inputStream,
            ENCODING_CHARSET));
      StringBuilder response = new StringBuilder();

      String line = null;
      while ((line = reader.readLine()) != null)
        response.append(line);

      return response.toString();
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (Throwable t) {
          // Really nothing we can do but log the error
          logger.warn("Unable to close stream, continuing on...", t);
        }
    }
  }

  /**
   * Joins the given {@code list} into a comma-separated string.
   * 
   * @param list
   *          The list to join.
   * @return A comma-separated string representation of the given {@code list}.
   */
  static String join(List<String> list) {
    if (list == null)
      return null;

    StringBuilder joined = new StringBuilder();
    boolean first = true;

    for (String element : list) {
      if (first)
        first = false;
      else
        joined.append(",");
      joined.append(element);
    }

    return joined.toString();
  }
}