#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *DeleteFeature)(int, int);

JNIEXPORT jint JNICALL Java_getFunction_DeleteFeature(JNIEnv *, jobject, 
	jint dbId, jint usid) {
		HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll");
		if(!hDLL) {
			printf("cannot get EVAL_x86_Accuracy.dll!");
		}
		int k=0;
		DeleteFeature df = (DeleteFeature) GetProcAddress (hDLL, "_PDB_DeleteFeature@8");
		if(df) {
			printf("get DeleteFeature success\n");
			k = df(dbId, usid);
			return k;
		}
		else {
			printf("get DeleteFeature failed\n");
			return 0;
		}
}