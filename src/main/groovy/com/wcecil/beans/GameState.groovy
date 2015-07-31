package com.wcecil.beans

import groovy.transform.CompileStatic

import java.util.concurrent.atomic.AtomicLong

import com.wcecil.beans.gameobjects.Card
import com.wcecil.beans.gameobjects.Player
import com.wcecil.triggers.Trigger

@CompileStatic
class GameState {
	AtomicLong ticCount = new AtomicLong(0l);
	
	List<Player> players = []
	Player currentPlayer
	
	List<Card> available = []
	List<Card> mainDeck = []
	List<Set<Card>> staticCards = []
	
	List<String> audit = []
	
	Set<Trigger> triggers = [] as Set
}
