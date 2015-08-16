import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class FeatureMatchingView extends JFrame{
	private Container c;
	private JLabel labelImg1;
	private JLabel labelImg2;
	private JLabel label1;
	private JLabel label2;
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JScrollPane scroll3;
	private JScrollPane scroll4;
	private JButton openImg1;
	private JButton openImg2;
	private JButton identifyImg;
	private JButton takepicture;
	private JFileChooser jfc1;
	private JFileChooser jfc2;
	private ImageIcon imageIcon1;
	private ImageIcon imageIcon2;
	private JTextArea textarea1;
	private JTextArea textarea2;
	private JPanel p1;
	private JPanel p2;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JRadioButton rb4;
	private JRadioButton rb5;
	private JRadioButton rb6;
	private JRadioButton rb7;
	private JRadioButton rb8;
	private ButtonGroup group1;
	private ButtonGroup group2;
	@SuppressWarnings("unused")
	private Graphics2D g2d1;
	@SuppressWarnings("unused")
	private Graphics2D g2d2;
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private String name1;//文件路径名
	private String name2;//文件路径名
	private byte[] bmpData1;
	private byte[] bmpData2;
	private int score = 0;
	public FeatureMatchingView() {
		ImageIcon img = new ImageIcon("22.jpg");//这是背景图片
		JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
		c=getContentPane();
		c.setLayout(new BorderLayout());
		((JPanel)c).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		setSize(1000,720);
		setResizable(false);
		int windowWidth = getWidth();                     //获得窗口宽
	    int windowHeight = getHeight();                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();        //定义工具包
	    Dimension screenSize = kit.getScreenSize();       //获取屏幕的尺寸
	    int screenWidth = screenSize.width;               //获取屏幕的宽
	    int screenHeight = screenSize.height;             //获取屏幕的高
	    setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示  
		creativeW();
		setTitle("对比界面");
		setVisible(true);
	}
	private void creativeW() {
		// TODO Auto-generated method stub
		c = getContentPane();
		c.setLayout(null);
		openImg1 = new JButton("打开图片1");
		openImg1.setBounds(138, 16, 100, 30);
		openImg1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc1 = new JFileChooser();
				jfc1.setCurrentDirectory(new File("D:\\pic"));
				int result = jfc1.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					name1 = jfc1.getSelectedFile().getPath();
					imageIcon1 = new ImageIcon(name1);
					System.out.println(name1);
					labelImg1.setIcon(imageIcon1);
					bufferedImage1 = new BufferedImage(imageIcon1.getIconHeight(), imageIcon1.getIconWidth(), bufferedImage1.TYPE_INT_RGB);
					g2d1 = (Graphics2D)bufferedImage1.getGraphics();
					try {
						bmpData1 = image2Bytes(name1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i = 0 ; i < bmpData1.length ; i++) {
						if(bmpData1[i] > 0)
							System.out.println(bmpData1[i]);
					}
				}
			}
		});
		c.add(openImg1);
		
		openImg2 = new JButton("打开图片2");
		openImg2.setBounds(488, 16, 100, 30);
		openImg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc2 = new JFileChooser();
				jfc2.setCurrentDirectory(new File("D:\\pic"));
				int result = jfc2.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					name2 = jfc2.getSelectedFile().getPath();
					imageIcon2 = new ImageIcon(name2);
					System.out.println(name2);
					labelImg2.setIcon(imageIcon2);
					bufferedImage2 = new BufferedImage(imageIcon2.getIconHeight(), imageIcon2.getIconWidth(), bufferedImage2.TYPE_INT_RGB);
					g2d2 = (Graphics2D)bufferedImage2.getGraphics();
					try {
						bmpData2 = image2Bytes(name2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i = 0 ; i < bmpData2.length ; i++) {
						if(bmpData2[i] > 0)
							System.out.println(bmpData2[i]);
					}
				}
			}
		});
		c.add(openImg2);
		
		labelImg1 = new JLabel();
		scroll1 = new JScrollPane(labelImg1);
		scroll1.setBounds(42, 62, 340, 400);
		c.add(scroll1);
		
		labelImg2 = new JLabel();
		scroll2 = new JScrollPane(labelImg2);
		scroll2.setBounds(402, 62, 340, 400);
		c.add(scroll2);
		
		textarea1 = new JTextArea();
		scroll3 = new JScrollPane(textarea1);
		scroll3.setBounds(63, 494, 275, 190);
		c.add(scroll3);
		
		textarea2 = new JTextArea();
		scroll4 = new JScrollPane(textarea2);
		scroll4.setBounds(418, 494, 275, 190);
		c.add(scroll4);
		
		takepicture = new JButton("拍照");
		takepicture.setBounds(808, 16, 70, 30);
		c.add(takepicture);
		takepicture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new test();
			}
		});
		label1 = new JLabel("得分:");
		label1.setBounds(765, 165, 55, 50);
		c.add(label1);
		label2 = new JLabel("0");
		label2.setBounds(800, 165, 55, 50);
		c.add(label2);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 1));
		group1 = new ButtonGroup();// 创建单选按钮组
		rb1 = new JRadioButton("人脸朝上");
		rb2 = new JRadioButton("右转90度");
		rb3 = new JRadioButton("旋转180度");
		rb4 = new JRadioButton("左转90度");
		p1.setBorder(BorderFactory.createTitledBorder("图片1人脸朝向"));
		group1.add(rb1);
		group1.add(rb2);
		group1.add(rb3);
		group1.add(rb4);
		p1.add(rb1);
		p1.add(rb2);
		p1.add(rb3);
		p1.add(rb4);
		p1.setBounds(808, 240, 155, 100);
		c.add(p1);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1));
		group2 = new ButtonGroup();
		rb5 = new JRadioButton("人脸朝上");
		rb6 = new JRadioButton("右转90度");
		rb7 = new JRadioButton("旋转180度");
		rb8 = new JRadioButton("左转90度");
		p2.setBorder(BorderFactory.createTitledBorder("图片2人脸朝向"));
		group2.add(rb5);
		group2.add(rb6);
		group2.add(rb7);
		group2.add(rb8);
		p2.add(rb5);
		p2.add(rb6);
		p2.add(rb7);
		p2.add(rb8);
		p2.setBounds(808, 370, 155, 100);
		c.add(p2);
		
		identifyImg = new JButton("识别图片");
		identifyImg.setBounds(820, 490, 120, 35);
		identifyImg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				getFunction t = new getFunction();
				//取得左边图片的特征值
		        byte[] feature1 = getImgFeature(bmpData1, true);
		        //取得右边图片的特征值
		        byte[] feature2 = getImgFeature(bmpData2, false);
		        if (feature1.length == feature2.length && feature1.length > 0)
		        {
		            score = t.FeatureMatching((short)feature1.length, feature1, (short)feature2.length, feature2);
		           	int score1 = Integer.parseInt(label2.getText());
		           	int score2 = score1 + score ;
		            label2.setText(score2+"");
		        }
				 //detect_btn_Click();
			}
		});
		c.add(identifyImg);
	}

	public static byte[] image2Bytes(String imagePath) throws IOException {
		// TODO Auto-generated method stub
		ImageIcon ima = new ImageIcon(imagePath); 
		BufferedImage bu = new BufferedImage(ima.getImage().getWidth(null), ima .getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		//把这个jpg图像写到这个流中去,这里可以转变图片的编码格式
		ImageIO.write(bu, "bmp", imageStream); 
		byte[] tagInfo = imageStream.toByteArray();
		return tagInfo;
	}

	private byte[] getImgFeature(byte[] bmpData, boolean no) {
		StringBuilder bs = new StringBuilder();
        byte[] feature = new byte[0];
		short faceRote = 0;
		if(no) {
			if(rb1.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_0;
			}
			else if(rb2.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_90;
			}
			else if(rb3.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_180;
			}
			else if(rb4.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_270;
			}
		}
		else {
			if(rb5.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_0;
			}
			else if(rb6.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_90;
			}
			else if(rb7.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_180;
			}
			else if(rb8.isSelected()) {
				faceRote = face_detect_sdk.PFD_OP_FACE_ROLL_270;
			}
		}
		
		PFD_FACE_DETECT faceInfo = new PFD_FACE_DETECT();
		faceInfo.info = new PFD_DETECT_INFO[face_detect_sdk.PFD_MAX_FACE_NUM];
		faceInfo.num = 1;
		for (int i = 0; i < faceInfo.info.length; i++) {
			faceInfo.info[i] = new PFD_DETECT_INFO();
			faceInfo.info[i].faceInfo = new PFD_FACE_POSITION();
			faceInfo.info[i].faceInfo.conf = 10;
			faceInfo.info[i].faceInfo.eye_lx = 188;
			faceInfo.info[i].faceInfo.eye_ly = 126;
			faceInfo.info[i].faceInfo.eye_rx = 142;
			faceInfo.info[i].faceInfo.eye_ry = 125;
			faceInfo.info[i].faceInfo.rect_b = 205;
			faceInfo.info[i].faceInfo.rect_l = 108;
			faceInfo.info[i].faceInfo.rect_r = 206;
			faceInfo.info[i].faceInfo.rect_t = 167;
		    faceInfo.info[i].ageConf = 1;
			faceInfo.info[i].genConf = 1;
			faceInfo.info[i].age = 30;
			faceInfo.info[i].gen = 1;
			faceInfo.info[i].smile = 15;
			faceInfo.info[i].pitch = 17;
			faceInfo.info[i].yaw = 15;
			faceInfo.info[i].roll = 15;
			faceInfo.info[i].flen = 16;
			faceInfo.info[i].lb = 17;
			faceInfo.info[i].rb = 18;
		}
		byte[] kkk = new byte[2000000];
        for (int i = 0; i < kkk.length; i++)
        {
            kkk[i] = 0;
        }
        //识别图片，并取得特征值
        new getFunction().GetFeature(bmpData, faceInfo, kkk, faceRote);
        //输出识别信息
        if (faceInfo.num != 0)
        {
            feature = new byte[faceInfo.info[0].flen];
            //将特征值复制到数组中
            for(int i = 0;i<(int)faceInfo.info[0].flen;i++) {
            	feature[i] = kkk[i];
            }
        }
        else
        {
            feature = new byte[0];

        }
        //返回特征值 
        for(int i=0;i<faceInfo.num;i++) {
    		bs.append("\r\n" + "\t年龄:" + (faceInfo.info[i].age - (short)2) + "->" + (faceInfo.info[i].age + (short)2) + "\r\n"
                + "\t年龄可信度:" + faceInfo.info[i].ageConf + "\r\n"
                + "\t性别:" + (faceInfo.info[i].gen==1?"女":"男") + "\r\n"
                + "\t性别可信度:" + faceInfo.info[i].genConf + "\r\n"
                + "\t微笑程度:" + faceInfo.info[i].smile + "\r\n"
                + "\t抬头低头程度:" + faceInfo.info[i].pitch+ "\r\n"
                + "\t摇头程度:" + faceInfo.info[i].yaw + "\r\n"
                + "\t倾斜程度:" + faceInfo.info[i].roll + "\r\n"
                + "\t左眼闭眼程度:" + faceInfo.info[i].lb + "\r\n"
                + "\t右眼闭眼程度:" + faceInfo.info[i].rb + "\r\n");
    	}
        textarea1.setText(textarea1.getText()+bs.toString());
        textarea2.setText(textarea1.getText()+bs.toString());
        return feature;
	}
}
