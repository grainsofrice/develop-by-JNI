 import java.awt.BorderLayout;  
import java.awt.Component;  
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;  
import javax.media.Manager;  
import javax.media.MediaLocator;  
import javax.media.Player;  
import javax.media.cdm.CaptureDeviceManager;  
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JPanel; 

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//l ��ѯCaptureDeviceManager������λ����Ҫʹ�õ�ý��ɼ��豸��
//l �õ����豸��CaptureDeviceInfoʵ����
//l  �Ӵ�CaptureDeviceInfoʵ�����һ��MediaLocator����ͨ����������һ��DataSource��
//l �ô�DataSource����һ��Player��Processor��
//l ������Player��Processor��ʼ����ý�����ݡ�
@SuppressWarnings("serial")
public class test extends JFrame{  
    public  static Player player = null;  
    private CaptureDeviceInfo deviceInfo = null;
    private MediaLocator mediaLocator = null;  
    private Component component = null;  
    private JPanel vedioPanel = null;  
      
    String   str1   =   "vfw:Logitech   USB   Video   Camera:0";      //��ȡUSB����ͷ���ַ���  
    String   str2   =   "vfw:Microsoft WDM Image Capture (Win32):0";    //��ȡ��������ͷ���ַ���  
    public test() {
    	init();
    }
    
    public void init(){  
    	JButton getCapBt=new JButton("����");
    	JButton close = new JButton("�ر�");
    	//CaptureDeviceManager��getDevice()����ֱ�ӻ���豸����Ȩ
        deviceInfo = CaptureDeviceManager.getDevice(str2);  //�����ַ�����ȡ�ɼ��豸������ͷ��������  
        //   System.out.println(deviceInfo);         //��ʾ�ɼ��豸(����ͷ)����Ϣ  
        //   System.out.println(deviceInfo.getName());     //��ʾ�ɼ��豸������ͷ�����豸����  
        //�豸�Ŀ���Ȩһ���õ����Ϳ����Դ��豸��Ϊһ��MediaLocator,����ͨ��CaptureDeviceInfo��getLocator()�����õ���
        mediaLocator = deviceInfo.getLocator(); //��ȡ�ɼ��豸�Ķ�λ�������ã���Ҫ���ݴ�������������Ƶ������  
          
        try{  
            player = Manager.createRealizedPlayer(mediaLocator);// ����mediaLocator ��ȡһ��player  
            component = player.getVisualComponent();  //�õ������������û�У�����null
            if (component != null){  
                vedioPanel = new JPanel();  
                vedioPanel.add(component, BorderLayout.NORTH); 
                vedioPanel.add(getCapBt, BorderLayout.SOUTH);
                vedioPanel.add(close, BorderLayout.CENTER);
                this.add(vedioPanel);  
                this.pack();    // �Զ����䴰���С  
                this.setResizable(false);  
                this.setVisible(true);
            	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                player.start();  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        getCapBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameGrabbingControl fgc = (FrameGrabbingControl) player
                        .getControl("javax.media.control.FrameGrabbingControl");
                Buffer buffer = fgc.grabFrame();
                BufferToImage bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
                Image image = bufferToImage.createImage(buffer);
                saveImage(image, getTimeStr());// ������Ƭ����
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        
    }  
	public static String getTimeStr() {
		Date currentTime = new Date();// ʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");// ��ʽ��ʱ��
		String dateString = formatter.format(currentTime);// ת�����ַ���
		String[] str = dateString.split(" ");
		return "D:\\pic\\" + str[0] + str[1] + ".jpg";
	}
	public static void saveImage(Image image,String path)     
	{     
	    BufferedImage bi=new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = bi.createGraphics();     
	    g2.drawImage(image, null, null);     
	    FileOutputStream fos=null;     //�ļ����������
	    try {     
	            fos=new FileOutputStream(path);     //���·��
	             
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();     
	    }     
	    JPEGImageEncoder je= JPEGCodec.createJPEGEncoder(fos);   //����һ��ָ��  fos��JPEGImageEncoder����
	    JPEGEncodeParam jp=je.getDefaultJPEGEncodeParam(bi);     //@@@
	    jp.setQuality(0.5f, false);      //���������ǰ�ѽ����������������
	    je.setJPEGEncodeParam(jp);      //����JPEGImageEncoder����������
	    //������ʵ���Ǵ���ͼƬ�ĸ�ʽ�ͱ��롣
	    try {     
	        je.encode(bi);      // �� BufferedImage ��Ϊ JPEG ���������롣
	        fos.close();     
	    } catch (ImageFormatException e) {
	        e.printStackTrace();     
	    } catch (IOException e) {
	        e.printStackTrace();     
	    }          
	}
/*	public static void main(String[] args) {
		new test();
	}*/
}  
