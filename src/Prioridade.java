package src;
import java.util.ArrayList;

public class Prioridade {
	int executionTime;
    int tBurst = 0;
    int processesExecution;
    int currentProcessSize;
    int averageWaitingTime;
    int idle;
    int j = 0, p = 0;
    int smallerPosition = 0;
    int smaller = 999999;
    int i = 0, highPriority = 0, highPriorityPosition = 0;
	ArrayList<Process> temporary = new ArrayList<>();
	
	public Prioridade(ArrayList<Process> processes){
		
		
		for(i = 0; i < processes.size(); i++) {
			temporary.add(processes.get(i));			// salva uma copia da lista de processos localmente
    	}
		
		
		// acha o que tem a maior prioridade
		for(i = 0;i< temporary.size(); i++) {
			if(i == 0) {
				highPriority = temporary.get(i).priority;
				highPriorityPosition = i;
			} else {
				if(highPriority > temporary.get(i).priority && temporary.get(i).arrivalTime <= i) {
					highPriority = temporary.get(i).priority;
					highPriorityPosition = i;
				}
			}
		}
		
		
	   	for(j = 0; j < temporary.size(); j++) {							// varre a lista, 
    		if(j == 0) {												// se for o primeiro elemento, assume ele como menor
    			smallerPosition = j;
    			smaller = temporary.get(j).arrivalTime;
    			processesExecution = temporary.get(j).execution;
    		} else if(temporary.get(j).arrivalTime <= smaller) {			// se não for o primeiro, compara para verificar se é menor tempo de chegada
    			smallerPosition = j;										// salva a posição
    			smaller = temporary.get(j).execution;					// e o menor valor de execução do proximo processo
    			processesExecution = temporary.get(j).execution;
    			//System.out.println(smallerPosition);   //debug 
    			//System.out.println(smaller);			 // ^^
    			
    		} 
    	}
		
        for(int i = 0; i < temporary.size(); i++){					// executa essa laço para verificar o tempo de execução de todos os processos
            executionTime += temporary.get(i).execution;
            //System.out.println("processo "+ (i + 1)+ ": " + processes.get(i).waitingTime);
            
        }
        
        for(int i = 0; i < executionTime; i++){
        		
	    		for(j = 0;j< temporary.size(); j++) {
	    			if(j == 0) {
	    				highPriority = temporary.get(j).priority;
	    				highPriorityPosition = j;
	    			} else {
	    				if(highPriority > temporary.get(j).priority && temporary.get(j).arrivalTime <= i) {
	    					highPriority = temporary.get(j).priority;
	    					highPriorityPosition = j;
	    				}
	    			}
	    		}
	    		
	 	       if(i > 0) {													// se não é a primeira execução
		        	for(j = 0; j < temporary.size(); j++) {					// ira varrer os processos 
		        		if(temporary.get(j).arrivalTime <= i && highPriority > temporary.get(i).priority) {	//que já tenha chegado e tenha uma prioridade menor
		        			temporary.get(smallerPosition).execution -= i - temporary.get(smallerPosition).arrivalTime;
		        			processesExecution -= temporary.get(smallerPosition).execution;									//remove o tempo de execução do processo anterior
		        			smallerPosition  = j;																			// adiciona a posição do novo processo que ira ser executado
		        			smaller = temporary.get(j).execution;															// e seu tamanho
		        			processesExecution += temporary.get(smallerPosition).execution ;								// e adiciona ao tempo de execução
			                

		        		}
		        	}
		        	
		        }
	        	

        		if(i < processesExecution){                                				// se não chegou ao fim do tempo de execução do processo
	                System.out.println(i + ": Processo p"+ temporary.get(smallerPosition).id);        // imprime o passo atual de execução
	                for(j =0 ; j < temporary.size(); j++) {
	                	if(j != smallerPosition && temporary.get(j).arrivalTime <= i) {
	                		temporary.get(j).waitingTime++;
	                	}
	                }
	                

	                

}
}}}

