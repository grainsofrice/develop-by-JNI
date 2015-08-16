/* 图像内的人脸信息结构体 */
public class PFD_FACE_DETECT {
	short num; /* 图像中人脸数(最大:PFD_MAX_FACE_NUM) */
	PFD_DETECT_INFO[] info; /* 人脸的位置和情报信息 */
}
