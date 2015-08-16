/* 年龄性别结构体*/
public class PFD_AGR_INFO {
	PFD_FACE_POSITION faceInfo; /* 人脸坐标结构体 */
	short	ageConf;			/* 年龄识别信赖度(0~1000 低~高) */
	short	genConf;			/* 性别识别信赖度(0~1000 低~高) */
	short	age; 				/* 年龄*/
	short	gen; 				/* 性别*/ 
}
