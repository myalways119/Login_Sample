package uz.uchqun.login_sample.core.util
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import uz.uchqun.login_sample.R
import java.util.HashMap

class ExpandableListViewAdapter(
    private val mContext: Context, // group titles
    private val listDataGroup: List<String>,
    // child data
    private val listDataChild: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {


    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return listDataChild[listDataGroup[groupPosition]]!![childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val childText = getChild(groupPosition, childPosition) as String

        if (convertView == null) {
            val layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_row_child, null)
        }


        val txtListChild = convertView!!.findViewById(R.id.textViewChild) as TextView
        txtListChild.text = childText

        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listDataChild[listDataGroup[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listDataGroup[groupPosition]
    }

    override fun getGroupCount(): Int {
        return listDataGroup.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_row_group, null)
        }

        val textViewGroup = convertView?.findViewById<TextView>(R.id.textViewGroup)
        textViewGroup?.setTypeface(null, Typeface.BOLD)
        textViewGroup?.text = headerTitle

        return convertView!!
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}