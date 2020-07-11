package cpuschdeular2;


import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
   
        System.out.println("SJF");
        sjf();
    
    }
    
    
    public static void sjf()
    {
        CPUScheduler sjf = new ShortestJobFirst();
   
        
    }
}
      
    /*
  public static void display(CPUScheduler object)
    {
        System.out.println("Process\tAT\tBT\tWT\tTAT");

        object.getRows().stream().forEach((row) -> {
            System.out.println(row.getProcessName() + " " + row.getArrivalTime() + " " + row.getBurstTime() + "\t" + row.getWaitingTime() + "\t" + row.getTurnaroundTime());
        });
        
        System.out.println();
        
        for (int i = 0; i < object.getTimeline().size(); i++)
        {
            List<Event> timeline = object.getTimeline();
            System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");
            
            if (i == object.getTimeline().size() - 1)
            {
                System.out.print(timeline.get(i).getFinishTime());
            }
        }
        
        System.out.println("\n\nAverage WT: " + object.getAverageWaitingTime() + "\nAverage TAT: " + object.getAverageTurnAroundTime());
    }
}
*/