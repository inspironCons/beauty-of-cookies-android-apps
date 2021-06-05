package com.submision.myfavcookieseid

import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide

class DetailActivity:AppCompatActivity() {
    private  var titles:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val intent:Intent = intent
        getDetail(intent)
        supportActionBar?.title = titles

    }

    private fun getDetail(intent:Intent) {
        val banner:ImageView = findViewById(R.id.banner)
        val title_detail:TextView = findViewById(R.id.tv_title_detail)
        val desc_detail:TextView = findViewById(R.id.tv_desc_detail)
        val bahan:TextView = findViewById(R.id.tv_bahan)
        val cara:TextView = findViewById(R.id.tv_cara)
        val sumber:TextView = findViewById(R.id.tv_sumber)
        val score:RatingBar = findViewById(R.id.rating_detail)


        Glide.with(this)
            .load(intent.getIntExtra("img",0))
            .into(banner)

        titles = intent.getStringExtra("title")!!
        title_detail.text = intent.getStringExtra("title")
        desc_detail.text = HtmlCompat.fromHtml(intent.getStringExtra("desc")!!,HtmlCompat.FROM_HTML_MODE_LEGACY)
        bahan.text = intent.getStringExtra("bahan")
        cara.text = HtmlCompat.fromHtml(intent.getStringExtra("cara")!!,HtmlCompat.FROM_HTML_MODE_LEGACY)
        sumber.text = HtmlCompat.fromHtml(intent.getStringExtra("sumber")!!,HtmlCompat.FROM_HTML_MODE_COMPACT)
        sumber.movementMethod = LinkMovementMethod.getInstance()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            desc_detail.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        //rating bar
        score.rating = intent.getFloatExtra("score",0F)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMenu(id: Int) {
        when(id){
            R.id.account->{
                var intent:Intent = Intent(this,ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}