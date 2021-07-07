import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主Panel的绘制，单例化实现
 *  内容包括：Scroll，新建按钮，标题
 */
public class HuangPanel extends JPanel {
    private static final HuangPanel myPanel = new HuangPanel(HuangFrame.getInstance());

    public static HuangPanel getInstance() {
        return myPanel;
    }

    /**
     * 所有内容物的设置
     * @param frame 主窗口
     */
    private HuangPanel(HuangFrame frame){
        super();
        setLayout(null);
        JLabel name = new JLabel("平平无奇の备忘录");
        name.setBounds(50,30,320,40);
        name.setFont(new Font("华文彩云",Font.BOLD,34));
        add(name);
        JButton addNotice = new JButton("New");
        addNotice.setBounds(370,30,80,40);
        addNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialog.getInstance().setVisible(true);
                CreateDialog.getInstance().refreshContent();
            }
        });
        add(addNotice);
        add(NoticeListScroll.getInstance());
        setVisible(true);
    }
}