let registerTeamTemplate = `
<div id="add-team-wrapper" class="card">
<div class="card-head">
     <h4 class="text-center">Register Team</h4>
</div>
<div class="card-body">
   <form class="form form-inline" id="registerTeamForm" data-parsley-validate="">
        <div class="form-group">
          <label for="name">Name : </label>
          <input class="form-control" type="text" id="name" required="true">
        </div>
        <div class="form-group">
          <label for="captainRegNo">Captain RegNo: </label>
          <input class="form-control" type="text" id="captainRegNo" required="true">
        </div>
        <div class="form-group">
          <label for="coachId">Coach Employee Id :</label>
          <input class="form-control" type="text" id="coachId" required="true">
        </div>

        <div class="form-group">
          <label for="Sport">Sport : </label>
          <select class="form-control" id="sportSelect">
            
          </select>
        </div><label for=""></label>
        <div class="form-group">
          Male : &nbsp; &nbsp;<input class="form-control" type="radio" id="gender" name="gender" required="true" value="male">
          <label>Female :&nbsp; &nbsp;<input class="form-control" type="radio" id="gender" name="gender" required="true" value="female">
        </div>
        <label for=""></label>
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
  width: 80%;
  height: 400px;
}
#sportSelect{
    width : 100%;
}
label{
  display : block !important;
  width:100%;
}

.form-group{
  margin : 10px;
}
</style>`

function setupRegisterTeamTemplate(){
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
        $("#registerTeamForm").on('submit', function(e){

            e.preventDefault();
            var form = $(this);

            form.parsley().validate();

            if (form.parsley().isValid()){
                //show alert
                Swal.fire({
                    text: `Add team ${$("#name").val()}`,
                    type: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes'
                  }).then((result) => {
                    if (result.value) {
                        //send post request
                        axios.post(`/teams`,{
                            name : $("#name").val(),
                            gender : $("input[name='gender']:checked").val(),
                            captainRegNo : $("#captainRegNo").val().trim(),
                            coachId : $("#coachId").val().trim(),
                            teamId : -1,
                            sportId : $("#sportSelect").val()
                        })
                        .then((rs)=>{
                            Swal.fire({
                                position: 'center',
                                type: 'success',
                                title: 'Team Added',
                                showConfirmButton: false,
                                timer: 1500
                              })
                             $("#name").val("")
                             $("#captainRegNo").val("")
                             $("#coachId").val()
                            
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