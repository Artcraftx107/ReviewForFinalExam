import calculator.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest{
    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator=new Calculator();
    }

    @AfterEach
    public void tearDown(){
        calculator=null;
    }

    @Test
    public void testAdd(){
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    public void testSubstract(){
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    public void testMultiply(){
        assertEquals(6, calculator.multiply(3, 2));
    }

    @Test
    public void testDivide(){
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    public void testDivideByZero(){
        assertThrows(IllegalArgumentException.class, ()-> calculator.divide(1, 0));
    }
}
