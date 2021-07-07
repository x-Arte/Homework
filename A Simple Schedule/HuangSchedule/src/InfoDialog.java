import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * 弹出详细信息的弹窗
 */
public class InfoDialog extends JDialog{
    JLabel titleLabel,infoLabel,beginLabel,endLabel;
    NoticePanel noticePanel;
    JTextArea title,info;
    TimePanel begin,end;
    JButton confirmButton,cancelButton;
    final int maxTitleLength = 30;

    public InfoDialog(NoticePanel noticePanel){
        super();
        this.noticePanel = noticePanel;
        init();
    }

    /**
     * 初始化所有组件的位置
     */
    public void init(){
        this.setContentPane(new Panel());
        setBounds(400,300,280,250);
        setLayout(null);

        titleLabel = new JLabel("title: ");
        titleLabel.setBounds(10,10,50,30);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        this.getContentPane().add(titleLabel);

        beginLabel = new JLabel("begin: ");
        beginLabel.setBounds(10,40,50,30);
        beginLabel.setVerticalAlignment(JLabel.TOP);
        this.getContentPane().add(beginLabel);

        endLabel = new JLabel("end: ");
        endLabel.setBounds(10,70,50,30);
        endLabel.setVerticalAlignment(JLabel.TOP);
        this.getContentPane().add(endLabel);

        infoLabel = new JLabel("info: ");
        infoLabel.setBounds(10,100,50,30);
        infoLabel.setVerticalAlignment(JLabel.TOP);
        this.getContentPane().add(infoLabel);

        title = new JTextArea();
        title.setBounds(60,10,180,20);
        this.getContentPane().add(title);

        begin = new TimePanel(noticePanel.getNotice().begin);
        begin.setBounds(70,40,160,20);
        this.getContentPane().add(begin);

        end = new TimePanel(noticePanel.getNotice().end);
        end.setBounds(70,70,160,20);
        this.getContentPane().add(end);

        info = new JTextArea();
        info.setBounds(60,100,180,48);
        this.getContentPane().add(info);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(30,165,80,30);
        this.getContentPane().add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(160,165,80,30);
        this.getContentPane().add(cancelButton);
        addListeners();

        this.setIconImage(Toolkit.getDefaultToolkit().createImage("Huang.jpg"));
        setResizable(false);
        setModal(true);
    }

    /**
     * 再刷新时对于Notice的内容进行更新
     */
    public void refreshContent() {
        title.setText(noticePanel.getNotice().title);
        info.setText(noticePanel.getNotice().info);
        begin.setTime(noticePanel.getNotice().begin);
        end.setTime(noticePanel.getNotice().end);
    }

    /**
     * 修改之后，确认按钮监听器的设置
     * 判断标题和时间是否符合标准
     * 对于取消按钮的事件处理
     */
    public void addListeners(){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(title.getText().length()< maxTitleLength){
                    GregorianCalendar beginTime = begin.getTime();
                    GregorianCalendar endTime = end.getTime();
                    if(beginTime!=null&&endTime!=null) {
                        if (beginTime.compareTo(endTime)<=0) {
                            noticePanel.getNotice().title = title.getText();
                            noticePanel.getNotice().info = info.getText();
                            noticePanel.getNotice().begin = begin.getTime();
                            noticePanel.getNotice().end = end.getTime();
                            noticePanel.getNotice().started = false;
                            noticePanel.getNotice().ended = false;
                            noticePanel.setVisible(false);
                            noticePanel.getTitle().setText(title.getText());
                            noticePanel.setVisible(true);
                            setVisible(false);
                        } else {
                            ErrorDialog.popUp("You can't end before you start!");
                        }
                    }
                } else {
                    ErrorDialog.popUp("Title is longer than "+ maxTitleLength+" characters!");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

}
