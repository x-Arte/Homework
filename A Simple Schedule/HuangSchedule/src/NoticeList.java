import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * 一个用来储存全部事件的容器，通过单例化实现
 */
public class NoticeList {
    private static NoticeList noticeList = null;

    private final Vector<Notice> notices;

    private NoticeList() {
        this.notices = new Vector<>();
    }

    public static NoticeList getInstance() {
        if(noticeList == null) {
            noticeList = new NoticeList();
        }
        return noticeList;
    }


    public Vector<Notice> getNotices() {
        return notices;
    }

    /**
     * 这个函数用来将年月日时分生成一个GregorianCalender类
     */
    public static GregorianCalendar getCalendar(int year,int month,int day,int hour,int minute) {
        return new GregorianCalendar(year,month,day,hour,minute);
    }

    /**
     * 程序关闭时执行，将数据储存进txt文件
     * @param saveNotices 指定储存的文件名
     * @throws IOException 文件不存在的异常，不需要处理
     */
    public void SaveToFile(File saveNotices) throws IOException {
        if(!saveNotices.exists()){
            final boolean newFile = saveNotices.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(saveNotices,false),StandardCharsets.UTF_8));
        for(Notice e: notices){
            bufferedWriter.write(e.title+"\\%\\"+e.info+"\\%\\"+
                    e.begin.get(Calendar.YEAR) + ":" +
                    e.begin.get(Calendar.MONTH) + ":" +
                    e.begin.get(Calendar.DATE) + ":" +
                    e.begin.get(Calendar.HOUR_OF_DAY) + ":" +
                    e.begin.get(Calendar.MINUTE) + "\\%\\" +
                    e.end.get(Calendar.YEAR) + ":" +
                    e.end.get(Calendar.MONTH) + ":" +
                    e.end.get(Calendar.DATE) + ":" +
                    e.end.get(Calendar.HOUR_OF_DAY) + ":" +
                    e.end.get(Calendar.MINUTE) + "\\%\\" +
                    (e.started?(1):(0)) + "\\%\\" +
                    (e.ended?(1):(0)));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    /**
     * 程序运行开始时需要从文件读取用户之前储存的事件
     * @param savedNotices 指定储存的文件名
     * @throws IOException 文件不存在的异常，不需要处理
     */
    void ReadFile(File savedNotices) throws IOException {
        if(savedNotices.exists()){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(savedNotices), StandardCharsets.UTF_8));
            notices.clear();
            String inStr;
            while((inStr = bufferedReader.readLine())!=null){
                String[] inArgs = inStr.split("\\\\%\\\\");
                String[] begin = inArgs[2].split(":");
                String[] end = inArgs[3].split(":");
                notices.add(new Notice(inArgs[0],inArgs[1],
                        getCalendar(Integer.parseInt(begin[0]),
                                Integer.parseInt(begin[1]),
                                Integer.parseInt(begin[2]),
                                Integer.parseInt(begin[3]),
                                Integer.parseInt(begin[4])),
                        getCalendar(Integer.parseInt(end[0]),
                                Integer.parseInt(end[1]),
                                Integer.parseInt(end[2]),
                                Integer.parseInt(end[3]),
                                Integer.parseInt(end[4])),
                        (inArgs[4].equals("1")),
                        (inArgs[5].equals("1"))
                        )
                );
            }
            bufferedReader.close();
        }
    }

    /*public static void main(String[] args) throws IOException {
        NoticeList.getInstance().notices.add(new Notice("A hello",
                getCalendar(2021,5,29,23,53),
                new GregorianCalendar()
                )
        );
        NoticeList.getInstance().SaveToFile(new File("saved.txt"));
        NoticeList.getInstance().ReadFile(new File("saved.txt"));
        for(Notice e: NoticeList.getInstance().notices) {
            System.out.println(e.title + " " + e.info + " " +
                    e.begin.get(Calendar.YEAR) + ":" +
                    e.begin.get(Calendar.MONTH) + ":" +
                    e.begin.get(Calendar.DATE) + ":" +
                    e.begin.get(Calendar.HOUR_OF_DAY) + ":" +
                    e.begin.get(Calendar.MINUTE) + " " +
                    e.end.get(Calendar.YEAR) + ":" +
                    e.end.get(Calendar.MONTH) + ":" +
                    e.end.get(Calendar.DATE) + ":" +
                    e.end.get(Calendar.HOUR_OF_DAY) + ":" +
                    e.end.get(Calendar.MINUTE)
            );
        }
    }*/
}