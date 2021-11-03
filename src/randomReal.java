import java.util.Random;

public class randomReal implements IRandomValueGenerator {

    @Override
    public double  getRandom() {
        Random r=new Random();
        return r.nextInt();
    }

   @Override
    public double  getRandominRange(int min, int max) {
        Random r=new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
