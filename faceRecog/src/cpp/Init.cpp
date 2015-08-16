#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(_stdcall *Init)(int);

JNIEXPORT jint JNICALL Java_getFunction_Init(JNIEnv *, jobject, 
	jint imgSize) {
		HINSTANCE hDLL = LoadLibrary("face.dll");
		if(!hDLL) {
			printf("cannot get face.dll");
		}
		int k = 0;
		Init ini = (Init) GetProcAddress(hDLL, "_PFD_Init@4 ");
		if(ini) {
			printf("get Init success\n");
			k = ini(imgSize);
			return k;
		}
		else {
			printf("get Init failed\n");
			return 0;
		}
}