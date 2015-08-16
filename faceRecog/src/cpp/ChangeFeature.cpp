#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *ChangeFeature)(int, int, short, unsigned char *);

JNIEXPORT jint JNICALL Java_getFunction_ChangeFeature(JNIEnv *env, jobject,
	jint dbId, jint usid, jshort fsize, jbyteArray feature) {
		HINSTANCE hDLL = LoadLibrary("face.dll");
		if(!hDLL) {
			printf("cannot get face.dll");
		}
		int k = 0;
		ChangeFeature cf = (ChangeFeature) GetProcAddress (hDLL,"_PDB_ChangeFeature@16");
		if(cf) {
			//TODO:jstring->unsigned char *
			unsigned char *feature1 = (unsigned char *) env->GetByteArrayElements(feature,0);
			k = cf(dbId, usid, fsize, feature1);
			printf("get ChangeFeature success\n");
			return k;
		}
		else {
			printf("getChangeFeature failed\n");
			return 0;
		}
}