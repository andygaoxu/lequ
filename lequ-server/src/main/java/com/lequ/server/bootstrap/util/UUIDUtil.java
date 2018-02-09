package com.lequ.server.bootstrap.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
	public static String[] getUUID(int num) {

		if (num <= 0)
			return null;

		String[] uuidArr = new String[num];

		for (int i = 0; i < uuidArr.length; i++) {
			uuidArr[i] = getUUID32();
		}

		return uuidArr;
	}
	 /**实现订单后16位数据
	 * @return
	 */
	public static String getOrderIdByUUId() {
         int first = new Random(10).nextInt(2) + 1;
         int hashCodeV = UUID.randomUUID().toString().hashCode();
         if (hashCodeV < 0) {//有可能是负数
             hashCodeV = -hashCodeV;
         }
         // 0 代表前面补充0
         // 4 代表长度为4
         // d 代表参数为正数型
         //30(14+16)14位为当前日期16位为随机数
         return  DateTimeUtil.DateToYYMMDDHHMMSS()+first + String.format("%015d", hashCodeV);
     }

	/**实现32位的UUID
	 * @return
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	public static void main(String[] para){
		System.out.print(getOrderIdByUUId());
	}
}
