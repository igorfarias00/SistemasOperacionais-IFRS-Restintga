package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Process {
    BufferedReader tcl = new BufferedReader(new InputStreamReader(System.in));
    public int execution;
    public int arrivalTime;
    public int waitingTime;
    public int id;
    public int priority;

    Random generator = new Random();

    public Process(boolean randomico, int id) throws IOException {
    	this.id = id;
    	
    	
    	if (randomico == true) {
            this.execution = generator.nextInt(1, 12);		// gera um tempo de execução aleatorio, entre 1 e 12
            this.priority = generator.nextInt(1,6);
        } else {
        	System.out.println("Digite o tempo de execução do processo ( p" + this.id + " ): ");
            this.execution = Integer.parseInt(tcl.readLine()); // solicita o tempo de execução
            System.out.println("Digite o tempo de chegada do processo: ");
            this.arrivalTime = Integer.parseInt(tcl.readLine());
            System.out.println("Digite a prioridade do processo");
            this.priority = Integer.parseInt(tcl.readLine());
        }
        
        
    }

}
