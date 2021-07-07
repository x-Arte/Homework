import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用双线程
 * Timer每次精确计算距离下一分钟所需时间
 * 绑定Frame
 */
public class CalendarPad{

    public static void main(String[] args)  {
        HuangFrame frame = HuangFrame.getInstance();
        Timer timer = new Timer();
        for (Notice i : NoticeList.getInstance().getNotices()) {
            i.check();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.add(Calendar.MINUTE,1);
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                for (Notice i : NoticeList.getInstance().getNotices()) {
                    i.check();
                }
            }
        },(calendar.getTimeInMillis()-new GregorianCalendar().getTimeInMillis()+10),(long)60000);
    }
}
