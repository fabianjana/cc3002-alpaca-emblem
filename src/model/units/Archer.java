package model.units;

import model.items.Bow;
import model.items.IEquipableItem;
import model.map.Location;
import model.roles.DamageDealer;

/**
 * This class represents an <i>Archer</i> type unit.
 * <p>
 * This kind of unit <b>can only use bows</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Archer extends AbstractUnit {

  /**
   * Creates a new archer
   *
   * @param maxHitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int maxHitPoints, final int movement, final Location location,
      final IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
  }

  @Override
  public void equipBow(final Bow bow) {
    setEquippedItem(bow);
    setRole(new DamageDealer());
  }
}
