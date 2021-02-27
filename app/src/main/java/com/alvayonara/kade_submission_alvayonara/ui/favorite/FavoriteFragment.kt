package com.alvayonara.kade_submission_alvayonara.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.FavoriteSectionPageAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.act

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        // init ViewPager TabLayout
        val sectionPageAdapter =
            FavoriteSectionPageAdapter(
                act,
                childFragmentManager
            )
        view_pager.adapter = sectionPageAdapter
        tabs.setupWithViewPager(view_pager)
    }
}
