package com.lequ.server.bootstrap.util;

import java.util.ArrayList;
import java.util.List;

import com.lequ.server.bootstrap.model.PacketSplitEntity;
public class CopyOfPacketUtil {

	public static void main(String[] args) {
		
		double total_money;	// 红包总金额
		int total_people;	// 抢红包总人数
		double min_money;  // 每个人最少能收到0.01元
		total_money = 10;
		total_people = 5;
		String packetId ="";
		 packetSplit(total_money,total_people);
	
	}
	
	/**红包拆分算法
	 * @param total_money 红包总金额
	 * @param total_people 抢红包总人数
	 */
	public static void packetSplit(double total_money ,int total_people){
		// total_money红包总金额
		//  total_people抢红包总人数
		double min_money;  // 每个人最少能收到0.01元
		total_money = 100;
		total_people = 5;
		min_money = 0.99;
		double tmp_sum = 0 ;
		
		for (int i = 0; i < total_people - 1; i++) {

			int j = i + 1;
			
			double safe_money = (total_money - (total_people - j) * min_money)
					/ (total_people - j);
			//当前红包钱数
			double tmp_money = (Math.random()
					* (safe_money * 100 - min_money * 100) + min_money * 100) / 100;
			//剩余钱数
			total_money = total_money - tmp_money;
			tmp_sum += tmp_money;
			System.out.format("第 %d 个红包： %.2f 元，剩下： %.2f 元\n", j, tmp_money,
					total_money);
		}
		System.out.println("ziji jisuan :"+(100-tmp_sum));
		System.out.format("第 %d 个红包： %.2f 元，剩下： 0 元\n", total_people,
				total_money);


	}
}
