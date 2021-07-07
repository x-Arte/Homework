import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * 在选择新建时间时弹出的窗口
 * 每次输入信息并进行保存新事件，单例化实现
 */
public class CreateDialog extends JDialog{
    JLabel titleLabel,infoLabel,beginLabel,endLabel;
    NoticePanel noticePanel;
    JTextArea title,info;
    TimePanel begin,end;
    JButton confirmButton,cancelButton;
    static final CreateDialog createDialog = new CreateDialog();
    final int maxTitleLength = 30;

    private CreateDialog(){
        super();
        init();
    }

    static public CreateDialog getInstance(){
        return createDialog;
    }

    public void init(){
        noticePanel = new NoticePanel(new Notice());
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

        title = new JTextArea(noticePanel.getNotice().title);
        title.setBounds(60,10,180,20);
        this.getContentPane().add(title);

        begin = new TimePanel(noticePanel.getNotice().begin);
        begin.setBounds(70,40,160,20);
        this.getContentPane().add(begin);

        end = new TimePanel(noticePanel.getNotice().end);
        end.setBounds(70,70,160,20);
        this.getContentPane().add(end);

        info = new JTextArea(noticePanel.getNotice().info);
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
     * 可以删除
     */
    public void refreshContent() {
        noticePanel = new NoticePanel(new Notice());
        title.setText(noticePanel.getNotice().title);
        info.setText(noticePanel.getNotice().info);
        begin.setTime(noticePanel.getNotice().begin);
        end.setTime(noticePanel.getNotice().end);
    }

    /**
     *修改之后，确认按钮监听器的设置
     *判断标题和时间是否符合标准
     *对于取消按钮的事件处理
     */
    public void addListeners(){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(title.getText().length()< maxTitleLength){
                    GregorianCalendar beginTime = begin.getTime();
                    GregorianCalendar endTime = end.getTime();
                    if(beginTime!=null&&endTime!=null){
                        if(beginTime.compareTo(endTime)<=0) {
                            noticePanel.getNotice().title = title.getText();
                            noticePanel.getNotice().info = info.getText();
                            noticePanel.getNotice().begin = begin.getTime();
                            noticePanel.getNotice().end = end.getTime();
                            NoticeList.getInstance().getNotices().add(noticePanel.getNotice());
                            NoticeListPanel.getInstance().draw();
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
