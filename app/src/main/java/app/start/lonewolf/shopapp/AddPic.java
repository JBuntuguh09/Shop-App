package infoview.io.shschoice;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;

/**
 * Created by lonewolf on 9/16/2017.
 */

public class AddPic extends DialogFragment {
    private LinearLayout camera, gallery;
    private User_Preference userPreference;
    public static final int SELECT_CAMERA = 101;
    public static final int SELECT_GALLERY = 102;
    private ImageView imgPicture;

    // @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_layout, container, false);

        imgPicture = (ImageView)view.findViewById(R.id.testPicture);
        userPreference = new User_Preference(getActivity());
        camera = (LinearLayout) view.findViewById(R.id.layCamera);
        gallery = (LinearLayout) view.findViewById(R.id.layGallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(
                        Intent.createChooser(intent, "Select Picture"),
                        SELECT_CAMERA);


                // dismiss();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent1, "Select Picture"),
                        SELECT_GALLERY);

                //dismiss();

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        
        Uri selectedImage = null;
        //switch (requestCode) {
          //  case SELECT_CAMERA:
                String imagebase64format;
                if (requestCode == SELECT_CAMERA && null !=data ) {

                    Bitmap picture = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    picture.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                   /* String path = MediaStore.Images.Media.insertImage(getActivity()
                            .getContentResolver(), picture, "title", null);
                    selectedImage = Uri.parse(path);*/

                    byte[] b = stream.toByteArray();
                    imagebase64format = Base64.encodeToString(b, Base64.DEFAULT);
                    userPreference.setPicture(imagebase64format);
                     imgPicture.setImageBitmap(picture);

                    Intent intent = new Intent(getActivity(), StartPage.class);
                    startActivity(intent);

                    dismiss();

                }else
            //    break;

            //case SELECT_GALLERY:
                if (requestCode== SELECT_GALLERY && null != data) {
                    selectedImage = data.getData();
                    imgPicture.setImageURI(selectedImage);
                    // Bitmap pic = selectedImage.;

                    Bitmap picture = ((BitmapDrawable) imgPicture.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    picture.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] b = stream.toByteArray();
                    imagebase64format = Base64.encodeToString(b, Base64.DEFAULT);
                    userPreference.setPicture(imagebase64format);

                    Intent intent = new Intent(getActivity(), StartPage.class);
                    startActivity(intent);
                    dismiss();

                }
            //default:
              //  break;


        //}
        super.onActivityResult(requestCode, resultCode, data);


    }


}
