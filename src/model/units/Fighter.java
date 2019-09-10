package model.units;

import model.items.Axe;
import model.items.IEquipableItem;
import model.items.Sword;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  /**
   * Creates a new fighter.
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
  public Fighter(final int maxHitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
  }

  @Override
  public void equipSword(final Sword sword) {
    sword.setOwner(this);
    equippedItem = sword;
  }
}
