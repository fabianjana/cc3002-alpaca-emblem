package model.units;

import model.items.IEquipableItem;
import model.items.MagicBook;
import model.map.Location;
import model.roles.DamageDealer;

/**
 * This class represents a <i>Sorcerer</i> type unit.
 * <p>
 * This kind of unit <b>can only use magic books</b>.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class Sorcerer extends AbstractUnit {

    /**
     * Creates a new sorcerer
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
    public Sorcerer(final int maxHitPoints, final int movement, final Location location,
                  final IEquipableItem... items) {
        super(maxHitPoints, movement, location, 3, items);
    }

    @Override
    public void equipMagicBook(final MagicBook magicBook) {
        setEquippedItem(magicBook);
        setRole(new DamageDealer());
    }
}
