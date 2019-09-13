package model.roles;

import model.units.IUnit;

/**
 * This class represents a null role.
 * <p>
 * NoRole units can't combat or counter attack.
 *
 * @author Fabián Jaña
 * @since 1.1
 */
public class NoRole implements Role {

    /**
     * Creates a new NoRole role
     */
    public NoRole() {
    }

    /**
     * Units with NoRole role can't combat
     * @param unit unit who entered in combat
     * @param target target of the combat
     */
    @Override
    public void combat(IUnit unit, IUnit target) {
    }

    /**
     * Units with NoRole role can't counter attack
     *
     * @param unit unit who will receive the counter attack
     * @param target unit who will execute the counter attack
     */
    @Override
    public void counterAttack(IUnit unit, IUnit target) {
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NoRole;
    }
}
