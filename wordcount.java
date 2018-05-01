import java.util.*;
import java.io.*;

class wordcount {
  /* An application that reads in the file called ips.txt and then determines the k most frequent occuring values. If there is a tie it includes all values
   */
  static int k = 0;

  public static void main(String[] args) {
     printk(reader("ips.txt"), k);
  }

  static HashMap<Integer, ArrayList<String>> reader (String file) {
    /* input:  string for file name
     * output: hashmap containing all values
     * Algo:   1) read in value O(1)
     *         2) check if exists
     *            YES1) update count O(1)
     *            YES2) update occurance list (remove from arraylist and add under new key) O(n)
     *            NO1)  add to count O(1)
     *            NO2)  add to occurance list (add to arraylist with key value of 1) O(1)
     */
               
    try {
      //open file
      BufferedReader in = new BufferedReader(new FileReader("ips.txt"));

      // Read in the first line from the file save as the Static k
      k = Integer.parseInt(in.readLine());

      // Stores all ips by key with value of occurances
      HashMap<String, Integer> first = new HashMap<String, Integer>();
      // Stores all ips by occurances in an ArrayList for each
      HashMap<Integer, ArrayList<String>> counter = new HashMap<Integer, ArrayList<String>>();
      String temp;
      int value;
      
      // See Algo
      while((temp = in.readLine()) != null) {
  
        if (!first.containsKey(temp)) {
          first.put(temp, 1);
          if (counter.get(1) == null)
            counter.put(1, new ArrayList<String>());
          counter.get(1).add(temp);        
        } else {
          value = first.get(temp);
          first.put(temp, value + 1);
          counter.get(value).remove(temp);
          if (counter.get(value+1) == null)
            counter.put(value + 1, new ArrayList<String>());
          counter.get(value + 1).add(temp);
        }
      }
      
      return counter;
    } catch (IOException e) {
      return null;
    }
  }

  static void printk( HashMap<Integer, ArrayList<String>> counts, int k) {
    /* input: hashmap of sorted values and k
     * output: print the k highest keys
     * Algo:  1) convert to treemap sorted in reverse O(1)?
              2) output next ArrayList of map O(n)
     */

    Map<Integer, ArrayList<String>> map = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
    map.putAll(counts);
    Set set = map.entrySet();
    Iterator iterator = set.iterator();

    int i = 1;
    int i2 = i;
    while(iterator.hasNext()) {
      Map.Entry me = (Map.Entry) iterator.next();
      ArrayList<String> l = new ArrayList<String>();

      l = (ArrayList<String>) me.getValue();
      i2 = i;
      // prints the k top values and tiebreaker
      for (String temp : l) {
        System.out.println(i2+ ": " + temp + " " + me.getKey());
        k--;
        i++;
      }
      if (k <= 0)
        break;
    }
  }
}
