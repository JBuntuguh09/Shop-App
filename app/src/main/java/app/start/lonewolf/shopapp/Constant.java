package app.start.lonewolf.shopapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {

    // Url for webservice
    public static final String URL = "http://gsims-api-1.eu-west-1.elasticbeanstalk.com/";

    // username and password
    public static final String USERNAME = "appsetup";
    public static final String PASSWORD = "msis";

    //Content type
    public static final String CONTENT_TYPE = "application/json";

    //url for webservice append
    public static final String TEACHERSLIST = "teacher/names?school_id=";
    public static final String ADDTEACHER = "teacher";

    public static final String EMPLOYEELIST = "school/employees?school_id=";

    public static final String CLASSROOMLIST = "classroom?school_id=";
    public static final String ADDCLASSROOM = "classroom";

    public static final String COURSELIST = "course?school_id=";
    public static final String ADDCOURSE = "course";

    public static final String COUNTRYLIST = "country/codes";

    public static final String ROLELIST = "role?school_id=";

    public static final String ADDSTAFF = "staff";
    public static final String UPDATESTAFF = "staff/";

    public static final String ADDVENDOR = "school/vendor";
    public static final String VENDORLIST = "school/vendors?school_id=";

    public static final String USAGEREPORT = "user_activity?user_id=";
    public static final String STARTDATE = "&start_date=";
    public static final String ENDDATE = "&end_date=";

    public static final String USERPROFILE = "user/profile?user_id=";
    public static final String UPDATEPROFILE = "user/profile";

    public static final String USERGETPROFILE = "user/profile?user_name=";
    public static final String USERUPDATEPROFILE = "user/profile";

    public static final String ENROLLSTUDENT = "student";

    public static final String FINDPARENT="legal_guardian?";
    //==================================================================//
    public static final String URL_CLASS = URL + "student?class_id";
    public static final String TEACHERS_ROOM = "teachers_room";
    public static final String STUDENTS = "students";
    public static final String ATTENDANCE = "attendance";
    public static final String ADDCLASSROOMMULTI = "multisynctoserver";

    public static final String SEARCHSTUDENT = "searchstudent";
    public static final String GETLGINFO = "legalguardian";
    public static final String UPDATESTUDENT = "updatestudent";
    public static final String UPDATEOTHERSTUDENTINFO = "updateotherinfo";
    public static final String UPDATEGUARDIANINFO = "guardianinfoupdate";
    public static final String UPDATEDOCUMENT = "updatedocuments";
    public static final String UPLOADGRADESTOSERVER = "uploadgradestoserver";
    public static final String STUDENTDOCUMENTS = "studentdocuments";
    public static final String STUDENTPROFILEIMAGE = "profileimage";
    public static final String STUDENTUPDATEDOC = "profimageupdatedoc";
    public static final String UPDATEGRADESDOCUMENTS = "updategradesdocuments";
    public static final String RECORDREVENUE = "recordrevenulist";
    public static final String RECORDREQUET = "recordexpense";
    public static final String RECORDEXPENSE = "recordexpensedocumet";
    public static final String RECORDSEARCH = "recordsearchdata";
    public static final String SCHOOLCONTACT = "schoolcontact";
    public static final String UPDATESCHOOL = "updateschool";
    public static final String UPDATESTUDENTPROFILEPIC = "updatestudentprofilepc";
    public static final String SCHOOL_DOCUMENT = "schooldocuments";
    public static final String LOGIN = "login";
    public static final String USER_REGISTER = "user";
    public static final String USER_LOGIN = "token";
    public static final String GET_STUDENT_DASHBOARD = Constant.STUDENT + "performance";
    public static final String GET_SCHOOL_TERM = "school_term";
    public static final String GET_STUDENT_BEHAVIOR = "behavior_discipline";
    public static final String GET_STUDENT_GRADE = "grade";
    public static final String GET_STUDENT_ATTENDACE = "attendance";
    public static final String STUDENT = "student/";
    public static final String POST_FINAL_REPORT = "/final_report";
    public static final String POST_STUDENT_BEHAVIOR = "behavior_discipline";

    public static final String TEACHERS_MANAGEMENT = "teachers_management";
    public static final String TEACHER_CLASSROOM = "teacher_getclassroom";
    public static final String TEACHER_SETUP = "teachers_setup";
    public static final String TEACHER_GET_PROFILE = "teachers_get_profile";
    public static final String TEACHER_UPDATE_PROFILE = "teachers_update_profile";
    public static final String SCHOOLNOTICE = "schoolnotice";
    public static final String SCHOOLLIST = "schoollist";
    public static final String GETCOURSE = "getcourse";
    public static final String CLASSROOMNOTICE = "classroomnotice";
    public static final String GETSCHOOLNOTIFYLIST = "schoolnotifylist";
    public static final String TEACHERPROFILE = "teacherprofile";
    public static final String GETTEACHERLIST = "teacherlist";
    public static final String GETSTUDENTLIST = "studentlist";
    public static final String GETCLASSROOMLIST = "classroomlist";
    public static final String GETSTUDENTPROFILE = "getstudentprofile";
    public static final String COURSEAVAILABLE = "courseavailable";
    public static final String PARAM_STUDENT_ID = "student_id";
    public static final String PARAM_STUDENT_NAME = "student_name";
    public static final String NEWCOURSESETUP = "newcoursesetup";
    public static final String GETCURRENCYLIST = "currencylist";

    public static final String UPDATEUSERPROFILE = "updateuserprofile";
    public static final String GETINVOICEITEM = "invoiceitem";
    public static final String CREATEINVOICE = "createinvoice";

    public static final int SELECT_CAMERA = 101;
    public static final int SELECT_GALLERY = 102;

    public static Bitmap bitmapImage;

    public static boolean signature = false;

    public static boolean getSignature() {
        return signature;
    }

    public static void setSignature(Boolean signature) {
        Constant.signature = signature;
    }

    public static void setBitmapImage(Bitmap imagename) {
        bitmapImage = imagename;
    }

    public static Bitmap getBitmapImage() {

        return bitmapImage;
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }


    public static Bitmap decodeBase64(String input) {

        try {
            byte[] decodedByte = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedByte, 0,
                    decodedByte.length);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    /**
     * Create a File for saving an image or video
     */
    @SuppressLint("SimpleDateFormat")
    public static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "prive");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        if (type == 1) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), 0);
    }

    public static Bitmap getImageResize(Bitmap bitmapOrg) {
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
        int newWidth = 300;
        int newHeight = 300;

        // calculate the scale - in this case = 0.4f
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // createa matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width,
                height, matrix, true);

        return resizedBitmap;
    }

    public static double roundLatLong(double value) {
        double factor = 1e5; // = 1 * 10^5 = 100000.
        return Math.round(value * factor) / factor;
    }

    /*public static int getEducationselect(String higesteducation, Context context) {

        String[] array = context.getResources().getStringArray(
                R.array.higesteducationlevel);

        for (int i = 0; i < array.length; i++) {
            if (higesteducation.toLowerCase().equals(array[i].toLowerCase())) {
                return i;
            }
        }
        return 0;
    }


    public static int publicprivateSchool(String publicprivate, Context context) {

        String[] array = context.getResources().getStringArray(
                R.array.schooltype);

        for (int i = 0; i < array.length; i++) {
            if (publicprivate.toLowerCase().equals(array[i].toLowerCase())) {
                return i;
            }
        }
        return 0;
    }
*/
    public static void setupUI(View view, final Activity activity) {

        // Set ic_down touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new OnTouchListener() {

                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }

            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView, activity);
            }
        }
    }

}