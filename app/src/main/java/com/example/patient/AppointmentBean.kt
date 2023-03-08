package com.example.patient

import android.content.Context

class AppointmentBean(c: Context) {

    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var appointmentId = ""
    private var code = ""
    private var patientId = ""

    private var errors = ArrayList<String>()
    private var checkParameter = "is not exist"

    fun setAppointmentId(appointmentIdx: String) {
	 appointmentId = appointmentIdx
    }
    
    fun setCode(codex: String) {
	 code = codex
    }
    

    fun setPatientId(patientIdx : String) {
	patientId = patientIdx
    }

    fun resetData() {
	  appointmentId = ""
	  code = ""
    }
    
    fun isCreateAppointmentError(): Boolean {
	        
	 errors.clear()
	        
          if (appointmentId != "") {
	//validate
}
	else {
	 	  errors.add("appointmentId cannot be empty")
	}
          if (code != "") {
	//validate
}
	else {
	 	  errors.add("code cannot be empty")
	}

	        return errors.size > 0
	    }
	    
	    fun createAppointment() {
	        model.createAppointment(AppointmentVO(appointmentId, code))
	        resetData()
	    }

    fun isListAppointmentError(): Boolean {
	        errors.clear()
		//if statement
	        return errors.size > 0
	    }

     fun editAppointment() {
		     model.editAppointment(AppointmentVO(appointmentId, code))
		     resetData()
		 }
		       
		 fun isEditAppointmentError(allAppointmentappointmentIds: List<String>): Boolean {
	        
	        errors.clear()
			
			if (!allAppointmentappointmentIds.contains(appointmentId)) {
				errors.add("appointmentId" + checkParameter)
		    }
			          if (appointmentId != "") {
	        	//validate
	        }
	         else {
	               errors.add("appointmentId cannot be empty")
	         }
	                  if (code != "") {
	        	//validate
	        }
	         else {
	               errors.add("code cannot be empty")
	         }
	        
       return errors.size > 0
   }

	    fun deleteAppointment() {
	        model.deleteAppointment(appointmentId)
	        resetData()
	    }
	    
	    fun isDeleteAppointmentError(allAppointmentappointmentIds: List<String>): Boolean {
	         errors.clear()
			 if (!allAppointmentappointmentIds.contains(appointmentId)) {
			    errors.add("appointmentId" + checkParameter)
	         }
	         return errors.size > 0
		}    


		fun isSearchAppointmentIdError(allAppointmentIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allAppointmentIds.contains(appointmentId)) {
    	       errors.add("appointmentId" + checkParameter)
    	   }
           return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

   fun isAddPatientattendsAppointmentError(): Boolean {
        errors.clear()
        return errors.size > 0
    }

    fun addPatientattendsAppointment() {
         model.addPatientattendsAppointment(appointmentId, patientId)
         resetData()
    }
    
   fun isRemovePatientattendsAppointmentError(): Boolean {
        errors.clear()
        return errors.size > 0
    }

    fun removePatientattendsAppointment() {
         model.removePatientattendsAppointment(appointmentId, patientId)
         resetData()
    }
    


}

