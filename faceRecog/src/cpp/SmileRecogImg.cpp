#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *SmileRecogImg)(unsigned char *, PFD_SMILE_DETECT*, short);

JNIEXPORT jint JNICALL Java_getFunction_SmileRecogImg(JNIEnv *env, jobject, 
	jbyteArray bmpData, jobject smileInfo, jshort faceRote) {
		//获取java中实例类PFD_FACE_POSITION
		jclass jcpfp = env->FindClass("PFD_FACE_POSITION");
		//short	conf
		jfieldID jfconf = env->GetFieldID(jcpfp, "conf", "S");
    	//short	rect_l
		jfieldID jfrect_l = env->GetFieldID(jcpfp, "rect_l", "S");
    	//short	rect_r
		jfieldID jfrect_r = env->GetFieldID(jcpfp, "rect_r", "S");
    	//short	rect_t
		jfieldID jfrect_t = env->GetFieldID(jcpfp, "rect_t", "S");
    	//short	rect_b
		jfieldID jfrect_b = env->GetFieldID(jcpfp, "rect_b", "S");
    	//short	eye_lx
		jfieldID jfeye_lx = env->GetFieldID(jcpfp, "eye_lx", "S");
    	//short	eye_ly
		jfieldID jfeye_ly = env->GetFieldID(jcpfp, "eye_ly", "S");
    	//short	eye_rx
		jfieldID jfeye_rx = env->GetFieldID(jcpfp, "eye_rx", "S");
    	//short	eye_ry
		jfieldID jfeye_ry = env->GetFieldID(jcpfp, "eye_ry", "S");


		//获取java中实例类PFD_SMILE_INFO
		jclass jcpsi = env->FindClass("PFD_SMILE_INFO");
		//PFD_FACE_POSITION faceInfo
		jfieldID jfpfp = env->GetFieldID(jcpsi, "faceInfo", "LPFD_FACE_POSITION;");
		//short smile
		jfieldID jfsmile = env->GetFieldID(jcpsi, "smile", "S");

		//获取java中实例类PFD_SMILE_DETECT
		jclass jcpsd = env->FindClass("PFD_SMILE_DETECT");
		//short num
		jfieldID jfnum = env->GetFieldID(jcpsd, "num", "S");//short->S
		//PFD_SMILE_INFO[] info
		jfieldID jfpsi = env->GetFieldID(jcpsd, "info", "[LPFD_SMILE_INFO;");

		PFD_SMILE_DETECT psd;//c结构体

		//获取实例中的num值给c结构体的num值
		psd.num = env->GetShortField(smileInfo, jfnum);
		//获取实例中的PFD_SMILE_INFO类的值给c结构体的PFD_SMILE_INFO值
		jobject joinfo = env->GetObjectField(smileInfo, jfpsi);
		//short smile
		psd.info->smile = env->GetShortField(joinfo, jfsmile);
		//获取PFD_FACE_POSITION对象
		jobject jopfp = env->GetObjectField(joinfo, jfpfp);
		psd.info->faceInfo.conf = env->GetShortField(jopfp, jfconf);
		psd.info->faceInfo.rect_l = env->GetShortField(jopfp, jfrect_l);
		psd.info->faceInfo.rect_r = env->GetShortField(jopfp, jfrect_r);
		psd.info->faceInfo.rect_t = env->GetShortField(jopfp, jfrect_t);
		psd.info->faceInfo.rect_b = env->GetShortField(jopfp, jfrect_b);
		psd.info->faceInfo.eye_lx = env->GetShortField(jopfp, jfeye_lx);
		psd.info->faceInfo.eye_ly = env->GetShortField(jopfp, jfeye_ly);
		psd.info->faceInfo.eye_rx = env->GetShortField(jopfp, jfeye_rx);
		psd.info->faceInfo.eye_ry = env->GetShortField(jopfp, jfeye_ry);
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll"); 
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k;
		SmileRecogImg sri = (SmileRecogImg) GetProcAddress(hDLL, "_PFD_SmileRecogImg@12"); 
		if(sri) {
			//TODO: convert jstring to char*
			unsigned char *bmpData1 = (unsigned char*)env->GetByteArrayElements(bmpData,0);
			k = sri(bmpData1, &psd, faceRote);
			printf("get SmileRecogImg success\n");
			return k;
		} else {
			printf("get SmileRecogImg failed!\n");
			return 0;
		}
}