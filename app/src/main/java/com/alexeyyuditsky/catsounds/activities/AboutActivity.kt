package com.alexeyyuditsky.catsounds.activities

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.alexeyyuditsky.catsounds.BuildConfig
import com.alexeyyuditsky.catsounds.R
import com.alexeyyuditsky.catsounds.databinding.ActivityAboutBinding
import com.alexeyyuditsky.catsounds.util.rateApp

const val LINK_DEV = "https://play.google.com/store/apps/dev?id=5412871842907287238"

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater).also { setContentView(it.root) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            version.text = getString(R.string.version).plus(BuildConfig.VERSION_NAME)
            llDev.setOnClickListener { followLink() }
            llShare.setOnClickListener { share() }
            llLinks.setOnClickListener { rateApp(this@AboutActivity) }
        }
    }

    private fun followLink() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_DEV)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.no_app_for_open), Toast.LENGTH_SHORT).show()
        }
    }

    private fun share() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, resources.getText(R.string.share_message))
            putExtra(Intent.EXTRA_SUBJECT, resources.getText(R.string.share_subject))
            startActivity(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.left_out_close_activity, R.anim.right_in_close_activity)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.left_out_close_activity, R.anim.right_in_close_activity)
        super.onBackPressed()
    }

}