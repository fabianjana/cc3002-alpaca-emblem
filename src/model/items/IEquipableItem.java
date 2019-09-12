package model.items;

import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be equipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * Set the owner for this item.
   *
   * @param unit
   *      the unit that will be the owner of the item
   */
  void setOwner(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * Use this item on the target.
   * <p>
   * The effect of the item change depending if
   * the target have or not an equipped item.
   *
   * @param target who will take the effect of the item
   */
  void useOn(IUnit target);

  /**
   * Receive an attack from an Axe
   *
   * @param item item which will receive the attack
   */
  void receiveAxeDamage(IEquipableItem item);

  /**
   * Receive an attack from a Bow
   *
   * @param item item which will receive the attack
   */
  void receiveBowDamage(IEquipableItem item);

  /**
   * Receive an attack from a Spear
   *
   * @param item item which will receive the attack
   */
  void receiveSpearDamage(IEquipableItem item);

  /**
   * Receive heal from a Staff
   *
   * @param item item which will receive the healing
   */
  void receiveStaffHealing(IEquipableItem item);

  /**
   * Receive an attack from a Sword
   *
   * @param item item which will receive the attack
   */
  void receiveSwordDamage(IEquipableItem item);

  /**
   * Receive an attack from a Light Magic Book
   *
   * @param item item which will receive the attack
   */
  void receiveLightMagicBookDamage(IEquipableItem item);

  /**
   * Receive an attack from a Dark Magic Book
   *
   * @param item item which will receive the attack
   */
  void receiveDarkMagicBookDamage(IEquipableItem item);

  /**
   * Receive an attack from a Soul Magic Book
   *
   * @param item item which will receive the attack
   */
  void receiveSoulMagicBookDamage(IEquipableItem item);
}
