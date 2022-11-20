import java.util.*;

public class FCFS_SJF {
    // 平均周转时间
    public static double avgTotalTime;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please input the total number of processes");
        int n = scan.nextInt();
        List<ProInfo> info = new ArrayList<>();
        System.out.println("Please input the process number, submit time and run time");
        for (int i = 0 ; i < n ; i++){
            Scanner sc = new Scanner(System.in);
            String[] a = sc.nextLine().split(" ");
            info.add(new ProInfo(Integer.valueOf(a[0]),Float.valueOf(a[1]),Float.valueOf(a[2])));
        }
        System.out.println("What kind of algorithm do you want? Please input 1 to select FCFS, or 2 to select SJF or 0 to exit.");
        int a = scan.nextInt();
        if (a == 1){
            FCFS(info);
        }else if (a == 2){
            SJF(info);
        }else if (a == 0){
            //System.out.print("程序退出");
            System.exit(0);

        }else{
            System.out.println("You have input a wrong number, please input again.");
        }
    }


    private static void FCFS(List<ProInfo> infos){
        float prefinished = (float) 0.0; // 前一个作业的完成时间,即下一个作业的开始时间
        avgTotalTime = 0;    // 平均周转时间
        // 初始化完成时间、周转时间
        for (int i = 0; i < infos.size(); i++) {
            infos.get(i).finalTime = 0;
            infos.get(i).turnTime = 0;
        }
        //按到达时间进行冒泡升序排列
//        ProInfo temp;
//        for(int i = 0; i < infos.length; i++){
//            for (int j = 0; j < infos.length - i - 1; j++) {
//                if(infos[j].submitTime > infos[j+1].submitTime){
//                    temp = infos[j];
//                    infos[j] = infos[j+1];
//                    infos[j+1] = temp;
//                }
//            }
//        }
        Collections.sort(infos);

        for (int i = 0; i < infos.size(); i++) {
            //start = submit vs prefinished
            if(i == 0){
                infos.get(i).startTime = infos.get(i).submitTime;
            }else{
                float  a = infos.get(i).submitTime;
                float b = prefinished;
                infos.get(i).startTime = Math.max(a, b);
            }
            // final = start + run
            infos.get(i).finalTime = infos.get(i).startTime + infos.get(i).runTime;
            // refresh pre-time
            prefinished = infos.get(i).finalTime;
            //wait = strating - submit
            infos.get(i).waitTime = infos.get(i).startTime - infos.get(i).submitTime;
            //turnaround = final - submit
            infos.get(i).turnTime = infos.get(i).finalTime - infos.get(i).submitTime;
        }
        // 输出结果
        Display(infos);
    }


    private static void SJF(List<ProInfo> infos){
        //初始化参数
        Collections.sort(infos);
        float prefinished = infos.get(0).submitTime;
        avgTotalTime = 0;

        for (int i = 0; i < infos.size() ; i++) {
            infos.get(i).finalTime = 0;
            infos.get(i).turnTime = 0;
        }

        int n = 0;  // 定义作业号
        // 定义双层for循环用于比较作业的完成时间和服务时间
        for (int i = 0; i < infos.size(); i++) {
            //start = submit vs prefinished
            if(i == 0){
                infos.get(i).startTime = infos.get(i).submitTime;
            }else{
                float  a = infos.get(i).submitTime;
                float b = prefinished;
                infos.get(i).startTime = Math.max(a, b);
            }
            // final = start + run
            infos.get(i).finalTime = infos.get(i).startTime + infos.get(i).runTime;
            // refresh pre-time
            prefinished = infos.get(i).finalTime;
            //wait = strating - submit
            infos.get(i).waitTime = infos.get(i).startTime - infos.get(i).submitTime;
            //turnaround = final - submit
            infos.get(i).turnTime = infos.get(i).finalTime - infos.get(i).submitTime;

            float min = 8888;
            for (int j = i+1; j < infos.size(); j++) {
                if (infos.get(j).runTime < min && infos.get(j).submitTime <= prefinished && infos.get(j).finalTime == 0) {
                    min = infos.get(j).runTime; // 将目前服务时间最短的作业的服务时间赋值给作业的最小完成时间
                    n = j; // 将下一个进行调度的作业号赋值给number
                }
            }
            if(n > i+1 && i+1 < infos.size()){
                ProInfo temp = infos.get(n);
                infos.set(n, infos.get(i+1));
                infos.set(i+1, temp);
            }

        }
        // 输出结果
        Display(infos);
    }


    private static void Display(List<ProInfo> process) {
        System.out.println("\t\t" + "submit\t" + "run\t" + "starting\t" + "final\t" + "wait\t" + "turnaround");

        for (int i = 0; i < process.size(); i++) {
            System.out.println(process.get(i));
            //process.get(i).processNum + "\t" + process.get(i).submitTime + "\t" + process.get(i).runTime + "\t"+ process.get(i).startTime + "\t" + process.get(i).finalTime + "\t" + process.get(i).waitTime + "\t" + process.get(i).turnTime
            avgTotalTime += process.get(i).turnTime; // 求总周转时间，此时avgTotalTime中存储的值为总周转时间
        }
        avgTotalTime = avgTotalTime / process.size(); // 平均周转时间
        System.out.println("The average turnaround time is " + String.format("%.3f", avgTotalTime));
    }
}
