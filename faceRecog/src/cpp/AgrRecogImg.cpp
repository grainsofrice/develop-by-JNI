#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int( __stdcall *AgrRecogImg)(unsigned char * ,PFD_AGR_DETECT* ,short );

JNIEXPORT jint JNICALL Java_getFunction_AgrRecogImg(JNIEnv *env, jobject, 
	jbyteArray bmpData, jobject agrInfo, jshort faceRote){
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


		//获取java中实例类PFD_AGR_INFO
		jclass jcpai = env->FindClass("PFD_AGR_INFO");
		//PFD_FACE_POSITION faceInfo
		jfieldID jfpfp = env->GetFieldID(jcpai, "faceInfo", "LPFD_FACE_POSITION;");
		//short ageConf
		jfieldID jfageConf = env->GetFieldID(jcpai, "ageConf", "S");
		//short genConf
		jfieldID jfgenConf = env->GetFieldID(jcpai, "genConf","S");
		//short age
		jfieldID jfage = env->GetFieldID(jcpai, "age","S");
    	//short gen
		jfieldID jfgen = env->GetFieldID(jcpai, "gen","S");


		//获取java中实例类PFD_AGR_DETECT
		jclass jcpad = env->FindClass("PFD_AGR_DETECT");
		//short num
		jfieldID jfnum = env->GetFieldID(jcpad, "num", "S");//short->S
		//PFD_AGR_INFO[] info
		jfieldID jfpai = env->GetFieldID(jcpad, "info", "[LPFD_AGR_INFO;");

		PFD_AGR_DETECT pad;//c结构体

		//获取实例中的num值给c结构体的num值
		pad.num = env->GetShortField(agrInfo, jfnum);
		//获取实例中的PFD_ARG_INFO类的值给c结构体的PFD_ARG_INFO值
		jobject joinfo = env->GetObjectField(agrInfo, jfpai);
		pad.info->ageConf = env->GetShortField(joinfo, jfageConf);
		pad.info->genConf = env->GetShortField(joinfo, jfgenConf);
		pad.info->age = env->GetShortField(joinfo, jfage);
		pad.info->gen = env->GetShortField(joinfo, jfgen);
		//获取PFD_FACE_POSITION对象
		jobject jopfp = env->GetObjectField(joinfo, jfpfp);
		pad.info->faceInfo.conf = env->GetShortField(jopfp, jfconf);
		pad.info->faceInfo.rect_l = env->GetShortField(jopfp, jfrect_l);
		pad.info->faceInfo.rect_r = env->GetShortField(jopfp, jfrect_r);
		pad.info->faceInfo.rect_t = env->GetShortField(jopfp, jfrect_t);
		pad.info->faceInfo.rect_b = env->GetShortField(jopfp, jfrect_b);
		pad.info->faceInfo.eye_lx = env->GetShortField(jopfp, jfeye_lx);
		pad.info->faceInfo.eye_ly = env->GetShortField(jopfp, jfeye_ly);
		pad.info->faceInfo.eye_rx = env->GetShortField(jopfp, jfeye_rx);
		pad.info->faceInfo.eye_ry = env->GetShortField(jopfp, jfeye_ry);
		HINSTANCE hDLL = LoadLibrary("face.dll"); 
		if(!hDLL) {
			printf("cannot get face.dll!");
		}
		int k;
		AgrRecogImg ari = (AgrRecogImg) GetProcAddress(hDLL, "_PFD_AgrRecogImg@12"); 
		if(ari) {
			//TODO: convert jstring to char*
			unsigned char *bmpData1 = (unsigned char*)env->GetByteArrayElements(bmpData, 0);
			k = ari(bmpData1, &pad, faceRote);
			printf("get AgrRecogImg success\n");
			return k;
		} else {
			printf("get AgrRecogImg failed!\n");
			return 0;
		}
}