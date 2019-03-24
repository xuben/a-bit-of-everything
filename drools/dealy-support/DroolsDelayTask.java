package com.sample;

import java.util.TimerTask;

/**
 * 延迟若干时间后再次触发规则匹配和发布
 * 
 * @author Xuben
 *
 */
public class DroolsDelayTask extends TimerTask {

	/**
	 * 日志事件
	 */
	private Event e;

	public DroolsDelayTask(Event e) {
		this.e = e;
	}

	@Override
	public void run() {
		if (null == e) {
			return;
		}
		synchronized (e) {
			// 取消定时器
			e.cancelDroolsDelayTimer();
			// 设置超时
			e.setTimeout(true);
			// 重新触发规则匹配
			e.fireAllRules(true);
		}
	}

}
