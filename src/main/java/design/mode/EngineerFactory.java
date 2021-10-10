package design.mode;

public class EngineerFactory implements JobFactory{
    @Override
    public Engineer getJob(String jobName) {
        if("Architect".equalsIgnoreCase(jobName)){
            return new Architect();
        }else if("Programmer".equalsIgnoreCase(jobName)){
            return new Programmer();
        }
        return null;
    }
}
