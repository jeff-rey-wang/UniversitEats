package proj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;

public class parseResto {
	private static List<RestaurantT> list;// list of Restaurant objects used by multiple methods in this class
	
	/**
     * Method to parse the CSV file and create a list of Restaurant Objects in a specific state/province
     *
     * @param  state user inputted state of which they reside
	 */
	public void parse(String state) throws IOException {
		list = new ArrayList <RestaurantT>();//creates new Arraylist
		FileInputStream input = null;
		Scanner scan = null;
		try {
		    input = new FileInputStream("business.csv");//business.csv is read in the input steam
		    scan = new Scanner(input, "UTF-8");
		    scan.nextLine();
		while (scan.hasNextLine()) {
			
		    String line = scan.nextLine();
		    String [] p = line.split(",", -1);// the array is split by line
		
		    if (!p[1].equals("")&& !p[1].equals("None")&& 
		    		!p[1].equals("0") && p[5].equals(state)) {//if the csv Restaurant has same state create a restaurnt obj
		        	
		        	RestaurantT obj = new RestaurantT(); //new restaurant object
		        	ArrayList<String> categories = new ArrayList<String>();//create an arraylist of types of cuisine fo the restaurant
		        	for (int i =9 ; i <p.length; i++) {
		        		p[i]=p[i].replace("\"", "");
		        		p[i]=p[i].replace(" ", "");
		        		categories.add(p[i]);
		        	}
		        	
		        	if(categories.contains("Restaurants")) {
		        		obj.setLat(Double.parseDouble(p[2]));//set lat,lon,name,stars,price, star
		        		obj.setLon(Double.parseDouble(p[3]));
		        		obj.setName(p[8]);
		        		obj.setStars(Double.parseDouble(p[7]));
		        		obj.setPrice(Integer.parseInt(p[1]));
		        		obj.setType(categories);
		        		
		        		list.add(obj);
		        	}
		        }
		    }
		    
		} finally {
			
		    if (input != null) {
		        input.close();//close input
		    }
		    
		    if (scan != null) {
		    	
		        scan.close();//close scanner
		    }
		     
		}
		}
			
	/**
     * Method creates a smaller subset of Restauarnat objects of type cusine.
     *
     * @param  cu Type fo cusine
	 * @return ArrayList finallist : list of all restuarnts with right cusine and state
	 */
		
		public ArrayList<RestaurantT> cuisine(String cu){
			ArrayList<RestaurantT> finalList = new ArrayList<RestaurantT>();
			int totalS = list.size();
			for(int i = 0; i < totalS;  i++) {
				if (list.get(i).getType().contains(cu)) { //create a new list of all the restaurants in specific state with specific cuisine.
					finalList.add(list.get(i));
				}
				
			}
			return finalList;
		}
		
}
