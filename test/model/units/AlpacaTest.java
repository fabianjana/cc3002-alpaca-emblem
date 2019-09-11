package model.units;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  /**
   * Alpaca can't equip any item
   */
  @Override
  public void equipTestItem() {
    // do nothing
  }
}