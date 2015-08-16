#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(_stdcall *MatchFeature)(int,unsigned int,short,unsigned char * ,unsigned short ,int* ,short*);

JNIEXPORT jint JNICALL Java_getFunction_MatchFeature(JNIEnv *env, jobject, 
	jint dbId, jint threshold, jshort fsize, jbyteArray feature, jshort num, jintArray candidate, jshortArray score) {
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll");
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k=0;
		MatchFeature mf = (MatchFeature) GetProcAddress (hDLL, "_PDB_MatchFeature@28");
		if(mf) {
			//jstring->unsigned char*
			unsigned char* feature1 = (unsigned char*)env->GetByteArrayElements(feature,0);
			//jintArray->int*
			jint *candidate1 = env->GetIntArrayElements(candidate, 0);
			jshort *score1 =env->GetShortArrayElements(score, 0);
			k = mf(dbId, (unsigned int)threshold, fsize, feature1, (unsigned short)num, (int*)candidate1,score1);
			env->ReleaseIntArrayElements(candidate,candidate1,0);
			env->ReleaseShortArrayElements(score, score1,0);
			printf("get MatchFeature success\n");
			return k;
		}
		else {
			printf("get MatchFeature failed\n");
			return 0;
		}
}