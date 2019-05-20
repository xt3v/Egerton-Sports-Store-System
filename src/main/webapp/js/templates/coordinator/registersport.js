let registerSportTemplate = `
<div id="add-sport-wrapper" class="card">
<div class="card-head">
    <h4 class="text-center">Register Sport</h4>
</div>
<div class="card-body">
  <form id="registerSportsForm" data-parsley-validate=""> 
      <div class="form-group">
          <label for="name">Name</label>
          <input class="form-control" type="text" name="name" id="name" required="true">        
      </div>
    
      <div>
          <button id="registerSportBtn" class="btn btn-success">
            Save
          </button>
      </div>
  </form>
</div>
</div>
</div>
<style>
    #add-sport-wrapper{
        padding: 30px;
        border-radius: 5%;
        width: 60%;
        height:300px;
    }
</style>`


function setupRegisterSportTemplate(){
 
    //activate validation
    $(document).ready(function() {
        $("#registerSportsForm").on('submit', function(e){
            e.preventDefault();
            var form = $(this);

            form.parsley().validate();

            if (form.parsley().isValid()){
                //show alert
                Swal.fire({
                    text: `Add sport ${$("#name").val()}`,
                    type: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes'
                  }).then((result) => {
                    if (result.value) {
                        //send post request
                        axios.post(`/sports`,{
                            name : $("#name").val()
                        })
                        .then((rs)=>{
                            Swal.fire({
                                position: 'center',
                                type: 'success',
                                title: 'Sport Added',
                                showConfirmButton: false,
                                timer: 1500
                              })
                              document.querySelector("#name").value = ""
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