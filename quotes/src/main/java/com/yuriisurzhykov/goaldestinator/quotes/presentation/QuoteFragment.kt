package com.yuriisurzhykov.goaldestinator.quotes.presentation

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yuriisurzhykov.goaldestinator.core.sl.ViewModelsFactory
import com.yuriisurzhykov.goaldestinator.quotes.R
import com.yuriisurzhykov.goaldestinator.quotes.domain.Quote
import com.yuriisurzhykov.goaldestinator.quotes.sl.QuotesCoreModule

class QuoteFragment : Fragment(R.layout.fragment_quote_preview) {

    private var viewModel: QuoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val module =
            (activity!!.application as QuotesCoreModule).provideQuoteDependencyContainer()
        viewModel =
            ViewModelProvider(activity!!, ViewModelsFactory(module))[QuoteViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quoteTextView = view.findViewById<TextView>(R.id.quote_text)
        quoteTextView.movementMethod = ScrollingMovementMethod()
        val authorView = view.findViewById<TextView>(R.id.author)
        viewModel?.observe(viewLifecycleOwner) {
            it.map(Quote.Mapper.ApplyToView(quoteTextView, authorView))
        }
    }
}
