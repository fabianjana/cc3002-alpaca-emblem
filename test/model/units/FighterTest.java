package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.roles.DamageDealer;
import model.roles.NoRole;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   * Also checks if role and owner works correctly
   */
  @Test
  @Override
  public void equipAxeTest() {
    //without axe on inventory
    assertNull(fighter.getEquippedItem());
    fighter.equipItem(axe);
    assertNull(fighter.getEquippedItem());
    assertNull(axe.getOwner());
    assertEquals(fighter.getRole(), new NoRole());

    //adding an axe to inventory
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
    assertEquals(fighter, axe.getOwner());
    assertEquals(fighter.getRole(), new DamageDealer());

    //removing the axe in the inventory
    fighter.removeItem(axe);
    assertNull(fighter.getEquippedItem());
    assertNull(axe.getOwner());
    assertEquals(fighter.getRole(), new NoRole());
  }

  /**
   * Check if combat against alpaca works correctly
   */
  @Test
  @Override
  public void combatAlpacaTest() {
    setTargetAlpaca();
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetAlpaca().getHitPoints());

    // Attack out of range
    getTargetAlpaca().moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetAlpaca().getHitPoints());

    // Attack in range
    getTargetAlpaca().moveTo(field.getCell(1,0));
    getTestUnit().combat(getTargetAlpaca());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetAlpaca().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillAxe);
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
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetArcher().getHitPoints());

    // Attack out of range
    getTargetArcher().moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetArcher().getHitPoints());

    // Attack in range, without target equipping item
    getTargetArcher().moveTo(field.getCell(1,0));
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetArcher().getHitPoints());

    // Attack in range with both using item
    getTargetArcher().equipItem(targetBow);
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(30, getTargetArcher().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillAxe);
    getTestUnit().combat(getTargetArcher());
    assertEquals(50, getTestUnit().getHitPoints());
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
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetCleric().getHitPoints());

    // Attack out of range
    getTargetCleric().moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetCleric().getHitPoints());

    // Attack in range, without target equipping item
    getTargetCleric().moveTo(field.getCell(1,0));
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetCleric().getHitPoints());

    // Attack in range with both using item
    getTargetCleric().equipItem(targetStaff);
    getTestUnit().combat(getTargetCleric());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(30, getTargetCleric().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillAxe);
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
    getTargetFighter().addItem(targetAxe);
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetFighter().getHitPoints());

    // Attack out of range
    getTargetFighter().moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetFighter().getHitPoints());

    // Attack  in range, without target equipping item
    getTargetFighter().moveTo(field.getCell(1,0));
    getTestUnit().combat(getTargetFighter());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetFighter().getHitPoints());

    // Attack in range with both using item
    getTargetFighter().equipItem(targetAxe);
    getTestUnit().combat(getTargetFighter());
    assertEquals(40, getTestUnit().getHitPoints());
    assertEquals(30, getTargetFighter().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillAxe);
    getTestUnit().combat(getTargetFighter());
    assertEquals(40, getTestUnit().getHitPoints());
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
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetHero().getHitPoints());

    // Attack out of range
    getTargetHero().moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetHero().getHitPoints());

    // Attack in range, without target equipping item
    getTargetHero().moveTo(field.getCell(1,0));
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetHero().getHitPoints());

    // Attack in range with both using item
    getTargetHero().equipItem(targetSpear);
    getTestUnit().combat(getTargetHero());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(25, getTargetHero().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillAxe);
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
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped, out of range
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetSwordMaster().getHitPoints());

    // Attack out of range
    getTargetSwordMaster().moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, getTargetSwordMaster().getHitPoints());

    // Attack in range, without target equipping item
    getTargetSwordMaster().moveTo(field.getCell(1,0));
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, getTargetSwordMaster().getHitPoints());

    // Attack in range with both using item
    getTargetSwordMaster().equipItem(targetSword);
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(40, getTargetSwordMaster().getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillAxe);
    getTestUnit().combat(getTargetSwordMaster());
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, getTargetSwordMaster().getHitPoints());
  }

  @Test
  @Override
  public void combatSorcererTest() {
    setTargetSorcerer();
    IUnit target = getTargetSorcerer();
    target.addItem(targetLightMagicBook);
    getTestUnit().addItem(axe);
    getTestUnit().addItem(overkillAxe);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    fighter.equipItem(axe);
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
    getTestUnit().equipItem(overkillAxe);
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());

  }
}