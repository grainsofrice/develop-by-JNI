/***********************************************************
 *
 *  Copyright (C) 2014~2017  PSDCD Corporation
 *  All Rights Reserved.
 *
 ***********************************************************/

#define  PFD_MAX_FACE_NUM (20)
#define  PFD_OP_FACE_ROLL_0    (0x10)  /* �������ϵĳ���(����ת) */
#define  PFD_OP_FACE_ROLL_90   (0x20)  /* �������ҵĳ���(��ʱ����ת90��) */
#define  PFD_OP_FACE_ROLL_180  (0x40)  /* �������µĳ���(����) */
#define  PFD_OP_FACE_ROLL_270  (0x80)  /* ��������ĳ���(˳ʱ����ת90��) */

#define PFD_STATUS_OK		(0)		/* �������� */
#define	PFD_STATUS_NG		(-1)	/* �쳣���� */
#define	PFD_STATUS_INVALID	(-2)	/* ��֤ʧ�� */

#define PFD_MALE	(0)        /* ����  */
#define PFD_FEMALE	(1)        /* Ů��  */

#define PFD_ENABLEINFO	(1)
#define PFD_DISABLEINFO	(0)

#define IMG_SIZE_VGA  (0)
#define IMG_SIZE_FULLHD (1)

/* ��������ṹ�� */
typedef struct _pfd_face_position {
	short	conf;	/* ʶ��������(0~1000 ��~��) */
	short	rect_l;	/* �������������*/
	short	rect_r;	/*�������Ҳ�����*/
	short	rect_t;	/*�������ϲ�����*/
	short	rect_b;	/*�������²�����*/
	short	eye_lx;	/*��������x����*/
	short	eye_ly;	/*��������y����*/
	short	eye_rx;	/*��������x����*/
	short	eye_ry;	/*��������y����*/
} PFD_FACE_POSITION;


/* �����鱨����ṹ�� */
typedef struct _pfd_detect_info{
	PFD_FACE_POSITION faceInfo; 	/* ��������ṹ�� */
	short ageConf;                 	/* ����ʶ����Ŷ� (0~1000 ��~��) */
	short genConf;                 	/* �Ա�ʶ����Ŷ� (0~1000 ��~��) */
	short age;                     	/* ���� */
	short gen;                     	/* �Ա� */
	short smile;                   	/* Ц���̶�(0~100 ��~��)*/
	short pitch;                   	/* ̧ͷ��ͷ�ĽǶ�(-180~180�ȣ�̧ͷ����ֵ)*/ 
	short yaw;                   	/* ҡͷ�Ƕ�(-180~180�ȣ�����ҡͷ����ֵ)*/
	short roll;                    	/* ��б�Ƕ�(-180~180�ȣ�˳ʱ������ֵ)*/
	short lb;                     	/* ���۱��۸��ʣ���ֵԽ������۾�Խ��*/ 
	short rb;                     	/* ���۱��۸��ʣ���ֵԽ������۾�Խ��*/
	short flen;                    	/* ����ֵ���� */ 
}PFD_DETECT_INFO;


/* ͼ���ڵ�������Ϣ�ṹ�� */
typedef struct _pfd_face_detect {
	short	num;					/* ͼ����������(���:PFD_MAX_FACE_NUM) */
	PFD_DETECT_INFO	info[PFD_MAX_FACE_NUM];		/* ������λ�ú��鱨��Ϣ */
} PFD_FACE_DETECT;


/* �����Ա�ṹ��*/
typedef struct _pfd_agr_info {
	PFD_FACE_POSITION faceInfo; /* ��������ṹ�� */
	short	ageConf;			/* ����ʶ��������(0~1000 ��~��) */
	short	genConf;			/* �Ա�ʶ��������(0~1000 ��~��) */
	short	age; 				/* ����*/
	short	gen; 				/* �Ա�*/ 
} PFD_AGR_INFO;


typedef struct _pfd_agr_detect {
	short	num;							/* ͼƬ��������(���:PFD_MAX_FACE_NUM) */
	PFD_AGR_INFO	info[PFD_MAX_FACE_NUM];	/*�����Ա�*/
} PFD_AGR_DETECT;


/* ���鹹����*/
typedef struct _pfd_smile_info {
	PFD_FACE_POSITION faceInfo; /* ��������ṹ�� */
    short	smile;  			/* Ц���̶�(0~100 ��~��)*/ 
} PFD_SMILE_INFO;


typedef struct _pfd_smile_detect {
	short	num;							/* ͼƬ��������(���:PFD_MAX_FACE_NUM) */
	PFD_SMILE_INFO	info[PFD_MAX_FACE_NUM];	/*Ц����Ϣ*/
} PFD_SMILE_DETECT;


/* ������������*/
typedef struct _pfd_direct_info {
	PFD_FACE_POSITION faceInfo; /* ��������ṹ�� */
	short	pitch;				/* ̧ͷ��ͷ�ĽǶ�(-180~180�ȣ�̧ͷ����ֵ)*/ 
	short	yaw;				/* ҡͷ�Ƕ�(-180~180�ȣ�����ҡͷ����ֵ)*/
	short	roll;     			/* ��б�Ƕ�(-180~180�ȣ�˳ʱ������ֵ)*/
} PFD_DIRECT_INFO;


typedef struct _pfd_direct_detect {
	short	num;							/* ͼƬ��������(���:PFD_MAX_FACE_NUM) */
	PFD_DIRECT_INFO	info[PFD_MAX_FACE_NUM];	/*����������Ϣ*/
} PFD_DIRECT_DETECT;


/* ������������*/
typedef struct _pfd_blink_info {
    PFD_FACE_POSITION  faceInfo;  	/* ��������ṹ�� */
	short	lb;      				/* ���۱��۳̶ȣ���ֵԽ������۾�Խ��**/ 
	short	rb;      				/* ���۱��۸��ʣ���ֵԽ������۾�Խ��**/
} PFD_BLINK_INFO;

typedef struct _pfd_blink_detect {
	short	num;							/* ͼƬ��������(���:PFD_MAX_FACE_NUM) */
	PFD_BLINK_INFO	info[PFD_MAX_FACE_NUM];	/*������Ϣ*/
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