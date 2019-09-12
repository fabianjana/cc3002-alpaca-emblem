package model.roles;

import model.units.IUnit;

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

}
