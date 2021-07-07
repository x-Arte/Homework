import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 绘制时间的Panel，其中包括年、月、日、小时、分钟五个Textfield
 */

public class TimePanel extends JPanel {

    JTextArea year,month,date,hour,minute;

    public TimePanel(GregorianCalendar time){
        super();
        year = new JTextArea();
        month = new JTextArea();
        date = new JTextArea();
        hour = new JTextArea();
        minute = new JTextArea();
        setLayout(null);
        setTime(time);
        add(year);
        add(month);
        add(date);
        add(hour);
        add(minute);
        setVisible(true);
    }

    public void setTime(GregorianCalendar time) {
        year.setText(String.valueOf(time.get(Calendar.YEAR)));
        year.setBounds(0,2,40,26);
        month.setText(String.valueOf(time.get(Calendar.MONTH)+1));
        month.setBounds(50,2,20,26);
        date.setText(String.valueOf(time.get(Calendar.DATE)));
        date.setBounds(80,2,20,26);
        hour.setText(String.valueOf(time.get(Calendar.HOUR_OF_DAY)));
        hour.setBounds(110,2,20,26);
        minute.setText(String.valueOf(time.get(Calendar.MINUTE)));
        minute.setBounds(140,2,20,26);
    }

    /**
     * 在用户填完时间后对于时间格式进行检查
     * @return 如果是正确的时间，则返回，否则返回null方便检查
     */
    public GregorianCalendar getTime() {
        int year,month,date,hour,minute;
        int[] monthDays = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        try{
            year = Integer.parseInt(this.year.getText());
            month = Integer.parseInt(this.month.getText())-1;
            date = Integer.parseInt(this.date.getText());
            hour = Integer.parseInt(this.hour.getText());
            minute = Integer.parseInt(this.minute.getText());
        }catch(NumberFormatException e) {
            ErrorDialog.popUp("Please input numbers");
            return null;
        }
        if(hour>=0&&hour<=24){
            if(minute>=0&&minute<60){
                if(year>1900&&year<3000){
                    if(month>=0&&month<12){
                        if(date>0&&date<=monthDays[month]||(month==1&&date==29&&((year%4==0&&year%100!=0)||(year%400==0)))){
                            return NoticeList.getCalendar(year,month,date,hour,minute);
                        } else {
                            ErrorDialog.popUp("Wrong date!");
                        }
                    } else {
                        ErrorDialog.popUp("Wrong month!");
                    }
                } else {
                    ErrorDialog.popUp("Wrong year!");
                }
            } else {
                ErrorDialog.popUp("Wrong minute!");
            }
        } else {
            ErrorDialog.popUp("Wrong hour!");
        }
        return null;
    }
}
