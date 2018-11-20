import java.util.Scanner;
import java.util.Random;

/** DNA Program - Project 2 - Fall 2018.
 *  @author [add your name here]
 */
public class Proj2B {

   /** Convert a character from a DNA sequence to its complement. 
    *  @param ch the character to convert
    *  @return comp the complementary character if ch is one of 
    *  A, T, C, G, 5, or 3 and the original character otherwise.
    */
   public static char complement(char ch) {
      //initialize a complement character and set it to ch
      char comp = ch; 
      
      //Cases changing the given character to its complement
      switch (comp) {
         case 'A':
            comp = 'T';
            break;
         case 'T':
            comp = 'A';
            break;
         case 'G':
            comp = 'C';
            break;
         case 'C':
            comp = 'G';
            break;
         case '5':
            comp = '3';
            break;
         case '3':
            comp = '5';
            break;
         case '\'':
            comp = '\'';
            break;
         default:
            break;
      }
      
      //Returns complement of passed character
      return comp; 
   }
   
  /**  Determine whether a DNA sequence is valid or not valid.
    *  @param seq the DNA sequence to validate
    *  @return validity the validity of the DNA sequence
    */
   public static boolean valid(String seq) {
      
      //Overall validity of the passed seq
      boolean validity = true;
      
      //Validation for non-letter portion of DNA sequence
      boolean validity1 = false;
      
      //Validation for DNA bases in sequence 
      boolean validity2 = false; 
      
      //Test to see that the inputed sequence is at least 6 characters long
      if (!(seq.length() > 6)) {
         validity = false;
         return validity;
      }
      
      //Tests to see if non-letter portions of the DNA sequence are valid
      if (seq.charAt(0) == '5') {
         if (seq.charAt(1) == '\'') {
            if (seq.charAt(2) == '-') {
               if (seq.charAt(seq.length() - 3) == '-') {
                  if (seq.charAt(seq.length() - 2) == '3') {
                     if (seq.charAt(seq.length() - 1) == '\'') {
                        validity1 = true;
                     }
                  }
               }
            }
         }  
      }
      
      //Tests to see if DNA bases are valid i.e. either A, T, G, or C
      for (int i = 3; i < seq.length() - 3; ++i) {
         if (Character.toUpperCase(seq.charAt(i)) ==  'A' || 
            Character.toUpperCase(seq.charAt(i)) ==  'T' || 
            Character.toUpperCase(seq.charAt(i)) ==  'G' || 
            Character.toUpperCase(seq.charAt(i)) ==  'C') {
            validity2 = true;
         } 
         else {
            validity2 = false;
         }
         //If either validity test is false, returns invalid sequence
         if (!(validity1) || !(validity2)) {
            validity = false;
            break;
         }
      }
   
      //returns true if validity tests 1 and 2 are true(valid)
      return validity; 
   }
   
   /** Generate a random sequence of a given length.
     * @param rand the random number generator to use
     * @param length the length of the strand (# of bases)
     * @return the full sequence including 5'- and -3'
     */
   public static String generate(Random rand, int length) {
      //Initialize a new string variable seq
      String seq = ""; 
      
      //Set sequence to beginning of DNA strand (5'-, always constant)
      seq = "5'-"; 
      
      //Generates 1 of 4 letters randomly equal
      //to the passed length and adds each to seq 
      for (int i = 0; i < length; ++i) {
         int len = rand.nextInt(4);
         String letter = "";
         
         switch (len) {
            case 0:
               letter = "A";
               break;
            case 1:
               letter = "C";
               break;
            case 2:
               letter = "G";
               break;
            case 3:
               letter = "T";
               break;
            default:
               break;
         }
         seq = seq + letter; 
      }
      //Set sequence to end of DNA strand (-3', always constant)
      seq = seq + "-3'"; 
      
      //Returns the new randomly generated sequence
      return seq; 
   }

   /** Compute and return the complement of a valid DNA sequence.
     * @param seq the original DNA sequence
     * @return seqComplement the complement of the original DNA sequence
     */
   public static String complement(String seq) {
     //Initialize a new string to hold the complement of the passed seqeunce seq
      String seqComplement = ""; 
      
      //Creates the complement based on the passed seqeunce
      for (int i = 0; i < seq.length(); ++i) {
         //Adds the complement of the current character in seq to seqComplement
         seqComplement = seqComplement + complement(seq.charAt(i)); 
      }
      //Returns the complement of the passed seq
      return seqComplement;       
   }

