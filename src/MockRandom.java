public class MockRandom implements IRandomValueGenerator {
   private double  rand;
   private double rr;

    public double  getRandom() {
        return rand;
    }

    public double getRandominRange(int min, int max) {
        return rr;
    }

    public void setRandom(double  r){
        rand=r;
    }

    public void setRandominRange(int min,int max){
        rr=((max-min)-1)+min;
    }
}
