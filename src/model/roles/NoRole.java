package model.roles;

import model.units.IUnit;

public class NoRole implements Role {

    /**
     * Creates a new NoRole role
     */
    public NoRole() {
    }

    /**
     * Units with NoRole role can't combat
     */
    @Override
    public void combat(IUnit unit, IUnit target) {
    }

    /**
     * Units with NoRole role can't counter attack
     */
    @Override
    public void counterAttack(IUnit unit, IUnit target) {
    }
}
