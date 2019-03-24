package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	// 进入房间事件
        	Event e = new Event("enterroom", true, kSession);
        	FactHandle handle = kSession.insert(e);
        	e.setFactHandle(handle);
        	kSession.fireAllRules();
        	
        	Thread.sleep(2000);
        	
        	// 音频事件
        	Event e2 = new Event("receiveradio", true, kSession);
        	handle = kSession.insert(e2);
        	e2.setFactHandle(handle);
        	kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
