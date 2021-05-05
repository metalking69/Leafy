package com.example.leafy.Utilities

import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Spinner

interface ShowHideInterface {
    fun Hide5(v1: SearchView, v2: Spinner, v3: ImageButton, v4: ImageButton, v5: ImageButton){

        v1?.visibility= View.INVISIBLE
        v1?.isEnabled = false
        v1?.isClickable = false

        v2?.visibility= View.INVISIBLE
        v2?.isEnabled = false
        v2?.isClickable = false

        v3?.visibility= View.INVISIBLE
        v3?.isEnabled = false
        v3?.isClickable = false

        v4?.visibility= View.INVISIBLE
        v4?.isEnabled = false
        v4?.isClickable = false

        v5?.visibility= View.INVISIBLE
        v5?.isEnabled = false
        v5?.isClickable = false

    }

    fun showFilterShowSearch(v1: SearchView, v2: Spinner, v3: ImageButton, v4: ImageButton, v5: ImageButton){

        v1?.visibility= View.VISIBLE
        v1?.isEnabled = true
        v1?.isClickable = true

        v2?.visibility= View.VISIBLE
        v2?.isEnabled = true
        v2?.isClickable = true

        v3?.visibility= View.INVISIBLE
        v3?.isEnabled = false
        v3?.isClickable = false

        v4?.visibility= View.INVISIBLE
        v4?.isEnabled = false
        v4?.isClickable = false

        v5?.visibility= View.INVISIBLE
        v5?.isEnabled = false
        v5?.isClickable = false

    }

    fun showAdd(v1: SearchView, v2: Spinner, v3: ImageButton, v4: ImageButton, v5: ImageButton){

        v1?.visibility= View.INVISIBLE
        v1?.isEnabled = false
        v1?.isClickable = false

        v2?.visibility= View.INVISIBLE
        v2?.isEnabled = false
        v2?.isClickable = false

        v3?.visibility= View.VISIBLE
        v3?.isEnabled = true
        v3?.isClickable = true

        v4?.visibility= View.INVISIBLE
        v4?.isEnabled = false
        v4?.isClickable = false

        v5?.visibility= View.INVISIBLE
        v5?.isEnabled = false
        v5?.isClickable = false

    }

    fun showFilter(v1: SearchView, v2: Spinner, v3: ImageButton, v4: ImageButton, v5: ImageButton){

        v1?.visibility= View.VISIBLE
        v1?.isEnabled = true
        v1?.isClickable = true

        v2?.visibility= View.INVISIBLE
        v2?.isEnabled = false
        v2?.isClickable = false

        v3?.visibility= View.INVISIBLE
        v3?.isEnabled = false
        v3?.isClickable = false

        v4?.visibility= View.INVISIBLE
        v4?.isEnabled = false
        v4?.isClickable = false

        v5?.visibility= View.INVISIBLE
        v5?.isEnabled = false
        v5?.isClickable = false

    }

    fun showSave(v1: SearchView, v2: Spinner, v3: ImageButton, v4: ImageButton, v5: ImageButton){

        v1?.visibility= View.INVISIBLE
        v1?.isEnabled = false
        v1?.isClickable = false

        v2?.visibility= View.INVISIBLE
        v2?.isEnabled = false
        v2?.isClickable = false

        v3?.visibility= View.VISIBLE
        v3?.isEnabled = true
        v3?.isClickable = true

        v4?.visibility= View.INVISIBLE
        v4?.isEnabled = false
        v4?.isClickable = false

        v5?.visibility= View.INVISIBLE
        v5?.isEnabled = false
        v5?.isClickable = false

    }


}