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
    	return s.equals("en_US") ? "English"    :
            s.equals("de_DE") ? "German"     :
            s.equals("zh_CN") ? "Chinese (Simplified)"   :
            s.equals("zh_TW") ? "Chinese (Traditional)"  :
            s.equals("cs_CZ") ? "Czech"      :
            s.equals("nl_BE") ? "Dutch"      :
            s.equals("nl_NL") ? "Dutch"      :
            s.equals("en_AU") ? "English"    :
            s.equals("en_GB") ? "English"    :
            s.equals("en_CA") ? "English"    :
            s.equals("en_NZ") ? "English"    :
            s.equals("en_SG") ? "English"    :
            s.equals("fr_BE") ? "French"     :
            s.equals("fr_CA") ? "French"     :
            s.equals("fr_FR") ? "French"     :
            s.equals("fr_CH") ? "French"     :
            s.equals("de_AT") ? "German"     :
            s.equals("de_LI") ? "German"     :
            s.equals("de_CH") ? "German"     :
            s.equals("it_IT") ? "Italian"    :
            s.equals("it_CH") ? "Italian"    :
            s.equals("ja_JP") ? "Japanese"   :
            s.equals("ko_KR") ? "Korean"     :
            s.equals("pl_PL") ? "Polish"     :
            s.equals("ru_RU") ? "Russian"    :
            s.equals("es_ES") ? "Spanish"    :
            s.equals("ar_EG") ? "Arabic"     :
            s.equals("ar_IL") ? "Arabic"     :
            s.equals("bg_BG") ? "Bulgarian"  :
            s.equals("ca_ES") ? "Catalan"    :
            s.equals("hr_HR") ? "Croatian"   :
            s.equals("da_DK") ? "Danish"     :
            s.equals("en_IN") ? "English"    :
            s.equals("en_IE") ? "English"    :
            s.equals("en_ZA") ? "English"    :
            s.equals("fi_FI") ? "Finnish"    :
            s.equals("el_GR") ? "Greek"      :
            s.equals("iw_IL") ? "Hebrew"     :
            s.equals("hi_IN") ? "Hindi"      :
            s.equals("hu_HU") ? "Hungarian"  :
            s.equals("in_ID") ? "Indonesian" :
            s.equals("lv_LV") ? "Latvian"    :
            s.equals("lt_LT") ? "Lithuanian" :
            s.equals("nb_NO") ? "Norwegian-Bokmol"     :
            s.equals("pt_BR") ? "Portuguese" :
            s.equals("pt_PT") ? "Portuguese" :
            s.equals("ro_RO") ? "Romanian"   :
            s.equals("sr_RS") ? "Serbian"    :
            s.equals("sk_SK") ? "Slovak"     :
            s.equals("sl_SI") ? "Slovenian"  :
            s.equals("es_US") ? "Spanish"    :
            s.equals("sv_SE") ? "Swedish"    :
            s.equals("tl_PH") ? "Tagalog"    :
            s.equals("th_TH") ? "Thai"       :
            s.equals("tr_TR") ? "Turkish"    :
            s.equals("uk_UA") ? "Ukrainian"  :
            s.equals("vi_VN") ? "Vietnamese" : "English";
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

