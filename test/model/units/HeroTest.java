package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.roles.DamageDealer;
import model.roles.NoRole;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    //without item on inventory
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertNull(hero.getEquippedItem());
    assertNull(spear.getOwner());
    assertEquals(hero.getRole(), new NoRole());

    //adding item to inventory
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
    assertEquals(hero, spear.getOwner());
    assertEquals(hero.getRole(), new DamageDealer());

    //removing the item in the inventory
    hero.removeItem(spear);
    assertNull(hero.getEquippedItem());
    assertNull(spear.getOwner());
    assertEquals(hero.getRole(), new NoRole());
  }

  @Override
  public void combatTest(IUnit target) {
    getTestUnit().addItem(spear);
    getTestUnit().addItem(overkillSpear);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    hero.equipItem(spear);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSpear);
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
    target.equipItem(targetBow);
    getTestUnit().addItem(spear);
    getTestUnit().addItem(overkillSpear);
    getTestUnit().equipItem(spear);

    // Attack in range (out of archer range)
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(40, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSpear);
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
    getTestUnit().addItem(spear);
    getTestUnit().addItem(overkillSpear);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    hero.equipItem(spear);
    target.equipItem(targetAxe);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSpear);
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }

  @Test
  @Override
  public void combatSwordMasterTest() {
    setTargetSwordMaster();
    IUnit target = getTargetSwordMaster();
    target.addItem(targetSword);
    getTestUnit().addItem(spear);
    getTestUnit().addItem(overkillSpear);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    hero.equipItem(spear);
    target.equipItem(targetSword);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(35, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSpear);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }

  @Test
  @Override
  public void combatSorcererTest() {
    setTargetSorcerer();
    IUnit target = getTargetSorcerer();
    target.addItem(targetDarkMagicBook);
    getTestUnit().addItem(spear);
    getTestUnit().addItem(overkillSpear);

    // Attack without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack out of range
    target.moveTo(field.getCell(2,1));
    hero.equipItem(spear);
    target.equipItem(targetDarkMagicBook);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());

    // Attack in range
    target.moveTo(field.getCell(2,0));
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(35, target.getHitPoints());

    // Overkill test
    getTestUnit().equipItem(overkillSpear);
    getTestUnit().combat(target);
    assertEquals(35, getTestUnit().getHitPoints());
    assertEquals(0, target.getHitPoints());
  }
}