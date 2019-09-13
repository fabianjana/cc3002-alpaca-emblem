package model.roles;

import model.units.IUnit;

/**
 * This class represents a DamageDealer role.
 * <p>
 * DamageDealer units can combat and counter attack.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class DamageDealer implements Role {

    /**
     * Creates a new DamageDealer role
     */
    public DamageDealer() {
    }

    /**
     * DamageDealer units can combat and receive counter attacks.
     *
     * @param unit unit who entered in combat
     * @param target target of the combat
     */
    @Override
    public void combat(IUnit unit, IUnit target) {
        if (unit.useItem(target))
            target.getRole().counterAttack(unit, target);
    }

    /**
     * DamageDealer units can counter attack.
     *
     * @param unit unit who will receive the counter attack
     * @param target unit who will execute the counter attack
     */
    @Override
    public void counterAttack(IUnit unit, IUnit target) {
        target.useItem(unit);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DamageDealer;
    }
}
