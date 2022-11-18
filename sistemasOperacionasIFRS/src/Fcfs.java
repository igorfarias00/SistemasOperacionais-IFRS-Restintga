import java.io.IOException;
import java.util.ArrayList;


public class Fcfs {
    ArrayList<Process> process  = new ArrayList<>();
    int executionTime = 0; // tBurst
    int processesExecution;
    int averageWaitingTime;
    int j = 0;
    public Fcfs(ArrayList<Process> processes)  {
        processesExecution = processes.get(0).execution;
        averageWaitingTime = 0;

        for(int i = 0; i < processes.size(); i++){
            executionTime += processes.get(i).execution;
            //System.out.println("processo "+ (i + 1)+ ": " + processes.get(i).waitingTime);
        }

        //System.out.println(processes.size());
        //System.out.println(executionTime);
        ;
        for(int i = 0; i < executionTime; i++){
            if(i < processesExecution){                                // se não chegou ao fim do tempo de execução do processo
                System.out.println(i + ": Processo p"+ (j + 1));       // imprime o passo atual de execução
            } else {                                                   // se chegou ao proximo processo
                j++;                                                   // soma um ao contador de processos
                processesExecution += processes.get(j).execution;      // soma ao tempo de execução, o tempo do proximo processo
                averageWaitingTime += processes.get(j).execution;      // soma ao tempo total de execução para o calculo da média

                System.out.println(i + ": Processo p"+ (j + 1));       // imprime o passo atual de execução
            }

            if(i == (executionTime - 1)){   // se o laço chegou ao ultimo processo,
                averageWaitingTime /= j;    // realiza o calculo da média
            }
        }

        System.out.println("Tempo médio de espera: " + averageWaitingTime);

    }


}
