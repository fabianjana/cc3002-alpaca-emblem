package model.roles;

import model.units.IUnit;

/**
 * This interface represents the <i>roles</i> that an unit have in game.
 * <p>
 * Combats execution depends on the role of the unit who executed it and on the role of the target unit.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public interface Role {

    /**
     * Establish a combat between a unit and his target
     *
     * @param unit unit who entered in combat
     * @param target target of the combat
     */
    void combat(IUnit unit, IUnit target);

    /**
     * Execute a counter attack on the unit by the target
     *
     * @param unit unit who will receive the counter attack
     * @param target unit who will execute the counter attack
     */
    void counterAttack(IUnit unit, IUnit target);

    /**
     * Checks if Role is equals to another object
     *
     * @param obj object to compare
     * @return true if the instances match
     */
    @Override
    boolean equals(Object obj);
}
