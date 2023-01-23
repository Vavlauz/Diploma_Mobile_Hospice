package additional;

public class ExampleOfNews {
    public boolean emptyCategory;
    public boolean choiceOfCategory;
    public String chosenCategory;
    public boolean category;
    public boolean title;
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

    public enum Status {
        ACTIVE ("Active"), NOTACTIVE ("Not active");

        public String param;

        Status(String p) {
            this.param = p;
        }
    };


    
    public ExampleOfNews (boolean emptyCategory,boolean choiceOfCategory,String chosenCategory,boolean category,boolean title,boolean emptyDate,boolean emptyTime,ClockEnter clockEnter,TimeShift timeShift,String description) {
      this.emptyCategory = emptyCategory;
      this.choiceOfCategory = choiceOfCategory;
      this.chosenCategory = chosenCategory;
      this.category = category;
      this.title = title;
      this.emptyDate = emptyDate;
      this.emptyTime = emptyTime;
      this.description = description;
      this.clockEnter = clockEnter;
      this.timeShift = timeShift;

    }
}
