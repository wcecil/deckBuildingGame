package com.wcecil.triggers

import static org.junit.Assert.*

import org.junit.Test

import com.wcecil.actions.Action
import com.wcecil.actions.NullAction
import com.wcecil.beans.GameState
import com.wcecil.triggers.Persistence
import com.wcecil.triggers.Trigger


class TriggerTest {
	@Test
	void testShell(){
		def sharedData = new Binding()
		def shell = new GroovyShell(sharedData)
		def now = new Date()
		sharedData.setProperty('text', 'I am shared data!')
		sharedData.setProperty('date', now)
		
		String result = shell.evaluate('"At $date, $text"')
		
		assert result == "At $now, I am shared data!"
	}
	
	@Test
	void test() {
		def script = '''
println "$trigger"
println trigger.isTriggered(triggeringAction)
	
'x'
''' 

		Trigger t = new Trigger(frequency: Persistence.EVERY, actionType: Action.class, actionScript:script );
		
		NullAction na = new NullAction()
		
		assertTrue t.isTriggered(na)
		
		def result = t.doTrigger(new GameState(), na)
		
		assertEquals result, 'x'
	}
}