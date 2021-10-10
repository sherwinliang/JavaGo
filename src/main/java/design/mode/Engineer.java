package design.mode;

public abstract class Engineer implements Job{
    @Override
    public void doJob() {
        String name = stateName();
        System.out.println(name + " is doing his job.");
    }
    public abstract String stateName();
}
