package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.egci428.ex_listactivitymodel.Course
import com.egci428.ex_listactivitymodel.DataProvider
import com.egci428.ex_listactivitymodel.R
import kotlinx.android.synthetic.main.course_item.view.*


class MainActivity : AppCompatActivity() {

    val DETAIL_REQUEST_CODE = 1001
    protected var data: ArrayList<Course>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProvider.getData()
        val courseArrayAdapter = CourseArrayAdapter(this,0, data!!)
        list.setAdapter(courseArrayAdapter)

        list.setOnItemClickListener { adapterView, view, position, id ->
            val course = data!!.get(position)
            displayDetail(course,position)
        }

    }

    private fun displayDetail(course: Course, position: Int) {
        //set up intent start activity

        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("Position",position)
        startActivity(intent)

    }

    private class CourseArrayAdapter(var context: Context, resource: Int, var objects: ArrayList<Course>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //view คือ rowMain
            val view: View
            val course = objects[position]

            if (convertView == null){
                val layoutInflator = LayoutInflater.from(parent!!.context)
                view = layoutInflator.inflate(R.layout.course_item,parent,false)
                val viewHolder = ViewHolder(view.titleText, view.imageCourse)
                view.tag = viewHolder
                // tag ของ xml
            }
            else{
                view = convertView
            }
            val viewHolder = view.tag as ViewHolder
            viewHolder.titleTextView.text = course.title

            val impos = position%3 +1
            val res = context.resources.getIdentifier("image1010" + impos,"drawable",context.packageName)
            viewHolder.imageCourse.setImageResource(res)

            return view
        }

        override fun getCount(): Int {

            return objects.size
        }

        override fun getItem(position: Int): Any {

            return objects[position]
        }

        override fun getItemId(position: Int): Long {

            return position.toLong()
        }
        private class ViewHolder(val titleTextView: TextView, val imageCourse: ImageView)


    }
}
