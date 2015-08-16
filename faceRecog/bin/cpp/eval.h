/***********************************************************
 *
 *  Copyright (C) 2014~2017  PSDCD Corporation
 *  All Rights Reserved.
 *
 ***********************************************************/

#define  PFD_MAX_FACE_NUM (20)
#define  PFD_OP_FACE_ROLL_0    (0x10)  /* 人脸向上的场合(无旋转) */
#define  PFD_OP_FACE_ROLL_90   (0x20)  /* 人脸向右的场合(逆时针旋转90度) */
#define  PFD_OP_FACE_ROLL_180  (0x40)  /* 人脸向下的场合(倒置) */
#define  PFD_OP_FACE_ROLL_270  (0x80)  /* 人脸向左的场合(顺时针旋转90度) */

#define PFD_STATUS_OK		(0)		/* 正常结束 */
#define	PFD_STATUS_NG		(-1)	/* 异常结束 */
#define	PFD_STATUS_INVALID	(-2)	/* 验证失败 */

#define PFD_MALE	(0)        /* 男性  */
#define PFD_FEMALE	(1)        /* 女性  */

#define PFD_ENABLEINFO	(1)
#define PFD_DISABLEINFO	(0)

#define IMG_SIZE_VGA  (0)
#define IMG_SIZE_FULLHD (1)

/* 人脸坐标结构体 */
typedef struct _pfd_face_position {
	short	conf;	/* 识别信赖度(0~1000 低~高) */
	short	rect_l;	/* 人脸最左侧坐标*/
	short	rect_r;	/*人脸最右侧坐标*/
	short	rect_t;	/*人脸最上侧坐标*/
	short	rect_b;	/*人脸最下侧坐标*/
	short	eye_lx;	/*人脸左眼x坐标*/
	short	eye_ly;	/*人脸左眼y坐标*/
	short	eye_rx;	/*人脸右眼x坐标*/
	short	eye_ry;	/*人脸右眼y坐标*/
} PFD_FACE_POSITION;


/* 人脸情报保存结构体 */
typedef struct _pfd_detect_info{
	PFD_FACE_POSITION faceInfo; 	/* 人脸坐标结构体 */
	short ageConf;                 	/* 年龄识别可信度 (0~1000 低~高) */
	short genConf;                 	/* 性别识别可信度 (0~1000 低~高) */
	short age;                     	/* 年龄 */
	short gen;                     	/* 性别 */
	short smile;                   	/* 笑脸程度(0~100 低~高)*/
	short pitch;                   	/* 抬头低头的角度(-180~180度，抬头是正值)*/ 
	short yaw;                   	/* 摇头角度(-180~180度，向左摇头是正值)*/
	short roll;                    	/* 倾斜角度(-180~180度，顺时针是正值)*/
	short lb;                     	/* 左眼闭眼概率，数值越大代表眼睛越大*/ 
	short rb;                     	/* 右眼闭眼概率，数值越大代表眼睛越大*/
	short flen;                    	/* 特征值长度 */ 
}PFD_DETECT_INFO;


/* 图像内的人脸信息结构体 */
typedef struct _pfd_face_detect {
	short	num;					/* 图像中人脸数(最大:PFD_MAX_FACE_NUM) */
	PFD_DETECT_INFO	info[PFD_MAX_FACE_NUM];		/* 人脸的位置和情报信息 */
} PFD_FACE_DETECT;


/* 年龄性别结构体*/
typedef struct _pfd_agr_info {
	PFD_FACE_POSITION faceInfo; /* 人脸坐标结构体 */
	short	ageConf;			/* 年龄识别信赖度(0~1000 低~高) */
	short	genConf;			/* 性别识别信赖度(0~1000 低~高) */
	short	age; 				/* 年龄*/
	short	gen; 				/* 性别*/ 
} PFD_AGR_INFO;


typedef struct _pfd_agr_detect {
	short	num;							/* 图片中人脸数(最大:PFD_MAX_FACE_NUM) */
	PFD_AGR_INFO	info[PFD_MAX_FACE_NUM];	/*年龄性别*/
} PFD_AGR_DETECT;


