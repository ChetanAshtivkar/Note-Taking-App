package com.chetan.note.common

import android.R
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Chetan on 09/03/20.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}