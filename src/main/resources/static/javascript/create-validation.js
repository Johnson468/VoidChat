// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "createform"
  $("form[name='createform']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      maxMembers: {
    	  min: 2,
    	  max: 20,
    	  required: true,
    	  digits: true
      }
    },
    // Specify validation error messages
    messages: {
      roomId: "Please enter a room Id"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});