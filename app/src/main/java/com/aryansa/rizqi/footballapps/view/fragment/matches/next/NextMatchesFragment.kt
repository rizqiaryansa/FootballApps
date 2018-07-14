package com.aryansa.rizqi.footballapps.view.fragment.matches.next


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.model.Event
import com.aryansa.rizqi.footballapps.presenter.NextPresenter
import com.aryansa.rizqi.footballapps.util.gone
import com.aryansa.rizqi.footballapps.util.visible
import com.aryansa.rizqi.footballapps.view.fragment.matches.MatchesAdapter
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NextMatchesFragment : Fragment(), NextView {

    private var schedules: MutableList<Event> = mutableListOf()
    private lateinit var listSchedules: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: MatchesAdapter
    private lateinit var presenter: NextPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        initAdapter()

        swipeRefresh.onRefresh {
            presenter.getEventList(getString(R.string.resource_eventsnextleague))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(8)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            listSchedules = recyclerView {
                                id = R.id.rvNextEvent
                                layoutManager = LinearLayoutManager(ctx)
                            }.lparams(width = matchParent, height = matchParent) {
                                centerInParent()
                                below(R.id.spinner_matches)
                            }

                            progressBar = progressBar {
                                id = R.id.pbNextEvent
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                }
            }
        }.view
    }


    companion object {
        fun nextIntance(): NextMatchesFragment = NextMatchesFragment()
    }


    private fun initAdapter() {

        //recyclerview
        adapter = MatchesAdapter(schedules)
        listSchedules.adapter = adapter

        presenter = NextPresenter(this, ApiRequest(), Gson())
        presenter.getEventList(getString(R.string.resource_eventsnextleague))
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showEventList(data: List<Event>?) {
        swipeRefresh.isRefreshing = false
        schedules.clear()
        if(data != null) schedules.addAll(data)
        else errorMessage(getString(R.string.next_event_not_found))
        if (schedules.size == 0) errorMessage(getString(R.string.next_event_not_found))
        adapter.notifyDataSetChanged()
    }

    override fun errorMessage(message: String?) {
        view?.let { message?.let { lol -> longSnackbar(it, lol) } }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.backgroundColorResource = R.color.colorWhite
        searchView.setQueryHint(getString(R.string.example_search_matches))
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchEventList(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getSearchEventList(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
