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

package com.restfb.types;

import java.util.Date;

import com.restfb.Facebook;

/**
 * Represents the <a
 * href="http://developers.facebook.com/docs/reference/api/group">Group Graph
 * API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Group extends NamedFacebookType {
  @Facebook
  private NamedFacebookType owner;

  @Facebook
  private String description;

  @Facebook
  private String link;

  @Facebook
  private Venue venue;

  @Facebook
  private String privacy;

  @Facebook("updated_time")
  private String updatedTime;

  /**
   * An object containing the name and ID of the user who owns the group.
   * 
   * @return An object containing the name and ID of the user who owns the
   *         group.
   */
  public NamedFacebookType getOwner() {
    return owner;
  }

  /**
   * The group description.
   * 
   * @return The group description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * The URL for the group's website.
   * 
   * @return The URL for the group's website.
   */
  public String getLink() {
    return link;
  }

  /**
   * The location of this group, a structured address object.
   * 
   * @return The location of this group, a structured address object.
   */
  public Venue getVenue() {
    return venue;
  }

  /**
   * The privacy setting of the group, either 'OPEN', 'CLOSED', or 'SECRET'.
   * 
   * @return The privacy setting of the group, either 'OPEN', 'CLOSED', or
   *         'SECRET'.
   */
  public String getPrivacy() {
    return privacy;
  }

  /**
   * The last time the group was updated.
   * 
   * @return The last time the group was updated.
   */
  public Date getUpdatedTime() {
    return StringUtils.toDate(updatedTime);
  }
}