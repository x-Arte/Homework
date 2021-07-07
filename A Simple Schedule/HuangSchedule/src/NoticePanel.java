import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 在主界面的ScollList中每一个事件自己拥有的Panel
 * 包括查看更多，删除的按钮
 */

public class NoticePanel extends JPanel {
    private final Notice notice;
    private final JButton info,delete;
    private final JLabel title;
    private final JDialog infoDialog,deleteDialog;

    public Notice getNotice(){
        return notice;
    }

    public JLabel getTitle() {
        return title;
    }

    /**
     * 初始化所有的按钮和位置，为每个按钮绑定事件
     * @param notice 需要被显示的notice
     */

    NoticePanel(Notice notice){
        this.notice = notice;
        title = new JLabel();
        info = new JButton("...");
        delete = new JButton("Del");
        infoDialog = new InfoDialog(this);
        deleteDialog = new DeleteDialog(this);
        initPanel();
        initTitle();
        initInfo();
        initDelete();
    }

    private void initPanel() {
        this.setLayout(null);
        this.setSize(400,30);
        this.setVisible(true);
        this.setBackground(Color.PINK);

    }

    private void initTitle() {
        title.setText(notice.title);
        title.setBounds(10,0,300,30);
        this.add(title);
        title.setVisible(true);
    }

    private void initInfo() {
        info.setBounds(240,0,80,30);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((InfoDialog)infoDialog).refreshContent();
                infoDialog.setVisible(true);
            }
        });
        this.add(info);
        info.setVisible(true);
    }

    private void initDelete() {
        delete.setBounds(320,0,80,30);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDialog.setVisible(true);
            }
        });
        this.add(delete);
        delete.setVisible(true);
    }
}