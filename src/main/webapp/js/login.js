var signUpForm = document.getElementById("signUpForm")
var loginForm = document.getElementById("loginForm")

document.getElementById("signUpFormSet").onclick = ()=>{
   loginForm.style.display = "none"
   signUpForm.style.display = "block";
}

document.getElementById("loginFormSet").onclick = ( )=>{
    loginForm.style.display = "block";
    signUpForm.style.display = "none";
}

$(document).ready(function(){
    $("#signUpFormR").parsley();
    $("#loginFormR").parsley();
})

$("#signUpFormR").on('submit',function(e){
    e.preventDefault()
    var form = $(this)
    //TODO regex for reg no

    if (form.parsley().isValid()){
        axios.post("/students",{
            name : $("#name").val(),
            regNo : $("#regNo").val(),
            residence : $("#residence").val(),
            phoneNo : $("#phoneNo").val(),
            password : $("#password").val()
        })
        .then(data=>{
            Swal.fire({
                position: 'center',
                type : 'info',
                title : `Registered !`,
                showConfirmButton: false,
                timer: 1500
            })
            $("#name").val("")
            $("#regNo").val("")
            $("#residence").val("")
            $("#phoneNo").val("");
            $("#password").val("")
            loginForm.style.display = "block"
            signUpForm.style.display = "none"
        })
        .catch(err=>{
            console.log(err)
            let message = err.response.data.message
            $("#signUpMessage").html(message)
            
        });
    }
})

