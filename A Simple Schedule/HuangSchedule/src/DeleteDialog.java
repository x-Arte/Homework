import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 在删除时间时弹出的窗口
 * 每个Notice会拥有一个
 */
public class DeleteDialog extends JDialog {
    NoticePanel noticePanel;
    JButton yes,no;
    JLabel tLabel;
    DeleteDialog(NoticePanel noticePanel) {
        this.noticePanel = noticePanel;
        this.setContentPane(new Panel());
        this.setBounds(300,300,250,100);
        this.setTitle("Confirm!");
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("Huang.jpg"));
        tLabel = new JLabel("Are you sure to delete this notice? ");
        tLabel.setLocation(0,0);
        this.getContentPane().add(tLabel);
        yes = new JButton("Confirm");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoticeList.getInstance().getNotices().remove(noticePanel.getNotice());
                NoticeListPanel.getInstance().draw();
                setVisible(false);
            }
        });
        yes.setVisible(true);
        yes.setLocation(50,50);
        this.getContentPane().add(yes);
        no = new JButton("Cancel");
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        no.setVisible(true);
        no.setLocation(175,50);
        this.getContentPane().add(no);
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("Huang.jpg"));
        setResizable(false);
        setModal(true);
    }
}