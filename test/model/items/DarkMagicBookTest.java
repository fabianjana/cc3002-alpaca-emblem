package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Test set for dark magic books
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class DarkMagicBookTest extends AbstractTestItem {

  private MagicBook magicBook;
  private MagicBook wrongMagicBook;
  private Sorcerer sorcerer;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Dark Magic Book";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    magicBook = new DarkMagicBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongMagicBook = new DarkMagicBook("Wrong Magic Book", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    sorcerer = new Sorcerer(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongMagicBook;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return magicBook;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return sorcerer;
  }
}