/* 表情构造体*/
typedef struct _pfd_smile_info {
	PFD_FACE_POSITION faceInfo; /* 人脸坐标结构体 */
    short	smile;  			/* 笑脸程度(0~100 低~高)*/ 
} PFD_SMILE_INFO;


typedef struct _pfd_smile_detect {
	short	num;							/* 图片中人脸数(最大:PFD_MAX_FACE_NUM) */
	PFD_SMILE_INFO	info[PFD_MAX_FACE_NUM];	/*笑脸信息*/
} PFD_SMILE_DETECT;


/* 人脸方向构造体*/
typedef struct _pfd_direct_info {
	PFD_FACE_POSITION faceInfo; /* 人脸坐标结构体 */
	short	pitch;				/* 抬头低头的角度(-180~180度，抬头是正值)*/ 
	short	yaw;				/* 摇头角度(-180~180度，向左摇头是正值)*/
	short	roll;     			/* 倾斜角度(-180~180度，顺时针是正值)*/
} PFD_DIRECT_INFO;


typedef struct _pfd_direct_detect {
	short	num;							/* 图片中人脸数(最大:PFD_MAX_FACE_NUM) */
	PFD_DIRECT_INFO	info[PFD_MAX_FACE_NUM];	/*人脸方向信息*/
} PFD_DIRECT_DETECT;


/* 人脸方向构造体*/
typedef struct _pfd_blink_info {
    PFD_FACE_POSITION  faceInfo;  	/* 人脸坐标结构体 */
	short	lb;      				/* 左眼闭眼程度，数值越大代表眼睛越大**/ 
	short	rb;      				/* 右眼闭眼概率，数值越大代表眼睛越大**/
} PFD_BLINK_INFO;

typedef struct _pfd_blink_detect {
	short	num;							/* 图片中人脸数(最大:PFD_MAX_FACE_NUM) */
	PFD_BLINK_INFO	info[PFD_MAX_FACE_NUM];	/*闭眼信息*/
} PFD_BLINK_DETECT;
/*
DLL_API int __stdcall PFD_Init(int imgSize);	
DLL_API int __stdcall PFD_Exit();		
DLL_API int __stdcall PFD_FaceRecog(unsigned char * bmpData,PFD_FACE_DETECT* faceInfo,int faceInfoFlag,short faceRote);
DLL_API int __stdcall PFD_GetFeature(unsigned char * bmpData,PFD_FACE_DETECT* faceInfo,unsigned char ** feature,short faceRote);
DLL_API int __stdcall PFD_FeatureMatching(short flen1,unsigned char *feature1,short flen2,unsigned char * feature2);
DLL_API int __stdcall PDB_StoreFeature(int dbId,short fsize,unsigned char * feature);
DLL_API int __stdcall PDB_DeleteFeature(int dbId,int usid);
DLL_API int __stdcall PDB_MatchFeature(int dbId,unsigned int threshold,short fsize,unsigned char * feature,unsigned short num,int* candidate,short* score);
DLL_API int __stdcall PDB_ResetDataBase(int dbId);
DLL_API int __stdcall PDB_DeleteDataBase(int dbId);
DLL_API int __stdcall PDB_AddDataBase(int maxFaceNum);
DLL_API int __stdcall PDB_ChangeFeature(int dbId,int usid,short fsize,unsigned char * feature);
DLL_API int __stdcall PFD_AgrRecogImg(unsigned char * bmpData,PFD_AGR_DETECT* agrInfo,short faceRote);
DLL_API int __stdcall PFD_SmileRecogImg(unsigned char * bmpData,PFD_SMILE_DETECT* smileInfo,short faceRote);
DLL_API int __stdcall PFD_DirectRecogImg(unsigned char * bmpData,PFD_DIRECT_DETECT* directInfo,short faceRote);
DLL_API int __stdcall PFD_BlinkRecogImg(unsigned char * bmpData,PFD_BLINK_DETECT* blinkInfo,short faceRote);
DLL_API int __stdcall PFD_GetFeatureByManual(unsigned char * bmpData,PFD_FACE_POSITION* faceInfo,unsigned char* feature,short faceRote,short* fsize);
DLL_API int __stdcall PFD_GetFeatureByFaceInfo(unsigned char * bmpData,PFD_DETECT_INFO* faceInfo,short num,short faceRote,short infoFlag,unsigned char * feature);
*/