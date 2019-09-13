package model.units;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Archer targetArcher;
  protected Cleric targetCleric;
  protected Fighter targetFighter;
  protected Hero targetHero;
  protected SwordMaster targetSwordMaster;
  protected Sorcerer targetSorcerer;
  protected Field field;
  protected Bow bow, targetBow, overkillBow;
  protected Axe axe, targetAxe, overkillAxe;
  protected Sword sword, targetSword, overkillSword;
  protected Staff staff, targetStaff, overhealStaff;
  protected Spear spear, targetSpear, overkillSpear;
  protected MagicBook lightMagicBook, darkMagicBook, soulMagicBook, targetLightMagicBook, targetDarkMagicBook, targetSoulMagicBook, overkillMagicBook;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetArcher() {
    targetArcher = new Archer(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetCleric() {
    targetCleric = new Cleric(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetFighter() {
     targetFighter = new Fighter(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetHero() {
    targetHero = new Hero(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetSwordMaster() {
    targetSwordMaster = new SwordMaster(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetSorcerer() {
    targetSorcerer = new Sorcerer(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.lightMagicBook = new LightMagicBook("Book", 10, 2, 3);
    this.darkMagicBook = new DarkMagicBook("Book", 10, 2, 3);
    this.soulMagicBook = new SoulMagicBook("Book", 10, 2, 3);

    this.targetAxe = new Axe("Axe", 10, 1, 2);
    this.targetSword = new Sword("Sword", 10, 1, 2);
    this.targetSpear = new Spear("Spear", 10, 1, 2);
    this.targetStaff = new Staff("Staff", 10, 1, 2);
    this.targetBow = new Bow("Bow", 10, 2, 3);
    this.targetLightMagicBook = new LightMagicBook("Book", 10, 2, 3);
    this.targetDarkMagicBook = new DarkMagicBook("Book", 10, 2, 3);
    this.targetSoulMagicBook = new SoulMagicBook("Book", 10, 2, 3);

    this.overkillAxe = new Axe("Axe", 100, 1, 2);
    this.overkillSword = new Sword("Sword", 100, 1, 2);
    this.overkillSpear = new Spear("Spear", 100, 1, 2);
    this.overhealStaff = new Staff("Staff", 100, 1, 2);
    this.overkillBow = new Bow("Bow", 100, 2, 3);
    this.overkillMagicBook = new LightMagicBook("Book", 100, 2, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().addItem(item);
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  @Test
  @Override
  public void equipLightMagicBookTest() {
    checkEquippedItem(getLightMagicBook());
  }

  @Override
  public MagicBook getLightMagicBook() {
    return lightMagicBook;
  }

  @Test
  @Override
  public void equipDarkMagicBookTest() {
    checkEquippedItem(getDarkMagicBook());
  }

  @Override
  public MagicBook getDarkMagicBook() {
    return darkMagicBook;
  }

  @Test
  @Override
  public void equipSoulMagicBookTest() {
    checkEquippedItem(getSoulMagicBook());
  }

  @Override
  public MagicBook getSoulMagicBook() {
    return soulMagicBook;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    setTargetAlpaca();
    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  @Override
  public Archer getTargetArcher() {
    return targetArcher;
  }

  @Override
  public Cleric getTargetCleric() {
    return targetCleric;
  }

  @Override
  public Fighter getTargetFighter() {
    return targetFighter;
  }

  @Override
  public Hero getTargetHero() {
    return targetHero;
  }

  @Override
  public SwordMaster getTargetSwordMaster() {
    return targetSwordMaster;
  }

  @Override
  public Sorcerer getTargetSorcerer() {
    return targetSorcerer;
  }

  @Override
  public void combatTest(IUnit target) {
    getTestUnit().combat(target);
    assertEquals(getTestUnit().getHitPoints(), 50);
    assertEquals(target.getHitPoints(), 50);
  }

  @Override
  @Test
  public void combatAlpacaTest() {
    setTargetAlpaca();
    combatTest(getTargetAlpaca());
  }

  @Override
  @Test
  public void combatArcherTest() {
    setTargetArcher();
    combatTest(getTargetArcher());
  }

  @Override
  @Test
  public void combatClericTest() {
    setTargetCleric();
    combatTest(getTargetCleric());
  }

  @Override
  @Test
  public void combatFighterTest() {
    setTargetFighter();
    combatTest(getTargetFighter());
  }

  @Override
  @Test
  public void combatHeroTest() {
    setTargetHero();
    combatTest(getTargetHero());
  }

  @Override
  @Test
  public void combatSwordMasterTest() {
    setTargetSwordMaster();
    combatTest(getTargetSwordMaster());
  }

  @Override
  @Test
  public void combatSorcererTest() {
    setTargetSorcerer();
    combatTest(getTargetSorcerer());
  }

  @Override
  @Test
  public void tradeItemTest() {
    setTargetAlpaca();

    //Empty inventory
    assertTrue(getTestUnit().getItems().isEmpty());
    assertTrue(getTargetAlpaca().getItems().isEmpty());

    //Trade axe with empty inventory
    getTestUnit().tradeItem(getTargetAlpaca(), axe);
    assertTrue(getTestUnit().getItems().isEmpty());
    assertTrue(getTargetAlpaca().getItems().isEmpty());

    //Adding axe to alpaca, checking the owner
    getTargetAlpaca().addItem(axe);
    assertFalse(getTestUnit().onInventory(axe));
    assertTrue(getTargetAlpaca().onInventory(axe));
    assertEquals(axe.getOwner(), getTargetAlpaca());

    //Trade axe, checking the owner
    getTargetAlpaca().tradeItem(getTestUnit(), axe);
    assertTrue(getTestUnit().onInventory(axe));
    assertFalse(getTargetAlpaca().onInventory(axe));
    assertEquals(axe.getOwner(), getTestUnit());
  }
}
