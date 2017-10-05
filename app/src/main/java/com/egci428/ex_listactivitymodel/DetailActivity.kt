package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import android.view.Menu
import java.text.NumberFormat


class DetailActivity : AppCompatActivity() {
    protected var data: ArrayList<Course>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = DataProvider.getData()
        val bundle = intent.extras
        val position = bundle!!.getInt("Position",0)
        val index = data!!.get(position)
        titleText.setText(index.title)
        descriptionText.setText(index.description)
        courseNoText.setText(index.courseNumber.toString())
        creditsText.setText(index.credits.toString())


        val impos = position.toInt()%3 +1
        val res = resources.getIdentifier("image1010" + impos,"drawable",packageName)
        imageCourse.setImageResource(res)





    }
}
