package com.example.demo.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;


/**
 * 模拟用户的并发请求，检测用户乐观锁的性能问题
 * 
 * @author zzg
 * @date 2017-02-10
 */
public class ConcurrentTest {
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);// 模拟5人并发请求，用户钱包

		for (int i = 0; i < 20; i++) {// 模拟5个用户
			AnalogUser analogUser = new AnalogUser("user" + i, "58899dcd-46b0-4b16-82df-bdfd0d953bfb", "1", "20.024", latch);
			analogUser.start();
		}
		latch.countDown();// 计数器減一 所有线程释放 并发访问。
		System.out.println("所有模拟请求结束  at " + sdf.format(new Date()));

	}

	static class AnalogUser extends Thread {
		String workerName;// 模拟用户姓名
		String openId;
		String openType;
		String amount;
		CountDownLatch latch;

		public AnalogUser(String workerName, String openId, String openType, String amount, CountDownLatch latch) {
			super();
			this.workerName = workerName;
			this.openId = openId;
			this.openType = openType;
			this.amount = amount;
			this.latch = latch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				latch.await(); // 一直阻塞当前线程，直到计时器的值为0
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			post();// 发送post 请求

		}

		public void post() {
			String result = "";
			System.out.println("模拟用户： " + workerName + " 开始发送模拟请求  at " + sdf.format(new Date()));
			result = HttpRequestUtil.sendGet("http://127.0.0.1:9005/testOptimisitcLock", "");
			System.out.println("操作结果：" + result);
			System.out.println("模拟用户： " + workerName + " 模拟请求结束  at " + sdf.format(new Date()));

		}

	}
}