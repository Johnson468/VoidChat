
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "messageform"
  $("form[name='messageform']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      contents: {
    	  required:true,
    	  minlength:2
      }
    },
    // Specify validation error messages
    messages: {
      contents: "Please enter a valid message"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});