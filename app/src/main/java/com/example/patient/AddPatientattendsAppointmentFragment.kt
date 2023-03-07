package com.example.patient

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class AddPatientattendsAppointmentFragment : Fragment() , View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var root: View
    private lateinit var myContext: Context
    private lateinit var model: ModelFacade
    
    private lateinit var appointmentBean: AppointmentBean

    private lateinit var patientIdSpinner: Spinner
    private lateinit var appointmentIdSpinner: Spinner

    private var allPatientpatientIds: List<String> = ArrayList()
    private var allAppointmentappointmentIds: List<String> = ArrayList()

    private lateinit var patientIdTextField: EditText
    private var patientIdData = ""
    private lateinit var appointmentIdTextField: EditText
    private var appointmentIdData = ""
    
    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    companion object {
        fun newInstance(c: Context): AddPatientattendsAppointmentFragment {
            val fragment = AddPatientattendsAppointmentFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        root = inflater.inflate(R.layout.addpatientattendsappointment_layout, container, false)
        super.onViewCreated(root, savedInstanceState)
        return root
    }

    override fun onResume() {
        super.onResume()
        model = ModelFacade.getInstance(myContext)
		Log.i("model",model.listPatient().toString())
		Log.i("model",model.allPatientPatientIds().toString())
        
        allPatientpatientIds = model.allPatientPatientIds()
        patientIdTextField = root.findViewById<View>(R.id.addPatientattendsAppointmentpatientIdField) as EditText
        patientIdSpinner = root.findViewById<View>(R.id.addPatientattendsAppointmentpatientIdSpinner) as Spinner
        val patientIdAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, allPatientpatientIds)
        patientIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        patientIdSpinner.setAdapter(patientIdAdapter)
        patientIdSpinner.setOnItemSelectedListener(this)
       
		
		allAppointmentappointmentIds = model.allAppointmentAppointmentIds()
        appointmentIdTextField = root.findViewById<View>(R.id.addPatientattendsAppointmentappointmentIdField) as EditText
        appointmentIdSpinner = root.findViewById<View>(R.id.addPatientattendsAppointmentappointmentIdSpinner) as Spinner
        val appointmentIdAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, allAppointmentappointmentIds)
        appointmentIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        appointmentIdSpinner.setAdapter(appointmentIdAdapter)
        appointmentIdSpinner.setOnItemSelectedListener(this)
		
        appointmentBean = AppointmentBean(myContext)

        okButton = root.findViewById(R.id.addPatientattendsAppointmentOK)
        okButton.setOnClickListener(this)
        cancelButton = root.findViewById(R.id.addPatientattendsAppointmentCancel)
        cancelButton.setOnClickListener(this)
    }

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
        if (parent === patientIdSpinner) {
            patientIdTextField.setText(allPatientpatientIds.get(position))
        }
        if (parent ==appointmentIdSpinner) {
            appointmentIdTextField.setText(allAppointmentappointmentIds.get(position))
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    	//onNothingSelected
    }

    override fun onClick(v: View?) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(v?.windowToken, 0)
        } catch (e: Exception) {
        	 e.message
        }

        when (v?.id) {
            R.id.addPatientattendsAppointmentOK -> {
                addPatientattendsAppointmentOK()
            }
            R.id.addPatientattendsAppointmentCancel -> {
                addPatientattendsAppointmentCancel()
            }
        }
    }

    private fun addPatientattendsAppointmentOK() {
        patientIdData = patientIdTextField.getText().toString() + ""
        appointmentBean.setPatientId(patientIdData)
        appointmentIdData = appointmentIdTextField.getText().toString() + ""
        appointmentBean.setAppointmentId(appointmentIdData)
        if (appointmentBean.isAddPatientattendsAppointmentError()) {
            Log.w(javaClass.name, appointmentBean.errors())
            Toast.makeText(myContext, "Errors: " + appointmentBean.errors(), Toast.LENGTH_LONG).show()
        } else {
            appointmentBean.addPatientattendsAppointment()
            Toast.makeText(myContext, "added!", Toast.LENGTH_LONG).show()
            
        }
    }

    private fun addPatientattendsAppointmentCancel() {
        appointmentBean.resetData()
        patientIdTextField.setText("")
        appointmentIdTextField.setText("")
    }
}
