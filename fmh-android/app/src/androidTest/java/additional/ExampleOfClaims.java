package additional;

public class ExampleOfClaims {
    public String title;
    public String chosenExecutor;
    public boolean executor;
    public boolean emptyDate;
    public boolean emptyTime;
    public String description;
    public ClockEnter clockEnter;
    public TimeShift timeShift;

    public enum ClockEnter {
        DIAL, TEXT
    };

    public enum TimeShift {
        SAVE, CANCEL
    };

    public ExampleOfClaims(String title, String chosenExecutor, boolean executor, boolean emptyDate, boolean emptyTime, ClockEnter clockEnter, TimeShift timeShift, String description) {
        this.title = title;
        this.chosenExecutor = chosenExecutor;
        this.executor = executor;
        this.emptyDate = emptyDate;
        this.emptyTime = emptyTime;
        this.description = description;
        this.clockEnter = clockEnter;
        this.timeShift = timeShift;

    }
}
