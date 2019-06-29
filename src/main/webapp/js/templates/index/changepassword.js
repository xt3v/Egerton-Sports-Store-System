let changeUserPasswordTemplate = `
  <div class="row justify-content-center">
     <div class="col-md-8">
         <form id="changepasswordform" >
            <div class="form-group">
                <label for="oldpassword">Current password</label>
                <input type="password" class="form-control" id="oldpassword" minlength="8" placeholder="password" required="true">
                <small class="form-text text-muted">Enter your current password !</small>
            </div>
            <div class="form-group">
                <label for="newpassword">New Password</label>
                <input type="password" class="form-control" id="newpassword" parsley-trigger="change" data-parsley-minlength="8" placeholder="Enter new password" required="true">
            </div>
            <div class="form-group">
                <label for="confirmpassword">Confirm New Password</label>
                <input type="password" class="form-control" data-parsley-equalto="#newpassword" parsley-trigger="change" id="confirmpassword" data-parsley-minlength="8" placeholder="Confirm new password" required="true">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
         </form>
     </div>
  </div>
`

function setupChangeUserPasswordTemplate(){
    $("#changepasswordform").parsley();
    $("#changepasswordform").on('submit',function(e){
        e.preventDefault()
        var form = $(this)
        
        //TODO regex for reg no
        if (form.parsley().isValid()){
            var value = getCookie("egersportsession")
            axios.put("/password",{
                session : value,
                oldpassword : $('#oldpassword').val(),
                newpassword : $("#newpassword").val(),
            })
            .then(data=>{
                $("#modal").modal("hide")
                Swal.fire({
                    position: 'center',
                    type: 'success',
                    title: 'Password Changed',
                    showConfirmButton: false,
                    timer: 1500
                  })

            })
            .catch(err=>{
                console.log(JSON.stringify(err))
                let message = err.response.data.message
                Swal.fire({
                    position: 'center',
                    type: 'error',
                    title: message,
                    showConfirmButton: false,
                    timer: 1500
                  })
            })
        }
    })

}