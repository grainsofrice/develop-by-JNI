#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int( __stdcall *BlinkRecogImg)(unsigned char * ,PFD_BLINK_DETECT* ,short );

JNIEXPORT jint JNICALL Java_getFunction_BlinkRecogImg(JNIEnv *env, jobject, 
	jbyteArray bmpData, jobject blinkInfo, jshort faceRote){
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


		//获取java中实例类PFD_BLINK_INFO
		jclass jcpbi = env->FindClass("PFD_BLINK_INFO");
		//PFD_FACE_POSITION faceInfo
		jfieldID jfpfp = env->GetFieldID(jcpbi, "faceInfo", "LPFD_FACE_POSITION;");
		//short lb
		jfieldID jflb = env->GetFieldID(jcpbi, "lb", "S");
		//short rb
		jfieldID jfrb = env->GetFieldID(jcpbi, "rb","S");

		//获取java中实例类PFD_BLINK_DETECT
		jclass jcpbd = env->FindClass("PFD_BLINK_DETECT");
		//short num
		jfieldID jfnum = env->GetFieldID(jcpbd, "num", "S");//short->S
		//PFD_BLINK_INFO[] info
		jfieldID jfpai = env->GetFieldID(jcpbd, "info", "[LPFD_BLINK_INFO;");

		PFD_BLINK_DETECT pbd;//c结构体

		//获取实例中的num值给c结构体的num值
		pbd.num = env->GetShortField(blinkInfo, jfnum);
		//获取实例中的PFD_ARG_INFO类的值给c结构体的PFD_ARG_INFO值
		jobject joinfo = env->GetObjectField(blinkInfo, jfpai);
		pbd.info->lb = env->GetShortField(joinfo, jflb);
		pbd.info->rb = env->GetShortField(joinfo, jfrb);
		//获取PFD_FACE_POSITION对象
		jobject jopfp = env->GetObjectField(joinfo, jfpfp);
		pbd.info->faceInfo.conf = env->GetShortField(jopfp, jfconf);
		pbd.info->faceInfo.rect_l = env->GetShortField(jopfp, jfrect_l);
		pbd.info->faceInfo.rect_r = env->GetShortField(jopfp, jfrect_r);
		pbd.info->faceInfo.rect_t = env->GetShortField(jopfp, jfrect_t);
		pbd.info->faceInfo.rect_b = env->GetShortField(jopfp, jfrect_b);
		pbd.info->faceInfo.eye_lx = env->GetShortField(jopfp, jfeye_lx);
		pbd.info->faceInfo.eye_ly = env->GetShortField(jopfp, jfeye_ly);
		pbd.info->faceInfo.eye_rx = env->GetShortField(jopfp, jfeye_rx);
		pbd.info->faceInfo.eye_ry = env->GetShortField(jopfp, jfeye_ry);
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll"); 
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k;
		BlinkRecogImg bri = (BlinkRecogImg) GetProcAddress(hDLL, "_PFD_BlinkRecogImg@12"); 
		if(bri) {
			//TODO: convert jstring to char*
			unsigned char *bmpData1 = (unsigned char*)env->GetByteArrayElements(bmpData,0);
			k = bri(bmpData1, &pbd, faceRote);
			printf("get BlinkRecogImg success\n");
			return k;
		} else {
			printf("get BlinkRecogImg failed!\n");
			return 0;
		}
}