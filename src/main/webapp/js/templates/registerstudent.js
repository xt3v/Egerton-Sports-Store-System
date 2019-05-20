let registerStudentTemplate=`<div id="add-item-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Register Student</h4>
</div>
<div class="card-body">
   <form id="registerStudentForm" data-parsley-validate="">
       <div class="form-group">
          <label for="name">Name</label>
          <input class="form-control" type="text" name="name" id="name" required="true">
          
       </div>
       <div class="form-group">
             <label for="regno">RegNo.</label>
             <input class="form-control" type="text" name="regno" id="regno" required="true">
       </div>
       <div class="form-group">
             <label for="regno">Residence</label>
             <input class="form-control" type="text" name="residence" id="residence" required="true">
       </div>
       
     <div class="form-group">
      <label for="phoneNo">Phone No.</label>
      <input class="form-control" type="text" name="phoneNo" id="phoneNo" required="true">
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
#add-item-wrapper{
  padding: 30px;
  border-radius: 5%;
  width: 60%;
  height: 540px;
}
</style>
`


function setupRegisterStudentTemplate(){

  //activate validation
  $(document).ready(function() {
    $("#registerStudentForm").on('submit', function(e){
        e.preventDefault();
        var form = $(this);

        form.parsley().validate();

        if (form.parsley().isValid()){
            //show alert
            Swal.fire({
                text: `Add Student ${$("#name").val()}`,
                type: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes'
              }).then((result) => {
                if (result.value) {
                   
                    //send post request
                    axios.post(`/students`,{
                        name : $("#name").val(),
                        regNo : $("#regno").val(),
                        residence : $("#residence").val(),
                        phoneNo : $("#phoneNo").val(),
                    })
                    .then((rs)=>{
                        Swal.fire({
                            position: 'center',
                            type: 'success',
                            title: 'Student added',
                            showConfirmButton: false,
                            timer: 1500
                          })
                          $("#name").val('')
                          $("#regno").val('')
                          $("#residence").val('')
                          $("#phoneNo").val('')
                    })
                    .catch((err)=>{
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
