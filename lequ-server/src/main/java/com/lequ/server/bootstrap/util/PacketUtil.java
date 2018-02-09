package com.lequ.server.bootstrap.util;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.lequ.server.bootstrap.model.PacketSplitEntity;
public class PacketUtil {

	public static void main(String[] args) {
		
		double total_money;	// 红包总金额
		int total_people;	// 抢红包总人数
		double min_money;  // 每个人最少能收到0.01元
		total_money = 10;
		total_people = 5;
		String packetId ="";
//		long start = System.currentTimeMillis();
		List<PacketSplitEntity> listPacketSplit  = packetSplit(total_money,total_people);
//		for(PacketSplitEntity a:listPacketSplit){
//			System.out.println("第 "+a.getSplitId()+"个红包：   "+a.getAmount()+"元");
//		}
//		long end= System.currentTimeMillis();
//		System.out.println(end-start);
	}
	
	/**红包拆分算法
	 * @param total_money 红包总金额
	 * @param total_people 抢红包总人数
	 */
	public static List<PacketSplitEntity> packetSplit(double total_money ,int total_people){
		// total_money红包总金额
		//  total_people抢红包总人数
		double min_money;  // 每个人最少能收到0.01元
		List<PacketSplitEntity> listPacketSplit = new ArrayList<PacketSplitEntity>();
		PacketSplitEntity packetSplit = null;
//		total_money = 10;
//		total_people = 5;
		min_money = 0.99;
		
		for (int i = 0; i < total_people - 1; i++) {
			packetSplit = new PacketSplitEntity();
			int j = i + 1;
			
			double safe_money = (total_money - (total_people - j) * min_money)
					/ (total_people - j);
			//当前红包钱数
			double tmp_money = (Math.random()
					* (safe_money * 100 - min_money * 100) + min_money * 100) / 100;
			//剩余钱数
			//剩余钱数
			total_money = total_money - tmp_money;
			System.out.println(formatDouble1(tmp_money));
			System.out.format("第 %d 个红包： %.2f 元，剩下： %.2f 元\n", j, tmp_money,
					total_money);
		}

		System.out.format("第 %d 个红包： %.2f 元，剩下： 0 元\n", total_people,
				total_money);
		System.out.println(formatDouble1(total_money));
		return listPacketSplit;

	}
	
	public static String formatDouble(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        
        // 保留两位小数
        nf.setMaximumFractionDigits(2); 
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        
        return nf.format(d);
    }
	/**保留两位小数，别看方法土，但是挺准确
	 * @param d
	 * @return
	 */
	public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }
}
