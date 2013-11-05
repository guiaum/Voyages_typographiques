package senorgenerativefont.senorInterface;

import java.util.ArrayList;
import java.util.Hashtable;


public class Params {

	// Format of the Pages
	public static float Format = 1.0f;
	// Number of Char per Line
	public static int NumberLines = 4;
	//Activation Management of the UI
	public static boolean UIcreated = false;

	int[] color1 = {255,255,255};
	int[] color2 = {255,0,255};
	int[] color3 = {255,255,0};
	int[] color4 = {0,0,0};

	public static ArrayList<int[]> colorArray = new ArrayList<int[]>();

	public static boolean BW = false;
	
	public static Hashtable<Character, int[]> myCharHashTable = new Hashtable<Character, int[]>();

	public Params()
	{

		colorArray.add(color1);
		colorArray.add(color2);
		colorArray.add(color3);
		colorArray.add(color4);
		
		createCharTable();

	}

	private void createCharTable() {
		//LETTERS
		myCharHashTable.put('A', new int[] {1,0,2,0});
		myCharHashTable.put('B', new int[] {0,1,0,4});
		myCharHashTable.put('C', new int[] {0,1,0,4});
		myCharHashTable.put('D', new int[] {0,2,0,2});
		myCharHashTable.put('E', new int[] {3,1,0,0});
		myCharHashTable.put('F', new int[] {2,1,0,0});
		myCharHashTable.put('G', new int[] {1,2,0,0});
		myCharHashTable.put('H', new int[] {1,2,0,0});
		myCharHashTable.put('I', new int[] {0,1,0,0});
		myCharHashTable.put('J', new int[] {0,1,0,0});
		myCharHashTable.put('K', new int[] {0,1,2,0});
		myCharHashTable.put('L', new int[] {1,1,0,0});
		myCharHashTable.put('M', new int[] {0,2,2,0});
		myCharHashTable.put('N', new int[] {0,2,1,0});
		myCharHashTable.put('O', new int[] {0,2,0,4});
		myCharHashTable.put('P', new int[] {0,1,0,2});
		myCharHashTable.put('Q', new int[] {0,2,1,4});
		myCharHashTable.put('R', new int[] {0,1,1,2});
		myCharHashTable.put('S', new int[] {0,0,1,4});
		myCharHashTable.put('T', new int[] {1,1,0,0});
		myCharHashTable.put('U', new int[] {0,2,0,2});
		myCharHashTable.put('V', new int[] {0,0,2,0});
		myCharHashTable.put('W', new int[] {0,0,4,0});
		myCharHashTable.put('X', new int[] {0,0,2,0});
		myCharHashTable.put('Y', new int[] {0,1,2,0});
		myCharHashTable.put('Z', new int[] {2,0,1,0});
		//NUMBERS
		myCharHashTable.put('0', new int[] {0,2,0,4});
		myCharHashTable.put('1', new int[] {2,1,0,0});
		myCharHashTable.put('2', new int[] {1,0,1,2});
		myCharHashTable.put('3', new int[] {0,0,0,6});
		myCharHashTable.put('4', new int[] {1,1,1,0});
		myCharHashTable.put('5', new int[] {1,1,0,4});
		myCharHashTable.put('6', new int[] {0,1,0,6});
		myCharHashTable.put('7', new int[] {1,0,1,0});
		myCharHashTable.put('8', new int[] {0,0,0,8});
		myCharHashTable.put('9', new int[] {0,1,0,6});
		//PONCTUATION
		myCharHashTable.put(' ', new int[] {0,0,0,0});
		
	}


}