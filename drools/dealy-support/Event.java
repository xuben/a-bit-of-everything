package com.sample;

import java.util.Timer;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class Event {

	/**
	 * 规则引擎会话
	 */
	private KieSession kieSession;
	/**
	 * 规则引擎fact句柄
	 */
	private FactHandle factHandle;
	/**
	 * 事件类型
	 */
	private String keyword;
	/**
	 * 事件结果
	 */
	private boolean value;
	/**
	 * 是否超时
	 */
	private boolean timeout;
	/**
	 * 定时器
	 */
	private Timer droolsDelayTimer;
	
	public Event(String keyword, boolean value, KieSession kieSession) {
		this.keyword = keyword;
		this.value = value;
		this.kieSession = kieSession;
	}
	
	public KieSession getKieSession() {
		return kieSession;
	}

	public void setKieSession(KieSession kieSession) {
		this.kieSession = kieSession;
	}

	public FactHandle getFactHandle() {
		return factHandle;
	}

	public void setFactHandle(FactHandle factHandle) {
		this.factHandle = factHandle;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public boolean isTimeout() {
		return timeout;
	}

	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}

	public Timer getDroolsDelayTimer() {
		return droolsDelayTimer;
	}

	public void setDroolsDelayTimer(Timer timer) {
		this.droolsDelayTimer = timer;
	}

	public synchronized void startDroolsDelayTimer(long delay) {
		if (null != droolsDelayTimer) {
			droolsDelayTimer.cancel();
		}
		droolsDelayTimer = new Timer();
        droolsDelayTimer.schedule(new DroolsDelayTask(this), delay);
	}
	
	public synchronized void cancelDroolsDelayTimer() {
		if (null != droolsDelayTimer) {
			droolsDelayTimer.cancel();
		}
	}
	
	public synchronized void fireAllRules(boolean force) {
		if (force) {
			kieSession.update(factHandle, this);
		}
		kieSession.fireAllRules();
	}
}
