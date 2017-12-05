package apps.amine.bou.readerforselfoss

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import apps.amine.bou.readerforselfoss.api.selfoss.Item
import apps.amine.bou.readerforselfoss.fragments.ArticleFragment
import apps.amine.bou.readerforselfoss.transformers.DepthPageTransformer
import com.ftinc.scoop.Scoop
import kotlinx.android.synthetic.main.activity_reader.*
import me.relex.circleindicator.CircleIndicator

class ReaderActivity : AppCompatActivity() {

    private lateinit var allItems: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Scoop.getInstance().apply(this)
        setContentView(R.layout.activity_reader)

        allItems = intent.getParcelableArrayListExtra<Item>("allItems")
        val currentItem = intent.getIntExtra("currentItem", 0)

        var adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        pager.adapter = adapter
        pager.currentItem = currentItem

        pager.setPageTransformer(true, DepthPageTransformer())
        (indicator as CircleIndicator).setViewPager(pager)
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return allItems.size
        }

        override fun getItem(position: Int): ArticleFragment {
            return ArticleFragment.newInstance(position, allItems)
        }
    }
}