   /** Compute and return the reverse of a valid DNA sequence.
     * @param seq the original DNA sequence
     * @return seqReverse the reverse of the original DNA sequence
     */
   public static String reverse(String seq) {
     //Initialize a new string to hold the reverse of the passed sequence seq
      String seqReverse = "";
      
      //Initializes a new string  and sets it to the 
      //beginning characters of the passed sequence (non-letters) 
      String startChars = seq.substring(0, 3); 
      
      //Reverses the beginning characters of the passed sequence
      //Checks to see what the ending characters are
      switch (startChars) {
         case "5'-":
            //Adds the reverse of the beginning characters 
            //of the passed sequence to the reverse sequence
            seqReverse = "3'-"; 
            break;
         case "3'-":
            //Adds the reverse of the beginning characters 
            //of the passed sequence to the reverse sequence
            seqReverse = "5'-"; 
            break;
         default:
            break;
      }
      
      //Reverses the bases of the passed sequence 
      //and adds them to the reverser sequence
      for (int i = 4; seqReverse.length() != seq.length() - 3; ++i) {
         seqReverse = seqReverse + seq.charAt(seq.length() - i);
      }
      
      //Initializes a new string  and sets it to the
      //ending characters of the passed sequence (non-letters) 
      String endChars = seq.substring(seq.length() - 3, seq.length()); 
      //Reverses the ending characters of the passed sequence
      //Checks to see what the ending characters are
      switch (endChars) {
         case "-3'":
            //Adds the reverse of the ending characters of
            //the passed sequence to the reverse sequence 
            seqReverse = seqReverse + "-5'"; 
            break;
         case "-5'":
            //Adds the reverse of the ending characters of
            //the passed sequence to the reverse sequence 
            seqReverse = seqReverse + "-3'"; 
            break;
         default:
            break;
      }
      //Returns the reverse sequence    
      return seqReverse; 
   }
 
   /** Compare two DNA sequences, character by character, and return number of 
     * character mismatches between the two sequences.
     * @param seq1 the first DNA sequence to compare
     * @param seq2 the second DNA sequence to compare
     * @return the number of character mismatches, 
     *    -1 if they are incompatible lengths
     */
   public static int mutation(String seq1, String seq2) {
      //Initializes number of mutations and sets it to zero
      int mutationNum = 0; 
      
      //Tests to make sure that both passed sequences are equal in length
      if (seq1.length() != seq2.length()) {
         return -1;
      }
      //Initializes a new string equal to the DNA 
      //bases of the first passed sequence (A, T, G, or C)
      String bases1 = seq1.substring(3, seq1.length() - 3);
      
      //Initializes a new string equal to the DNA 
      //bases of the second passed sequence (A, T, G, or C) 
      String bases2 = seq2.substring(3, seq1.length() - 3); 
      bases1 = bases1.toUpperCase(); //Capitalizes bases
      bases2 = bases2.toUpperCase(); //Capitalizes bases
      
      //Compares the first passed sequence(bases) to the  
      //second passed sequence(bases) and increases the
      //number of mutations for each unmatching base
      for (int i = 0; i < bases1.length(); ++i) {
         if (bases1.charAt(i) == bases2.charAt(i)) {
            continue;
         }
         else {
            mutationNum += 1;
         }
      }
      //Returns the numebr of mutations
      return mutationNum; 
   }

   /** Check if a DNA sequence is palindromic, meaning it is the same as its 
     * reverse complement.
     * @param seq the DNA sequence to check
     * @return boolean true if palindromic, false if not palindromic
     */
   public static boolean palindromic(String seq) {
      //Intiializes a new string to hold 
      //the reverse complement of the given sequence
      String revComp = "";
      
      //Set the passes seqeunce to its reverse 
      seq = reverse(seq); 
      
      //Initializes a new string and sets 
      //it equal to the reverse sequence bases
      String bases = seq.substring(3, seq.length() - 3); 
      
      //Takes the complement of the reverse of the 
      //passed seqeunce and adds it to the reverse complement
      for (int i = 0; i < bases.length(); ++i) {
         revComp = revComp + complement(bases.charAt(i));
      }
      //Resets the reverse sequence to the original passed sequence
      seq = reverse(seq); 
      
      //Checks to see if the reverse complement 
      //is equal to the original passed sequence
      if (revComp.equals(seq.substring(3, seq.length() - 3))) {
         //Returns true if the passed sequence is palindromic
         return true; 
      }
      //Returns false if the passed seqeuence is not palindromic
      return false;  
   }
         
