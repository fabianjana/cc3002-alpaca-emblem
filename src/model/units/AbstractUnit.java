package model.units;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.*;
import model.map.Location;
import model.roles.NoRole;
import model.roles.Role;

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
  private final int maxItems;
  private int hitPoints;
  protected IEquipableItem equippedItem;
  private Location location;
  private Role role;

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
    this.maxItems = maxItems;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.role = new NoRole();
  }

  @Override
  public boolean onInventory(IEquipableItem item) {
    return items.contains(item);
  }

  @Override
  public void addItem(IEquipableItem item) {
    if (inventoryNotFull()) {
      items.add(item);
      item.setOwner(this);
    }
  }

  @Override
  public void removeItem(IEquipableItem item) {
    item.setOwner(null);
    if (item.equals(getEquippedItem())) unequipItem();
    items.remove(item);
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
    if (onInventory(item))
      item.equipTo(this);
  }

  public void unequipItem() {
    setEquippedItem(null);
    setRole(new NoRole());
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

  @Override
  public void equipMagicBook(final MagicBook MagicBook) {
    //do nothing
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
  public Role getRole() {
    return role;
  }

  @Override
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Check if the target is on range of unit's equipped item
   */
  private boolean outOfRange(Location targetLocation) {
    return getLocation().distanceTo(targetLocation) < getEquippedItem().getMinRange()
            || getLocation().distanceTo(targetLocation) > getEquippedItem().getMaxRange();
  }

  @Override
  public void combat(IUnit target) {
    getRole().combat(this, target);
  }

  @Override
  public boolean useItem(IUnit target) {
    if (outOfRange(target.getLocation())) return false;
    getEquippedItem().useOn(target);
    return true;
  }

  /**
   * Unit dies
   *
   * @return the new hitPoints of the unit
   */
  private int die() {
    setRole(new NoRole());
    return 0;
  }

  @Override
  public void receiveNormalDamage(IEquipableItem item) {
    hitPoints = (hitPoints > item.getPower()) ? (hitPoints - item.getPower()) : die();
  }

  @Override
  public void receiveNormalHeal(IEquipableItem item) {
    int healing = Math.min(hitPoints + item.getPower(),maxHitPoints);
    hitPoints = (maxHitPoints > hitPoints) ? healing : maxHitPoints;
  }

  /**
   * If the item power is odd, the healing effect will round down.
   *
   * @param item the item which will heal this unit.
   */
  @Override
  public void receiveIncreasedHealing(IEquipableItem item) {
    int increasedHealing = Math.min((int) (hitPoints + item.getPower() * 1.5), maxHitPoints);
    hitPoints = (maxHitPoints > hitPoints) ? increasedHealing : maxHitPoints;
  }

  /**
   * If the item power is odd, the damage effect will round down.
   *
   * @param item the item which will attack this unit.
   */
  @Override
  public void receiveIncreasedDamage(IEquipableItem item) {
    hitPoints = (hitPoints > item.getPower() * 1.5) ? (int) (hitPoints - item.getPower() * 1.5) : die();
  }

  @Override
  public void receiveReducedDamage(IEquipableItem item) {
    int reducedDamage = Math.max(0, item.getPower() - 20);
    hitPoints = (hitPoints > reducedDamage) ? (hitPoints - reducedDamage) : die();
  }

  @Override
  public void tradeItem(IUnit target, IEquipableItem item) {
    if (onInventory(item) && target.inventoryNotFull()) {
      this.removeItem(item);
      target.addItem(item);
    }
  }

  @Override
  public boolean inventoryNotFull() {
    return items.size() < maxItems;
  }
}
