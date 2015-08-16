#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(__stdcall *DeleteDataBase)(int);

JNIEXPORT jint JNICALL Java_getFunction_DeleteDataBase(JNIEnv *, jobject,
	jint dbId) {
		HINSTANCE hDLL = LoadLibrary("face.dll");
		if(!hDLL) {
			printf("cannot get face.dll");	
		}
		int k=0;
		DeleteDataBase ddb = (DeleteDataBase) GetProcAddress(hDLL, "_PDB_DeleteDataBase@4");
		if(ddb) {
			k = ddb(dbId);
			printf("get DeleteDataBase success\n");
			return k;
		}
		else {
			printf("get DeleteDataBase failed\n");
			return 0;
		}
}
