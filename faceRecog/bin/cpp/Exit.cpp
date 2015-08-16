#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *Exit)();

JNIEXPORT jint JNICALL Java_getFunction_Exit(JNIEnv *, jobject) {
	HINSTANCE hDLL = LoadLibrary("EVAL_x86_Accuracy.dll");
	if(!hDLL) {
		printf("cannot get EVAL_x86_Accuracy.dll");
	}
	int k = 0;
	Exit exit = (Exit) GetProcAddress(hDLL, "_PFD_Exit@0");
	if(exit) {
		printf("get Exit success\n");
		k = exit();
		return k;
	}
	else {
		printf("get Exit failed\n");
		return 0;
	}
}