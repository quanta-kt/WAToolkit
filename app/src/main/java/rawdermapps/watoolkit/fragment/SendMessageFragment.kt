package rawdermapps.watoolkit.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.frag_send_message.*
import kotlinx.android.synthetic.main.frag_send_message.view.*
import kotlinx.android.synthetic.main.frag_send_message.view.edit_phone
import rawdermapps.watoolkit.R
import java.lang.Exception

class SendMessageFragment : Fragment() {

    lateinit var sendButton : Button
    lateinit var editPhone : EditText
    lateinit var editMessage : EditText
    lateinit var ccp : CountryCodePicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.frag_send_message, container, false).also {
            sendButton = it.button_send
            editPhone = it.edit_phone
            editMessage = it.edit_message
            ccp = it.ccp

            ccp.registerCarrierNumberEditText(editPhone)

            sendButton.setOnClickListener {onClickSend()}
        }

    private fun onClickSend() {
        if (!ccp.isValidFullNumber) {
            Toast.makeText(context, "Invalid phone number!", Toast.LENGTH_SHORT).show()
            return
        }

        val phone = ccp.fullNumberWithPlus
        val message = editMessage.text.toString()

        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("http://api.whatsapp.com/send?phone=$phone&text=$message")
            startActivity(this)
        }
    }
}