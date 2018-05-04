package seph.android.com.itemtracker.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by seph on 04/05/2018.
 */

class ImagePicker(private val activity: Activity) {

    var imageUri: Uri? = null
        private set

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> if (Activity.RESULT_OK == resultCode) {
                return true
            }

            REQUEST_IMAGE_GALLERY -> if (Activity.RESULT_OK == resultCode) {
                imageUri = data?.data
                return true
            }
        }

        return false
    }

    fun askSource(title: String) {
        val adapter = object : ArrayAdapter<String>(activity.applicationContext, android.R.layout.simple_list_item_1, arrayOf("Take picture", "Choose from gallery")) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<View>(android.R.id.text1) as TextView
                textView.setTextColor(Color.BLACK)
                return view
            }
        }

        createDialog(activity, title, adapter, DialogInterface.OnClickListener { dialogInterface, which ->
            dialogInterface.dismiss()
            if (which === 0) {
                openCamera()
            } else {
                pickFromGallery()
            }
        }).show()
    }

    fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {

            var imageFile : File? = null

            try {
                imageFile = createImageFile(activity.applicationContext)

            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (imageFile != null) {
                imageUri = FileProvider.getUriForFile( activity.getApplicationContext(),
                        activity.getPackageName()+".fileprovider", imageFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity.startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE_GALLERY)
    }

    fun createDialog(context: Context, title: String, adapter: ArrayAdapter<String>, adapterClickListener: DialogInterface.OnClickListener): AlertDialog {
        var builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setNegativeButton("Cancel", { dialogInterface, _ ->  dialogInterface.dismiss() })
        builder.setAdapter(adapter, adapterClickListener)
        return builder.create()
    }

    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imagefileName = "JPEG_" + timestamp + "_"

        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imagefileName, ".jpg", storageDir)
    }

    companion object {
        private val REQUEST_IMAGE_CAPTURE = 0x101
        private val REQUEST_IMAGE_GALLERY = 0x110
    }
}