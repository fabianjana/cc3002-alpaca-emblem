package model.items;

import model.units.IUnit;

/**
 * This class represents a Light Magic Book.
 * <p>
 * Light Magic Books are strong against dark magic books
 * and weak against soul magic books.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class LightMagicBook extends AbstractMagicBook {

    /**
     * Creates a new LightMagicBook item
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
    public LightMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void useOn(IUnit target) {
        if (target.getEquippedItem() == null) {
            target.receiveNormalDamage(this);
        } else {
            target.getEquippedItem().receiveLightMagicBookDamage(this);
        }
    }

    @Override
    public void receiveSoulMagicBookDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    @Override
    public void receiveDarkMagicBookDamage(IEquipableItem item) {
        getOwner().receiveReducedDamage(item);
    }
}
