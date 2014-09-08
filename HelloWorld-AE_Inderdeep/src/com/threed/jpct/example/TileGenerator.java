package com.threed.jpct.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TileGenerator {
	private static Map<TileType, Float> _tileFrequencies = new HashMap<TileType, Float>();

	public static void AddTileTypeFrequency(TileType type, Float frequency) {
		_tileFrequencies.put(type, frequency);
	}

	public static int calcMinimumDistance(int total) {

		// Array to store the frequencies of each type of tile
		int[] a = new int[] { 5, 1, 2, 3, 4 };

		// Array to store the distance of the tiles
		int[] dist = new int[5];

		for (int i = 0; i < a.length; i++) {
			dist[i] = a[i] - total;
		}

		int smallest = dist[0];

		for (int j = 0; j < dist.length; j++) {
			if (dist[j] < smallest)
				smallest = dist[j];
		}
		// System.out.print(smallest);
		if (smallest < 0)
			return (smallest * (-1));
		else
			return smallest;

	}

	private static float getFrequencySum() {
		float total = 0;
		for (float frequency : _tileFrequencies.values()) {
			total += frequency;
		}

		return total;
	}

	public static TileType getRandomTileType() {
		Random rand = new Random();

		float randomTarget = rand.nextFloat() * getFrequencySum();

		float positionInTileFrequencies = 0;
		for (int i = 0; i < _tileFrequencies.size(); ++i) {
			positionInTileFrequencies += _tileFrequencies.get(_tileFrequencies
					.keySet().toArray()[i]);
			if (positionInTileFrequencies > randomTarget)
				return (TileType) _tileFrequencies.keySet().toArray()[i];

		}
		return TileType.BLANK_TILE;

		/*
		 * //Sum of all the relative frequencies int total=0; Random r=new
		 * Random(); for(TileType tile:TileType.values()) {
		 * //System.out.println(tile.name()); total+=tile.getFrequency(); } int
		 * val=r.nextInt((5-1)+1)+1 ; if(total>val) { int
		 * k=calcMinimumDistance(val);
		 * 
		 * tile.setFrequency(k);
		 * 
		 * if(k==0) { k=5; tile.setFrequency(k); }
		 * 
		 * }
		 * 
		 * TileGenerator ob=new TileGenerator(this.posx, this.posy);
		 * ob.tile=tile; return ob.tile;
		 */
	}

}
