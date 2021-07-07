import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * 主窗口，单例化实现
 */
public class HuangFrame extends JFrame {
    private static final HuangFrame myFrame = new HuangFrame();

    public static HuangFrame getInstance() {
        return myFrame;
    }

    private HuangFrame() {
        super();
        init();
    }

    /**
     * 绑定Panel，设置窗口标题，logo等
     */
    public void init() {
        try {
            NoticeList.getInstance().ReadFile(new File("saved.txt"));
        } catch (IOException ignored) { }
        this.setTitle("这是一个平平无奇的备忘录0_0");
        setSize(520,480);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    NoticeList.getInstance().SaveToFile((new File("saved.txt")));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.exit(0);
            }
        });
        setContentPane(HuangPanel.getInstance());
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("Huang.jpg"));
        setVisible(true);
    }
}