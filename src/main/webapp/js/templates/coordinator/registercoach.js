let registerCoachTemplate = `
<div id="add-team-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Register Coach</h4>
</div>
<div class="card-body">
   <form id="registerCoachForm" data-parsley-validate="">
       <div class="form-group">
          <label for="name">Name</label>
          <input class="form-control" type="text" name="name" id="name" required="true">
       </div>

       <div class="form-group">
          <label for="employeeId">EmployeeId</label>
          <input  class="form-control" type="text" id="employeeId" required="true">
       </div>       

       <div>
          <button class="btn btn-success">
             Save
          </button>
       </div>
   </form>
</div>
</div>

  </div>
  <style>
#add-team-wrapper{
  padding: 30px;
  border-radius: 5%;
  width: 60%;
  height: 350px;
}
</style>
`

function setupRegisterCoachTemplate(){

      $(document).ready(function() {
          $("#registerCoachForm").on('submit', function(e){
  
              e.preventDefault();
              var form = $(this);
  
              form.parsley().validate();
  
              if (form.parsley().isValid()){
                  //show alert
                  Swal.fire({
                      text: `Add Coach ${$("#name").val()}`,
                      type: 'info',
                      showCancelButton: true,
                      confirmButtonColor: '#3085d6',
                      cancelButtonColor: '#d33',
                      confirmButtonText: 'Yes'
                    }).then((result) => {
                      if (result.value) {
                          //send post request
                          axios.post(`/coaches`,{
                              name : $("#name").val(),
                              employeeId : $("#employeeId").val()
                          })
                          .then((rs)=>{

                              Swal.fire({
                                  position: 'center',
                                  type: 'success',
                                  title: 'Coach Added',
                                  showConfirmButton: false,
                                  timer: 1500
                                })
                               $("#name").val("")
                               $("#employeeId").val("")
                              
                          })
                          .catch((err)=>{
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
          });
      });
}