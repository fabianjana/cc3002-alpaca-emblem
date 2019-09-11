package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.*;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private final int movement;
  private int hitPoints;
  protected IEquipableItem equippedItem;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param maxHitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   * @param items
   *     the items carried by this unit
   */
  protected AbstractUnit(final int maxHitPoints, final int movement,
                         final Location location, final int maxItems, final IEquipableItem... items) {
    this.maxHitPoints = maxHitPoints;
    this.hitPoints = maxHitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public int getHitPoints() {
    return hitPoints;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipTo(this);
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public void equipBow(final Bow bow) {
    // do nothing
  }

  @Override
  public void equipAxe(final Axe axe) {
    // do nothing
  }

  @Override
  public void equipSpear(final Spear spear) {
    // do nothing
  }

  @Override
  public void equipStaff(final Staff staff) {
    // do nothing
  }

  @Override
  public void equipSword(final Sword sword) {
    // do nothing
  }

  /**
   * Check if the target is out of range.
   */
  private boolean isOutOfRange(Location targetLocation) {
    return getLocation().distanceTo(targetLocation) < getEquippedItem().getMinRange()
            || getLocation().distanceTo(targetLocation) > getEquippedItem().getMaxRange();
  }

  @Override
  public void combat(IUnit target) {
    if (getEquippedItem() == null || isOutOfRange(target.getLocation())) { return; }
    getEquippedItem().useItem(target);
    target.counterAttack(this);
  }

   public void counterAttack(IUnit target) {
     if (getEquippedItem() == null || isOutOfRange(target.getLocation())
        || this.hitPoints == 0) {
       return;
     }
     getEquippedItem().useItem(target);
  }

  @Override
  public void receiveNormalDamage(IEquipableItem item) {
    hitPoints = (hitPoints > item.getPower()) ? (hitPoints - item.getPower()) : 0;
  }

  @Override
  public void receiveNormalHeal(IEquipableItem item) {
    hitPoints = (maxHitPoints < item.getPower()) ? maxHitPoints : (hitPoints + item.getPower());
  }

  @Override
  public void receiveIncreasedDamage(IEquipableItem item) {
    hitPoints = (hitPoints > item.getPower() * 1.5) ? 0 : (int) (hitPoints - item.getPower() * 1.5);
  }

  @Override
  public void receiveReducedDamage(IEquipableItem item) {
    hitPoints = (hitPoints > item.getPower() - 20) ? 0 : (hitPoints - (item.getPower() - 20));
  }
}
