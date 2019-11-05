package android.eric.kaiyue.secretary;

import java.lang.reflect.Member;

public class Reminder {

    /* Member variables
    DateTime: mm/dd/yyyy, mm:ss
    Title (String): title of the reminder
     */
    private String mDate;
    private String mTime;
    private String mTitle;

    // Constructor for Reminder
    public Reminder(String title,
                    String date,
                    String time){
        this.mTitle = title;
        this.mDate= date;
        this.mTime = time;
    }

    public String getTitle(){return mTitle;}

    public String getDate(){return  mDate;}

    public String getTime() {return  mTime;}
}
