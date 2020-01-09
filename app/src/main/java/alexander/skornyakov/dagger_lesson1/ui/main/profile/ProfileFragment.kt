package alexander.skornyakov.dagger_lesson1.ui.main.profile

import alexander.skornyakov.dagger_lesson1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment

class ProfileFragment:DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Toast.makeText(activity,"Profile Fragment",Toast.LENGTH_LONG).show()


        return inflater.inflate(R.layout.fragment_profile,container,false)
    }
}