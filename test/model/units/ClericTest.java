package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Axe;
import model.roles.NoRole;
import model.roles.Support;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    //without item on inventory
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertNull(cleric.getEquippedItem());
    assertNull(staff.getOwner());
    assertEquals(cleric.getRole(), new NoRole());

    //adding item to inventory
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
    assertEquals(cleric, staff.getOwner());
    assertEquals(cleric.getRole(), new Support());

    //removing the item in the inventory
    cleric.removeItem(staff);
    assertNull(cleric.getEquippedItem());
    assertNull(staff.getOwner());
    assertEquals(cleric.getRole(), new NoRole());
  }

  @Override
  public void combatTest(IUnit target) {
    target.receiveNormalDamage(new Axe("Axe", 40, 1,2));
    getTestUnit().addItem(staff);
    getTestUnit().addItem(overhealStaff);

    // Heal without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(10, target.getHitPoints());

    // Heal out of range
    target.moveTo(field.getCell(2,1));
    cleric.equipItem(staff);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(10, target.getHitPoints());

    // Heal in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(20, target.getHitPoints());

    // Overheal test
    getTestUnit().equipItem(overhealStaff);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());
  }

  @Test
  @Override
  public void combatArcherTest() {
    setTargetArcher();
    IUnit target = getTargetArcher();
    target.receiveNormalDamage(new Axe("Axe", 40, 1,2));
    target.addItem(targetBow);
    getTestUnit().addItem(staff);
    getTestUnit().addItem(overhealStaff);
    getTestUnit().equipItem(staff);
    target.equipItem(targetBow);

    // Heal in range
    target.moveTo(field.getCell(1,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(20, target.getHitPoints());

    // Overheal test
    getTestUnit().equipItem(overhealStaff);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());
  }

  @Test
  @Override
  public void combatSorcererTest() {
    setTargetSorcerer();
    IUnit target = getTargetSorcerer();
    target.receiveNormalDamage(new Axe("Axe", 40, 1,2));
    target.addItem(targetLightMagicBook);
    getTestUnit().addItem(staff);
    getTestUnit().addItem(overhealStaff);

    // Heal without any item equipped
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(10, target.getHitPoints());

    // Heal out of range
    target.moveTo(field.getCell(2,1));
    cleric.equipItem(staff);
    target.equipItem(targetLightMagicBook);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(10, target.getHitPoints());

    // Heal in range
    target.moveTo(field.getCell(2,0));
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(25, target.getHitPoints());

    // Overheal test
    getTestUnit().equipItem(overhealStaff);
    getTestUnit().combat(target);
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(50, target.getHitPoints());
  }
}