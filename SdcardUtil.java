
import android.os.Environment;

/**
 * ��ȡ SD ���Ĳ���״̬
 * @author baisu
 *
 */
public class SdcardUtil {

	/**
	 * �ж��Ƿ���� SD ��
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
