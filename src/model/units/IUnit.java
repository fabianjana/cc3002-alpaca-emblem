package model.units;

import java.util.List;

import controller.Tactician;
import model.items.*;
import model.map.Location;
import model.roles.Role;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Checks if an item is in the inventory
   * <p>
   * Don't need to override the equals method from object
   * because our intention is compare them by reference.
   *
   * @param item item to check
   * @return true if the item is in the unit inventory
   */
  boolean onInventory(IEquipableItem item);

  /**
   * Add an item to the inventory if it isn't full.
   *
   * @param item item to add
   */
  void addItem(IEquipableItem item);

  /**
   * Remove an item from the inventory
   *
   * @param item to remove
   */
  void removeItem(IEquipableItem item);

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getHitPoints();

  /**
   * @return max hp of the unit
   */
  int getMaxHitPoints();

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * Equip a bow to this unit.
   */
  void equipBow(final Bow bow);

  /**
   * Equip an axe to this unit.
   */
  void equipAxe(final Axe axe);

  /**
   * Equip a spear to this unit.
   */
  void equipSpear(final Spear spear);

  /**
   * Equip a staff to this unit.
   */
  void equipStaff(final Staff staff);

  /**
   * Equip a sword to this unit.
   */
  void equipSword(final Sword sword);

  /**
   * Equip a magic book to this unit
   */
  void equipMagicBook(final MagicBook MagicBook);

  /**
   * @return the current role of the unit
   */
  Role getRole();

  /**
   * @param role the new role of the unit
   */
  void setRole(Role role);

  /**
   * Establish a combat between this unit and the target
   *
   * @param target the unit who will be targeted on the combat
   */
  void combat(IUnit target);

  /**
   * If the target is on range of the equipped item, use the equipped item on the target unit
   *
   * @param target target who will receive this unit equipped item effect.
   * @return true if the item was used successfully
   */
  boolean useItem(IUnit target);

  /**
   * Receive a no-modified attack
   *
   * @param item the item which will attack this unit.
   */
  void receiveNormalDamage(IEquipableItem item);

  /**
   * Receive a no-modified heal
   *
   * @param item the item which will heal this unit.
   */
  void receiveNormalHeal(IEquipableItem item);

  /**
   * Receive heal from a item which is strong against the unit equipped item
   *
   * @param item the item which will heal this unit.
   */
  void receiveIncreasedHealing(IEquipableItem item);

  /**
   * Receive an attack from a item which is strong against the unit equipped item
   *
   * @param item the item which will attack this unit.
   */
  void receiveIncreasedDamage(IEquipableItem item);

  /**
   * Receive an attack from a item which is weak against the unit equipped item
   *
   * @param item the item which will attack this unit.
   */
  void receiveReducedDamage(IEquipableItem item);

  /**
   * Gives an item on the unit inventory to the target unit
   *
   * @param target unit who will receive the item
   * @param item item which will be traded
   */
  void tradeItem(IUnit target, IEquipableItem item);

  /**
   * @return true if inventory have 1 or more slots
   */
  boolean inventoryNotFull();

  /**
   * Set a tactician as the owner of this unit
   * @param owner the tactician in possession of this unit
   */
  void setOwner(Tactician owner);

  /**
   * @return the owner of this unit
   */
  Tactician getOwner();

  /**
   * Dismark the movement mark of this unit
   */
  void resetMovements();
}
