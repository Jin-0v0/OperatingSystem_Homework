class ProInfo implements Comparable{
    public int processNum;
    // 到达时间
    public float submitTime;
    // 运行时间
    public float runTime;
    // 开始时间
    public float startTime;
    // 完成时间
    public float finalTime;
    // 等待时间
    public float waitTime;
    // 周转时间
    public float turnTime;


    // 作业的构造方法中传来的初值为到达时间和服务时间
    public ProInfo(int processNum, float submitTime, float runTime) {
        this.processNum = processNum;
        this.submitTime = submitTime;
        this.runTime = runTime;
    }

    //重写排序
    @Override
    public int compareTo(Object o) {
        ProInfo s = (ProInfo) o;
        if (this.submitTime > s.submitTime) {
            return 1;
        }else if(this.submitTime < s.submitTime){
            return -1;
        }else {
            return -1;
        }
        //return this.submitTime - p.submintTime();
    }

    // 重写toString方法便于之后的数据展示
    @Override
    public String toString() {
        return  processNum + "\t\t" + submitTime + "\t\t" + runTime + "\t\t" + String.format("%.1f", startTime) + "\t\t"
                + String.format("%.1f", finalTime) + "\t\t" + String.format("%.1f", waitTime) + "\t\t" + String.format("%.1f", turnTime) ;
    }
}
