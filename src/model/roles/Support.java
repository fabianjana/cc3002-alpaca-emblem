package model.roles;

import model.units.IUnit;

public class Support implements Role {

    /**
     * Creates a new Support role
     */
    public Support() {
    }

    /**
     * Units with Support role don't receive counter attacks
     */
    @Override
    public void combat(IUnit unit, IUnit target) {
        unit.useItem(target);
    }

    /**
     * Support units can't counterAttack
     */
    @Override
    public void counterAttack(IUnit unit, IUnit target) {
    }
}
