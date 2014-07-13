package takuma.tutorial.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/**
 * Class containing some static utility methods.
 */
public class Utils {
    public static final int IO_BUFFER_SIZE = 8 * 1024;
    public static final int HONEYCOMB_VERSION = 11;
    private Utils() {};

    /**
     * Workaround for bug pre-Froyo, see here for more info:
     * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
     */
    public static void disableConnectionReuseIfNecessary() {
        // HTTP connection reuse which was buggy pre-froyo
        if (hasHttpConnectionBug()) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    /**
     * Get the size in bytes of a bitmap.
     * @param bitmap
     * @return size in bytes
     */
    public static int getBitmapSize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false
     *         otherwise.
     */
    public static boolean isExternalStorageRemovable() {
        return true;
    }

    /**
     * Get the external app cache directory.
     *
     * @param context The context to use
     * @return The external cache dir
     */
    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    /**
     * Check how much usable space is available at a given path.
     *
     * @param path The path to check
     * @return The space available in bytes
     */
    public static long getUsableSpace(File path) {
        final StatFs stats = new StatFs(path.getPath());
        return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    }

    /**
     * Get the memory class of this device (approx. per-app memory limit)
     *
     * @param context
     * @return
     */
    public static int getMemoryClass(Context context) {
        return ((ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE)).getMemoryClass();
    }

    /**
     * Check if OS version has a http URLConnection bug. See here for more information:
     * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
     *
     * @return
     */
    public static boolean hasHttpConnectionBug() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO;
    }

    /**
     * Check if OS version has built-in external cache dir method.
     *
     * @return
     */
    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * Check if ActionBar is available.
     *
     * @return
     */
    public static boolean hasActionBar() {
        return Build.VERSION.SDK_INT >= HONEYCOMB_VERSION;
    }
    
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String getLanguage(String s) {
    	switch (s) {
		case "en_US":
			return "English";
		case "de_DE":
			return "German";
		case "zh_CN":
			return "Chinese (Simplified)";
		case "zh_TW":
			return "Chinese (Traditional)";
		case "cs_CZ":
			return "Czech";
		case "nl_BE":
			return "Dutch";
		case "nl_NL":
			return "Dutch";
		case "en_AU":
			return "English";
		case "en_GB":
			return "English";
		case "en_CA":
			return "English";
		case "en_NZ":
			return "English";
		case "en_SG":
			return "English";
		case "fr_BE":
			return "French";
		case "fr_CA":
			return "French";
		case "fr_FR":
			return "French";
		case "fr_CH":
			return "French";
		case "de_AT":
			return "German";
		case "de_LI":
			return "German";
		case "de_CH":
			return "German";
		case "it_IT":
			return "Italian";
		case "it_CH":
			return "Italian";
		case "ja_JP":
			return "Japanese";
		case "ko_KR":
			return "Korean";
		case "pl_PL":
			return "Polish ";
		case "ru_RU":
			return "Russian";
		case "es_ES":
			return "Spanish ";
		case "ar_EG":
			return "Arabic";
		case "ar_IL":
			return "Arabic";
		case "bg_BG":
			return "Bulgarian";
		case "ca_ES":
			return "Catalan";
		case "hr_HR":
			return "Croatian";
		case "da_DK":
			return "Danish";
		case "en_IN":
			return "English";
		case "en_IE":
			return "English";
		case "en_ZA":
			return "English";
		case "fi_FI":
			return "Finnish";
		case "el_GR":
			return "Greek";
		case "iw_IL":
			return "Hebrew";
		case "hi_IN":
			return "Hindi";
		case "hu_HU":
			return "Hungarian";
		case "in_ID":
			return "Indonesian";
		case "lv_LV":
			return "Latvian";
		case "lt_LT":
			return "Lithuanian";
		case "nb_NO":
			return "Norwegian-Bokmol";
		case "pt_BR":
			return "Portuguese";
		case "pt_PT":
			return "Portuguese";
		case "ro_RO":
			return "Romanian";
		case "sr_RS":
			return "Serbian";
		case "sk_SK":
			return "Slovak";
		case "sl_SI":
			return "Slovenian";
		case "es_US":
			return "Spanish";
		case "sv_SE":
			return "Swedish";
		case "tl_PH":
			return "Tagalog";
		case "th_TH":
			return "Thai";
		case "tr_TR":
			return "Turkish";
		case "uk_UA":
			return "Ukrainian";
		case "vi_VN":
			return "Vietnamese";

		default:
			return "English";
		}
    }
    
    /**
     * Java Object Filter
     * 
     * @return Collection
     * @author takumalee
     * 
     * */
    
    public static <T> Collection<T> filter(Collection<T> target, PredicateInterface<T> predicate) {
    		Collection<T> filteredCollection = new ArrayList<T>();
    		for (T t : target) {
    			if (predicate.apply(t)) {
    				filteredCollection.add(t);
    			}
    		}
    		return filteredCollection;
    }
}

