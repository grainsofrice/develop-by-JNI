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

//l 查询CaptureDeviceManager，来定位你需要使用的媒体采集设备。
//l 得到此设备的CaptureDeviceInfo实例。
//l  从此CaptureDeviceInfo实例获得一个MediaLocator，并通过它来创建一个DataSource。
//l 用此DataSource创建一个Player或Processor。
//l 启动此Player或Processor开始捕获媒体数据。
@SuppressWarnings("serial")
public class test extends JFrame{  
    public  static Player player = null;  
    private CaptureDeviceInfo deviceInfo = null;
    private MediaLocator mediaLocator = null;  
    private Component component = null;  
    private JPanel vedioPanel = null;  
      
    String   str1   =   "vfw:Logitech   USB   Video   Camera:0";      //获取USB摄像头的字符串  
    String   str2   =   "vfw:Microsoft WDM Image Capture (Win32):0";    //获取本地摄像头的字符串  
    public test() {
    	init();
    }
    
    public void init(){  
    	JButton getCapBt=new JButton("拍照");
    	JButton close = new JButton("关闭");
    	//CaptureDeviceManager的getDevice()方法直接获得设备控制权
        deviceInfo = CaptureDeviceManager.getDevice(str2);  //根据字符串获取采集设备（摄像头）的引用  
        //   System.out.println(deviceInfo);         //显示采集设备(摄像头)的信息  
        //   System.out.println(deviceInfo.getName());     //显示采集设备（摄像头）的设备名称  
        //设备的控制权一旦得到，就可以以此设备作为一个MediaLocator,可以通过CaptureDeviceInfo的getLocator()方法得到。
        mediaLocator = deviceInfo.getLocator(); //获取采集设备的定位器的引用，需要根据此引用来创建视频播放器  
          
        try{  
            player = Manager.createRealizedPlayer(mediaLocator);// 利用mediaLocator 获取一个player  
            component = player.getVisualComponent();  //得到可视组件，若没有，返回null
            if (component != null){  
                vedioPanel = new JPanel();  
                vedioPanel.add(component, BorderLayout.NORTH); 
                vedioPanel.add(getCapBt, BorderLayout.SOUTH);
                vedioPanel.add(close, BorderLayout.CENTER);
                this.add(vedioPanel);  
                this.pack();    // 自动分配窗体大小  
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
                saveImage(image, getTimeStr());// 设置照片名称
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
		Date currentTime = new Date();// 时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");// 格式化时间
		String dateString = formatter.format(currentTime);// 转换成字符串
		String[] str = dateString.split(" ");
		return "D:\\pic\\" + str[0] + str[1] + ".jpg";
	}
	public static void saveImage(Image image,String path)     
	{     
	    BufferedImage bi=new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = bi.createGraphics();     
	    g2.drawImage(image, null, null);     
	    FileOutputStream fos=null;     //文件输出流对象
	    try {     
	            fos=new FileOutputStream(path);     //获得路径
	             
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();     
	    }     
	    JPEGImageEncoder je= JPEGCodec.createJPEGEncoder(fos);   //创建一个指向  fos的JPEGImageEncoder对象
	    JPEGEncodeParam jp=je.getDefaultJPEGEncodeParam(bi);     //@@@
	    jp.setQuality(0.5f, false);      //创建替代当前已建量化表的新量化表
	    je.setJPEGEncodeParam(jp);      //设置JPEGImageEncoder对象编码操作
	    //以上其实就是创建图片的格式和编码。
	    try {     
	        je.encode(bi);      // 將 BufferedImage 作为 JPEG 数据流编码。
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
