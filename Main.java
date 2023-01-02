import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;


/*
popular-processos
+
escolhe_algoritmo
+
executa-processos
-- de 0 a 999
-- varrer a lista de processos e escolher qual executar
-- imprimir qual processo foi escolhido e o valor de i
-- armazenar o tempo de espera em uma variavel de controle
-- controlar o tempo restante de cada processo
+
calcula_estatisticas
+
imprime-estatisticas


-tempo de execução [nº de processos]
-tempo restante
-tempo espera
-tempo chegada

 */
public class Main {
    static ArrayList<Process> processes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader tcl = new BufferedReader(new InputStreamReader(System.in));

        int opc = 0, processNum = 3;

        do {
            menu();
            opc = Integer.parseInt(tcl.readLine());
            if(opc == 1 || opc ==2) {
                System.out.println("quantos Processos deseja inserir");
                processNum = Integer.parseInt(tcl.readLine());
                populateProcesses(opc, processNum);
                opc = 0;        // reseta a opcao do menu para continuar a execução
            } if (opc == 0){
                do {
                    algortihmMenu();
                    opc = Integer.parseInt(tcl.readLine());
                    switch (opc) {
                        // FCFS
                        case 1: Fcfs fcfs= new Fcfs(processes);
                            break;

                        // SJF
                        case 2:
                            Sjf sjf = new Sjf(processes);
                            break;

                        // SJF P
                        case 3:
                            System.out.println("ainda não implementado");
                            break;

                        // ROBIN
                        case 4:
                            System.out.println("ainda não implementado");
                            break;

                        // PRIORIDADE
                        case 5:
                            System.out.println("ainda não implementado");
                            break;

                        // PRIORIDADE P
                        case 6:
                            System.out.println("ainda não implementado");
                            break;

                        // byebye
                        case 7:
                            System.out.println("Até mais!");
                            break;

                        default:
                            System.out.println("EEE O Q? não entendi");
                            break;
                    }
                } while (opc != 7);
            }
            opc = 0;
            for(int i = 0; i < processes.size(); i++){
                System.out.println(processes.get(i).execution);
                System.out.println(processes.get(i).arrivalTime);
            }
        } while(opc > 0);
    }


    public static void menu() {
            // niveis do menu
            // menu - 0 - tempo de espera manual e Automatica
            // menu - 1 - tipo de algoritmo da fila de processos
            System.out.println("---- OPCÕES ----");
            System.out.println("1) Atribuição Automatica.");
            System.out.println("2) Atribuição Manual");

    }

    public static void algortihmMenu(){
        System.out.println(" -- escolha o tipo de algoritmo --");
        System.out.println("1) FCFS");
        System.out.println("2) SJF");
        System.out.println("3) SJF P");
        System.out.println("4) ROBIN");
        System.out.println("5) PRIORIDADE ");
        System.out.println("6) PRIORIDADE P");
        System.out.println("7) SAIR");

    }

    public static void populateProcesses(int opc, int processNum) throws IOException {
        Random generator = new Random();
        BufferedReader tcl = new BufferedReader(new InputStreamReader(System.in));

        if (opc == 1) {                                     // opção para popular o arraylist com a quantidade selecionada
            for (int i = 0; i < processNum; i++) {          // de forma aleatoria
                processes.add(new Process(true));
                if(processes.size() == 1){
                    processes.get(0).arrivalTime = 0;
                } else {
                    processes.get(i).arrivalTime = generator.nextInt((processes.get(i-1).arrivalTime+1), (processes.get(i-1).arrivalTime+1) +(processes.get(i).execution+4));
                }
            }
        } else if (opc == 2) {                              // opção para popular o arraylist com a quantidade seleciona
            for (int i = 0; i < processNum; i++) {          // de forma manual
                System.out.println("Digite o tempo de execução do processo ( p" + (i + 1) + " ): ");
                processes.add(new Process(false));
                System.out.println("Digite o tempo de chegada desse processo: ");
                processes.get(i).arrivalTime = Integer.parseInt(tcl.readLine());
            }
        }
    }


}

