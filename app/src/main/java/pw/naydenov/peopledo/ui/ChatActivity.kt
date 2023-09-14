package pw.naydenov.peopledo.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pw.naydenov.peopledo.ChatApplication
import pw.naydenov.peopledo.R
import javax.inject.Inject


class ChatActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: ChatViewModel

    private lateinit var messagesRecycler: RecyclerView
    private lateinit var messageField: AppCompatEditText
    private lateinit var messsgeSend: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)
        window.setBackgroundDrawableResource(R.drawable.background_chat)

        ChatApplication.getAppComponent().inject(this)


        findViews()
        initMessagesRecycler()
        subscribeToData()
    }

    private fun findViews() {
        messagesRecycler = findViewById(R.id.messages_recycler)
        messageField = findViewById(R.id.message_text_field)
        messsgeSend = findViewById(R.id.message_send_button)
        messsgeSend.setOnClickListener {
            viewModel.onMessageButtonPressed(messageField.text.toString())
            messageField.setText("")
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

    private fun initMessagesRecycler() {
        messagesRecycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        ).apply { stackFromEnd = true }
        val space = dpToPixel(16.0f).toInt()
        messagesRecycler.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = space
                outRect.right = space
                outRect.bottom = space
                outRect.top = space
            }
        })
    }

    private fun subscribeToData() {
        viewModel.messagesAdapterSource.observe(this) { adapter ->
            messagesRecycler.adapter = adapter
        }
        viewModel.scrollPositionSource.observe(this) { position ->
            messagesRecycler.smoothScrollToPosition(position)
        }
    }

    private fun dpToPixel(dp: Float): Float {
        val metrics = this.resources.displayMetrics
        return dp * (metrics.densityDpi / 160f)
    }

}