   /** Compute and return restriction enzyme cuts for a sequence.
     * @param seq the original valid DNA sequence
     * @param site a valid recognition site
     * @param where the cut position
     * @return seqCut the new cut DNA sequence
     */
   public static String restrictEnzyme(String seq, String site, int where) {
      //Initialize a new string for the new cut sequence
      String seqCut = "";
      
      //Initialize a new string and set it to the passed site bases 
      String siteChars = site.substring(3, site.length() - 3);
      
      //Initialize a new string and set it to the passed sequence bases 
      String seqChars = seq.substring(3, seq.length() - 3); 
      
      //Checks to see if passed site is in passed sequence
      if (seq.contains(siteChars)) { 
         //Checks to see if cut location(where) is 
         //less than or equal to site length
         if (where <= siteChars.length() && where > 0) { 
            //Inputs the cut(space) at the specified 
            //cut location and stores it in the site
            siteChars = siteChars.substring(0, where) +
               " " + siteChars.substring(where, siteChars.length());
               
            //Replaces the original sequence with the cut version 
            //of the sequence and stores it in the cut sequence
            seqCut = seq.replace(site.substring(3, site.length() - 
               3), siteChars);
         }
         else {
            //Returns this if the cut location is bigger 
            //than length of the restriction site
            return "Cut location is out of bounds."; 
         }
      }
      else {
         //Returns origianl sequence if the passed sequence 
         //does not contain the passed site
         return seq; 
      }
      
      //Returns the cut sequence      
      return seqCut; 
   }
   
   /** Compute and return restriction enzyme cuts for a sequence.
     * @param sTester the String to be tested
     * @return test if sTester is true or false
     */
   public static boolean isNumber(String sTester) {
     //Initializes a new boolean test and sets it to false
      boolean test = false;
      
      //Initializes a counter and sets it to 0 
      int count = 0; 
      
      //Tests to see if every character 
      //in the passed string is a number
      for (int i = 0; i < sTester.length(); ++i) {
         //Checks to see if every character in 
         //the passed string is a number(0 - 9)
         for (int j = 0; j < 10; ++j) {
            //Initializes a new character and sets it to the current number j
            char casted = (char) (j + 48);
            
            //Initializes a new Character and sets it to the passed string at i 
            Character chrctr = sTester.charAt(i);
            
            //If the current character is a number, turns test to true 
            if (chrctr.equals(casted)) { 
               test = true;
            }
            else {
               //Increments count when the given character is 
               //not equal to a number(0 - 9)
               count = count + 1;
               
               //If count is incremented 10 times, it is 
               //not a number(0 - 9) and returns false 
               if (count == 10) { 
                  return false;
               }
            }
         }
         //Resets count after each character is checked
         count = 0; 
      }
      //Returns true when the passed string is a number
      return test; 
   }
   
   /** Run case 6. 
    *  @param seq the current sequence
    *  @param scnr the Scanner object
    */
   
   public static void case6(String seq, Scanner scnr) {
      //Gives current DNA strand
      System.out.println("The current DNA strand is " + seq);
      
      //Prompts for a base pair restriction enzyme 
      System.out.print("Enter a base pair restriction enzyme: ");
      
      //Initializes and sets user input to base sequence 
      String baseSeq = scnr.nextLine(); 
      
      //Reprompts the user is the entered sequence is invalid         
      while (!(valid(baseSeq))) {
         System.out.print("\n");
         System.out.println("Invalid DNA sequence.");
         System.out.println("The current DNA strand is " + seq);
         System.out.print("Enter a base pair restriction enzyme: ");
         baseSeq = scnr.nextLine();
      }
      
      //Checks to see if the entered sequence is palindromic        
      if (!palindromic(baseSeq)) {
         System.out.println("This is not a" +
                     "palindromic base pair sequence.");
      }
      //If valid, continues to prompt the user
      else {
         //Prompts the user for a cut location
         System.out.print("Enter a cut location(number): ");
         
         //Initializes and sets user input to the cut 
         int cut = scnr.nextInt(); 
         
         
         while (cut <= 0 || restrictEnzyme(seq, baseSeq, cut).equals("Cut" +
                   " location is out of bounds.")) {
            //Reprompts the user if entered cut is less than 0
            if (cut <= 0) {
               System.out.print("\n");
               System.out.println("Cut location must be positive.");
               System.out.print("Enter a cut location(number): ");
               cut = scnr.nextInt();
               continue;
            }
            
            //Reprompts the user if the entered cut is
            //greater than the restriction sequence       
            if (restrictEnzyme(seq, baseSeq, cut).equals("Cut" +
                      " location is out of bounds.")) {
               System.out.print("\n");
               System.out.println("Cut location is too big");
               System.out.print("Enter cut location: ");
               cut = scnr.nextInt();
               System.out.print("\n");
            }
         }
         
         //Prints the cut sequences(restricted enzyme)
         System.out.println("The cut sequence is " +
                     restrictEnzyme(seq, baseSeq, cut));
         String clearLine = scnr.nextLine();
         System.out.print("\n");
      }
   }

