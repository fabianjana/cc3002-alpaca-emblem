package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines default behavior for Magic Books items.
 * <p>
 * Magic Books are weak and strong against not magic items.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public abstract class AbstractMagicBook extends AbstractItem implements MagicBook {

    /**
     * Constructor for a default magic book
     *
     * @param name     the name of the item
     * @param power    the power of the item
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    protected AbstractMagicBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.equipMagicBook(this);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive increased damage from non-magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveAxeDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive increased damage from non-magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveBowDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive increased damage from non-magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveSpearDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive increased healing from staffs.
     *
     * @param item item which will receive the healing
     */
    @Override
    public void receiveStaffHealing(IEquipableItem item) {
        getOwner().receiveIncreasedHealing(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive increased damage from non-magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveSwordDamage(IEquipableItem item) {
        getOwner().receiveIncreasedDamage(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive normal damage from book magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveDarkMagicBookDamage(IEquipableItem item) {
        getOwner().receiveNormalDamage(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive normal damage from book magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveLightMagicBookDamage(IEquipableItem item) {
        getOwner().receiveNormalDamage(item);
    }

    /**
     * Default behavior for magic book items
     * <p>
     * Magic book items will receive normal damage from book magic items.
     *
     * @param item item which will receive the attack
     */
    @Override
    public void receiveSoulMagicBookDamage(IEquipableItem item) {
        getOwner().receiveNormalDamage(item);
    }
}
