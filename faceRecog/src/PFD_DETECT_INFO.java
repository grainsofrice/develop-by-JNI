/* 人脸情报保存结构体 */
public class PFD_DETECT_INFO {
	PFD_FACE_POSITION faceInfo; /* 人脸坐标结构体 */
	short ageConf; /* 年龄识别可信度 (0~1000 低~高) */
	short genConf; /* 性别识别可信度 (0~1000 低~高) */
	short age; /* 年龄 */
	short gen; /* 性别 */
	short smile; /* 笑脸程度(0~100 低~高) */
	short pitch; /* 抬头低头的角度(-180~180度，抬头是正值) */
	short yaw; /* 摇头角度(-180~180度，向左摇头是正值) */
	short roll; /* 倾斜角度(-180~180度，顺时针是正值) */
	short lb; /* 左眼闭眼概率，数值越大代表眼睛越大 */
	short rb; /* 右眼闭眼概率，数值越大代表眼睛越大 */
	short flen; /* 特征值长度 */
}