
import android.os.Environment;

/**
 * 获取 SD 卡的插入状态
 * @author baisu
 *
 */
public class SdcardUtil {

	/**
	 * 判断是否插入 SD 卡
	 * @return
	 */
	public static boolean isHasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
