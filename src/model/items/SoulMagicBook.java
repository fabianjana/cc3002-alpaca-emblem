package model.items;

import model.units.IUnit;

/**
 * This class represents a Soul Magic Book.
 * <p>
 * Soul Magic Books are strong against light magic books
 * and weak against dark magic books.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class SoulMagicBook extends AbstractMagicBook {

    /**
     * Creates a new SoulMagicBook item
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
    public SoulMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void useOn(IUnit target) {
        if (target.getEquippedItem() == null) {
            target.receiveNormalDamage(this);
        } else {
            target.getEquippedItem().receiveSoulMagicBookDamage(this);
        }
    }

    @Override
    public void receiveDarkMagicBookDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    @Override
    public void receiveLightMagicBookDamage(IEquipableItem item) {
        getOwner().receiveReducedDamage(item);
    }
}
