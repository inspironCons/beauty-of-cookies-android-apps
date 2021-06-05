package com.submision.myfavcookieseid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.submision.myfavcookieseid.adapter.CookiesAdapter
import com.submision.myfavcookieseid.data.Cookies
import com.submision.myfavcookieseid.data.CookiesData

class MainActivity : AppCompatActivity() {
    private lateinit var listCookie:RecyclerView
    private var CookiesList:ArrayList<Cookies> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Eid Cookies"
        listCookie = findViewById(R.id.rv_items)
        CookiesList.addAll(CookiesData.listCookies)
        showRecycleView()
    }

    private fun showRecycleView() {
        listCookie.layoutManager = LinearLayoutManager(this)
        val list = CookiesAdapter(CookiesList)
        listCookie.adapter = list

        list.setOnItemClickCallback(object : CookiesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Cookies) {
             navigateToDetail(data)
            }
        })
    }

    private fun navigateToDetail(data: Cookies) {
        var intent:Intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("title",data.title)
        intent.putExtra("desc",data.desc)
        intent.putExtra("img",data.img)
        intent.putExtra("bahan",data.bahan)
        intent.putExtra("cara",data.cara)
        intent.putExtra("sumber",data.sumber)
        intent.putExtra("score",data.score)

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu,menu)
        return super.onCreateOptionsMenu(menu)
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
            }
        }
    }
}