package model.units;


import model.items.*;
import model.map.Field;
import org.junit.jupiter.api.Test;

/**
 * Interface that defines the common behaviour of all the test for the units classes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface ITestUnit {

  /**
   * Set up the game field
   */
  void setField();

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  void setTestUnit();

  /**
   * Set up an Alpaca target
   */
  void setTargetAlpaca();

  /**
   * Set up a Archer target
   */
  void setTargetArcher();

  /**
   * Set up a Cleric target
   */
  void setTargetCleric();

  /**
   * Set up a Fighter target
   */
  void setTargetFighter();

  /**
   * Set up a Hero target
   */
  void setTargetHero();

  /**
   * Set up a SwordMaster target
   */
  void setTargetSwordMaster();

  /**
   * Creates a set of testing weapons
   */
  void setWeapons();

  /**
   * Checks that the constructor works properly.
   */
  @Test
  void constructorTest();

  /**
   * @return the current unit being tested
   */
  IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  void equipAxeTest();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedItem(IEquipableItem item);

  /**
   * @return the test axe
   */
  Axe getAxe();

  @Test
  void equipSwordTest();

  /**
   * @return the test sword
   */
  Sword getSword();

  @Test
  void equipSpearTest();

  /**
   * @return the test spear
   */
  Spear getSpear();

  @Test
  void equipStaffTest();

  /**
   * @return the test staff
   */
  Staff getStaff();

  @Test
  void equipBowTest();

  /**
   * @return the test bow
   */
  Bow getBow();

  /**
   * Checks if the unit moves correctly
   */
  @Test
  void testMovement();

  /**
   * @return the test field
   */
  Field getField();

  /**
   * Equip a valid item to this unit
   */
  void equipTestItem();

  /**
   * @return the target Alpaca
   */
  Alpaca getTargetAlpaca();

  /**
   * @return the target Archer
   */
  Archer getTargetArcher();

  /**
   * @return the target Cleric
   */
  Cleric getTargetCleric();

  /**
   * @return the target Fighter
   */
  Fighter getTargetFighter();

  /**
   * @return the target Hero
   */
  Hero getTargetHero();

  /**
   * @return the target SwordMaster
   */
  SwordMaster getTargetSwordMaster();

  /**
   * Checks if combat works correctly against a target
   */
  void combatTest(IUnit target);

  /**
   * Checks if combat works correctly against an Alpaca Unit
   */
  @Test
  void combatAlpacaTest();

  /**
   * Checks if combat works correctly against an Archer Unit
   */
  @Test
  void combatArcherTest();

  /**
   * Checks if combat works correctly against an Cleric Unit
   */
  @Test
  void combatClericTest();

  /**
   * Checks if combat works correctly against an Fighter Unit
   */
  @Test
  void combatFighterTest();

  /**
   * Checks if combat works correctly against an Hero Unit
   */
  @Test
  void combatHeroTest();

  /**
   * Checks if combat works correctly against an SwordMaster Unit
   */
  @Test
  void combatSwordMasterTest();
}
