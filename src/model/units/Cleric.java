package model.units;

import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;
import model.roles.Support;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new cleric.
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
  public Cleric(final int maxHitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
  }

  @Override
  public void equipStaff(final Staff staff) {
    setEquippedItem(staff);
    setRole(new Support());
  }
}
