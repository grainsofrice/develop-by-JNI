
/* 人脸方向构造体*/
 public class PFD_DIRECT_INFO {
	PFD_FACE_POSITION faceInfo; /* 人脸坐标结构体 */
	short	pitch;				/* 抬头低头的角度(-180~180度，抬头是正值)*/ 
	short	yaw;				/* 摇头角度(-180~180度，向左摇头是正值)*/
	short	roll;     			/* 倾斜角度(-180~180度，顺时针是正值)*/
}