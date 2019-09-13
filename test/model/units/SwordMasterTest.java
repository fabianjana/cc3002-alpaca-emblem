package model.units;

import model.roles.DamageDealer;
import model.roles.NoRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  /**
   * Checks if the sword is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipSwordTest() {
    //without item on inventory
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertNull(swordMaster.getEquippedItem());
    assertNull(sword.getOwner());
    assertEquals(swordMaster.getRole(), new NoRole());

    //adding item to inventory
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
    assertEquals(swordMaster, sword.getOwner());
    assertEquals(swordMaster.getRole(), new DamageDealer());

    //removing the item in the inventory
    swordMaster.removeItem(sword);
    assertNull(swordMaster.getEquippedItem());
    assertNull(sword.getOwner());
    assertEquals(swordMaster.getRole(), new NoRole());
  }

  @Override
  public void combatTest(IUnit target) {
    getTestUnit().addItem(sword);
    getTestUnit().addItem(overkillSword);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    swordMaster.equipItem(sword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }

  @Test
  @Override
  public void combatArcherTest() {
    setTargetArcher();
    IUnit target = getTargetArcher();
    target.addItem(targetBow);
    getTestUnit().addItem(sword);
    getTestUnit().addItem(overkillSword);
    getTestUnit().equipItem(sword);
    target.equipItem(targetBow);

    // Attack in range (out of archer range)
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }

  @Test
  @Override
  public void combatFighterTest() {
    setTargetFighter();
    IUnit target = getTargetFighter();
    target.addItem(targetAxe);
    getTestUnit().addItem(sword);
    getTestUnit().addItem(overkillSword);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    target.equipItem(targetAxe);
    swordMaster.equipItem(sword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(35, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }

  @Test
  @Override
  public void combatHeroTest() {
    setTargetHero();
    IUnit target = getTargetHero();
    target.addItem(targetSpear);
    getTestUnit().addItem(sword);
    getTestUnit().addItem(overkillSword);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    target.equipItem(targetSpear);
    swordMaster.equipItem(sword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSword);
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }

  @Test
  @Override
  public void combatSorcererTest() {
    setTargetSorcerer();
    IUnit target = getTargetSorcerer();
    target.addItem(targetSoulMagicBook);
    getTestUnit().addItem(sword);
    getTestUnit().addItem(overkillSword);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    swordMaster.equipItem(sword);
    target.equipItem(targetSoulMagicBook);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(2,0));
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(35, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSword);
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }
}