$(document).ready(function () {
    $('#registrationForm').validate({
        rules: {
            firstName: {
                required: true,
                minlength: 2
            },
            lastName: {
                required: true,
                minlength: 2
            },
            phoneNumber: {
                required: true,
                pattern: /^\+\d{3}-\d{2}-\d{3}-\d{3}$/
            },
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            passwordRepeat: {
                required: true,
                equalTo: "#password"
            },
            birthDate: {
                required: true
            },
            cvPdf: {
                required: true,
                extension: "pdf"
            },
            image: {
                required: true,
                accept: "image/*"
            }
        },
        messages: {
            firstName: {
                required: "Please enter your first name",
                minlength: "Your first name must be at least 2 characters long"
            },
            lastName: {
                required: "Please enter your last name",
                minlength: "Your last name must be at least 2 characters long"
            },
            phoneNumber: {
                required: "Please enter your mobile number",
                pattern: "Please enter a valid mobile number in the format +XXX-XX-XXX-XXX"
            },
            email: {
                required: "Please enter your email address",
                email: "Please enter a valid email address"
            },
            password: {
                required: "Please enter a password",
                minlength: "Your password must be at least 6 characters long"
            },
            passwordRepeat: {
                required: "Please repeat your password",
                equalTo: "Passwords do not match"
            },
            birthDate: {
                required: "Please enter your date of birth"
            },
            cvPdf: {
                required: "Please upload your CV",
                extension: "Please upload a PDF file"
            },
            image: {
                required: "Please upload your photo",
                accept: "Please upload an image file"
            }
        },
        errorPlacement: function (error, element) {
            // Display error message below the input field
            error.appendTo(element.siblings('.error'));
        }
    });
});