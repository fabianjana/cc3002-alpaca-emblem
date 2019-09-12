package model.items;

import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

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
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(final IUnit unit) {
    unit.equipSpear(this);
  }

  @Override
  public void useOn(IUnit target) {
    if (target.getEquippedItem() == null) {
      target.receiveNormalDamage(this);
    } else {
      target.getEquippedItem().receiveSpearDamage(this);
    }
  }

  /**
   * Spears receives increased damage from Axes.
   *
   * @param item item which will receive the attack
   */
  @Override
  public void receiveAxeDamage(IEquipableItem item) {
    getOwner().receiveIncreasedDamage(item);
  }

  /**
   * Spears receives reduced damage from Swords.
   *
   * @param item item which will receive the attack
   */
  @Override
  public void receiveSwordDamage(IEquipableItem item) {
    getOwner().receiveReducedDamage(item);
  }
}
