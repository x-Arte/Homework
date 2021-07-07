import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 每一个事件
 * 包括标题，具体内容，是否开始/结束状态，开始结束的时间
 */
public class Notice{
    public String title,info;
    public boolean  started = false,ended = false;
    public GregorianCalendar begin,end;

    public void check(){
        if(!started&&begin.compareTo(new GregorianCalendar())<=0) {
            started = true;
            ErrorDialog.popUp("Notice "+ title+" started");
        }
        if(!ended&&end.compareTo(new GregorianCalendar())<=0) {
            ended = true;
            ErrorDialog.popUp("Notice "+ title+" ended");
        }
    }

    Notice(){
        this("No title","No information",new GregorianCalendar(),new GregorianCalendar());
        this.end.add(Calendar.HOUR_OF_DAY, 1);
    }

    Notice(String title,GregorianCalendar begin,GregorianCalendar end){
        this(title,"No information",begin,end);
    }

    Notice(String title,String info,GregorianCalendar begin,GregorianCalendar end){
        this(title,info,begin,end,false,false);
    }

    Notice(String title,String info,GregorianCalendar begin,GregorianCalendar end,boolean started,boolean ended) {
        this.title = title;
        this.info = info;
        this.begin = begin;
        this.end = end;
        this.started = started;
        this.ended = ended;
    }
}

