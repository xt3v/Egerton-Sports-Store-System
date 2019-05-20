let registerFieldTemplate = `
<div id="add-field-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Register Coach</h4>
</div>
<div class="card-body">
   <form id="registerFieldForm" data-parsley-validate="">
       <div class="form-group">
          <label for="name">Name</label>
          <input class="form-control" type="text" name="name" id="name" required="true">
       </div>
       <div class="form-group">
          <label for="sport">Sport : </label>
          <select class="form-control" name="sportSelect" id="sportSelect">

          </select>
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
#add-field-wrapper{
  padding: 30px;
  border-radius: 5%;
  width: 60%;
  height: 350px;
}
</style>`


function setupRegisterFieldTemplate(){
      //get sports to populate entry 
   axios.get("/sports")
   .then((res)=>{
       let sports = res.data

       //populate the select
       let select = document.querySelector("#sportSelect")
       let html = ""
       sports.forEach(sport => {
           html += `<option value="${sport.sportId}">${sport.name}</option>`
       });

       select.innerHTML = html
    })
    $(document).ready(function() {
        $("#registerFieldForm").on('submit', function(e){

            e.preventDefault();
            var form = $(this);

            form.parsley().validate();

            if (form.parsley().isValid()){
                //show alert
                Swal.fire({
                    text: `Add field ${$("#name").val()}`,
                    type: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes'
                  }).then((result) => {
                    if (result.value) {
                        //send post request
                        axios.post(`/fields`,{
                            fieldName : $("#name").val(),
                            sportId : $("#sportSelect").val(),
                            fieldId : -1
                        })
                        .then((rs)=>{
                            Swal.fire({
                                position: 'center',
                                type: 'success',
                                title: 'Field Added',
                                showConfirmButton: false,
                                timer: 1500
                              })
                             $("#name").val("")
                            
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