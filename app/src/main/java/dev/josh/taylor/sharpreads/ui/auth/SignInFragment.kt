package dev.josh.taylor.sharpreads.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.josh.taylor.sharpreads.R
import dev.josh.taylor.sharpreads.SharpReadsApplication
import dev.josh.taylor.sharpreads.di.component.SignInComponent
import kotlinx.android.synthetic.main.sign_in_fragment.*
import javax.inject.Inject

class SignInFragment @Inject constructor() : Fragment() {

    private val viewModel: SignInViewModel by viewModels()

    lateinit var signInComponent: SignInComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)

        signInComponent = (context.applicationContext as SharpReadsApplication).appComponent
            .signInComponent()
            .create()

        signInComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.message.observe(viewLifecycleOwner, Observer { signInMessage.setText(it) })
    }
}
