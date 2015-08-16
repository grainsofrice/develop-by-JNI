import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class ThreadView extends JFrame{
	private JLabel label1;
	private JLabel label2;
	private JButton newThread;
	private Container c;
	public static int shouldBreak = 0;//Ҫֹͣ���߳���������3ʱֹͣ
	public ThreadView() {
		setResizable(false);
		setSize(400, 400);
		setTitle("���߳�");
		setLocation(0,0);
	    createwindow();
		setVisible(true);
	}

	private void createwindow() {
		// TODO Auto-generated method stub
		c = getContentPane();
		c.setLayout(null);
		label1 = new JLabel("��ǰ�߳���Ϊ:");
		label1.setBounds(100, 200, 140, 30);
		label1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		c.add(label1);
		
		label2 = new JLabel(shouldBreak+"");
		label2.setBounds(250, 200, 20, 30);
		label2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		c.add(label2);
		
		newThread = new JButton("���߳�");
		newThread.setBounds(150, 100, 100, 30);
		c.add(newThread);
		newThread.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(shouldBreak > 3) {
					JOptionPane.showMessageDialog(null, "�߳����Ѿ�����4�����´��ڵ���ʶ��⺯����Ч��", "����", JOptionPane.ERROR_MESSAGE);
				}
				else if (new getFunction().Init(0)!= face_detect_sdk.PFD_STATUS_OK) {
					JOptionPane.showMessageDialog(null, "��ʼ��ʧ��", "^_^", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					UserThread ut = new UserThread();
					ut.start();
					shouldBreak++;
					label2.setText(shouldBreak+"");
				}
			}
		});
	}
	public static void main(String[] agrs) {
		ThreadView tv = new ThreadView();
		tv.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
