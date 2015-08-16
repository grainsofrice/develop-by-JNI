
public class face_detect_sdk {
	public static short PFD_MAX_FACE_NUM = 20;
    public static short PFD_OP_FACE_ROLL_0 = 0x10;
    public static short PFD_OP_FACE_ROLL_90 = 0x20;
    public static short PFD_OP_FACE_ROLL_180 = 0x40;
    public static short PFD_OP_FACE_ROLL_270 = 0x80;

    public static int PFD_STATUS_OK = 0;
    public static int PFD_STATUS_NG = -1;
    public static int PFD_STATUS_INVALID = -2;

    public static int PFD_MALE = 0;
    public static int PFD_FEMALE = 1;

    public static int PFD_ENABLEINFO = 1;
    public static int PFD_DISABLEINFO = 0;

    public static int IMG_SIZE_VGA = 0;
    public static int IMG_SIZE_FULLHD = 1;
}
