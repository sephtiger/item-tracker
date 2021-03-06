package seph.android.com.itemtracker.view.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.View
import android.arch.lifecycle.Observer
import android.content.Intent
import android.text.TextUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_item.*
import seph.android.com.itemtracker.R
import seph.android.com.itemtracker.utils.ImagePicker
import seph.android.com.itemtracker.view.ui.base.BaseActivity
import seph.android.com.itemtracker.viewmodel.ItemAddViewModel
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by seph on 03/05/2018.
 */

class ItemAddActivity : BaseActivity() {

    override val layoutResourceId = R.layout.activity_add_item

    @Inject
    lateinit var addViewModel: ItemAddViewModel

    var imagePicker = ImagePicker(this)

    var isEditting : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        image.setOnClickListener { imagePicker.askSource("Select from") }
        imageHint.setOnClickListener { imagePicker.askSource("Select from") }
        saveButton.setOnClickListener { attemptSave() }


        if(intent.hasExtra("item")) {
            addViewModel.liveData.value = intent.getParcelableExtra("item")
        }

        addViewModel.liveData.observe(this, Observer {

            title = "Edit Item"

            isEditting = true

            imageHint.visibility = View.GONE
            imageHint.setOnClickListener(null)
            image.setOnClickListener(null)
            Picasso.get()
                    .load(it?.image)
                    .into(image)

            name.text.clear()
            description.text.clear()
            location.text.clear()
            cost.text.clear()

            name.text.insert(0,it?.name)
            description.text.insert(0,it?.description)
            location.text.insert(0,it?.location)
            cost.text.insert(0,it?.cost.toString())
        })

        addViewModel.state.observe(this, Observer {
            when(it) {
                is BaseViewModel.State.Loading -> showProgress(true)
                is BaseViewModel.State.Success -> finish()
                is BaseViewModel.State.Error -> showProgress(false)
            }
        })
    }

    private fun attemptSave() {

        // Reset errors.
        name.error = null
        description.error = null
        location.error = null
        cost.error= null

        // Store values at the time of the login attempt.
        val nameStr = name.text.toString()
        val descriptionStr = description.text.toString()
        val imageStr = imagePicker.imageUri?.toString() ?: ""
        val locationStr = location.text.toString()
        val costStr = cost.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid name.
        if (TextUtils.isEmpty(nameStr)) {
            name.error = getString(R.string.error_field_required)
            focusView = name
            cancel = true
        }

        // Check for a valid description.
        if (TextUtils.isEmpty(descriptionStr)) {
            description.error = getString(R.string.error_field_required)
            focusView = description
            cancel = true
        }

        // Check for a valid image.
        if (!isEditting && TextUtils.isEmpty(imageStr)) {
            imageHint.text = "This is required"
            cancel = true
        }

        // Check for a location.
        if (TextUtils.isEmpty(locationStr)) {
            location.error = getString(R.string.error_field_required)
            focusView = location
            cancel = true
        }

        // Check for a cost.
        if (TextUtils.isEmpty(costStr) || !TextUtils.isDigitsOnly(costStr)) {
            cost.error = getString(R.string.error_field_required)
            focusView = cost
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt save and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the item save attempt.

            if (isEditting) {
                addViewModel.editItem(
                        addViewModel.liveData.value!!.id,
                        nameStr,
                        descriptionStr,
                        addViewModel.liveData.value!!.image,
                        locationStr,
                        costStr.toInt())
            }
            else {
                addViewModel.addItem(
                        nameStr,
                        descriptionStr,
                        imagePicker.imageUri!!,
                        locationStr,
                        costStr.toInt())
            }
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            itemForm.visibility = if (show) View.GONE else View.VISIBLE
            itemForm.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            itemForm.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            itemProgress.visibility = if (show) View.VISIBLE else View.GONE
            itemProgress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            itemProgress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            itemProgress.visibility = if (show) View.VISIBLE else View.GONE
            itemForm.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (imagePicker.onActivityResult(requestCode, resultCode, data)) {
            image.setImageURI(imagePicker.imageUri)
            imageHint.visibility = View.GONE
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
