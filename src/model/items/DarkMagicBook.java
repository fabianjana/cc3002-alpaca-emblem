package model.items;

import model.units.IUnit;

/**
 * This class represents a Dark Magic Book.
 * <p>
 * Dark Magic Books are strong against soul magic books
 * and weak against light magic books.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class DarkMagicBook extends AbstractMagicBook {

    /**
     * Creates a new DarkMagicBook item
     *
     * @param name
     *     the name of the magic book
     * @param power
     *     the damage of the magic book
     * @param minRange
     *     the minimum range of the magic book
     * @param maxRange
     *     the maximum range of the magic book
     */
    public DarkMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void useOn(IUnit target) {
        if (target.getEquippedItem() == null) {
            target.receiveNormalDamage(this);
        } else {
            target.getEquippedItem().receiveDarkMagicBookDamage(this);
        }
    }

    @Override
    public void receiveLightMagicBookDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    @Override
    public void receiveSoulMagicBookDamage(IEquipableItem item) {
        getOwner().receiveReducedDamage(item);
    }
}
