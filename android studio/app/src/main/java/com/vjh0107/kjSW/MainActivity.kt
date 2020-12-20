package com.vjh0107.kjSW

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val mFirebaseDatabase = FirebaseDatabase.getInstance()

    private lateinit var txtEmail: TextView
    private lateinit var txtName: TextView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView

    var selectedReport: Report? = null

    var reportInfoText: TextView? = null
    var ivImage: ImageView? = null
    var etContent: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)

        txtEmail = headerView.findViewById(R.id.txtEmail)
        txtName = headerView.findViewById(R.id.txtName)
        txtEmail.text = "vjh0107@naver.com"
        txtName.text = "Park Jun Hyung"


        reportInfoText = findViewById(R.id.report_info)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.remove_report)
        fab.setOnClickListener { view ->
            removeReport()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        displayReports()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun displayReports() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                navigationView.menu.clear()
                dataSnapshot.children.forEach { time ->
                    val map = (time.value as Map<String, Any?>)
                    val json = JSONObject(map)
                    displayReportList(Report(json.getString("value"), time.key!!,json.getString("name"),  ))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        mFirebaseDatabase.getReference("reports/").addValueEventListener(postListener)
    }
    private fun displayReportList(report: Report) {
        val leftMenu: Menu = navigationView.menu

        val item: MenuItem = leftMenu.add(report.time)
        val view = View(application)
        item.setOnMenuItemClickListener {
            selectedReport = report

            ivImage = findViewById(R.id.iv_image)
            etContent = findViewById(R.id.report_info)

            val imageUrl = "https://i.imgur.com/${report.imageLink}.jpeg"
            Glide
                    .with(this)
                    .load(imageUrl)
                    .into(ivImage!!);


            etContent!!.text = report.name
//            val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
//            drawer.closeDrawer(GravityCompat.START)
            true
        }
        view.tag = report
        item.actionView = view
    }

    private fun removeReport() {
        val targetReport = selectedReport ?: return
        val textView = reportInfoText ?: return
        mFirebaseDatabase
                .getReference("reports/${targetReport.time}")
                //.push() 오류 있는 구문으로 사용하지 말 것
                .removeValue()
                .addOnSuccessListener {
                    if (etContent != null) etContent!!.text = null
                    if (ivImage != null) ivImage!!.setImageBitmap(null)
                    Snackbar
                            .make(textView, "${targetReport.time}" + " 경 신고가 처리되었습니다.", Snackbar.LENGTH_LONG)
                            .show()
                }
    }
}