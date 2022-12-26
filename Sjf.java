import java.util.ArrayList;

public class Sjf {

    int tBurst = 0;
    int arival;
    int processesExecution;
    int averageWaitingTime;
    int next;
    int j = 0;
    int i,k;



    public Sjf(ArrayList<Process> processes){
        processesExecution = processes.get(0).execution;

        for(i = 0; i< processes.size(); i++){
            tBurst += processes.get(i).execution;

        }

        //1 - 0  / 1 - 1 / 2 - 3 / 2 -6
        for(i = 0; i < tBurst; i++){
            if(i < processesExecution){                                // se não chegou ao fim do tempo de execução do processo
                System.out.println(i + ": Processo p"+ (j + 1));       // imprime o passo atual de execução

            } else {                                                   // se chegou ao fim da execução do processo
                j++;
                for(k = j; k < (processes.size()-1); j++){
                    if (processes.get(k).execution < processes.get(k+1).execution){
                        next = k;
                    }
                }

                processesExecution += processes.get(next).execution;      // soma ao tempo de execução, o tempo do proximo processo
                averageWaitingTime += processes.get(next).execution;      // soma ao tempo total de execução para o calculo da média

                System.out.println(i + ": Processo p" + (j + 1));       // imprime o passo atual de execução

            }

            if(i == (tBurst - 1)){   // se o laço chegou ao ultimo processo,
                averageWaitingTime /= j;    // realiza o calculo da média
            }
        }
    }
}
