import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 每次发生错误时的弹出窗口
 * 只需要报出错误，采用单例化实现
 */
public class ErrorDialog extends JDialog {
    JLabel label;
    JButton button;
    static ErrorDialog errorDialog = new ErrorDialog();

    private ErrorDialog() {
        this.setContentPane(new Panel());
        this.setLayout(null);
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0,8,250,30);
        this.getContentPane().add(label);
        button = new JButton("Confirm");
        button.addActionListener(e -> setVisible(false));
        button.setBounds(75,45,100,25);
        this.getContentPane().add(button);
        setBounds(500,300,250,120);
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("Huang.jpg"));
        setResizable(false);
        setModal(true);
    }

    /**
     * 弹出错误窗口
     * @param string 错误提示
     */
    public static void popUp(String string) {
        errorDialog.label.setText(string);
        errorDialog.setVisible(true);
    }
}
