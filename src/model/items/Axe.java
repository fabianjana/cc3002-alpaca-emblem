package model.items;

import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(final IUnit unit) {
    unit.equipAxe(this);
  }

  @Override
  public void useOn(IUnit target) {
    if (target.getEquippedItem() == null) {
      target.receiveNormalDamage(this);
    } else {
      target.getEquippedItem().receiveAxeDamage(this);
    }
  }

  /**
   * Axes receives increased damage from Swords.
   *
   * @param item item which will receive the attack
   */
  @Override
  public void receiveSwordDamage(IEquipableItem item) {
    getOwner().receiveIncreasedDamage(item);
  }

  /**
   * Axes receives reduced damage from Spears.
   *
   * @param item item which will receive the attack
   */
  @Override
  public void receiveSpearDamage(IEquipableItem item) {
    getOwner().receiveReducedDamage(item);
  }
}
