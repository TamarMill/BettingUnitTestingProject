import static org.junit.Assert.*;
import org.junit.*;

public class UnitTests {
    Betting betting;
    Betting betting1;
    MockRandom mock;
    @Before
   public void setUp(){
        mock=new MockRandom();
        betting=new Betting(mock,0);
    }

    @Test
    public void testGetCurrentBalanceWhenBalanceIsBelowZero(){
        betting.balance=-80;
        assertEquals(-80,betting.getCurrentBalance(),.01);
    }

    @Test
    public void testGetCurrentBalance(){
        betting.balance=90;
        assertEquals(90,betting.getCurrentBalance(),.01);
    }

    @Test
    public void testCanBetMethod(){
        betting.balance=7;
        assertTrue(betting.canBet(6));
    }
    @Test
    public void testCanBetMethodWhenMinimumIsNegative(){
        betting1=new Betting(mock,-10);
        betting1.balance=7;
        assertTrue(betting1.canBet(6));
    }
    @Test
    public void testCanBetMethodWhenAmntandBalanceAreEqualandMinimumIsZero(){
        betting.balance=7;
        assertTrue(betting.canBet(7));
    }
    @Test
    public void testCanBetMethodWhenAmntIsGreaterThanBalanceAndMinimumIsZero(){
        betting.balance=7;
        assertFalse(betting.canBet(9));
    }


    @Test
    public void testAddMoneyWhenAmountIsZero(){
        betting.balance=0;
        betting.addMoney(0);
        assertEquals(0,betting.balance,.01);
    }
    @Test
    public void testAddMoneyWhenAmountIsPositive(){
        betting.balance=0;
        betting.addMoney(25);
        assertEquals(25,betting.balance,.01);
    }
    @Test
    public void testAddMoneyWhenAmountIsNegToMakeSureItDoesntAddAnything(){
        betting.balance=0;
        betting.addMoney(-8);
        assertEquals(0,betting.balance,.01);
    }
    @Test
    public void testBetOnANumberReturnsCorrectAmountWhenCorrectNumberSelected(){
        betting.balance=100;
        mock.setRandominRange(3,5);
        assertEquals(8,betting.betOnANumber(4,3,5,4),.01);
    }
    @Test
    public void testBetOnANumberAddsCorrectAmounttoBalanceWhenCorrectNumberSelected(){
        betting.balance=100;
        mock.setRandominRange(3,5);
        betting.betOnANumber(4,3,5,4);
        assertEquals(108,betting.balance);

    }

    @Test
    public void testBetOnANumberReturnsCorrectAmountWhenIncorrectNumberSelected(){
        betting.balance=100;
        mock.setRandominRange(3,5);
        assertEquals(-4,betting.betOnANumber(4,3,5,5),.01);
    }
    @Test
    public void testBetOnANumberSubtractsFromBalanceWhenIncorrectNumberSelected(){
        betting.balance=100;
        mock.setRandominRange(3,5);
        betting.betOnANumber(4,3,5,5);
        assertEquals(96,betting.balance);
    }
    @Test
    public void testBetOnANumberWhenNotEnoughInAccount(){
        betting.balance=0;
        mock.setRandominRange(3,5);
        assertEquals(0,betting.betOnANumber(3,3,5,2),.01);
    }

    @Test
    public void testBetOnProbabilityReturnsCorrectAmountByWin()throws ProbablityIsNotBetweenZeroAndOneException{
        betting.balance=100;
        mock.setRandom(.3);
        assertEquals(15, betting.betOnProbability(10,.4),.01);

    }
    @Test
    public void testBetOnProbabilityAddsCorrectAmountToBalanceByWin()throws ProbablityIsNotBetweenZeroAndOneException{
        betting.balance=100;
        mock.setRandom(.3);
       betting.betOnProbability(10,.4);
       assertEquals(115,betting.balance);

    }
    @Test
    public void testBetOnProbabilityWhenYouLose()throws ProbablityIsNotBetweenZeroAndOneException{
        betting.balance=100;
        mock.setRandom(.5);
        assertEquals(-10, betting.betOnProbability(10,.4),.01);

    }
    @Test(expected=ProbablityIsNotBetweenZeroAndOneException.class)
    public void testThatExceptionIsThrownWhenProbabilityGreaterThanOne() throws ProbablityIsNotBetweenZeroAndOneException {
        betting.balance=100;
        betting.betOnProbability(10,2);

    }
    @Test(expected=ProbablityIsNotBetweenZeroAndOneException.class)
    public void testThatExceptionIsThrownWhenProbabilityIsLessThanZero() throws ProbablityIsNotBetweenZeroAndOneException {
        betting.balance=100;
        betting.betOnProbability(10,-2);
    }




}
