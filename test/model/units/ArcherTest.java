package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.map.Location;
import model.roles.DamageDealer;
import model.roles.NoRole;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   * Also checks if role and owner works correctly
   */
  @Test
  @Override
  public void equipBowTest() {
    //without bow on inventory
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertNull(archer.getEquippedItem());
    assertNull(bow.getOwner());
    assertEquals(archer.getRole(), new NoRole());

    //adding an axe to inventory
    archer.addItem(bow);
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
    assertEquals(archer, bow.getOwner());
    assertEquals(archer.getRole(), new DamageDealer());

    //removing the axe in the inventory
    archer.removeItem(bow);
    assertNull(archer.getEquippedItem());
    assertNull(bow.getOwner());
    assertEquals(archer.getRole(), new NoRole());
  }

  /**
   * Check if combat against alpaca works correctly
   */
  @Test
  @Override
  public void combatAlpacaTest() {
    setTargetAlpaca();
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetAlpaca().getHitPoints());

    // Attack with bow out of range
    archer.equipItem(bow);
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetAlpaca().getHitPoints());

    // Attack with bow in range
    getTargetAlpaca().moveTo(field.getCell(2,1));
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetAlpaca().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, getTargetAlpaca().getHitPoints());
  }

  /**
   * Check if combat against archer works correctly
   */
  @Test
  @Override
  public void combatArcherTest() {
    setTargetArcher();
    getTargetArcher().addItem(targetBow);
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetArcher().getHitPoints());

    // Attack with bow out of range
    archer.equipItem(bow);
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetArcher().getHitPoints());

    // Attack with bow in range, without target equipping item
    getTargetArcher().moveTo(field.getCell(2,1));
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetArcher().getHitPoints());

    // Attack in range with both using item
    getTargetArcher().equipItem(targetBow);
    getTestUnit().combat(getTargetArcher());
    assertEquals(40, getTestUnit().getHitPoints());
    assertEquals(30, getTargetArcher().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(getTargetArcher());
    assertEquals(40, getTestUnit().getHitPoints());
    assertEquals(0, getTargetArcher().getHitPoints());
  }

  /**
   * Check if combat against cleric works correctly
   */
  @Test
  @Override
  public void combatClericTest() {
    setTargetCleric();
    getTargetCleric().addItem(targetStaff);
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetCleric().getHitPoints());

    // Attack with bow out of range
    archer.equipItem(bow);
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetCleric().getHitPoints());

    // Attack with bow in range, without target equipping item
    getTargetCleric().moveTo(field.getCell(2,1));
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetCleric().getHitPoints());

    // Attack in range with both using item
    getTargetCleric().equipItem(targetStaff);
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(30, getTargetCleric().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, getTargetCleric().getHitPoints());
  }

  /**
   * Check if combat against fighter works correctly
   */
  @Test
  @Override
  public void combatFighterTest() {
    setTargetFighter();
    getTargetFighter().addItem(targetSword);
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetFighter().getHitPoints());

    // Attack with bow out of range
    archer.equipItem(bow);
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetFighter().getHitPoints());

    // Attack with bow in range, without target equipping item
    getTargetFighter().moveTo(field.getCell(2,1));
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetFighter().getHitPoints());

    // Attack in range with both using item
    getTargetFighter().equipItem(targetAxe);
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(30, getTargetFighter().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, getTargetFighter().getHitPoints());
  }

  /**
   * Check if combat against hero works correctly
   */
  @Test
  @Override
  public void combatHeroTest() {
    setTargetHero();
    getTargetHero().addItem(targetSpear);
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetHero().getHitPoints());

    // Attack with bow out of range
    archer.equipItem(bow);
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetHero().getHitPoints());

    // Attack with bow in range, without target equipping item
    getTargetHero().moveTo(field.getCell(2,1));
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetHero().getHitPoints());

    // Attack in range with both using item
    getTargetHero().equipItem(targetSpear);
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(30, getTargetHero().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, getTargetHero().getHitPoints());
  }

  /**
   * Check if combat against swordMaster works correctly
   */
  @Test
  @Override
  public void combatSwordMasterTest() {
    setTargetSwordMaster();
    getTargetSwordMaster().addItem(targetSword);
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetSwordMaster().getHitPoints());

    // Attack with bow out of range
    archer.equipItem(bow);
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetSwordMaster().getHitPoints());

    // Attack with bow in range, without target equipping item
    getTargetSwordMaster().moveTo(field.getCell(2,1));
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetSwordMaster().getHitPoints());

    // Attack in range with both using item
    getTargetSwordMaster().equipItem(targetSword);
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(30, getTargetSwordMaster().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, getTargetSwordMaster().getHitPoints());
  }

  /**
   * Checks if combat with sorcerer works correctly
   */
  @Test
  @Override
  public void combatSorcererTest() {
    setTargetSorcerer();
    IUnit target = getTargetSorcerer();
    target.addItem(targetLightMagicBook);
    getTestUnit().addItem(bow);
    getTestUnit().addItem(overkillBow);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(1,0));
    archer.equipItem(bow);
    target.equipItem(targetLightMagicBook);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(2,0));
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(35, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillBow);
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }
}