import java.util.ArrayList;

public class Sjf {
	int processesSize;
	int executionTime;
    int tBurst = 0;
    int arival;
    int processesExecution;
    int averageWaitingTime;
    int j = 0, p = 0;
    int smallerPosition = 0;
    int smaller = 0;

    

    public Sjf(ArrayList<Process> processes){
    	processesExecution = processes.get(0).execution;
    	averageWaitingTime = 0;
    	
    	processesSize = processes.size();
        for(int i = 0; i < processes.size(); i++){
            executionTime += processes.get(i).execution;
            //System.out.println("processo "+ (i + 1)+ ": " + processes.get(i).waitingTime);
        }
        
        for(int i = 0; i < executionTime; i++){
            if(i < processesExecution){                                // se não chegou ao fim do tempo de execução do processo
                System.out.println(i + ": Processo p"+ (p + 1));       // imprime o passo atual de execução
            } else {                                                   // se chegou ao proximo processo
            	if(processes.size() > 1) {
            		processes.remove(smallerPosition);
					  
                	for(j = 0; j < processes.size(); j++) {
                		if(processes.get(j).execution > smaller) {
                			smallerPosition = j;
                			smaller = processes.get(j).execution;
                		}
                	}
            		
            		processesExecution += processes.get(smallerPosition).execution;      // soma ao tempo de execução, o tempo do proximo processo
            		averageWaitingTime += processes.get(smallerPosition).execution;      // soma ao tempo total de execução para o calculo da média

            		System.out.println(i + ": Processo p"+ (p + 1));       // imprime o passo atual de execução
            		p++;
            	} else {
            		processes.remove(0);
					  
            		processesExecution += processes.get(0).execution;      // soma ao tempo de execução, o tempo do proximo processo
            		averageWaitingTime += processes.get(0).execution;      // soma ao tempo total de execução para o calculo da média

            		System.out.println(i + ": Processo p"+ (p + 1));       // imprime o passo atual de execução
            		p++;
            		
            	}
	

            }
            
        }
        
      
        averageWaitingTime /= processesSize;    // realiza o calculo da média
        
        
        System.out.println("Tempo médio de espera: " + averageWaitingTime);

    	
    }
}
