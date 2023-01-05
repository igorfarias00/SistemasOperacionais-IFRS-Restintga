import java.util.ArrayList;
// problemas desse código:

/*
 *  ele destroi o arraylist original
 * 
 */
public class Sjf {
	int executionTime;
    int tBurst = 0;
    int processesExecution;
    int averageWaitingTime;
    int idle;
    int j = 0, p = 0;
    int smallerPosition = 0;
    int smaller = 999999;
  

    

    public Sjf(ArrayList<Process> processes){
    	processesExecution = processes.get(0).execution;
    	averageWaitingTime = 0;
    	ArrayList<Process> temporary = new ArrayList<>();
    	idle = 0;
    	
    	for(int i = 0; i < processes.size(); i++) {
    		temporary.add(processes.get(i));
    	}
    	
    
        for(int i = 0; i < temporary.size(); i++){					// executa essa laço para verificar o tempo de execução de todos os processos
            executionTime += temporary.get(i).execution;
            //System.out.println("processo "+ (i + 1)+ ": " + processes.get(i).waitingTime);
        }
        
        for(int i = 0; i < executionTime; i++){
            if(i < processesExecution){                                				// se não chegou ao fim do tempo de execução do processo
                System.out.println(i + ": Processo p"+ temporary.get(p).id);        // imprime o passo atual de execução
            } else {                                                   				// se chegou ao fim...
            	if(temporary.size() > 1) {											// verifica se a lista não está no ultimo elemento
            		temporary.remove(smallerPosition);								// se não está, exclui o processo que acabou de ser executado
					  
                	for(j = 0; j < temporary.size(); j++) {							// varre a lista, 
                		if(j == 0) {												// se for o primeiro elemento, assume ele como menor
                			smallerPosition = j;
                			smaller = temporary.get(j).execution;
                		} else if(temporary.get(j).execution < smaller && temporary.get(j).arrivalTime <= i) {			// se não for o primeiro, compara para verificar se é menor
                			smallerPosition = j;									// salva a posição
                			smaller = temporary.get(j).execution;					// e o menor valor de execução do proximo processo
                			//System.out.println(smallerPosition);   //debug 
                			//System.out.println(smaller);			 // ^^
                			
                		} 
                		
                	}
                	
            		if(temporary.get(smallerPosition).arrivalTime > i ) {
            			
            			while(temporary.get(smallerPosition).arrivalTime > (idle + i) ) {
            				System.out.println((idle + i) + " Processador ocioso");
            				idle++;
            			}
            			
            			
            			i += idle;
                		processesExecution += temporary.get(smallerPosition).execution + idle;      // soma ao tempo de execução, o tempo do proximo processo
                			
            		} else {
            			
            			processesExecution += temporary.get(smallerPosition).execution ;      // soma ao tempo de execução, o tempo do proximo processo
            		}

            		averageWaitingTime += i;      // soma ao tempo total de execução para o calculo da média de espera
            		System.out.println(i + ": Processo p"+ temporary.get(smallerPosition).id);       // imprime o passo atual de execução
            		p = smallerPosition;															// assume a posição atual como a menor posição encontrada
            		idle = 0;
            	} else {
            		if(temporary.get(smallerPosition).arrivalTime > i ) {
            			
            			while(temporary.get(smallerPosition).arrivalTime > (idle + i) ) {
            				System.out.println((idle + i) + " Processador ocioso");
            				idle++;
            			}
            			
            			i += idle;
                		processesExecution += temporary.get(smallerPosition).execution + idle;      // soma ao tempo de execução, o tempo do proximo processo	
                		idle = 0;
            		} else {
            			
            			processesExecution += temporary.get(smallerPosition).execution ;      // soma ao tempo de execução, o tempo do proximo processo
                		
            		}
            		
            		averageWaitingTime += i;
            		idle = 0;
            		
            		System.out.println(i + ": Processo p"+ temporary.get(smallerPosition).id);       // imprime o passo atual de execução
            
            		
            	}
	

            }
            
        }
        
      
        averageWaitingTime /= processes.size();    // realiza o calculo da média
        
        
        System.out.println("Tempo médio de espera: " + averageWaitingTime);

    	
    }
}
