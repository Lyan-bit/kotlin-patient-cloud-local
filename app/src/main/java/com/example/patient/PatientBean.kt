package com.example.patient

import android.content.Context

class PatientBean(c: Context) {

    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var patientId = ""
    private var name = ""
    private var appointmentId = ""

    private var errors = ArrayList<String>()
    private var checkParameter = "is not exist"

    fun setPatientId(patientIdx: String) {
	 patientId = patientIdx
    }
    
    fun setName(namex: String) {
	 name = namex
    }
    
    fun setAppointmentId(appointmentIdx: String) {
	 appointmentId = appointmentIdx
    }
    
    fun resetData() {
	  patientId = ""
	  name = ""
	  appointmentId = ""
    }
    
    fun isCreatePatientError(): Boolean {
	        
	        errors.clear()
	        
          if (patientId != "") {
	//validate
}
	         else {
	               errors.add("patientId cannot be empty")
	         }
	          if (name != "") {
	//validate
}
	         else {
	               errors.add("name cannot be empty")
	         }
	          if (appointmentId != "") {
	//validate
}
	         else {
	               errors.add("appointmentId cannot be empty")
	         }
	
	        return errors.isNotEmpty()
	    }
	    
	    fun createPatient() {
	        model.createPatient(PatientVO(patientId, name, appointmentId))
	        resetData()
	    }
	   
     fun editPatient() {
		     model.editPatient(PatientVO(patientId, name, appointmentId))
		     resetData()
		 }
		       
		 fun isEditPatientError(allPatientpatientIds: List<String>): Boolean {
       
       errors.clear()
			
			if (!allPatientpatientIds.contains(patientId)) {
				errors.add("patientId" + checkParameter)
		    }
          if (patientId != "") {
	//validate
}
	         else {
	               errors.add("patientId cannot be empty")
	         }
          if (name != "") {
	//validate
}
	         else {
	               errors.add("name cannot be empty")
	         }
          if (appointmentId != "") {
	//validate
}
	         else {
	               errors.add("appointmentId cannot be empty")
	         }

       return errors.isNotEmpty()
   }
       
   fun deletePatient() {
       model.deletePatient(patientId)
       resetData()
   }
   
   fun isDeletePatientError(allPatientpatientIds: List<String>): Boolean {
        errors.clear()
			 if (!allPatientpatientIds.contains(patientId)) {
			    errors.add("patientId" + checkParameter)
        }
        return errors.isNotEmpty()
		}    

		fun isSearchPatientIdError(allPatientIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allPatientIds.contains(patientId)) {
    	       errors.add("patientId" + checkParameter)
    	   }
           return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }



}

