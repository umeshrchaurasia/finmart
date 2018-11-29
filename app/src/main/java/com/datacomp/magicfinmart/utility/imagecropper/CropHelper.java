package com.datacomp.magicfinmart.utility.imagecropper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

/**
 * Created by IN-RB on 02-03-2017.
 */

public class CropHelper {

    private static Context mcontext;
    public static final String TAG = "CropHelper";

    /**
     * request code of Activities or Fragments
     * You will have to change the values of the request codes below if they conflict with your own.
     */
    public static final int REQUEST_CROP = 127;
    public static final int REQUEST_CAMERA = 128;
    public static final int REQUEST_PICK = 129;


    public static final String CROP_CACHE_FOLDER = "Profile";
    public static final String ProfilePic = "ProfilePic.png";
    private static ImageCompression imageComp;

    public CropHelper(Context context) {
        this.mcontext = context;
        imageComp = new ImageCompression(context);
    }

    public static Uri generateUri() {


        File cacheFolder = new File(Environment.getExternalStorageDirectory(), "/FINMART");
        if (!cacheFolder.exists()) {
            try {
                boolean result = cacheFolder.mkdir();
                Log.d(TAG, "generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
            } catch (Exception e) {
                Log.e(TAG, "generateUri failed: " + cacheFolder, e);
            }
        }
        // ("ProfilePic.jpg"
        String name = String.format(ProfilePic);



        return Uri
                .fromFile(cacheFolder)
                .buildUpon()
                .appendPath(name)
                .build();
    }

    public static boolean isPhotoReallyCropped(Uri uri) {
        File file = new File(uri.getPath());
        long length = file.length();
        return length > 0;
    }

    public static void handleResult(CropHandler handler, int requestCode, int resultCode, Intent data) {
        String finalpath;
        if (handler == null) return;

        if (resultCode == Activity.RESULT_CANCELED) {
            handler.onCancel();
        } else if (resultCode == Activity.RESULT_OK) {
            CropParams cropParams = handler.getCropParams();
            if (cropParams == null) {
                handler.onFailed("CropHandler's params MUST NOT be null!");
                return;
            }
            switch (requestCode) {
                //                case REQUEST_GALLERY:
                case REQUEST_CROP:
                    if (isPhotoReallyCropped(cropParams.uri)) {
                        Log.d(TAG, "Photo cropped!");
                        onPhotoCropped(handler, cropParams);
                        break;
                    } else {
                        Context context = handler.getCropParams().context;
                        if (context != null) {
                            if (data != null) {
                                if (data.getData() != null) {
                                    String path = CropFileUtils.getSmartFilePath(context, data.getData());
                                    if (path == null || (path.isEmpty())) {
                                        handler.onFailed("Use image from Internal storage only..");
                                        break;
                                    }
                                    else {
                                        boolean result = CropFileUtils.copyFile(path, cropParams.uri.getPath());
                                        if (!result) {
                                            handler.onFailed("Copy file to cached folder failed");
                                            break;
                                        }
                                    }
                                } else if (data.getExtras().get("data") != null) {
                                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                                    String path = CropFileUtils.getSmartFilePath(context, getImageUri(context, bitmap));
                                    if (path == null || (path.isEmpty())) {
                                        handler.onFailed("Use image from Internal storage only..");
                                        break;
                                    }
                                    else {
                                        boolean result = CropFileUtils.copyFile(path, cropParams.uri.getPath());
                                        if (!result) {
                                            handler.onFailed("Copy file to cached folder failed");
                                            break;
                                        }
                                    }

                                }
                            } else {
                                handler.onFailed("Returned data is null " + data);
                                break;
                            }
                        } else {
                            handler.onFailed("CropHandler's context MUST NOT be null!");
                        }
                    }
                case REQUEST_CAMERA:
                    if (cropParams.enable) {
                        Context context = handler.getCropParams().context;
                        if (context != null) {
                            if (data != null) {
                                if (data.getData() != null) {
                                    String path = CropFileUtils.getSmartFilePath(context, data.getData());
                                    if (path == null || (path.isEmpty())) {
                                        handler.onFailed("Use image from Internal storage only..");
                                        break;
                                    }
                                    else{
                                    boolean result = CropFileUtils.copyFile(path, cropParams.uri.getPath());
                                        if (!result) {
                                            handler.onFailed("Copy file to cached folder failed");
                                            break;
                                        }
                                    }
                                } else if (data.getExtras().get("data") != null) {
                                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                                    String path = CropFileUtils.getSmartFilePath(context, getImageUri(context, bitmap));
                                    if (path == null || (path.isEmpty())) {
                                        handler.onFailed("Use image from Internal storage only..");
                                        break;
                                    }
                                    else {
                                        boolean result = CropFileUtils.copyFile(path, cropParams.uri.getPath());
                                        if (!result) {
                                            handler.onFailed("Copy file to cached folder failed");
                                            break;
                                        }
                                    }
                                }
                            } else {
                                handler.onFailed("Returned data is null " + data);
                                break;
                            }
                        } else {
                            handler.onFailed("CropHandler's context MUST NOT be null!");
                        }
                        Intent intent = buildCropFromUriIntent(cropParams);
                        handler.handleIntent(intent, REQUEST_CROP);
                    } else {
                        Log.d(TAG, "Photo cropped!");
                        onPhotoCropped(handler, cropParams);
                    }
                    break;
            }
        }
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private static void onPhotoCropped(CropHandler handler, CropParams cropParams) {
        if (cropParams.compress) {
            Uri originUri = cropParams.uri;
            Uri compressUri = generateUri();
            CompressImageUtils.compressImageFile(cropParams, originUri, compressUri);
            handler.onCompressed(compressUri);
        } else {
            handler.onPhotoCropped(cropParams.uri);
        }
    }

    // None-Crop Intents

    public static Intent buildGalleryIntent(CropParams params) {
       // return buildCropIntent(Intent.ACTION_GET_CONTENT, params);

        Intent intent;
        if (params.enable) {
            intent = buildCropIntent(Intent.ACTION_GET_CONTENT, params);
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT)
                    .setType("image/*")
                    .putExtra(MediaStore.EXTRA_OUTPUT, params.uri);
        }
        return intent;
    }


    public static Intent buildCameraIntent(CropParams params) {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
              //  .putExtra(MediaStore.EXTRA_OUTPUT, params.uri)
                .putExtra("android.intent.extras.CAMERA_FACING", 1);
    }

    // Crop Intents

    private static Intent buildCropFromUriIntent(CropParams params) {
        return buildCropIntent("com.android.camera.action.CROP", params);
    }

    private static Intent buildCropIntent(String action, CropParams params) {
        return new Intent(action)
                .setDataAndType(params.uri, params.type)
                .putExtra("crop", "true")
                //        .putExtra("scale", params.scale)
                .putExtra("aspectX", params.aspectX)
                .putExtra("aspectY", params.aspectY)
                .putExtra("outputX", params.outputX)
                .putExtra("outputY", params.outputY)
                .putExtra("return-data", params.returnData)
                .putExtra("outputFormat", params.outputFormat)
                .putExtra("noFaceDetection", params.noFaceDetection)
                .putExtra("scaleUpIfNeeded", params.scaleUpIfNeeded)
                .putExtra(MediaStore.EXTRA_OUTPUT, params.uri);
    }

    // Clear Cache
    public static boolean clearCacheDir() {
        File cacheFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + CROP_CACHE_FOLDER);
        if (cacheFolder.exists() && cacheFolder.listFiles() != null) {
            for (File file : cacheFolder.listFiles()) {
                boolean result = file.delete();
                Log.d(TAG, "Delete " + file.getAbsolutePath() + (result ? " succeeded" : " failed"));
            }
            return true;
        }
        return false;
    }


}
