import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class FaceRecogView extends JFrame{
	private getFunction t;
	private String name ;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JLabel labelImg;
	private JTextArea textarea;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JRadioButton rb4;
	private JButton openImg;
	private JButton identifyImg;
	private JFileChooser chooser;
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private JCheckBox cb4;
	private JCheckBox cb5;
	private Container c;
	byte[] bmpData;
	Graphics2D g2d;
	BufferedImage bufferedImage;
	ImageIcon image;
	
		public FaceRecogView() {
		ImageIcon img = new ImageIcon("22.jpg");//这是背景图片
		JLabel imgLabel = new JLabel(img);//将背景图放在标签里。

		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
		c=getContentPane();
		c.setLayout(new BorderLayout());

		((JPanel)c).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		setResizable(false);
		setSize(800, 700);
		setTitle("识别界面");
		int windowWidth = getWidth();                     //获得窗口宽
	    int windowHeight = getHeight();                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();        //定义工具包
	    Dimension screenSize = kit.getScreenSize();       //获取屏幕的尺寸
	    int screenWidth = screenSize.width;               //获取屏幕的宽
	    int screenHeight = screenSize.height;             //获取屏幕的高
	    setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示  
		createWindow();	
		setVisible(true);
	}
	public void createWindow() {
		t = new getFunction();
		c= getContentPane();
		c.setLayout(null);
		
		labelImg = new JLabel();
		JScrollPane scroll1 = new JScrollPane(labelImg);
		scroll1.setBounds(100, 70, 300, 300);
		c.add(scroll1);
		
		textarea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setBounds(100, 450, 300, 150);
		c.add(scroll);
		
		openImg = new JButton("打开图片");
		openImg.setBounds(600, 40, 100, 30);
		c.add(openImg);
		openImg.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("E:\\人脸识别\\测试图片"));
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					name = chooser.getSelectedFile().getPath();
					image = new ImageIcon(name);
					labelImg.setIcon(image);
					//System.out.println(name);
					bufferedImage = new BufferedImage(image.getIconHeight(),image.getIconWidth(),BufferedImage.TYPE_INT_RGB);
					g2d = (Graphics2D)bufferedImage.getGraphics(); 
					try {
						bmpData = image2Bytes(name);
						for(int i=0;i<bmpData.length;i++) {
							if(bmpData[i]>0)
								System.out.println(bmpData[i]);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4,1));
		cb1 = new JCheckBox("识别年龄性别");
		cb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cb5.isSelected()&&!cb1.isSelected()) {
					cb5.setSelected(false);
				}
			}
		});
		cb2 = new JCheckBox("识别表情");
		cb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cb5.isSelected()&&!cb2.isSelected()) {
					cb5.setSelected(false);
				}
			}
		});
		cb3 = new JCheckBox("识别脸方向");
		cb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cb5.isSelected()&&!cb3.isSelected()) {
					cb5.setSelected(false);
				}
			}
		});
		cb4 = new JCheckBox("识别闭眼程度");
		cb4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cb5.isSelected()&&!cb4.isSelected()) {
					cb5.setSelected(false);
				}
			}
		});
		cb5 = new JCheckBox("全部识别");
		cb5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cb5.isSelected()&&(!cb1.isSelected()||!cb2.isSelected()||!cb3.isSelected()||!cb4.isSelected())) {
					cb1.setSelected(true);
					cb2.setSelected(true);
					cb3.setSelected(true);
					cb4.setSelected(true);
				}
				if(!cb5.isSelected()) {
					cb1.setSelected(false);
					cb2.setSelected(false);
					cb3.setSelected(false);
					cb4.setSelected(false);
				}
			}
		});
		p1.setBorder(BorderFactory.createTitledBorder("识别选项"));
		p1.add(cb1);
		p1.add(cb2);
		p1.add(cb3);
		p1.add(cb4);
		p2 = new JPanel();
		p2.add(p1);
		p2.add(cb5);
		p2.setBounds(600, 100, 150, 170);
		c.add(p2);
		p3 = new JPanel();
		p3.setLayout(new GridLayout(4,1));
		ButtonGroup group = new ButtonGroup();// 创建单选按钮组
		rb1 = new JRadioButton("没有旋转");
		rb2 = new JRadioButton("右转90度");
		rb3 = new JRadioButton("旋转180度");
		rb4 = new JRadioButton("左转90度");
		p3.setBorder(BorderFactory.createTitledBorder("人脸方向"));
		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		group.add(rb4);
		p3.add(rb1);
		p3.add(rb2);
		p3.add(rb3);
		p3.add(rb4);
		p3.setBounds(600, 350, 150, 120);
		identifyImg = new JButton("识别图片");
		identifyImg.setBounds(600, 550, 100, 40);
		c.add(p3);
		c.add(identifyImg);
		identifyImg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				short faceRote = 0;
				if(cb5.isSelected()) {
					cb1.setSelected(true);
					cb2.setSelected(true);
					cb3.setSelected(true);
					cb4.setSelected(true);
				}
				if(rb1.isSelected()) {
					faceRote = (short)face_detect_sdk.PFD_OP_FACE_ROLL_0;
				}
				else if(rb2.isSelected()) {
					faceRote = (short)face_detect_sdk.PFD_OP_FACE_ROLL_90;
				}
				else if(rb3.isSelected()) {
					faceRote = (short)face_detect_sdk.PFD_OP_FACE_ROLL_180;
				}
				else if(rb3.isSelected()) {
					faceRote = (short)face_detect_sdk.PFD_OP_FACE_ROLL_270;
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
				
				PFD_AGR_DETECT agrInfo = new PFD_AGR_DETECT();
				agrInfo.info = new PFD_AGR_INFO[face_detect_sdk.PFD_MAX_FACE_NUM];
				agrInfo.num = 1;
				for (int i = 0; i < agrInfo.info.length; i++) {
					agrInfo.info[i] = new PFD_AGR_INFO();
					agrInfo.info[i].ageConf = 11;
					agrInfo.info[i].genConf = 1;
					agrInfo.info[i].age = 30;
					agrInfo.info[i].gen = 1;
					agrInfo.info[i].faceInfo = new PFD_FACE_POSITION();
					agrInfo.info[i].faceInfo.conf = 1;
					faceInfo.info[i].faceInfo.eye_lx = 188;
					faceInfo.info[i].faceInfo.eye_ly = 126;
					faceInfo.info[i].faceInfo.eye_rx = 142;
					faceInfo.info[i].faceInfo.eye_ry = 125;
					faceInfo.info[i].faceInfo.rect_b = 205;
					faceInfo.info[i].faceInfo.rect_l = 108;
					faceInfo.info[i].faceInfo.rect_r = 206;
					faceInfo.info[i].faceInfo.rect_t = 167;
				}
	            PFD_SMILE_DETECT smileInfo = new PFD_SMILE_DETECT();
	            smileInfo.info = new PFD_SMILE_INFO[face_detect_sdk.PFD_MAX_FACE_NUM];
	    		smileInfo.num = 1;
	    		for (int i = 0; i < smileInfo.info.length; i++) {
	    			smileInfo.info[i] = new PFD_SMILE_INFO();
	    			smileInfo.info[i].smile = 1;
	    			smileInfo.info[i].faceInfo = new PFD_FACE_POSITION();
	    			smileInfo.info[i].faceInfo.conf = 1;
					faceInfo.info[i].faceInfo.eye_lx = 188;
					faceInfo.info[i].faceInfo.eye_ly = 126;
					faceInfo.info[i].faceInfo.eye_rx = 142;
					faceInfo.info[i].faceInfo.eye_ry = 125;
					faceInfo.info[i].faceInfo.rect_b = 205;
					faceInfo.info[i].faceInfo.rect_l = 108;
					faceInfo.info[i].faceInfo.rect_r = 206;
					faceInfo.info[i].faceInfo.rect_t = 167;
	    		}
	            PFD_DIRECT_DETECT directInfo = new PFD_DIRECT_DETECT();
	            directInfo.info = new PFD_DIRECT_INFO[face_detect_sdk.PFD_MAX_FACE_NUM];
	    		directInfo.num = 1;
	    		for (int i = 0; i < directInfo.info.length; i++) {
	    			directInfo.info[i] = new PFD_DIRECT_INFO();
	    			directInfo.info[i].pitch = 1;
	    			directInfo.info[i].roll = 1;
	    			directInfo.info[i].yaw = 1;
	    			directInfo.info[i].faceInfo = new PFD_FACE_POSITION();
	    			directInfo.info[i].faceInfo.conf = 1;
					faceInfo.info[i].faceInfo.eye_lx = 188;
					faceInfo.info[i].faceInfo.eye_ly = 126;
					faceInfo.info[i].faceInfo.eye_rx = 142;
					faceInfo.info[i].faceInfo.eye_ry = 125;
					faceInfo.info[i].faceInfo.rect_b = 205;
					faceInfo.info[i].faceInfo.rect_l = 108;
					faceInfo.info[i].faceInfo.rect_r = 206;
					faceInfo.info[i].faceInfo.rect_t = 167;
	    		}
	            PFD_BLINK_DETECT blinkInfo = new PFD_BLINK_DETECT();
	            blinkInfo.info = new PFD_BLINK_INFO[face_detect_sdk.PFD_MAX_FACE_NUM];
	    		blinkInfo.num = 1;
	    		for (int i = 0; i < blinkInfo.info.length; i++) {
	    			blinkInfo.info[i] = new PFD_BLINK_INFO();
	    			blinkInfo.info[i].lb = 1;
	    			blinkInfo.info[i].rb = 1;
	    			blinkInfo.info[i].faceInfo = new PFD_FACE_POSITION();
	    			blinkInfo.info[i].faceInfo.conf = 1;
					faceInfo.info[i].faceInfo.eye_lx = 188;
					faceInfo.info[i].faceInfo.eye_ly = 126;
					faceInfo.info[i].faceInfo.eye_rx = 142;
					faceInfo.info[i].faceInfo.eye_ry = 125;
					faceInfo.info[i].faceInfo.rect_b = 205;
					faceInfo.info[i].faceInfo.rect_l = 108;
					faceInfo.info[i].faceInfo.rect_r = 206;
					faceInfo.info[i].faceInfo.rect_t = 167;
	    		}
	            if (cb5.isSelected())
	            {
	                //全部识别
	                int ret = t.FaceRecog(bmpData, faceInfo, face_detect_sdk.PFD_ENABLEINFO,faceRote);
	            }
	            else
	            {
	            
	                //只识别人脸位置
	                int ret = t.FaceRecog(bmpData, faceInfo, face_detect_sdk.PFD_ENABLEINFO, faceRote);
	                //System.out.println(ret);
	                if (cb1.isSelected())
	                {
	                    //识别年龄和性别
	                    ret = t.AgrRecogImg(bmpData, agrInfo, faceRote);
	                }
	                if (cb2.isSelected())
	                {
	                    //识别表情
	                    ret = t.SmileRecogImg(bmpData, smileInfo, faceRote);
	                }
	                if (cb3.isSelected())
	                {
	                    //识别人脸朝向
	                    ret = t.DirectRecogImg(bmpData, directInfo, faceRote);
	                }
	                if (cb4.isSelected())
	                {
	                    //识别闭眼程度
	                    ret = t.BlinkRecogImg(bmpData, blinkInfo, faceRote);
	                }
	            }
	            //输入识别结果
	            StringBuilder bs = new StringBuilder();
	            if (cb5.isSelected())
	            {
	            	for(int i=0;i<faceInfo.num;i++) {
						try {
							dRect(faceInfo.info[i].faceInfo.rect_l, faceInfo.info[i].faceInfo.rect_t, faceInfo.info[i].faceInfo.rect_r, faceInfo.info[i].faceInfo.rect_b,i,g2d,image);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							dEyes(faceInfo.info[i].faceInfo.eye_lx, faceInfo.info[i].faceInfo.eye_ly, faceInfo.info[i].faceInfo.eye_rx, faceInfo.info[i].faceInfo.eye_ry,g2d,image);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
	                System.out.println(bs);
	                textarea.setText(textarea.getText()+bs.toString());
	            }
	            else {
	            	for (int i = 0; i < faceInfo.num; i++)
	                {
						try {
							dRect(faceInfo.info[i].faceInfo.rect_l, faceInfo.info[i].faceInfo.rect_t, faceInfo.info[i].faceInfo.rect_r, faceInfo.info[i].faceInfo.rect_b,i,g2d,image);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							dEyes(faceInfo.info[i].faceInfo.eye_lx, faceInfo.info[i].faceInfo.eye_ly, faceInfo.info[i].faceInfo.eye_rx, faceInfo.info[i].faceInfo.eye_ry,g2d,image);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            	if (cb1.isSelected())
	            	{
	            		for(int i=0;i<agrInfo.num;i++) 
	            		{
	                        bs.append("\r\n" +"\t年龄:" + (agrInfo.info[i].age - (short)2) + "->" + (agrInfo.info[i].age + (short)2) + "\r\n"
	                             + "\t年龄可信度:" + agrInfo.info[i].ageConf + "\r\n"
	                             + "\t性别:" + (agrInfo.info[i].gen==1?"女":"男") + "\r\n"
	                             + "\t性别可信度:" + agrInfo.info[i].genConf + "\r\n");
	            		}
	            	}
	                if (cb2.isSelected())
	                {
	                	for (int i = 0; i <smileInfo.num; i++) 
	                	{
	                        bs.append("\r\n" +"\t微笑程度:" + smileInfo.info[i].smile + "\r\n");
	                	}
	                }
	                if (cb3.isSelected())
	                {
	                	for (int i = 0; i <directInfo.num; i++)
	                	{
	                        bs.append("\r\n" +"\t抬头低头程度:" + directInfo.info[i].pitch + "\r\n"
	                            + "\t摇头程度:" + directInfo.info[i].yaw + "\r\n"
	                            + "\t倾斜程度:" + directInfo.info[i].roll + "\r\n");
	                	}
	                }
	                if (cb4.isSelected())
	                {
	                	for (int i = 0; i <blinkInfo.num; i++)
	                	{
	                        bs.append("\r\n" +"\t左眼闭眼程度:" + blinkInfo.info[i].lb + "\r\n"
	                            + "\t右眼闭眼程度:" + blinkInfo.info[i].rb + "\r\n");
	                	}
	                }
	                System.out.println(bs);
	                textarea.setText(textarea.getText()+bs.toString());
	            }
			}
		});
	}
 
    public void dRect(int x1, int y1, int x2, int y2,int num,Graphics2D g, ImageIcon imageIcon) throws IOException {
		g.setColor(Color.yellow);
		g.drawRect(x1, y1, x2 - x1, y2 - y1);
	    g.drawImage(imageIcon.getImage(),0,0,imageIcon.getIconHeight(),imageIcon.getIconWidth(),imageIcon.getImageObserver());
	    ImageIO.write(bufferedImage, "jpg", new File(name));
    }
	private void dEyes(int x1, int y1, int x2, int y2,Graphics2D g, ImageIcon imageIcon) throws IOException
    {
	    g.drawImage(imageIcon.getImage(),0,0,imageIcon.getIconHeight(),imageIcon.getIconWidth(),imageIcon.getImageObserver());
	    ImageIO.write(bufferedImage, "jpg", new File(name));
		g.setColor(Color.yellow);
        g.drawRect(x1-10,y1-5,20,10);
        g.drawRect(x2 - 10, y2 - 5, 20, 10);
    }
	public static byte[] image2Bytes(String imagePath) throws IOException 
	{ 
		ImageIcon ima = new ImageIcon(imagePath); 
		BufferedImage bu = new BufferedImage(ima.getImage().getWidth(null), ima .getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		//把这个jpg图像写到这个流中去,这里可以转变图片的编码格式
		ImageIO.write(bu, "bmp", imageStream); 
		byte[] tagInfo = imageStream.toByteArray();
		return tagInfo;
	}
/*	public static void main(String[] args) {
		FaceRecogView a = new FaceRecogView();
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}*/
}
