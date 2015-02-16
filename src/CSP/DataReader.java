package CSP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
	
	private static String inputFile;
	private ArrayList<Item> items;
	private ArrayList<Bag> bags;
	private static int fitLimitMin;
	private static int fitLimitMax;
	
	/*
	 * Constructor.
	 */
	public DataReader () {
		items = new ArrayList<Item>();
		bags = new ArrayList<Bag>();
	}

	//Get a Bag from the ArrayList based on specified name
	public Bag getBagByName(char name) {
		for(Bag b: bags) {
			if(b.getName() == name) {
				return b;
			}
		}
		return null;
	}
	
	//Get an item from the ArrayList based on the specified name
	public Item getItemByName(char name) {
		for(Item i: items) {
			if(i.getName() == name) {
				return i;
			}
		}
		return null;
	}
	
	/*
	//Set info about the bag based on the specified name
	public void setBagFromList(char name) {
		for(Bag b: bags) {
			if(b.getName() == name) {
				b.set;
			}
		}
	}
	*/
	/*
	 * Process and parse input file.
	 */
	public void readData() {
		BufferedReader br;
		int numLines = 0;
		ArrayList<InclusiveUnary> IU = new ArrayList<InclusiveUnary>();
		ArrayList<EqualBinary> EB = new ArrayList<EqualBinary>();
		ArrayList<ExclusiveUnary> EU = new ArrayList<ExclusiveUnary>();
		ArrayList<MutualExclusiveBinary> MEB = new ArrayList<MutualExclusiveBinary>();
		ArrayList<NotEqualBinary> NEB = new ArrayList<NotEqualBinary>();
		
		try{
			br = new BufferedReader(new FileReader(inputFile));
			String thisLine = null;
			int typeNum = 0;

			while( (thisLine = br.readLine()) != null) {
				numLines++;
				String splitLine[] = thisLine.split(" ");

				if(thisLine.charAt(0) == '#') {
					typeNum++;
				}
				else if(typeNum == 1) {
					// Items
					items.add(new Item(splitLine[0].charAt(0), Integer.parseInt(splitLine[1])));
				}
				else if(typeNum == 2) {
					// Bags
					bags.add(new Bag(splitLine[0].charAt(0), Integer.parseInt(splitLine[1])));
				}
				else if(typeNum == 3) {
					// Fit limit values
					fitLimitMin = Integer.parseInt(splitLine[0]);
					fitLimitMax = Integer.parseInt(splitLine[1]);
				}
				else if(typeNum == 4) {
					// Unary inclusive
					Item i = getItemByName(splitLine[0].charAt(0));
					ArrayList<Bag> unaryInclusiveBags = new ArrayList<Bag>();
					
					for(int j = 1; j < splitLine.length; j++) {
						Bag b = getBagByName(splitLine[j].charAt(0));
						unaryInclusiveBags.add(b);
					}
					IU.add( new InclusiveUnary(i, unaryInclusiveBags) );
				}
				else if(typeNum == 5) {
					// Unary exclusive
					Item i = getItemByName(splitLine[0].charAt(0));
					ArrayList<Bag> unaryExclusiveBags = new ArrayList<Bag>();				
					
					for(int j = 1; j <splitLine.length; j++) {
						Bag b = getBagByName(splitLine[j].charAt(0));
						unaryExclusiveBags.add(b);
					}
					EU.add( new ExclusiveUnary(i, unaryExclusiveBags));
				}
				else if(typeNum == 6) {
					// Binary Equals
					Item i1 = getItemByName(splitLine[0].charAt(0));
					Item i2 = getItemByName(splitLine[1].charAt(0));
					EB.add(new EqualBinary(i1, i2));
				}
				else if(typeNum == 7) {
					// Binary Not Equals
					Item i1 = getItemByName(splitLine[0].charAt(0));
					Item i2 = getItemByName(splitLine[1].charAt(0));
					NEB.add(new NotEqualBinary(i1, i2));
				}
				else if(typeNum == 8) {
					// Mutual Exclusive
					
				}
			}
			
			if(numLines == 8) {
				System.out.println("There is no problem specified!");
				System.exit(-1);
			}
			
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Print function for debug purposes.
	 * Simply print data received from file.
	 */
	public void printData () {
		System.out.println("Item: ");
		for(Item i : items) {
			System.out.println("\t" + i.getName() + " " + i.getWeight());
		}
		
		System.out.println("Bag: ");
		for(Bag b : bags) {
			System.out.println("\t" + b.getName() + " " + b.getWeightCapacity());
		}
		
		System.out.println("Min: " + fitLimitMin);
		System.out.println("Max: " + fitLimitMax);
	}

	public static int getFitLimitMin() {
		return fitLimitMin;
	}
	
	public static int getFitLimitMax() {
		return fitLimitMax;
	}
	
	/*
	 * Entry point of program.
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Incorrect number of command line arguments");
			System.exit(-1);
		}

		inputFile = args[0];

		DataReader dReader = new DataReader();
		dReader.readData();
		dReader.printData();
	}
}