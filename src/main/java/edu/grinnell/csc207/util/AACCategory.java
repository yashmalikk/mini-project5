import java.util.NoSuchElementException;

/**
 * Represents the mappings for a single category of items that should
 * be displayed.
 * 
 * @author Catie Baker & Yash Malik
 *
 */
public class AACCategory implements AACPage {
  private String name; // Category name
  private AssociativeArray<String, String> items; // Associative array for image/text pairs

  /**
   * Creates a new empty category with the given name.
   * 
   * @param name the name of the category
   */
  public AACCategory(String name) {
    this.name = name;
    this.items = new AssociativeArray<>();
  }

  /**
   * Adds the image location, text pairing to the category.
   * 
   * @param imageLoc the location of the image
   * @param text     the text that image should speak
   */
  public void addItem(String imageLoc, String text) {
    items.set(imageLoc, text); // Set the image location and associated text
  }

  /**
   * Returns an array of all the images in the category.
   * 
   * @return the array of image locations; if there are no images,
   *         it should return an empty array
   */
  public String[] getImageLocs() {
    String[] imageLocs = new String[items.size()]; // Create an array to hold the keys
    for (int i = 0; i < items.size(); i++) {
      imageLocs[i] = items.pairs[i].key; // Collect image locations from the associative array
    }
    return imageLocs;
  }

  /**
   * Returns the name of the category.
   * 
   * @return the name of the category
   */
  public String getCategory() {
    return name;
  }

  /**
   * Returns the text associated with the given image in this category.
   * 
   * @param imageLoc the location of the image
   * @return the text associated with the image
   * @throws NoSuchElementException if the image provided is not in the current category
   */
  public String select(String imageLoc) {
    try {
      return items.get(imageLoc); // Retrieve the text for the specified image location
    } catch (KeyNotFoundException e) {
      throw new NoSuchElementException("Image not found in category: " + imageLoc);
    }
  }

  /**
   * Determines if the provided image is stored in the category.
   * 
   * @param imageLoc the location of the image
   * @return true if it is in the category, false otherwise
   */
  public boolean hasImage(String imageLoc) {
    return items.hasKey(imageLoc); // Check if the associative array contains the image location
  }
}
