import java.util.ArrayList;

//media do processo = inicio da execução - tempo de chegada

public class SjfPreemptivo {
	int executionTime;
    int tBurst = 0;
    int processesExecution;
    int currentProcessSize;
    int averageWaitingTime;
    int idle;
    int j = 0, p = 0;
    int smallerPosition = 0;
    int smaller = 999999;


    

    public SjfPreemptivo(ArrayList<Process> processes){
    	
    	averageWaitingTime = 0;
    	ArrayList<Process> temporary = new ArrayList<>();
    	idle = 0;
    	
    	for(int i = 0; i < processes.size(); i++) {
    		temporary.add(processes.get(i));			// salva uma copia da lista de processos localmente
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
        		
	 	       if(i > 0) {													// se não é a primeira execução
		        	for(j = 0; j < temporary.size(); j++) {					// ira varrer os processos 
		        		if(temporary.get(j).arrivalTime <= i && temporary.get(j).execution < temporary.get(smallerPosition).execution) {	//procurando algum processo menor e que já tenha chegado
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
	                			

	                			
	                		} 
	                		
	                	}
	                	
	            		if(temporary.get(smallerPosition).arrivalTime > i ) {					// verifica se o proximo processo chegou maior que o passo atual
	            			
	            			while(temporary.get(smallerPosition).arrivalTime > (idle + i)) {		// se chegou, incrementa o passo até chegar de fato
	            				System.out.println((idle + i) + " Processador ocioso");
	            				idle++;
	            				
	        	                for(j =0 ; j < temporary.size(); j++) {
	        	                	if(j != smallerPosition && temporary.get(j).arrivalTime <= i) {
	        	                		temporary.get(j).waitingTime++;
	        	                	}
	        	                }
	            			}
	            			
	            			
	            			
	            			i += idle;																// adiciona o tempo ocioso ao passo atual
	            			
	                		processesExecution += temporary.get(smallerPosition).execution + idle;      // soma ao tempo de execução, o tempo do proximo processo e o tempo ocioso
	                			
	            		} else {
	            			
	            			processesExecution += temporary.get(smallerPosition).execution ;      // soma ao tempo de execução, o tempo do proximo processo
	            		}
	     
	            		
	            		//System.out.println(temporary.get(smallerPosition).waitingTime);
	            		averageWaitingTime += temporary.get(smallerPosition).waitingTime;
	            		
	            		System.out.println(i + ": Processo p"+ temporary.get(smallerPosition).id);       // imprime o passo atual de execução
	            		idle = 0;
	            		
	            	} else {
	            		if(temporary.get(smallerPosition).arrivalTime > i ) {
	            			
	            			while(temporary.get(smallerPosition).arrivalTime > (idle + i) ) {
	            				System.out.println((idle + i) + " Processador ocioso");
	            				idle++;
	            				
	        	                for(j =0 ; j < temporary.size(); j++) {
	        	                	if(j != smallerPosition && temporary.get(j).arrivalTime <= i) {
	        	                		temporary.get(j).waitingTime++;
	        	                	}
	        	                }
	            			}
	            			
	            			
	            			i += idle;
	                		processesExecution += temporary.get(smallerPosition).execution + idle;      // soma ao tempo de execução, o tempo do proximo processo	
	                		idle = 0;
	            		} else {
	            			
	            			processesExecution += temporary.get(smallerPosition).execution ;      // soma ao tempo de execução, o tempo do proximo processo
	                		
	            		}

	            		
	            		idle = 0;
	            		//System.out.println(temporary.get(smallerPosition).waitingTime);
	            		averageWaitingTime += temporary.get(smallerPosition).waitingTime;
	            		
	            		System.out.println(i + ": Processo p"+ temporary.get(smallerPosition).id);       // imprime o passo atual de execução
	            		
    	                for(j =0 ; j < temporary.size(); j++) {
    	                	if(j != smallerPosition && temporary.get(j).arrivalTime <= i) {
    	                		temporary.get(j).waitingTime++;
    	                	}
    	                }
	            
	            		
	            	}
		
	
	            }
	        
        }
        
		//System.out.println(temporary.get(smallerPosition).waitingTime);
		
		averageWaitingTime = (averageWaitingTime + temporary.get(smallerPosition).waitingTime) / processes.size();
        
        
        System.out.println("Tempo médio de espera: " + averageWaitingTime);

    	
    }
}
