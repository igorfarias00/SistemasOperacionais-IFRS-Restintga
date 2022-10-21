import java.util.Random;

public class Process {
    public int waitingTime;     // criar metodo para definir um valor aleatoriamente e manualmente
    public int id;
    public int priority;

    Random generator = new Random();

    public Process(boolean randomico) {
        if (randomico == true) {
            this.waitingTime = generator.nextInt(1, 256);
        } else {
            this.waitingTime =
        }
    }

}
