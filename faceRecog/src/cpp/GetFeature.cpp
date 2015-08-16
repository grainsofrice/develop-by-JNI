#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *GetFeature)(unsigned char *, PFD_FACE_DETECT*, unsigned char *, short);

JNIEXPORT jint JNICALL Java_getFunction_GetFeature(JNIEnv *env, jobject,
	jbyteArray bmpData, jobject faceInfo, jstring feature, jshort faceRote) {

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


		//获取java中实例类PFD_DETECT_INFO
		jclass jcpdi = env->FindClass("PFD_DETECT_INFO");
		//PFD_FACE_POSITION faceInfo
		jfieldID jfpfp = env->GetFieldID(jcpdi, "faceInfo", "LPFD_FACE_POSITION;");
		//short ageConf
		jfieldID jfageConf = env->GetFieldID(jcpdi, "ageConf", "S");
		//short genConf
		jfieldID jfgenConf = env->GetFieldID(jcpdi, "genConf","S");
		//short age
		jfieldID jfage = env->GetFieldID(jcpdi, "age","S");
    	//short gen
		jfieldID jfgen = env->GetFieldID(jcpdi, "gen","S");
    	//short smile
		jfieldID jfsmile = env->GetFieldID(jcpdi, "smile", "S");
    	//short pitch
		jfieldID jfpitch = env->GetFieldID(jcpdi, "pitch","S");
    	//short yaw
		jfieldID jfyaw = env->GetFieldID(jcpdi, "yaw", "S");
    	//short roll
		jfieldID jfroll = env->GetFieldID(jcpdi, "roll", "S");
    	//short lb
		jfieldID jflb = env->GetFieldID(jcpdi, "lb", "S");
    	//short rb
		jfieldID jfrb = env->GetFieldID(jcpdi, "rb","S");
    	//short flen
		jfieldID jfflen = env->GetFieldID(jcpdi, "flen", "S");


		//获取java中实例类PFD_FACE_DETECT
		jclass jcpfd = env->FindClass("PFD_FACE_DETECT");
		//short num
		jfieldID jfnum = env->GetFieldID(jcpfd, "num", "S");//short->S
		//PFD_DETECT_INFO[] info
		jfieldID jfpdi = env->GetFieldID(jcpfd, "info", "[LPFD_DETECT_INFO;");

		PFD_FACE_DETECT pfd;//c结构体

		//获取实例中的num值给c结构体的num值
		pfd.num = env->GetShortField(faceInfo, jfnum);
		//获取实例中的PFD_DETECT_INFO类的值给c结构体的PFD_DETECT_INFO值
		//jobjectArray joinfo = (jobjectArray)env->GetObjectField(faceInfo, jfpdi);
		//int infolen = env->GetArrayLength(joinfo);
		jobject joinfo = env->GetObjectField(faceInfo, jfpdi);
		//PFD_DETECT_INFO *info1 =(PFD_DETECT_INFO *)env->GetObjectArrayElement(joinfo, 0); 
		//java中的PFD_DETECT_INFO[]对象数组 --> c中的PFD_DETECT_INFO[]结构体数组，需要上面的注释吗？还是直接像这句话这样。
		//memcpy(pfd.info, joinfo, infolen);
		pfd.info->ageConf = env->GetShortField(joinfo, jfageConf);
		pfd.info->genConf = env->GetShortField(joinfo, jfgenConf);
		pfd.info->age = env->GetShortField(joinfo, jfage);
		pfd.info->gen = env->GetShortField(joinfo, jfgen);
		pfd.info->smile = env->GetShortField(joinfo, jfsmile);
		pfd.info->pitch = env->GetShortField(joinfo, jfpitch);
		pfd.info->yaw = env->GetShortField(joinfo, jfyaw);
		pfd.info->roll = env->GetShortField(joinfo, jfroll);
		pfd.info->lb = env->GetShortField(joinfo, jflb);
		pfd.info->rb = env->GetShortField(joinfo, jfrb);
		pfd.info->flen = env->GetShortField(joinfo, jfflen);
		//获取PFD_FACE_POSITION对象
		jobject jopfp = env->GetObjectField(joinfo, jfpfp);
		pfd.info->faceInfo.conf = env->GetShortField(jopfp, jfconf);
		pfd.info->faceInfo.rect_l = env->GetShortField(jopfp, jfrect_l);
		pfd.info->faceInfo.rect_r = env->GetShortField(jopfp, jfrect_r);
		pfd.info->faceInfo.rect_t = env->GetShortField(jopfp, jfrect_t);
		pfd.info->faceInfo.rect_b = env->GetShortField(jopfp, jfrect_b);
		pfd.info->faceInfo.eye_lx = env->GetShortField(jopfp, jfeye_lx);
		pfd.info->faceInfo.eye_ly = env->GetShortField(jopfp, jfeye_ly);
		pfd.info->faceInfo.eye_rx = env->GetShortField(jopfp, jfeye_rx);
		pfd.info->faceInfo.eye_ry = env->GetShortField(jopfp, jfeye_ry);
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll");
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k=0;
		GetFeature gf = (GetFeature) GetProcAddress(hDLL, "_PFD_GetFeature@16");
		if(gf) {
			unsigned char *bmpData1 = (unsigned char*)env->GetByteArrayElements(bmpData,0);
			unsigned char *feature1 = (unsigned char*)env->GetStringUTFChars(feature,0);
			k = gf(bmpData1, &pfd, feature1, faceRote);
			env->ReleaseStringUTFChars(feature, (const char*)feature1);
			printf("get GetFeature success\n");
			return k;
		} else {
			printf("get GetFeature failed\n");
			return 0;
		}
}


