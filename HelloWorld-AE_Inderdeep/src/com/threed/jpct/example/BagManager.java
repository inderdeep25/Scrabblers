package com.threed.jpct.example;
import com.threed.jpct.example.LetterManager;

import java.util.Map;
import java.util.HashMap;

public class BagManager {

	private static Map<LetterData,Integer> letter_frequencies = new HashMap<LetterData,Integer>();
	
	public static void AddLetterFrequency(LetterData letter_data, Integer frequency) {
		letter_frequencies.put(letter_data, frequency);
	}

	
	int  default_frequency = 5;
	public Integer getLetterFrequency(LetterData letter){
		 
		for (int i=0;i<(int) LetterManager.list.size();++i){
			if(LetterManager.list.get(i)==letter)
				return (Integer) letter_frequencies.keySet().toArray()[i];
			}
		return default_frequency;
	}
	
	
}
