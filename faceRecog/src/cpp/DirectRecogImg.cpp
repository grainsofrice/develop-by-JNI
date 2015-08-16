#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *DirectRecogImg)(unsigned char *, PFD_DIRECT_DETECT*, short);

JNIEXPORT jint JNICALL Java_getFunction_DirectRecogImg(JNIEnv *env, jobject, 
	jbyteArray bmpData, jobject directInfo, jshort faceRote) {
		//��ȡjava��ʵ����PFD_FACE_POSITION
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


		//��ȡjava��ʵ����PFD_DIRECT_INFO
		jclass jcpdi = env->FindClass("PFD_DIRECT_INFO");
		//PFD_FACE_POSITION faceInfo
		jfieldID jfpfp = env->GetFieldID(jcpdi, "faceInfo", "LPFD_FACE_POSITION;");
		//short pitch
		jfieldID jfpitch = env->GetFieldID(jcpdi, "pitch", "S");
		//short yaw
		jfieldID jfyaw = env->GetFieldID(jcpdi, "yaw","S");
		//short roll
		jfieldID jfroll = env->GetFieldID(jcpdi, "roll","S");

		//��ȡjava��ʵ����PFD_DIRECT_DETECT
		jclass jcpdd = env->FindClass("PFD_DIRECT_DETECT");
		//short num
		jfieldID jfnum = env->GetFieldID(jcpdd, "num", "S");//short->S
		//PFD_DIRECT_INFO[] info
		jfieldID jfpdi = env->GetFieldID(jcpdd, "info", "[LPFD_DIRECT_INFO;");

		PFD_DIRECT_DETECT pdd;//c�ṹ��

		//��ȡʵ���е�numֵ��c�ṹ���numֵ
		pdd.num = env->GetShortField(directInfo, jfnum);
		//��ȡʵ���е�PFD_DIRECT_INFO���ֵ��c�ṹ���PFD_DIRECT_INFOֵ
		jobject joinfo = env->GetObjectField(directInfo, jfpdi);
		pdd.info->pitch = env->GetShortField(joinfo, jfpitch);
		pdd.info->roll = env->GetShortField(joinfo, jfroll);
		pdd.info->yaw = env->GetShortField(joinfo, jfyaw);
		//��ȡPFD_FACE_POSITION����
		jobject jopfp = env->GetObjectField(joinfo, jfpfp);
		pdd.info->faceInfo.conf = env->GetShortField(jopfp, jfconf);
		pdd.info->faceInfo.rect_l = env->GetShortField(jopfp, jfrect_l);
		pdd.info->faceInfo.rect_r = env->GetShortField(jopfp, jfrect_r);
		pdd.info->faceInfo.rect_t = env->GetShortField(jopfp, jfrect_t);
		pdd.info->faceInfo.rect_b = env->GetShortField(jopfp, jfrect_b);
		pdd.info->faceInfo.eye_lx = env->GetShortField(jopfp, jfeye_lx);
		pdd.info->faceInfo.eye_ly = env->GetShortField(jopfp, jfeye_ly);
		pdd.info->faceInfo.eye_rx = env->GetShortField(jopfp, jfeye_rx);
		pdd.info->faceInfo.eye_ry = env->GetShortField(jopfp, jfeye_ry);
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll"); 
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k;
		DirectRecogImg dri = (DirectRecogImg) GetProcAddress(hDLL, "_PFD_DirectRecogImg@12"); 
		if(dri) {
			//TODO: convert jstring to char*
			unsigned char *bmpData1 = (unsigned char*)env->GetByteArrayElements(bmpData,0);
			k = dri(bmpData1, &pdd, faceRote);
			printf("get DirectRecogImg success\n");
			return k;
		} else {
			printf("get DirectRecogImg failed!\n");
			return 0;
		}
}