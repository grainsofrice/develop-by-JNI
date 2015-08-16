/* 人脸坐标结构体 */
public class PFD_FACE_POSITION {
	short conf; /* 识别信赖度(0~1000 低~高) */
	short rect_l; /* 人脸最左侧坐标 */
	short rect_r; /* 人脸最右侧坐标 */
	short rect_t; /* 人脸最上侧坐标 */
	short rect_b; /* 人脸最下侧坐标 */
	short eye_lx; /* 人脸左眼x坐标 */
	short eye_ly; /* 人脸左眼y坐标 */
	short eye_rx; /* 人脸右眼x坐标 */
	short eye_ry; /* 人脸右眼y坐标 */
}