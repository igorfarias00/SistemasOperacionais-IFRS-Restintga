import java.io.IOException;
import java.util.ArrayList;


public class Fcfs {
    ArrayList<Process> process  = new ArrayList<>();
    int executionTime = 0;
    int j = 0;
    public Fcfs(ArrayList<Process> processes)  {
        for(int i = 0; i < processes.size(); i++){
            executionTime += processes.get(i).waitingTime;
            //System.out.println("processo "+ (i + 1)+ ": " + processes.get(i).waitingTime);
        }
        //System.out.println(executionTime);
        ;
        for(int i = 0; i < executionTime; i++){
            if(i < processes.get(j).waitingTime){
                System.out.println(i + ": Processo p"+ (j + 1));
            } else {
                j++;
                System.out.println(i + ": Processo p"+ (j + 1));
            }
        }

    }


}
