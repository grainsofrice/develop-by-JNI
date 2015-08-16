#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *FeatureMatching)(short, unsigned char *, short, unsigned char*);

JNIEXPORT jint JNICALL Java_getFunction_FeatureMatching(JNIEnv *env, jobject, 
	jshort flen1, jbyteArray feature1, jshort flen2, jbyteArray feature2) {
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll");
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k=0;
		FeatureMatching fm = (FeatureMatching) GetProcAddress (hDLL, "_PFD_FeatureMatching@16");
		if(fm) {
			//jstring->unsigned char*
			unsigned char* feature11 = (unsigned char*)env->GetByteArrayElements(feature1,0);
			unsigned char* feature22 = (unsigned char*)env->GetByteArrayElements(feature2,0);
			k = fm(flen1, feature11, flen2, feature22);
			printf("get FaceMatching success\n");
			return k;
		}
		else {
			printf("get FaceMatching failed\n");
			return 0;
		}
}