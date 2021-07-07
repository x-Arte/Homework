import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

/**
 * 绘制滚动框绑定的Panel，单例化实现
 */

class NoticeListPanel extends JPanel {
    private static final NoticeListPanel noticeListPanel = new NoticeListPanel();

    public static NoticeListPanel getInstance() {
        return noticeListPanel;
    }

    private NoticeListPanel() {
        setLayout(null);
        draw();
    }

    /**
     * 每次刷新时重新绘制的函数，按照开始时间的前后进行排序
     */
    public void draw() {
        setVisible(false);
        setPreferredSize(new Dimension(400,35 * NoticeList.getInstance().getNotices().size()));
        int index = 0;
        removeAll();
        NoticeList.getInstance().getNotices().sort(Comparator.comparing(e->e.begin));
        for (Notice i : NoticeList.getInstance().getNotices()) {
            NoticePanel temp = new NoticePanel(i);
            temp.setLocation(0, index * 35);
            add(temp);
            index++;
        }
        setVisible(true);
    }
}
