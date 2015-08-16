#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *AddDataBase)(int);

JNIEXPORT jint JNICALL Java_getFunction_AddDataBase(JNIEnv *, jobject, 
	jint maxFaceNum) {
		HINSTANCE hDLL = LoadLibrary("face.dll");
		if(!hDLL) {
			printf("cannot get face.dll");
		}
		int k = 0;
		AddDataBase adb = (AddDataBase) GetProcAddress(hDLL,"_PDB_AddDataBase@4");
		if(adb) {
			k = adb(maxFaceNum);
			printf("get AddDataBase success\n");
			return k;
		}
		else {
			printf("get AddDataBase failed\n");
			return 0;
		}
}