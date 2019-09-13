package model.roles;

import model.units.IUnit;

/**
 * This class represents a Support role.
 * <p>
 * Support units can combat but not counter attack, also support units
 * can't receive counter attacks.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class Support implements Role {

    /**
     * Creates a new Support role
     */
    public Support() {
    }

    /**
     * Units with Support role don't receive counter attacks
     *
     * @param unit unit who entered in combat
     * @param target target of the combat
     */
    @Override
    public void combat(IUnit unit, IUnit target) {
        unit.useItem(target);
    }

    /**
     * Support units can't counterAttack
     *
     * @param unit unit who will receive the counter attack
     * @param target unit who will execute the counter attack
     */
    @Override
    public void counterAttack(IUnit unit, IUnit target) {
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Support;
    }
}
