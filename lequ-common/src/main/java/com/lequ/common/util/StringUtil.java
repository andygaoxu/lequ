package com.lequ.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class StringUtil extends StringUtils
{
	  private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	  public static final int MOBILE_NUMBER_LENGTH = 11;
	  public static final String ELLIPSIS_CHARS = "...";
	  
	  public static BigDecimal toBigDecimal(String str)
	  {
	    if (isBlank(str)) {
	      return null;
	    }
	    try
	    {
	      return new BigDecimal(str);
	    }
	    catch (Exception e)
	    {
	      LOG.debug(e.getMessage(), e);
	    }
	    return null;
	  }
	  
	  public static Integer toInteger(String str)
	  {
	    if ((isBlank(str)) || (!isNumeric(str))) {
	      return null;
	    }
	    try
	    {
	      return new Integer(str);
	    }
	    catch (Exception e)
	    {
	      LOG.debug(e.getMessage(), e);
	    }
	    return null;
	  }
	  
	  public static Long toLong(String str)
	  {
	    if ((isBlank(str)) || (!isNumeric(str))) {
	      return null;
	    }
	    try
	    {
	      return new Long(str);
	    }
	    catch (Exception e)
	    {
	      LOG.debug(e.getMessage(), e);
	    }
	    return null;
	  }
	  
	  public static Double toDouble(String str)
	  {
	    if (isBlank(str)) {
	      return null;
	    }
	    try
	    {
	      return new Double(str);
	    }
	    catch (Exception e)
	    {
	      LOG.debug(e.getMessage(), e);
	    }
	    return null;
	  }
	  
	  public static String[] regexGroup(String regex, String str)
	  {
	    Pattern p = Pattern.compile(regex, 8);
	    Matcher m = p.matcher(str);
	    if (m.find())
	    {
	      String[] result = new String[m.groupCount() + 1];
	      for (int i = 0; i <= m.groupCount(); i++) {
	        result[i] = m.group(i);
	      }
	      return result;
	    }
	    return null;
	  }
	  
	  public static String regexGroup(String regex, String str, int groupIndex)
	  {
	    String[] groups = regexGroup(regex, str);
	    if ((groups != null) && (groups.length >= groupIndex)) {
	      return groups[groupIndex];
	    }
	    return null;
	  }
	  
	  public static Object defaultIfBlank(String str, Object defaultValue)
	  {
	    if (isNotBlank(str)) {
	      return str;
	    }
	    return defaultValue;
	  }
	  
	  public static String defaultIfBlank(String str, String defaultValue)
	  {
	    if (isNotBlank(str)) {
	      return str;
	    }
	    return defaultValue;
	  }
	  
	  public static String mapUnderscoreToCamelCase(String str)
	  {
	    char c = '_';
	    
	    String[] strArr = split(str, c);
	    if (strArr.length > 0)
	    {
	      StringBuilder buf = new StringBuilder();
	      buf.append(strArr[0]);
	      for (int i = 1; i < strArr.length; i++) {
	        buf.append(capitalize(strArr[i]));
	      }
	      return buf.toString();
	    }
	    return null;
	  }
	  
	  public static String getFirstCharByUnderscore(String str)
	  {
	    char c = '_';
	    
	    String[] strArr = split(str, c);
	    if (strArr.length > 0)
	    {
	      StringBuilder buf = new StringBuilder();
	      for (String s : strArr) {
	        if ((s != null) && (s.length() > 0)) {
	          buf.append(s.charAt(0));
	        }
	      }
	      return buf.toString();
	    }
	    return null;
	  }
	  
	  public static String substrBB(String str, String startPattern, String endPattern)
	  {
	    if (isBlank(str)) {
	      return null;
	    }
	    int sidx = str.indexOf(startPattern);
	    if (sidx == -1) {
	      return null;
	    }
	    int eidx = str.indexOf(endPattern, sidx + startPattern.length());
	    if (eidx == -1) {
	      return null;
	    }
	    if (sidx > eidx) {
	      return null;
	    }
	    return str.substring(sidx + startPattern.length(), eidx).trim();
	  }
	  
	  public static String substrAA(String str, String startPattern, String endPattern)
	  {
	    int sidx = str.lastIndexOf(startPattern);
	    if (sidx == -1) {
	      return null;
	    }
	    int eidx = str.lastIndexOf(endPattern);
	    if (eidx == -1) {
	      return null;
	    }
	    if (sidx > eidx) {
	      return null;
	    }
	    return str.substring(sidx + startPattern.length(), eidx);
	  }
	  
	  public static String substrBA(String str, String startPattern, String endPattern)
	  {
	    if (isBlank(str)) {
	      return null;
	    }
	    int sidx = str.indexOf(startPattern);
	    if (sidx == -1) {
	      return null;
	    }
	    int eidx = str.lastIndexOf(endPattern);
	    if (eidx == -1) {
	      return null;
	    }
	    if (sidx > eidx) {
	      return null;
	    }
	    return str.substring(sidx + startPattern.length(), eidx).trim();
	  }
	  
	  public static String substr(String str, String pattern)
	  {
	    if ((str == null) || (str.length() == 0)) {
	      return null;
	    }
	    Matcher matcher = Pattern.compile(pattern).matcher(str);
	    boolean result = matcher.find();
	    if (result) {
	      return matcher.group(1);
	    }
	    return null;
	  }
	  
	  public static String ellipsis(String str, int showCharLen, String ellipsisChars)
	  {
	    if (str == null) {
	      return null;
	    }
	    if (str.length() > showCharLen) {
	      return str.substring(0, showCharLen) + ellipsisChars;
	    }
	    return str;
	  }
	  
	  public static String ellipsis(String str, int showCharLen)
	  {
	    return ellipsis(str, showCharLen, "...");
	  }
	  
	  public static String blanchMobileNumber(String mobileNumber)
	  {
	    if (mobileNumber == null) {
	      return null;
	    }
	    if (mobileNumber.length() != 11) {
	      return mobileNumber;
	    }
	    return mobileNumber.substring(0, 3) + "****" + mobileNumber.substring(7);
	  }
	  
	  public static String blanchEmail(String email)
	  {
	    if (email == null) {
	      return null;
	    }
	    int atIdx = email.indexOf("@");
	    if (atIdx == -1) {
	      return email;
	    }
	    return leftPad(email.substring(atIdx), email.length(), "*");
	  }
	  
	  public static String getEmailUserName(String email)
	  {
	    if (email == null) {
	      return null;
	    }
	    int atIdx = email.indexOf("@");
	    if (atIdx == -1) {
	      return null;
	    }
	    return email.substring(0, atIdx);
	  }
	  
	  public static String getIDSex(String identity)
	  {
	    if (isBlank(identity)) {
	      return null;
	    }
	    if (identity.length() != 18) {
	      return null;
	    }
	    String sexChar = identity.charAt(16) + "";
	    return Integer.parseInt(sexChar) % 2 == 0 ? "2" : "1";
	  }
	  
	  public static String getIDBirthday(String identity)
	  {
	    if (isBlank(identity)) {
	      return null;
	    }
	    if (identity.length() != 18) {
	      return null;
	    }
	    return identity.substring(6, 14);
	  }
	  
	  public static boolean isUtf8String(byte[] data)
	  {
	    if ((data == null) || (data.length == 0)) {
	      return true;
	    }
	    int count_good_utf = 0;
	    int count_bad_utf = 0;
	    byte current_byte = 0;
	    byte previous_byte = 0;
	    for (int i = 1; i < data.length; i++)
	    {
	      current_byte = data[i];
	      previous_byte = data[(i - 1)];
	      if ((current_byte & 0xC0) == 128)
	      {
	        if ((previous_byte & 0xC0) == 192) {
	          count_good_utf++;
	        } else if ((previous_byte & 0x80) == 0) {
	          count_bad_utf++;
	        }
	      }
	      else if ((previous_byte & 0xC0) == 192) {
	        count_bad_utf++;
	      }
	    }
	    return count_good_utf > count_bad_utf;
	  }
	  
	  public static boolean isTrue(String value)
	  {
	    return (value != null) && ((value.equalsIgnoreCase("true")) || (value.equalsIgnoreCase("t")) || (value.equalsIgnoreCase("1")) || (value.equalsIgnoreCase("enabled")) || (value.equalsIgnoreCase("y")) || (value.equalsIgnoreCase("yes")) || (value.equalsIgnoreCase("on")));
	  }
	  
	  public static String blanchBankCardNo(String bankCardNo)
	  {
	    return blanchBankCardNo(bankCardNo, false);
	  }
	  
	  public static String blanchBankCardNo(String bankCardNo, boolean appendBlank)
	  {
	    if (bankCardNo == null) {
	      return null;
	    }
	    if (bankCardNo.length() <= 9) {
	      return bankCardNo;
	    }
	    String str = bankCardNo.substring(0, 6) + leftPad("", bankCardNo.length() - 9, "*") + bankCardNo.substring(bankCardNo.length() - 3);
	    if (!appendBlank) {
	      return str;
	    }
	    StringBuilder buf = new StringBuilder();
	    for (int i = 1; i <= str.length(); i++)
	    {
	      buf.append(str.charAt(i - 1));
	      if ((i % 4 == 0) && (i < str.length())) {
	        buf.append(" ");
	      }
	    }
	    return buf.toString();
	  }
	  
	  public static String blanchID(String identity)
	  {
	    if (identity == null) {
	      return null;
	    }
	    if (identity.length() != 18) {
	      return identity;
	    }
	    return identity.substring(0, 6) + leftPad("", identity.length() - 10, "*") + identity.substring(identity.length() - 4);
	  }
	}
