/**
 * Creates a set of mappings of an AAC that has two levels,
 * one for categories and then within each category, it has
 * images that have associated text to be spoken. This class
 * provides the methods for interacting with the categories
 * and updating the set of images that would be shown and handling
 * an interactions.
 * 
 * @author Catie Baker & Yash Malik
 *
 */
public class AACMappings implements AACPage {

  /**
   * Map for name and categories.
   */
  AssociativeArray<String, AACCategory> maps;

  /**
   * present working category
   */
  AACCategory pwc;

  AACCategory home;

  /**
   * Creates a set of mappings for the AAC based on the provided
   * file. The file is read in to create categories and fill each
   * of the categories with initial items. The file is formatted as
   * the text location of the category followed by the text name of the
   * category and then one line per item in the category that starts with
   * > and then has the file name and text of that image
   * 
   * for instance:
   * img/food/plate.png food
   * >img/food/icons8-french-fries-96.png french fries
   * >img/food/icons8-watermelon-96.png watermelon
   * img/clothing/hanger.png clothing
   * >img/clothing/collaredshirt.png collared shirt
   * 
   * represents the file with two categories, food and clothing
   * and food has french fries and watermelon and clothing has a 
   * collared shirt
   * @param filename the name of the file that stores the mapping information
   */
  public AACMappings(String filename) {
    this.maps = new AssociativeArray<String, AACCategory>();
    this.home = new AACCategory("");
    this.pwc = this.home;

    String[] lines = new String[2];
    String line = new String();

    try {
      File f = new File(filename);
      Scanner scan = new Scanner(f);
      while (scan.hasNextLine()) {
        line = scan.nextLine();
        lines = line.split(" ");
        if (!(line.substring(0,1).equals(">"))) {
          this.maps.set(lines[0], new AACCategory(lines[1]));
          this.pwc.addItem(lines[0], lines[1]);
          this.pwc = this.maps.get(lines[0]);
        } else {
          this.pwc.addItem(lines[0].substring(1), lines[1]);
        } // if-else block
      } // while loop
      this.pwc = this.home;
      scan.close();
    } catch  (Exception e) {
      System.err.println("Error");
    } // try-catch block
  } // AACMappings(String)


  /**
   * Given the image location selected, it determines the action to be
   * taken. This can be updating the information that should be displayed
   * or returning text to be spoken. If the image provided is a category, 
   * it updates the AAC's current category to be the category associated 
   * with that image and returns the empty string. If the AAC is currently
   * in a category and the image provided is in that category, it returns
   * the text to be spoken.
   * @param imageLoc the location where the image is stored
   * @return if there is text to be spoken, it returns that information, otherwise
   * it returns the empty string
   * @throws NoSuchElementException if the image provided is not in the current 
   * category
   */
  public String select(String imageLoc) {
    if (this.maps.hasKey(imageLoc)) {
      try {
        this.pwc = this.maps.get(imageLoc);
      } catch (Exception e) {
      } // try-catch block
    } // if check for key
    if (this.pwc.hasImage(imageLoc)) {
      return this.pwc.select(imageLoc);
    } //check for image
    return "";
  } // select(String)
	
  /**
   * Provides an array of all the images in the pwc category
   * @return the array of images in the pwc category
   */
  public String[] getImageLocs() {
	  return this.pwc.getImageLocs();
  } // getImageLocs()
	
  /**
   * Resets the pwc category of the AAC back to the default
   */
  public void reset() {
    this.pwc = this.home;
  } // reset()
	
  /**
   * Writes the ACC mappings stored to a file. The file is formatted as
   * the text location of the category followed by the text name of the
   * category and then one line per item in the category that starts with
   * > and then has the file name and text of that image
   * 
   * for instance:
   * img/food/plate.png food
   * >img/food/icons8-french-fries-96.png french fries
   * >img/food/icons8-watermelon-96.png watermelon
   * img/clothing/hanger.png clothing
   * >img/clothing/collaredshirt.png collared shirt
   * 
   * represents the file with two categories, food and clothing
   * and food has french fries and watermelon and clothing has a 
   * collared shirt
   * 
   * @param filename the name of the file to write the
   * AAC mapping to
   */
  public void writeToFile(String filename) {
	
  } // writeToFile(String)
	
  /**
   * Adds the mapping to the pwc category (or the default category if
   * that is the pwc category)
   * @param imageLoc the location of the image
   * @param text the text associated with the image
   */
  public void addItem(String imageLoc, String text) {
    if (this.pwc == this.home) {
      try {
        this.maps.set(imageLoc, new AACCategory(text));
      } catch (Exception e) {
      }
    }
    this.pwc.addItem(imageLoc, text);
  } // addItem(String, String)

  /**
   * Gets the name of the pwc category
   * @return returns the pwc category
   */
  public String getCategory() {
	  return this.pwc.getCategory();
  } // getCategory()

  /**
   * Determines if the provided image is in the set of images that
   * can be displayed and false otherwise
   * @param imageLoc the location of the category
   * @return true if it is in the set of images that
   * can be displayed, false otherwise
   */
  public boolean hasImage(String imageLoc) {
	  return this.pwc.hasImage(imageLoc);
  } // hasImage(String)
} // class AACMappings