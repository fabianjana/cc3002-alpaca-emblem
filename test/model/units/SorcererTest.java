package model.units;

import model.items.IEquipableItem;
import model.items.MagicBook;
import model.roles.DamageDealer;
import model.roles.NoRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set test for sorcerer unit
 *
 * @author Fabián Jaña
 * @since 1.1
 */
class SorcererTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    private void equipMagicBookTest(MagicBook magicBook) {
        //without item on inventory
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(magicBook);
        assertNull(sorcerer.getEquippedItem());
        assertNull(magicBook.getOwner());
        assertEquals(sorcerer.getRole(), new NoRole());

        //adding item to inventory
        sorcerer.addItem(magicBook);
        sorcerer.equipItem(magicBook);
        assertEquals(magicBook, sorcerer.getEquippedItem());
        assertEquals(sorcerer, magicBook.getOwner());
        assertEquals(sorcerer.getRole(), new DamageDealer());

        //removing the item in the inventory
        sorcerer.removeItem(magicBook);
        assertNull(sorcerer.getEquippedItem());
        assertNull(magicBook.getOwner());
        assertEquals(sorcerer.getRole(), new NoRole());
    }

    @Override
    @Test
    public void equipLightMagicBookTest() {
        equipMagicBookTest(lightMagicBook);
    }

    @Test
    @Override
    public void equipDarkMagicBookTest() {
        equipMagicBookTest(darkMagicBook);
    }

    @Test
    @Override
    public void equipSoulMagicBookTest() {
        equipMagicBookTest(soulMagicBook);
    }

    @Override
    public void combatTest(IUnit target) {
        getTestUnit().addItem(lightMagicBook);
        getTestUnit().addItem(darkMagicBook);
        getTestUnit().addItem(soulMagicBook);
        target.moveTo(field.getCell(2,0));

        // Attack with light
        getTestUnit().equipItem(lightMagicBook);
        getTestUnit().combat(target);
        assertEquals(50, getTestUnit().getHitPoints());
        assertEquals(40, target.getHitPoints());

        // Attack with dark
        getTestUnit().equipItem(darkMagicBook);
        getTestUnit().combat(target);
        assertEquals(50, getTestUnit().getHitPoints());
        assertEquals(30, target.getHitPoints());

        // Attack with soul
        getTestUnit().equipItem(soulMagicBook);
        getTestUnit().combat(target);
        assertEquals(50, getTestUnit().getHitPoints());
        assertEquals(20, target.getHitPoints());
    }

    /**
     * Tests a combat between a light magic book sorcerer and a soul magic book sorcerer
     */
    private void combatLightWithSoul() {
        setUp();
        setTargetSorcerer();
        IUnit target = getTargetSorcerer();
        target.addItem(targetSoulMagicBook);
        getTestUnit().addItem(lightMagicBook);

        // Attack in range
        sorcerer.equipItem(lightMagicBook);
        target.equipItem(targetSoulMagicBook);
        target.moveTo(field.getCell(2,0));
        getTestUnit().combat(target);
        assertEquals(35, getTestUnit().getHitPoints());
        assertEquals(50, target.getHitPoints());
    }

    /**
     * Tests a combat between a dark magic book sorcerer and a soul magic book sorcerer
     */
    private void combatDarkWithSoul() {
        setUp();
        setTargetSorcerer();
        IUnit target = getTargetSorcerer();
        target.addItem(targetSoulMagicBook);
        getTestUnit().addItem(darkMagicBook);

        // Attack in range
        sorcerer.equipItem(darkMagicBook);
        target.equipItem(targetSoulMagicBook);
        target.moveTo(field.getCell(2,0));
        getTestUnit().combat(target);
        assertEquals(50, getTestUnit().getHitPoints());
        assertEquals(35, target.getHitPoints());
    }

    /**
     * Tests a combat between a light magic book sorcerer and a dark magic book sorcerer
     */
    private void combatLightWithDark() {
        setUp();
        setTargetSorcerer();
        IUnit target = getTargetSorcerer();
        target.addItem(targetDarkMagicBook);
        getTestUnit().addItem(lightMagicBook);

        // Attack in range
        sorcerer.equipItem(lightMagicBook);
        target.equipItem(targetDarkMagicBook);
        target.moveTo(field.getCell(2,0));
        getTestUnit().combat(target);
        assertEquals(50, getTestUnit().getHitPoints());
        assertEquals(35, target.getHitPoints());
    }

    private void combatSameMagic(IEquipableItem book, IEquipableItem targetBook) {
        setUp();
        setTargetSorcerer();
        IUnit target = getTargetSorcerer();
        target.addItem(targetBook);
        getTestUnit().addItem(book);

        // Attack in range
        sorcerer.equipItem(book);
        target.equipItem(targetBook);
        target.moveTo(field.getCell(2,0));
        getTestUnit().combat(target);
        assertEquals(40, getTestUnit().getHitPoints());
        assertEquals(40, target.getHitPoints());
    }

    @Test
    @Override
    public void combatSorcererTest() {
        combatLightWithSoul();
        combatDarkWithSoul();
        combatLightWithDark();
        combatSameMagic(lightMagicBook, targetLightMagicBook);
        combatSameMagic(darkMagicBook, targetDarkMagicBook);
        combatSameMagic(soulMagicBook, targetSoulMagicBook);
    }
}