import javax.swing.*;
import java.awt.*;

/**
 * 在主界面中的滚动插件，单例化实现
 */
public class NoticeListScroll extends JScrollPane {
    private static final NoticeListScroll noticeListScroll = new NoticeListScroll(NoticeListPanel.getInstance());

    public static NoticeListScroll getInstance(){
        return noticeListScroll;
    }

    private NoticeListScroll(NoticeListPanel noticeListPanel){
        super(noticeListPanel);
        setBounds(50,100,420,300);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
    }

}

