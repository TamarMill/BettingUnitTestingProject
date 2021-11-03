public class Betting {
    private IRandomValueGenerator rand;
    private int minBalance;
     int balance;
    public Betting(IRandomValueGenerator random, int minBalance){
        rand=random;
        this.minBalance=minBalance;
    }
    public double getCurrentBalance(){
        return balance;
    }
    public boolean canBet(double amnt){
        if ((amnt+minBalance)<=balance){
            return true;
        }
        return false;
    }
    public void addMoney(double amnt){
        if (amnt<0){
            return;
        }
        balance+=amnt;
    }
    public double betOnANumber(double amnt,int min, int max,int selectedNumber){
        if (canBet(amnt)) {
            if (rand.getRandominRange(min, max) == selectedNumber) {
                balance += ((max - min) * amnt);
                return ((max - min) * amnt);
            } else {
                balance -= amnt;
                return (amnt * -1);
            }
        }
            else {
                return 0;
        }

    }

    public double betOnProbability(double amnt, double p) throws ProbablityIsNotBetweenZeroAndOneException{
        double number= rand.getRandom();

        if (p<0 ||p>1){
            throw new ProbablityIsNotBetweenZeroAndOneException("Probablity is not valid. It Must be between 0 and 1");
        }
        if (canBet(amnt)){
        if(number<p) {
            double total = ((1 / p) - 1) * amnt;
            balance += total;
            return total;
        }
            else{
                balance -= amnt;
                return (amnt*-1);
            }
            }
            else {
                return 0;}
    }

    }