   /** Main method to provide a menu-driven user interface for doing 
    *  various DNA sequence problems.
    *  @param args not used
    */
   public static void main(String[]args) {
      Scanner scnr = new Scanner(System.in);
      Random randy = new Random();
      // initialize seq to starting DNA sequence
      String seq = "5'-ATGCTC-3'";
      //Initializes a new boolean run and sets it to true
      boolean run = true;
      //Creates menu visual for users and runs 
      //until run is false(user determined)
      while (run) {
         System.out.println("0) Quit");
         System.out.println("1) Display DNA");
         System.out.println("2) Read DNA");
         System.out.println("3) Generate DNA");
         System.out.println("4) Complements");
         System.out.println("5) Mutation");      
         System.out.println("6) Restriction Enzymes");
         System.out.print("\n");
       
         //Prompt user for the program they want to run
         System.out.print("Enter the program number you " + 
            "would like to run on the DNA sequence: ");
         
         //Initializes a new string and sets it to the user input
         String program = scnr.nextLine(); 
         String clearLine = "";
         System.out.print("\n");
         
         //Sets cases based on the entered program
         //number and runs the selected program 
         switch (program) {
            //Ends program
            case "0":
               //Sets run to false, ends run and closes menu
               run = false;
               //Displays End of program 
               System.out.println("End of Program."); 
               break;
               
            //Displays currently stored DNA sequence
            case "1":
               //Gives current DNA strand
               System.out.println("The current DNA strand is " + seq); 
               System.out.print("\n");
               break;
               
            //Allows user to enter a new DNA strand 
            //to store and override the current strand
            case "2":
               //Prompts user for a DNA sequence
               System.out.print("Enter a DNA strand: "); 
               String newSeq = scnr.nextLine();
               //Checks if entered sequence is valid, if not, reprompts user
               while (!(valid(newSeq))) { 
                  System.out.println("Invalid DNA sequence.");
                  System.out.print("Enter a DNA strand: ");
                  newSeq = scnr.nextLine();
               }
               //Sets the new valid sequence bases to uppercase
               seq = newSeq.toUpperCase();
               //Displays changed sequence 
               System.out.println("DNA sequence changed to " + seq); 
               System.out.print("\n");
               break;
               
            //Generates a random DNA sequence
            //based on user inputed number of bases 
            case "3":
               //Prompts user for desired number of bases
               System.out.print("Enter desired number of bases: ");
               //Initializes a new string and sets it to the user input 
               String bases = scnr.nextLine();
               //Reprompts user if the input begins with a 0 or is not a number 
               while ((bases.charAt(0) == 48) || !(isNumber(bases))) { 
                  System.out.println("Invalid number.");
                  System.out.print("Enter desired number of bases: ");
                  bases = scnr.nextLine();
               }
               //Sets currently stored sequence to 
               //new randomly generated sequence
               seq = generate(randy, Integer.parseInt(bases));
               //Displays new stored sequence 
               System.out.println("DNA sequence changed to " + seq); 
               System.out.print("\n");
               break;
               
            //Displays the complement and the reverse 
            //of the currently stored DNA sequence
            case "4":
               //Gives current DNA strand
               System.out.println("The current DNA strand is " + seq);
               //Initializes a new string and sets it to
               //the complement of the currently stored string  
               String compSeq = complement(seq);
               //Initializes a new string and sets it to 
               //the reverse of the currently stored string 
               String revSeq = reverse(seq);
               //Displays the complement of the currently stored sequence 
               System.out.println("The complement sequence is " + compSeq);
               //Displays the reverse of the currently stored sequence 
               System.out.println("The reverse sequence is " + revSeq); 
               System.out.print("\n");
               break;
               
            //Takes a user entered DNA sequence and displays  
            //number of mutations, or differing bases
            //in the sequence compared to the currently stored sequence
            case "5":
               //Gives current DNA strand
               System.out.println("The current DNA strand is " + seq);
               //Prompts the user for a DNA sequence 
               System.out.print("Enter a DNA sequence: ");
               //Initializes a new string and sets it to user input 
               String mutSeq = scnr.nextLine(); 
               //Reprompts the user if the input is not equal in length 
               //to the currently stored sequence 
               //or if it is not a valid sequence
               while (mutation(seq, mutSeq) == -1 || !(valid(mutSeq))) {
                  System.out.print("\n");
                  System.out.println("Invalid DNA strand.");
                  //Gives current DNA strand
                  System.out.println("The current DNA strand is the " + seq);
                  //Prompts user for a DNA sequence 
                  System.out.print("Enter a DNA sequence: ");
                  //Sets mutation sequence to user input 
                  mutSeq = scnr.nextLine();             
               }
               
               //Initializes a new int and sets it to the number of mutations
               int mutNum = mutation(seq, mutSeq); 
               //Displays the number of mutations
               System.out.println("The entered sequence has " 
                  + mutNum + " mutations.");
               System.out.print("\n");
               break;
               
            //Runs the case 6 method
            case "6":
               case6(seq, scnr);
               break;
               
            //If invalid user input for program number,
            //tells the user and reprompts 
            default:
               System.out.println("Invalid number.");
               System.out.print("\n");  
         }
      }      
   } // end main   

} // end class
