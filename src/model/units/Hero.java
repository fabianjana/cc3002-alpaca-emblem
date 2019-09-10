package model.units;

import model.items.IEquipableItem;
import model.items.Spear;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new hero.
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
  public Hero(final int maxHitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
  }

  @Override
  public void equipSpear(final Spear spear) {
    spear.setOwner(this);
    equippedItem = spear;
  }
}
