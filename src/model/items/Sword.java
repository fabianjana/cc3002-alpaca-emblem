package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(final IUnit unit) {
    unit.equipSword(this);
  }

  @Override
  public void useOn(IUnit target) {
    if (target.getEquippedItem() == null) {
      target.receiveNormalDamage(this);
    } else {
      target.getEquippedItem().receiveSwordDamage(this);
    }
  }

  /**
   * Swords receives reduced damage from Axes.
   *
   * @param item item which will receive the attack
   */
  @Override
  public void receiveAxeDamage(IEquipableItem item) {
    getOwner().receiveReducedDamage(item);
  }

  /**
   * Swords receives increased damage from Spears.
   *
   * @param item item which will receive the attack
   */
  @Override
  public void receiveSpearDamage(IEquipableItem item) {
    getOwner().receiveIncreasedDamage(item);
  }
}
