#include<windows.h>
#include<jni.h>
#include"face.h"
#include"eval.h"

typedef int(_stdcall *StoreFeature)(int, short, unsigned char*);

JNIEXPORT jint JNICALL Java_getFunction_StoreFeature(JNIEnv *env, jobject, 
	jint dbId, jshort fsize, jbyteArray feature) {
		HINSTANCE hDLL = LoadLibrary("face.dll");
		if(!hDLL) {
			printf("cannot get face.dll!");
		}
		int k=0;
		StoreFeature sf = (StoreFeature) GetProcAddress (hDLL, "_PDB_StoreFeature@12");
		if(sf) {	
			//jstring->unsigned char*
			unsigned char* feature1 = (unsigned char*)env->GetByteArrayElements(feature,0);
			k = sf(dbId, fsize, feature1);
			printf("get StoreFeature success\n");
			return k;
		}
		else {
			printf("get StoreFeature failed\n");
			return 0;
		}
}