package com.challenge.kotlin.ravn.activity.person

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.challenge.kotlin.ravn.MainApplication
import com.challenge.kotlin.ravn.PersonDetailsQuery
import com.challenge.kotlin.ravn.R
import com.challenge.kotlin.ravn.activity.util.StringUtil

class PersonActivity : AppCompatActivity() {

    var application: MainApplication? = null

    val TAG = PersonActivity::class.java.simpleName
    val ARG_PERSON_ID = "arg_person_id"

    var content: ViewGroup? = null
    var progressBar: ProgressBar? = null

    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String>? = null

    var tv_eye_color: TextView? = null
    var tv_hair_color: TextView? = null
    var tv_skin_color: TextView? = null
    var tv_birth_year: TextView? = null

    lateinit var personId: String

    fun newIntent(context: Context?, personId: String?): Intent? {
        val intent = Intent(context, PersonDetailsActivity::class.java)
        intent.putExtra(ARG_PERSON_ID, personId)
        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        personId = getIntent().getStringExtra(ARG_PERSON_ID)
        application = getApplication() as MainApplication?
        content = findViewById<View>(R.id.content_holder) as ViewGroup?
        progressBar =
            findViewById<View>(R.id.loading_bar) as ProgressBar?

        expandableListView = findViewById(R.id.expandableListView)
        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = PersonExpandableAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->


            }
        }

        progressBar!!.visibility = View.GONE

        /*
        tv_eye_color = findViewById<View>(R.id.tv_eye_color) as TextView?
        tv_hair_color = findViewById<View>(R.id.tv_hair_color) as TextView?
        tv_skin_color = findViewById<View>(R.id.tv_skin_color) as TextView?
        tv_birth_year = findViewById<View>(R.id.tv_birth_year) as TextView?*/

        //fetchPersonDetailsDetailsByID(personId)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    open fun setEntryData(data: PersonDetailsQuery.Data?) {
        content!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
        val entry = data!!.Person()
        if (entry != null) {
            setTitle(entry.name())
            tv_eye_color!!.text = StringUtil.formatGraphQLQueryText(
                entry.eyeColor().toString()
            )
            tv_skin_color!!.text = StringUtil.formatGraphQLQueryText(
                entry.skinColor().toString()
            )
            tv_hair_color!!.text = StringUtil.formatGraphQLQueryText(
                entry.hairColor().toString()
            )
            tv_birth_year!!.text = entry.birthYear()
        }
    }

    open fun fetchPersonDetailsDetailsByID(personID: String) { //PersonDetailsQuery personDetailsQuery = PersonDetailsQuery.builder().personId(personID).build();
        application!!.apolloClient()
            ?.query(
                PersonDetailsQuery(personID)
            )
            ?.enqueue(object : ApolloCall.Callback<PersonDetailsQuery.Data?>() {
                override fun onResponse(response: Response<PersonDetailsQuery.Data?>) {
                    runOnUiThread(Runnable {
                        setEntryData(response.data())
                        progressBar!!.visibility = View.GONE
                        content!!.visibility = View.VISIBLE
                    })
                }

                override fun onFailure(e: ApolloException) {
                    Log.e(TAG, e.message, e)
                }
            })
    }

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val redmiMobiles = ArrayList<String>()
            redmiMobiles.add("F1#|#F1")
            redmiMobiles.add("F2#|#F2")
            redmiMobiles.add("F3#|#F3")

            val micromaxMobiles = ArrayList<String>()
            micromaxMobiles.add("F5")
            micromaxMobiles.add("F6")
            micromaxMobiles.add("F7")


            listData["Redmi"] = redmiMobiles
            listData["Micromax"] = micromaxMobiles


            return listData
        }


}