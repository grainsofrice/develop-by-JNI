
public class getFunction {
	public native int Init(int imgSize);	
	public native int Exit();
	public native int FaceRecog(byte[] bmpData, PFD_FACE_DETECT faceInfo, int faceInfoFlag, short faceRote);
	public native int GetFeature(byte[] bmpData,PFD_FACE_DETECT faceInfo,byte[] feature,short faceRote);
	public native int FeatureMatching(short flen1,byte[] feature1,short flen2,byte[] feature2);
	public native int StoreFeature(int dbId,short fsize,byte[] feature);
	public native int DeleteFeature(int dbId,int usid);
	public native int MatchFeature(int dbId,int threshold,short fsize,byte[] feature,short num,int[] candidate,short[] score);
	public native int ResetDataBase(int dbId);
	public native int DeleteDataBase(int dbId);
	public native int AddDataBase(int maxFaceNum);
	public native int ChangeFeature(int dbId,int usid,short fsize,byte[] feature);
	public native int AgrRecogImg(byte[] bmpData,PFD_AGR_DETECT agrInfo,short faceRote);
	public native int SmileRecogImg(byte[] bmpData,PFD_SMILE_DETECT smileInfo,short faceRote);
	public native int DirectRecogImg(byte[] bmpData,PFD_DIRECT_DETECT directInfo,short faceRote);
	public native int BlinkRecogImg(byte[] bmpData,PFD_BLINK_DETECT blinkInfo,short faceRote);
	//public native int GetFeatureByManual(String bmpData,PFD_FACE_POSITION faceInfo,String feature,short faceRote,short[] fsize);
	//public native int GetFeatureByFaceInfo(String bmpData,PFD_DETECT_INFO faceInfo,short num,short faceRote,short infoFlag,String feature);
	static {
		System.loadLibrary("testface");
	}
}