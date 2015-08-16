#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(_stdcall *ResetDataBase)(int);

JNIEXPORT jint JNICALL Java_getFunction_ResetDataBase(JNIEnv *, jobject,
	jint dbId) {
		HINSTANCE hDLL = LoadLibrary("face.dll");
		if(!hDLL) {
			printf("cannot get face.dll!");
		}
		int k=0;
		ResetDataBase rd = (ResetDataBase) GetProcAddress (hDLL, "_PDB_ResetDataBase@4");
		if(rd) {		
			k = rd(dbId);
			printf("get ResetDataBase success\n");
			return k;
		}
		else {
			printf("get ResetDataBase failed\n");
			return 0;
		}
}